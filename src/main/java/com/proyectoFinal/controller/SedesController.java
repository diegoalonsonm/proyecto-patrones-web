package com.proyectoFinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sedes")
public class SedesController {

    @GetMapping("/nuestrasSedes")
    public String getSedes() {
        return "/sedes/nuestrasSedes";
    }

}
