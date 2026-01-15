package com.daw.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Curriculum;
import com.daw.datamodel.repository.CiudadanoRepository;
import com.daw.datamodel.repository.CurriculumRepository;

import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import java.nio.file.Path;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CurriculumService {

    private static final String BASE_PATH = "uploads/curriculums/";

    @Autowired
    private CurriculumRepository curriculumRepository;

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    public Curriculum subirCurriculum(MultipartFile archivo) throws IOException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Ciudadano ciudadano = ciudadanoRepository
                .findByUsuarioNombreUsuario(username)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        String carpetaCiudadano = BASE_PATH + "ciudadano_" + ciudadano.getIdCiudadano();
        Files.createDirectories(Paths.get(carpetaCiudadano));

        String nombreArchivo = System.currentTimeMillis() + "_" + archivo.getOriginalFilename();
        Path rutaCompleta = Paths.get(carpetaCiudadano, nombreArchivo);

        Files.copy(archivo.getInputStream(), rutaCompleta);

        Curriculum curriculum = new Curriculum();
        curriculum.setNombreArchivo(nombreArchivo);
        curriculum.setRutaArchivo(rutaCompleta.toString());
        curriculum.setFechaSubida(LocalDate.now());
        curriculum.setCiudadano(ciudadano);

        return curriculumRepository.save(curriculum);
    }

    public List<Curriculum> listarCurriculumsCiudadano() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Ciudadano ciudadano = ciudadanoRepository
                .findByUsuarioNombreUsuario(username)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

        return curriculumRepository.findByCiudadano(ciudadano);
    }

    public Resource descargarCurriculum(Long idCurriculum) throws MalformedURLException {

        Curriculum curriculum = curriculumRepository.findById(idCurriculum)
                .orElseThrow(() -> new RuntimeException("Curriculum no encontrado"));

        Path path = Paths.get(curriculum.getRutaArchivo());
        return new UrlResource(path.toUri());
    }
}

