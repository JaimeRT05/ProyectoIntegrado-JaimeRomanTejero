package com.daw.controller;

import java.util.List;

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

import com.daw.datamodel.dto.ActualizarEmpresaDTO;
import com.daw.datamodel.dto.EmpresaDTO;
import com.daw.datamodel.entities.Empresa;
import com.daw.service.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService servicio;

    @PostMapping("/crearEmpresa")
    public ResponseEntity<EmpresaDTO> crearEmpresa(
            @ModelAttribute EmpresaDTO dto,
            @RequestParam(value = "archivo", required = false) MultipartFile archivo) {

        try {
            // Si se subió un archivo (logo, documento, etc.)
            if (archivo != null && !archivo.isEmpty()) {
                dto.setDireccionWeb(archivo.getOriginalFilename()); // o tu lógica de guardado
            }

            // Crear empresa junto con usuario
            Empresa empresa = servicio.crearEmpresa(dto);

            // Mapear a DTO de respuesta
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setIdEmpresa(empresa.getIdEmpresa());
            empresaDTO.setNombreCompleto(empresa.getNombreCompleto());
            empresaDTO.setCif(empresa.getCif());
            empresaDTO.setActividadEmpresa(empresa.getActividadEmpresa());
            empresaDTO.setCalle(empresa.getCalle());
            empresaDTO.setNumero(empresa.getNumero());
            empresaDTO.setPoligono(empresa.getPoligono());
            empresaDTO.setLocalidad(empresa.getLocalidad());
            empresaDTO.setCodigoPostal(empresa.getCodigoPostal());
            empresaDTO.setTelefono(empresa.getTelefono());
            empresaDTO.setEmail(empresa.getEmail());
            empresaDTO.setDireccionWeb(empresa.getDireccionWeb());
            if (empresa.getUsuario() != null) {
                empresaDTO.setNombreUsuario(empresa.getUsuario().getNombreUsuario());
            }

            return ResponseEntity.ok(empresaDTO);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/buscarEmpresaPorId/{id}")
    public ResponseEntity<EmpresaDTO> buscarEmpresa(@PathVariable Long id) {
        try {
            Empresa empresa = servicio.buscarPorId(id);

            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setIdEmpresa(empresa.getIdEmpresa());
            empresaDTO.setNombreCompleto(empresa.getNombreCompleto());
            empresaDTO.setCif(empresa.getCif());
            empresaDTO.setActividadEmpresa(empresa.getActividadEmpresa());
            empresaDTO.setCalle(empresa.getCalle());
            empresaDTO.setNumero(empresa.getNumero());
            empresaDTO.setPoligono(empresa.getPoligono());
            empresaDTO.setLocalidad(empresa.getLocalidad());
            empresaDTO.setCodigoPostal(empresa.getCodigoPostal());
            empresaDTO.setTelefono(empresa.getTelefono());
            empresaDTO.setEmail(empresa.getEmail());
            empresaDTO.setDireccionWeb(empresa.getDireccionWeb());
            if (empresa.getUsuario() != null) {
                empresaDTO.setNombreUsuario(empresa.getUsuario().getNombreUsuario());
            }

            return ResponseEntity.ok(empresaDTO);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    
    @GetMapping("/listarEmpresas")
    public ResponseEntity<List<EmpresaDTO>> listarEmpresas() {
        try {
            List<EmpresaDTO> empresas = servicio.listarEmpresas();
            return ResponseEntity.ok(empresas);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PutMapping("/actualizarEmpresa/{idEmpresa}")
    public ResponseEntity<EmpresaDTO> actualizarEmpresa(
            @PathVariable Long idEmpresa,
            @Valid @RequestBody ActualizarEmpresaDTO dto) {
    	
    	System.out.println("DTO recibido: " + dto.getCif());
    	System.out.println("DTO recibido: " + dto.getActividadEmpresa());

            Empresa empresaActualizada = servicio.actualizarEmpresa(idEmpresa, dto);

            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setIdEmpresa(empresaActualizada.getIdEmpresa());
            empresaDTO.setNombreCompleto(empresaActualizada.getNombreCompleto());
            empresaDTO.setCif(empresaActualizada.getCif());
            empresaDTO.setActividadEmpresa(empresaActualizada.getActividadEmpresa());
            empresaDTO.setCalle(empresaActualizada.getCalle());
            empresaDTO.setNumero(empresaActualizada.getNumero());
            empresaDTO.setPoligono(empresaActualizada.getPoligono());
            empresaDTO.setLocalidad(empresaActualizada.getLocalidad());
            empresaDTO.setLocalidad(empresaActualizada.getLocalidad());
            empresaDTO.setCodigoPostal(empresaActualizada.getCodigoPostal());
            empresaDTO.setTelefono(empresaActualizada.getTelefono());
            empresaDTO.setEmail(empresaActualizada.getEmail());
            empresaDTO.setDireccionWeb(empresaActualizada.getDireccionWeb());

            return ResponseEntity.ok(empresaDTO);
         
    }
    
    @GetMapping("/buscarEmpresaPorNombreUsuario/{nombreUsuario}")
    public ResponseEntity<EmpresaDTO> buscarPorNombreUsuario(@PathVariable String nombreUsuario) {
    	System.out.println("Buscando empresa con username: " + nombreUsuario);
    	try {
            Empresa empresa = servicio.buscarPorNombreUsuario(nombreUsuario);

            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setIdEmpresa(empresa.getIdEmpresa());
            empresaDTO.setNombreCompleto(empresa.getNombreCompleto());
            empresaDTO.setCif(empresa.getCif());
            empresaDTO.setActividadEmpresa(empresa.getActividadEmpresa());
            empresaDTO.setCalle(empresa.getCalle());
            empresaDTO.setNumero(empresa.getNumero());
            empresaDTO.setPoligono(empresa.getPoligono());
            empresaDTO.setLocalidad(empresa.getLocalidad());
            empresaDTO.setCodigoPostal(empresa.getCodigoPostal());
            empresaDTO.setTelefono(empresa.getTelefono());
            empresaDTO.setEmail(empresa.getEmail());
            empresaDTO.setDireccionWeb(empresa.getDireccionWeb());
            if (empresa.getUsuario() != null) {
                empresaDTO.setNombreUsuario(empresa.getUsuario().getNombreUsuario());
            }

            return ResponseEntity.ok(empresaDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    
    
    
}
