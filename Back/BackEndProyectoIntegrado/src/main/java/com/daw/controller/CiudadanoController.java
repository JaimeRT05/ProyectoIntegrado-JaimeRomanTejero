package com.daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daw.datamodel.dto.ActualizarCiudadanoDTO;
import com.daw.datamodel.dto.CiudadanoDTO;
import com.daw.datamodel.entities.Ciudadano;
import com.daw.service.CiudadanoService;

@RestController
@RequestMapping("/api/ciudadanos")
public class CiudadanoController {

    @Autowired
    private CiudadanoService servicio;

    @PostMapping("/crearCiudadano")
    public ResponseEntity<CiudadanoDTO> crearCiudadano(
            @ModelAttribute CiudadanoDTO dto,
            @RequestParam(value = "curriculum", required = false) MultipartFile curriculum) {

        try {
            // Si se subió un archivo
            if (curriculum != null && !curriculum.isEmpty()) {
                dto.setCurriculumURL(curriculum.getOriginalFilename());
            }

            // Crear ciudadano junto con usuario
            Ciudadano ciudadano = servicio.crearCiudadano(dto);

            // Mapear a DTO de respuesta
            CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();
            ciudadanoDTO.setIdCiudadano(ciudadano.getIdCiudadano());
            ciudadanoDTO.setNombre(ciudadano.getNombre());
            ciudadanoDTO.setApellidos(ciudadano.getApellidos());
            ciudadanoDTO.setFechaNacimiento(ciudadano.getFechaNacimiento());
            ciudadanoDTO.setCalle(ciudadano.getCalle());
            ciudadanoDTO.setNumero(ciudadano.getNumero());
            ciudadanoDTO.setLocalidad(ciudadano.getLocalidad());
            ciudadanoDTO.setCodigoPostal(ciudadano.getCodigoPostal());
            ciudadanoDTO.setTelefono(ciudadano.getTelefono());
            ciudadanoDTO.setEmail(ciudadano.getEmail());
            ciudadanoDTO.setProfesion(ciudadano.getProfesion());
            ciudadanoDTO.setNombreUsuario(ciudadano.getUsuario().getNombreUsuario());

            return ResponseEntity.ok(ciudadanoDTO);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/buscarCiudadanoPorId/{id}")
    public ResponseEntity<CiudadanoDTO> buscarCiudadano(@PathVariable Long id) {
        try {
            Ciudadano ciudadano = servicio.buscarPorId(id);

            CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();
            ciudadanoDTO.setIdCiudadano(ciudadano.getIdCiudadano());
            ciudadanoDTO.setNombre(ciudadano.getNombre());
            ciudadanoDTO.setApellidos(ciudadano.getApellidos());
            ciudadanoDTO.setFechaNacimiento(ciudadano.getFechaNacimiento());
            ciudadanoDTO.setCalle(ciudadano.getCalle());
            ciudadanoDTO.setNumero(ciudadano.getNumero());
            ciudadanoDTO.setLocalidad(ciudadano.getLocalidad());
            ciudadanoDTO.setCodigoPostal(ciudadano.getCodigoPostal());
            ciudadanoDTO.setTelefono(ciudadano.getTelefono());
            ciudadanoDTO.setEmail(ciudadano.getEmail());
            ciudadanoDTO.setProfesion(ciudadano.getProfesion());
            ciudadanoDTO.setNombreUsuario(ciudadano.getUsuario().getNombreUsuario());

            return ResponseEntity.ok(ciudadanoDTO);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PutMapping("/actualizarCiudadano/{idCiudadano}")
    public ResponseEntity<CiudadanoDTO> actualizarCiudadano(
            @PathVariable Long idCiudadano,
            @RequestBody ActualizarCiudadanoDTO dto) {

        Ciudadano actualizado = servicio.actualizarCiudadano(idCiudadano, dto);

        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();
        ciudadanoDTO.setIdCiudadano(actualizado.getIdCiudadano());
        ciudadanoDTO.setNombre(actualizado.getNombre());
        ciudadanoDTO.setApellidos(actualizado.getApellidos());
        ciudadanoDTO.setFechaNacimiento(actualizado.getFechaNacimiento());
        ciudadanoDTO.setCalle(actualizado.getCalle());
        ciudadanoDTO.setNumero(actualizado.getNumero());
        ciudadanoDTO.setLocalidad(actualizado.getLocalidad());
        ciudadanoDTO.setCodigoPostal(actualizado.getCodigoPostal());
        ciudadanoDTO.setTelefono(actualizado.getTelefono());
        ciudadanoDTO.setEmail(actualizado.getEmail());
        ciudadanoDTO.setProfesion(actualizado.getProfesion());
        ciudadanoDTO.setNombreUsuario(actualizado.getUsuario().getNombreUsuario());

        return ResponseEntity.ok(ciudadanoDTO);
    }
    
    
    @GetMapping("/buscarCiudadanoPorNombreUsuario/{nombreUsuario}")
    public ResponseEntity<CiudadanoDTO> buscarPorNombre(@PathVariable String nombreUsuario) {
        try {
            Ciudadano ciudadano = servicio.buscarPorNombreUsuario(nombreUsuario);

            // Mapear a DTO
            CiudadanoDTO dto = new CiudadanoDTO();
            dto.setIdCiudadano(ciudadano.getIdCiudadano());
            dto.setNombre(ciudadano.getNombre());
            dto.setApellidos(ciudadano.getApellidos());
            dto.setFechaNacimiento(ciudadano.getFechaNacimiento());
            dto.setCalle(ciudadano.getCalle());
            dto.setNumero(ciudadano.getNumero());
            dto.setLocalidad(ciudadano.getLocalidad());
            dto.setCodigoPostal(ciudadano.getCodigoPostal());
            dto.setTelefono(ciudadano.getTelefono());
            dto.setEmail(ciudadano.getEmail());
            dto.setProfesion(ciudadano.getProfesion());
            dto.setNombreUsuario(ciudadano.getUsuario().getNombreUsuario());

            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
}
