package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.DestinoDao;
import com.proyectoFinal.domain.Destino;
import com.proyectoFinal.services.DestinoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DestinoServiceImpl implements DestinoService {

    @Autowired
    private DestinoDao destinoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Destino> getDestinos() {
        return destinoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Destino getDestino(Destino destino) {
        return destinoDao.findById(destino.getIdDestino()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Destino destino) {
        destinoDao.save(destino);
    }

    @Override
    @Transactional
    public void delete(Destino destino) {
        destinoDao.delete(destino);
    }

}
