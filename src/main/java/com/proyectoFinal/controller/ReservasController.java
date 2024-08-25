package com.proyectoFinal.controller;

import com.proyectoFinal.domain.*;
import com.proyectoFinal.services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/reserva")
public class ReservasController {

    @Autowired
    private ReservaDestinoService reservaDestinoService;

    @Autowired
    private ReservaPaqueteService reservaPaqueteService;

    @Autowired
    private DestinoService destinoService;

    @Autowired
    private PaqueteService paqueteService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/misReservas")
    public String getMisReservas(Model model) {
        Long userId = (Long) session.getAttribute("usuarioId");

        var listaReservasDestino = reservaDestinoService.getReservasDestinoByUserId(userId);
        var listaReservasPaquete = reservaPaqueteService.getReservasPaqueteByUserId(userId);

        model.addAttribute("reservasDestino", listaReservasDestino);
        model.addAttribute("reservasPaquete", listaReservasPaquete);
        model.addAttribute("totalReservasDestino", listaReservasDestino.size());
        model.addAttribute("totalReservasPaquete", listaReservasPaquete.size());

        return "/reserva/misReservas";
    }

    @GetMapping("/reservaDestino/{idDestino}")
    public String getReservaDestino(Model model, @PathVariable Long idDestino) {
        Destino destino = destinoService.getDestinoById(idDestino);
        Long userId = (Long) session.getAttribute("usuarioId");

        model.addAttribute("userId", userId);
        model.addAttribute("destino", destino);
        model.addAttribute("reservaDestino", new ReservaDestino());

        return "/reserva/reservaDestino";
    }

    @PostMapping("/reservarDestino")
    public String postReservaDestino(ReservaDestino reserva, @RequestParam Long idDestino, @RequestParam Long idUsuario) {
        Destino destino = destinoService.getDestinoById(idDestino);
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);

        reserva.setDestino(destino);
        reserva.setUsuario(usuario);

        reservaDestinoService.save(reserva);

        return "redirect:/reserva/misReservas";
    }

    @GetMapping("/cancelarReservaDestino/{idReservaDestino}")
    public String cancelarReservaDestino(@PathVariable Long idReservaDestino) {
        ReservaDestino reservaDestino = reservaDestinoService.getReservaDestinoByIdReservaDestino(idReservaDestino);
        reservaDestinoService.delete(reservaDestino);

        return "redirect:/reserva/misReservas";
    }

    @GetMapping("/reservaPaquete/{idPaquete}")
    public String getReservaPaquete(Model model, @PathVariable Long idPaquete) {
        Paquete paquete = paqueteService.getPaqueteById(idPaquete);
        Long userId = (Long) session.getAttribute("usuarioId");

        model.addAttribute("userId", userId);
        model.addAttribute("paquete", paquete);
        model.addAttribute("reservaPaquete", new ReservaPaquete());

        return "/reserva/reservaPaquete";
    }

    @PostMapping("/reservarPaquete")
    public String postReservaPaquete(ReservaPaquete reserva, @RequestParam Long idPaquete, @RequestParam Long idUsuario) {
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        Paquete paquete = paqueteService.getPaqueteById(idPaquete);

        reserva.setUsuario(usuario);
        reserva.setPaquete(paquete);

        reservaPaqueteService.save(reserva);

        return "redirect:/reserva/misReservas";
    }

    @GetMapping("/cancelarReservaPaquete/{idReservaPaquete}")
    public String cancelarReservaPaquete(@PathVariable Long idReservaPaquete) {
        ReservaPaquete reservaPaquete = reservaPaqueteService.getReservaPaqueteByIdReservaPaquete(idReservaPaquete);
        reservaPaqueteService.delete(reservaPaquete);

        return "redirect:/reserva/misReservas";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}
