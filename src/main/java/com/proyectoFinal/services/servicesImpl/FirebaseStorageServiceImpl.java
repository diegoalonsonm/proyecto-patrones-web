package com.proyectoFinal.services.servicesImpl;

import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.proyectoFinal.services.FirebaseStorageService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    @Override
    public String cargaImagen(MultipartFile archivoImagenDestino, String carpeta, Long id) {
        try {
            String extension = archivoImagenDestino.getOriginalFilename();
            String fileName = "img" + sacaNumero(id) + extension;
            File file = this.convertToFile(archivoImagenDestino);
            String url = this.uploadFile(file, fileName, carpeta);
            file.delete();

            return url;
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private String uploadFile(File file, String fileName, String carpeta) throws IOException {
        ClassPathResource json = new ClassPathResource(rutaJson + File.separator + archivoJson);
        BlobId blobId = BlobId.of(bucketName, rutaStorage + File.separator + carpeta + File.separator + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();

        Credentials credentials = GoogleCredentials.fromStream(json.getInputStream());
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        String url = storage.signUrl(blobInfo, 3650, TimeUnit.DAYS, Storage.SignUrlOption.signWith((ServiceAccountSigner) credentials)).toString();

        return url;
    }

    private File convertToFile(MultipartFile archivo) throws IOException {
        File tempFile = File.createTempFile("img", null);

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(archivo.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String sacaNumero(Long id) {
        return String.format("%019d", id);
    }

}
