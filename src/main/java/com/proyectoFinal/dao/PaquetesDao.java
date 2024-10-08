package com.proyectoFinal.dao;

import com.proyectoFinal.domain.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoFinal.domain.Paquete;

import java.util.List;

public interface PaquetesDao extends JpaRepository<Paquete, Long> {

    public List<Paquete> findPaqueteByDestino(Destino destino);

    public Paquete findPaqueteByIdPaquete(Long id);

    public List<Paquete> findByPrecioBetween(double precioMinimo, double precioMaximo);

}
