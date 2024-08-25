package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.PaquetesDao;
import com.proyectoFinal.domain.Destino;
import com.proyectoFinal.domain.Paquete;
import com.proyectoFinal.services.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaqueteServiceImpl implements PaqueteService {

    @Autowired
    private PaquetesDao paqueteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Paquete> getPaquetes() {
        return paqueteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Paquete getPaquete(Paquete paquete) {
        return paqueteDao.findById(paquete.getIdPaquete()).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Paquete getPaqueteById(Long idPaquete) {
        return paqueteDao.findPaqueteByIdPaquete(idPaquete);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paquete> getPaquetesByDestino(Destino destino) {
        return paqueteDao.findPaqueteByDestino(destino);
    }

    @Override
    @Transactional
    public void save(Paquete paquete) {
        paqueteDao.save(paquete);
    }

    @Override
    @Transactional
    public void delete(Paquete paquete) {
        paqueteDao.delete(paquete);
    }

    @Override
    @Transactional
    public List<Paquete> getPaquetesPorPrecio(double precioMinimo, double precioMaximo) {
        return paqueteDao.findByPrecioBetween(precioMinimo, precioMaximo);
    }

}
