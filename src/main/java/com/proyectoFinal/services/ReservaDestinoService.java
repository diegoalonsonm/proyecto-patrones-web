package com.proyectoFinal.services;

import com.proyectoFinal.domain.ReservaDestino;
import com.proyectoFinal.domain.Usuario;

import java.util.List;

public interface ReservaDestinoService {

    public List<ReservaDestino> getReservasDestino();

    List<ReservaDestino> getReservasDestinoByUserId(Long idUsuario);

    ReservaDestino getReservaDestinoByIdReservaDestino(Long idReservaDestino);

    public ReservaDestino getReservaDestino(ReservaDestino reservaDestino);

    public void save(ReservaDestino reservaDestino);

    public void delete(ReservaDestino reservaDestino);

}
