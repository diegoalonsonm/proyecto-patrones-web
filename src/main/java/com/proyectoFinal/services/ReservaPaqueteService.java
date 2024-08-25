package com.proyectoFinal.services;

import com.proyectoFinal.domain.ReservaPaquete;

import java.util.List;

public interface ReservaPaqueteService {

    public List<ReservaPaquete> getReservasPaquete();

    List<ReservaPaquete> getReservasPaqueteByUserId(Long idUsuario);

    ReservaPaquete getReservaPaqueteByIdReservaPaquete(Long idReservaPaquete);

    public ReservaPaquete getReservaPaquete(ReservaPaquete reservaPaquete);

    public void save(ReservaPaquete reservaPaquete);

    public void delete(ReservaPaquete reservaPaquete);

}
