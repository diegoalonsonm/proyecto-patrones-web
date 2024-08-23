package com.proyectoFinal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectoFinal.domain.Destino;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DestinoDao extends JpaRepository<Destino, Long> {

    @Query("SELECT d FROM Destino d WHERE d.tiempoViaje < :tiempoMaximo")
    List<Destino> findDestinosByTiempoViajeMenorA(@Param("tiempoMaximo") String tiempoMaximo);

}