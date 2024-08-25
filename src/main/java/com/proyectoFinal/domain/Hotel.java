package com.proyectoFinal.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "hotel")
public class Hotel {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private Long idHotel;
    private String nombre;
    private int estrellas;
    private double precioNoche;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    private Destino destino;

}
