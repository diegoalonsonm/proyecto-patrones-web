package com.proyectoFinal.services;

import com.proyectoFinal.domain.Destino;
import java.util.List;

public interface DestinoService {

    public List<Destino> getDestinos();

    public Destino getDestino(Destino destino);

    public Destino getDestinoById(Long idDestino);

    public void save(Destino destino);

    public void delete(Destino destino);

    public List<Destino> getDestinosPorTiempo(double tiempoMaximo);

}
