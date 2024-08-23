package com.proyectoFinal.controller;

import com.proyectoFinal.domain.Paquete;
import com.proyectoFinal.services.DestinoService;
import com.proyectoFinal.services.PaqueteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/paquete")
public class PaquetesController {

    @Autowired
    private PaqueteService paquetesService;

    @Autowired
    private DestinoService destinoService;

    // ver listado
    @GetMapping("/listado")
    public String getPaquetes(Model model) {
        var listaPaquetes = paquetesService.getPaquetes();
        model.addAttribute("paquetes", listaPaquetes);
        model.addAttribute("totalPaquetes", listaPaquetes.size());

        return "/paquete/listado";
    }

    // ver listado publico
    @GetMapping("/listadoPublico")
    public String getPaquetesPublico(Model model) {
        var listaPaquetes = paquetesService.getPaquetes();
        model.addAttribute("paquetes", listaPaquetes);
        model.addAttribute("totalPaquetes", listaPaquetes.size());

        return "/paquete/listadoPublico";
    }

    // ver pagina agregar
    @GetMapping("/agregar")
    public String agregar(Model model) {
        var listaDestinos = destinoService.getDestinos();
        model.addAttribute("destinos", listaDestinos);

        return "/paquete/agregar";
    }

    // agregar paquetes a la bd
    @PostMapping("/agregarPaquete")
    public String nuevoPaquete(Paquete paquete) {
        paquetesService.save(paquete);
        return "redirect:/paquete/listado";
    }

    // eliminar paquetes de la bd
    @GetMapping("/eliminar/{idPaquete}")
    public String eliminarPaquete(Paquete paquete) {
        paquetesService.delete(paquete);
        return "redirect:/paquete/listado";
    }

    // editar paquetes en la bd
    @GetMapping("/editar/{idPaquete}")
    public String editarPaquete(Paquete paquete, Model model) {
        var paqueteEdit = paquetesService.getPaquete(paquete);
        model.addAttribute("paquete", paqueteEdit);

        return "/paquete/editar";
    }

    // buscar paquetes por precio
    @PostMapping("/buscarPorPrecio")
    public String consultaPorPrecio(@RequestParam(value = "precioMinimo") double precioMinimo, @RequestParam(value = "precioMaximo") double precioMaximo, Model model) {

        var listaPaquetes = paquetesService.getPaquetesPorPrecio(precioMinimo, precioMaximo);
        model.addAttribute("precioMinimo", precioMinimo);
        model.addAttribute("precioMaximo", precioMaximo);
        model.addAttribute("paquetes", listaPaquetes);
        model.addAttribute("totalPaquetes", listaPaquetes.size());

        System.out.println(listaPaquetes);

        return "/paquete/listadoPublico";
    }

}