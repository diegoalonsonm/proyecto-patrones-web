package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.ReservaHotelDao;
import com.proyectoFinal.domain.ReservaHotel;
import com.proyectoFinal.services.ReservaHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaHotelServiceImpl implements ReservaHotelService {

    @Autowired
    private ReservaHotelDao reservaHotelDao;

    @Override
    public List<ReservaHotel> getReservasHotel() {
        return reservaHotelDao.findAll();
    }

    @Override
    public List<ReservaHotel> getReservasHotelByUserId(Long idUsuario) {
        return reservaHotelDao.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public ReservaHotel getReservaHotelByIdReservaHotel(Long idReservaHotel) {
        return reservaHotelDao.findById(idReservaHotel).orElse(null);
    }

    @Override
    public ReservaHotel getReservaHotel(ReservaHotel reservaHotel) {
        return reservaHotelDao.findById(reservaHotel.getIdReservaHotel()).orElse(null);
    }

    @Override
    public void delete(ReservaHotel reservaHotel) {
        reservaHotelDao.delete(reservaHotel);
    }

    @Override
    public void save(ReservaHotel reservaHotel) {
        reservaHotelDao.save(reservaHotel);
    }

}
