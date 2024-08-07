package com.proyectoFinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/otros")
public class OtrosController {

    @GetMapping("/nuestrasSedes")
    public String getSedes() {
        return "/otros/nuestrasSedes";
    }

    @GetMapping("/contacto")
    public String getContacto() {
        return "/otros/contacto";
    }

    @GetMapping("/preguntasFrecuentes")
    public String getPreguntasFrecuentes() {
        return "/otros/preguntasFrecuentes";
    }

}
