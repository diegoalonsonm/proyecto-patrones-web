package com.proyectoFinal.dao;

import com.proyectoFinal.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelDao extends JpaRepository<Hotel, Long> {

    public List<Hotel> findByEstrellasIsGreaterThanEqual(int estrellas);

}
