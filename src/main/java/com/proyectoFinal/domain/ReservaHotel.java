package com.proyectoFinal.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "reserva_hotel")
public class ReservaHotel {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva_hotel")
    private Long idReservaHotel;
    private int cantidadPersonas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaReservacion;

    private int estado;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
