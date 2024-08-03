package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.ReservaPaqueteDao;
import com.proyectoFinal.domain.ReservaPaquete;
import com.proyectoFinal.services.ReservaPaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservaPaqueteServiceImpl implements ReservaPaqueteService {

    @Autowired
    private ReservaPaqueteDao reservaPaqueteDao;

    @Override
    @Transactional(readOnly = true)
    public List<ReservaPaquete> getReservasPaquete() {
        return reservaPaqueteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaPaquete getReservaPaquete(ReservaPaquete reservaPaquete) {
        return reservaPaqueteDao.findById(reservaPaquete.getIdReservaPaquete()).orElse(null);
    }

    @Override
    @Transactional
    public void save(ReservaPaquete reservaPaquete) {
        reservaPaqueteDao.save(reservaPaquete);
    }

    @Override
    @Transactional
    public void delete(ReservaPaquete reservaPaquete) {
        reservaPaqueteDao.delete(reservaPaquete);
    }

}
