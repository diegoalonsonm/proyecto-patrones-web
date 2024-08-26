package com.proyectoFinal.dao;

import com.proyectoFinal.domain.ReservaHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaHotelDao extends JpaRepository<ReservaHotel, Long> {

    List<ReservaHotel> findByUsuario_IdUsuario(Long idUsuario);

    ReservaHotel findByIdReservaHotel(Long idReservaHotel);

}
