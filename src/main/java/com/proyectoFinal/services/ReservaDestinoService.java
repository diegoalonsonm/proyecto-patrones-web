package com.proyectoFinal.services;

import com.proyectoFinal.domain.ReservaDestino;

import java.util.List;

public interface ReservaDestinoService {

    public List<ReservaDestino> getReservasDestino();

    public ReservaDestino getReservaDestino(ReservaDestino reservaDestino);

    public void save(ReservaDestino reservaDestino);

    public void delete(ReservaDestino reservaDestino);

}
