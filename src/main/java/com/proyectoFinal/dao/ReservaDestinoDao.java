package com.proyectoFinal.dao;

import com.proyectoFinal.domain.ReservaDestino;
import com.proyectoFinal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaDestinoDao extends JpaRepository<ReservaDestino, Long> {

    List<ReservaDestino> findByUsuario_IdUsuario(Long idUsuario);

    ReservaDestino findByIdReservaDestino(Long idReservaDestino);

}
