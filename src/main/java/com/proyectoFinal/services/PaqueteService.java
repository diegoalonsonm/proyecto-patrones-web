package com.proyectoFinal.services;

import com.proyectoFinal.domain.Destino;
import com.proyectoFinal.domain.Paquete;

import java.util.List;

public interface PaqueteService {

    public List<Paquete> getPaquetes();

    public Paquete getPaquete(Paquete paquete);

    public List<Paquete> getPaquetesByDestino(Destino destino);

    public void save(Paquete paquete);

    public void delete(Paquete paquete);

}
