package com.proyectoFinal.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "paquete")
public class Paquete {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paquete")
    private Long idPaquete;
    private String nombre;
    private String descripcion;
    private float precio;
    private boolean incluyeAlojamiento;
    private boolean incluyeTransporte;
    private boolean incluyeActividades;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    private Destino destino;

}
