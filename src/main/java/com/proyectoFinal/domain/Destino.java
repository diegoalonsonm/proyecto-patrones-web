package com.proyectoFinal.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "destino")
public class Destino {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destino")
    private Long idDestino;
    private String pais;
    private String ciudad;
    private double tiempoViaje;
    private double precioPersona;
    private String urlImagen;

}
