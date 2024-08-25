package com.proyectoFinal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoFinal.domain.Destino;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinoDao extends JpaRepository<Destino, Long> {

    public List <Destino> findByTiempoViajeIsGreaterThanEqual(double tiempoMaximo);

    public Destino findByIdDestino(Long idDestino);

}