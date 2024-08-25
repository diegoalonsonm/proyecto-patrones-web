package com.proyectoFinal.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "reserva_destino")
public class ReservaDestino {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva_destino")
    private Long idReservaDestino;
    private int cantidadPersonas;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaReservacion;

    private int estado;

    @ManyToOne
    @JoinColumn(name = "id_destino")
    private Destino destino;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
