package com.proyectoFinal.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private String contrasena;
    private String telefono;
    private String direccion;

    @OneToMany
    @JoinColumn(name = "id_usuario")
    private List<Rol> roles;

}
