package com.proyectoFinal.services;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoImagenDestino, String carpeta, Long id);

    final String bucketName = "proyecto-patrones-b109c.appspot.com";
    final String rutaStorage = "tiquiviajes";
    final String rutaJson = "firebase";
    final String archivoJson = "proyecto-patrones-b109c-firebase-adminsdk-269oz-da611d8a3c.json";

}
