package com.proyectoFinal.controller;

import com.proyectoFinal.domain.Usuario;
import com.proyectoFinal.services.FirebaseStorageService;
import com.proyectoFinal.services.RolService;
import com.proyectoFinal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios", usuarios.size());
        return "/usuario/listado";
    }

    @GetMapping("/agregar")
    public String usuarioAgregar(Usuario usuario) {
        return "/usuario/agregar";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario,@RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario,false);
            usuario.setRutaImagen(firebaseStorageService.cargaImagen(imagenFile, "usuario", usuario.getIdUsuario()));
        }
        usuarioService.save(usuario,true);
        return "redirect:/usuario/listado";
    }

    @PostMapping("/registro")
    public String usuarioRegistro(Usuario usuario, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario,false);
            usuario.setRutaImagen(firebaseStorageService.cargaImagen(imagenFile, "usuario", usuario.getIdUsuario()));
        }
        usuarioService.save(usuario,true);
        return "redirect:/login";
    }

    @PostMapping("/modificar")
    public String usuarioModificar(Usuario usuario, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            usuario.setRutaImagen(firebaseStorageService.cargaImagen(imagenFile, "usuario", usuario.getIdUsuario()));
        }
        usuarioService.save(usuario,true);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/registroNuevo")
    public String registroNuevo(Usuario usuario) {
        return "/usuario/registroNuevo";
    }

}
