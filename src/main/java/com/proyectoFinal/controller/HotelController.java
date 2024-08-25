package com.proyectoFinal.controller;

import com.proyectoFinal.domain.Hotel;
import com.proyectoFinal.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/listado")
    public String getHoteles(Model model) {
        var listaHoteles = hotelService.getHoteles();
        model.addAttribute("hoteles", listaHoteles);
        model.addAttribute("totalHoteles", listaHoteles.size());

        return "/hotel/listado";
    }

    @GetMapping("/listadoPublico")
    public String getHotelesPublico(Model model) {
        var listaHoteles = hotelService.getHoteles();
        model.addAttribute("hoteles", listaHoteles);
        model.addAttribute("totalHoteles", listaHoteles.size());

        return "/hotel/listadoPublico";
    }

    @GetMapping("/agregar")
    public String agregar() {
        return "/hotel/agregar";
    }

    @PostMapping("/agregarHotel")
    public String nuevoHotel(Hotel hotel){
        hotelService.save(hotel);

        return "redirect:/hotel/listado";
    }

    @GetMapping("/editar/{idHotel}")
    public String editarHotel(Model model, Hotel hotel) {
        var hotelEdit = hotelService.getHotel(hotel);
        model.addAttribute("hotel", hotelEdit);

        return "/hotel/editar";
    }

    @GetMapping("/eliminar/{idHotel}")
    public String eliminarHotel(Hotel hotel) {
        hotelService.delete(hotel);

        return "redirect:/hotel/listado";
    }

    @PostMapping("/buscarPorEstrellas")
    public String buscarPorEstrellas(Model model, @RequestParam("estrellas") int estrellas) {
        var listaHoteles = hotelService.getHotelesPorEstrellas(estrellas);
        model.addAttribute("hoteles", listaHoteles);
        model.addAttribute("totalHoteles", listaHoteles.size());

        return "/hotel/listadoPublico";
    }

}
