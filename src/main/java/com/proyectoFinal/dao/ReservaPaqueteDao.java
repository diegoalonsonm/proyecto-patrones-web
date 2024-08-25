package com.proyectoFinal.dao;

import com.proyectoFinal.domain.ReservaPaquete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaPaqueteDao extends JpaRepository<ReservaPaquete, Long> {

    List<ReservaPaquete> findByUsuario_IdUsuario(Long idUsuario);

    ReservaPaquete findByIdReservaPaquete(Long idReservaPaquete);

}
