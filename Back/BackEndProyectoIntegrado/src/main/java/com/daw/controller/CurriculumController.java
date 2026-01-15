package com.daw.controller;

import java.net.MalformedURLException;
import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daw.datamodel.entities.Curriculum;
import com.daw.service.CurriculumService;

import org.springframework.core.io.Resource;

@RestController
@RequestMapping("/api/curriculums")
public class CurriculumController {

    @Autowired
    private CurriculumService curriculumService;

    @PostMapping("/subir")
    public ResponseEntity<?> subirCurriculum(@RequestParam MultipartFile archivo) {
        try {
            Curriculum curriculum = curriculumService.subirCurriculum(archivo);
            return ResponseEntity.ok(curriculum);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/mis-curriculums")
    public ResponseEntity<List<Curriculum>> listar() {
        return ResponseEntity.ok(curriculumService.listarCurriculumsCiudadano());
    }

    @GetMapping("/descargar/{id}")
    public ResponseEntity<Resource> descargar(@PathVariable Long id) throws MalformedURLException {

        Resource resource = curriculumService.descargarCurriculum(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}



