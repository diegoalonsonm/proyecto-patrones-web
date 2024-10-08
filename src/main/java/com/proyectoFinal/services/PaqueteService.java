package com.proyectoFinal.services;

import com.proyectoFinal.domain.Destino;
import com.proyectoFinal.domain.Paquete;

import java.util.List;

public interface PaqueteService {

    public List<Paquete> getPaquetes();

    public Paquete getPaquete(Paquete paquete);

    public List<Paquete> getPaquetesByDestino(Destino destino);

    public Paquete getPaqueteById(Long idPaquete);

    public void save(Paquete paquete);

    public void delete(Paquete paquete);

    public List<Paquete> getPaquetesPorPrecio(double precioMinimo, double precioMaximo);

}
