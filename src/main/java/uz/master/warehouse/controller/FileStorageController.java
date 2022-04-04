package uz.master.warehouse.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.master.warehouse.dto.file.UploadsDto;
import uz.master.warehouse.services.file.FileStorageService;

import java.nio.file.NoSuchFileException;

@RestController
@RequestMapping("uploads/*")
public class FileStorageController {

    final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<Resource> download(@PathVariable(name = "filename") String fileName) throws NoSuchFileException {
        UploadsDto loadedResource = fileStorageService.loadResource(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadedResource.getOriginalName() + "\"")
                .body(loadedResource.getResource());
    }

}
