package com.proyectoFinal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoFinal.domain.Paquete;

import java.util.List;

public interface PaquetesDao extends JpaRepository<Paquete, Long> {

    List<Paquete> findByDestino(String destino);

}
