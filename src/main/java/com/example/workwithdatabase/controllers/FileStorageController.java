package com.example.workwithdatabase.controllers;


import com.example.workwithdatabase.models.FileStorage;
import com.example.workwithdatabase.services.baseServises.RootFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/file")
public class FileStorageController {

    @Autowired
    private final RootFileStorageService rootFileStorageService;

    @Value("${upload.folder}")
    private String uploadFolder;

    public FileStorageController(RootFileStorageService rootFileStorageService) {
        this.rootFileStorageService = rootFileStorageService;
    }



    // Berilgan faylini olib xotiraga shaqlash qismi
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("files") MultipartFile multipartFile) {

        rootFileStorageService.saveFiles(multipartFile);

        return ResponseEntity.ok("Fayl bazaga muvaffaqiyatli saqlandi!");

    }


    // Read file from base or Memory
    @RequestMapping(value = "/readFiles/{hashId}", method = RequestMethod.GET)
    public ResponseEntity<?> viewFiles(@PathVariable String hashId) throws IOException {

        FileStorage fileStorage = rootFileStorageService.getByHashId(hashId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; fileName=\"" + URLEncoder.encode(fileStorage.getFile_name()))
                .contentType(MediaType.parseMediaType(fileStorage.getContent_type()))
                .contentLength(fileStorage.getFile_size())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath())));

    }


    // Downloads files
    @RequestMapping(value = "/downloads/{hashId}", method = RequestMethod.GET)
    public ResponseEntity<?> downloadsFiles(@PathVariable String hashId) throws IOException {

        FileStorage fileStorage = rootFileStorageService.getByHashId(hashId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + URLEncoder.encode(fileStorage.getFile_name()))
                .contentType(MediaType.parseMediaType(fileStorage.getContent_type()))
                .contentLength(fileStorage.getFile_size())
                .body(new FileUrlResource(String.format("%s/%s", uploadFolder, fileStorage.getUploadPath())));

    }


    // Delete files

    @RequestMapping(value = "/deleteFile/{hashid}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFromBase(@PathVariable String hashid) {

        rootFileStorageService.deleteFiles(hashid);

        return ResponseEntity.ok("Fayl o'chirildi!");

    }




}
