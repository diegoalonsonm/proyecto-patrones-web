package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.RolDao;
import com.proyectoFinal.domain.Rol;
import com.proyectoFinal.services.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolDao rolDao;

    @Override
    public Rol deleteRolByIdUsuario(Long idUsuario) {
        return rolDao.deleteRolByIdUsuario(idUsuario);
    }
}
