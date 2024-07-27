package com.proyectoFinal.services;

import com.proyectoFinal.domain.ReservaPaquete;

import java.util.List;

public interface ReservaPaqueteService {

    public List<ReservaPaquete> getReservasPaquete();

    public ReservaPaquete getReservaPaquete(ReservaPaquete reservaPaquete);

    public void save(ReservaPaquete reservaPaquete);

    public void delete(ReservaPaquete reservaPaquete);

}
