package com.proyectoFinal.dao;

import com.proyectoFinal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao  extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    public Usuario findByUsernameAndPassword(String username, String password);

    public Usuario findByIdUsuario(Long id);

    public Usuario findByUsernameOrCorreoElectronico(String username, String correo);

    public boolean existsByUsernameOrCorreoElectronico(String username, String correo);

}
