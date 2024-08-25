package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.ReservaDestinoDao;
import com.proyectoFinal.domain.ReservaDestino;
import com.proyectoFinal.domain.Usuario;
import com.proyectoFinal.services.ReservaDestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaDestinoServiceImpl implements ReservaDestinoService {

    @Autowired
    private ReservaDestinoDao reservaDestinoDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDestino> getReservasDestino() {
        return reservaDestinoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDestino> getReservasDestinoByUserId(Long idUsuario) {
        return reservaDestinoDao.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaDestino getReservaDestinoByIdReservaDestino(Long idReservaDestino) {
        return reservaDestinoDao.findByIdReservaDestino(idReservaDestino);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaDestino getReservaDestino(ReservaDestino reservaDestino) {
        return reservaDestinoDao.findById(reservaDestino.getIdReservaDestino()).orElse(null);
    }

    @Override
    @Transactional
    public void save(ReservaDestino reservaDestino) {
        reservaDestinoDao.save(reservaDestino);
    }

    @Override
    @Transactional
    public void delete(ReservaDestino reservaDestino) {
        reservaDestinoDao.delete(reservaDestino);
    }

}
