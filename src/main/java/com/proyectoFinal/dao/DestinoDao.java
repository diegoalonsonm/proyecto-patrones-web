package com.proyectoFinal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoFinal.domain.Destino;

public interface DestinoDao extends JpaRepository<Destino, Long> {
}