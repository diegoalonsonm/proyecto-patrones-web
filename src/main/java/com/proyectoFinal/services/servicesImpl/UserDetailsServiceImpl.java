package com.proyectoFinal.services.servicesImpl;

import com.proyectoFinal.dao.UsuarioDao;
import com.proyectoFinal.domain.Rol;
import com.proyectoFinal.domain.Usuario;
import com.proyectoFinal.services.UsuarioDetailsService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        session.removeAttribute("usuarioId");
        session.setAttribute("usuarioId", usuario.getIdUsuario());

        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());

        var roles = new ArrayList<GrantedAuthority>();

        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
