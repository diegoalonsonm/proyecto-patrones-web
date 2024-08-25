package com.proyectoFinal.services;

import com.proyectoFinal.domain.Hotel;

import java.util.List;

public interface HotelService {

    public List<Hotel> getHoteles();

    public Hotel getHotel(Hotel hotel);

    public void save(Hotel hotel);

    public void delete(Hotel hotel);

    public List<Hotel> getHotelesPorEstrellas(int estrellas);

}
