package com.proyectoFinal.controller;

import com.proyectoFinal.domain.Destino;
import com.proyectoFinal.services.DestinoService;
import com.proyectoFinal.services.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/destino")
public class DestinoController {

    @Autowired
    private DestinoService destinoService;

    // ver listado version admin
    @GetMapping("/listado")
    public String getDestinos(Model model) {
        var listaDestinos = destinoService.getDestinos();
        model.addAttribute("destinos", listaDestinos);
        model.addAttribute("totalDestinos", listaDestinos.size());

        return "/destino/listado";
    }

    @GetMapping("/listadoPublico")
    public String getDestinosPublico(Model model) {
        var listaDestinos = destinoService.getDestinos();
        model.addAttribute("destinos", listaDestinos);
        model.addAttribute("totalDestinos", listaDestinos.size());

        return "/destino/listadoPublico";
    }

    // ver pagina agregar
    @GetMapping("/agregar")
    public String agregar() {
        return "/destino/agregar";
    }

    // agregar destinos a la bd
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/agregarDestino")
    public String nuevoDestino(Destino destino){ //@RequestParam("urlImagen") MultipartFile urlImagen
        //if (!urlImagen.isEmpty()) {
        //    String rutaImagen = firebaseStorageService.cargaImagen(urlImagen, "destinos", destino.getIdDestino());
        //    destino.setUrlImagen(rutaImagen);
        //    destinoService.save(destino);
        //}

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
