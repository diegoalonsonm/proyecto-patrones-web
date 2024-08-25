package com.proyectoFinal.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "reserva_paquete")
public class ReservaPaquete {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva_paquete")
    private Long idReservaPaquete;
    private int cantidadPersonas;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaReservacion;
    private int estado;

    @ManyToOne
    @JoinColumn(name = "id_paquete")
    private Paquete paquete;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
