package com.proyectoFinal.controller;

import com.proyectoFinal.domain.Destino;
import com.proyectoFinal.services.DestinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/destino")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    // ver listado
    @GetMapping("/listado")
    public String getDestinos(Model model) {
        var listaDestinos = destinoService.getDestinos();
        model.addAttribute("destinos", listaDestinos);
        model.addAttribute("totalDestinos", listaDestinos.size());

        return "/destino/listado";
    }

    // ver pagina agregar
    @GetMapping("/agregar")
    public String agregar() {
        return "/destino/agregar";
    }

    // agregar destinos a la bd
    @PostMapping("/agregarDestino")
    public String nuevoDestino(Destino destino) {
        destinoService.save(destino);
        return "redirect:/destino/listado";
    }

    // eliminar destinos de la bd
    @GetMapping("/eliminar/{idDestino}")
    public String eliminarDestino(Destino destino) {
        destinoService.delete(destino);
        return "redirect:/destino/listado";
    }

    // editar destinos en la bd
    @GetMapping("/editar/{idDestino}")
    public String editarDestino(Destino destino, Model model) {
        var destinoEdit = destinoService.getDestino(destino);
        model.addAttribute("destino", destinoEdit);

        return "/destino/editar";
    }
}
