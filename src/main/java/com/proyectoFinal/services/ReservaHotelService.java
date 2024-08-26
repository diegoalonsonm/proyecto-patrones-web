package com.proyectoFinal.services;

import com.proyectoFinal.domain.ReservaHotel;

import java.util.List;

public interface ReservaHotelService {

    public List<ReservaHotel> getReservasHotel();

    List<ReservaHotel> getReservasHotelByUserId(Long idUsuario);

    ReservaHotel getReservaHotelByIdReservaHotel(Long idReservaHotel);

    public ReservaHotel getReservaHotel(ReservaHotel reservaHotel);

    public void save(ReservaHotel reservaHotel);

    public void delete(ReservaHotel reservaHotel);

}
