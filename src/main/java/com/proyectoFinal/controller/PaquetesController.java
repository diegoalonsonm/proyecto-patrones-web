package com.proyectoFinal.controller;

import com.proyectoFinal.domain.Paquete;
import com.proyectoFinal.services.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paquetes")
public class PaquetesController {

    @Autowired
    private PaqueteService paquetesService;

    // ver listado
    @GetMapping("/listado")
    public String getPaquetes(Model model) {
        var listaPaquetes = paquetesService.getPaquetes();
        model.addAttribute("paquetes", listaPaquetes);
        model.addAttribute("totalPaquetes", listaPaquetes.size());

        return "/paquetes/listado";
    }

    // ver pagina agregar
    @GetMapping("/agregar")
    public String agregar() {
        return "/paquetes/agregar";
    }

    // agregar paquetes a la bd
    @PostMapping("/agregarPaquete")
    public String nuevoPaquete(Paquete paquete) {
        paquetesService.save(paquete);
        return "redirect:/paquetes/listado";
    }

    // eliminar paquetes de la bd
    @GetMapping("/eliminar/{idPaquete}")
    public String eliminarPaquete(Paquete paquete) {
        paquetesService.delete(paquete);
        return "redirect:/paquetes/listado";
    }

    // editar paquetes en la bd
    @GetMapping("/editar/{idPaquete}")
    public String editarPaquete(Paquete paquete, Model model) {
        var paqueteEdit = paquetesService.getPaquete(paquete);
        model.addAttribute("paquete", paqueteEdit);

        return "/paquetes/editar";
    }

}