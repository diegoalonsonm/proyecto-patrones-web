package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.HotelDao;
import com.proyectoFinal.domain.Hotel;
import com.proyectoFinal.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao hotelDao;

    @Override
    public List<Hotel> getHoteles() {
        return hotelDao.findAll();
    }

    @Override
    public Hotel getHotel(Hotel hotel) {
        return hotelDao.findById(hotel.getIdHotel()).orElse(null);
    }

    @Override
    public void save(Hotel hotel) {
        hotelDao.save(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        hotelDao.delete(hotel);
    }

    @Override
    public List<Hotel> getHotelesPorEstrellas(int estrellas) {
        return hotelDao.findByEstrellasIsGreaterThanEqual(estrellas);
    }

}
