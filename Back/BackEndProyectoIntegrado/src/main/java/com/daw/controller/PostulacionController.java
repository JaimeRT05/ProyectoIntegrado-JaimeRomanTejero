package com.daw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.datamodel.dto.CandidatoDTO;
import com.daw.datamodel.dto.EmpresaDTO;
import com.daw.datamodel.dto.PostulacionDTO;
import com.daw.datamodel.entities.Ciudadano;
import com.daw.service.CiudadanoService;
import com.daw.service.PostulacionService;

@RestController
@RequestMapping("/api/postulaciones")
public class PostulacionController {

    @Autowired
    private PostulacionService postulacionService;
    
    @Autowired 
    private CiudadanoService ciudadanoService;

    @PostMapping("/postular/{idOferta}")
    public ResponseEntity<?> postular(@PathVariable Long idOferta) {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        try {
            postulacionService.postularOferta(username, idOferta);
            return ResponseEntity.ok("Postulación realizada correctamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }	

    

    @GetMapping("/listarPostulaciones/{idCiudadano}")
    public ResponseEntity<List<PostulacionDTO>> listarPorCiudadano(@PathVariable Long idCiudadano) {
        List<PostulacionDTO> postulaciones = postulacionService.listarPorCiudadano(idCiudadano);
        return ResponseEntity.ok(postulaciones);
    }

    @DeleteMapping("/eliminarPostulacion")
    public ResponseEntity<?> eliminarPostulacion(@RequestParam Long idPostulacion) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Ciudadano ciudadano = ciudadanoService.buscarPorNombreUsuario(username);
        postulacionService.eliminarPostulacion(idPostulacion, ciudadano.getIdCiudadano());
        return ResponseEntity.ok("Postulación eliminada correctamente");
    }

    @GetMapping("/listarEmpresas/{idCiudadano}")
    public ResponseEntity<List<EmpresaDTO>> listarEmpresasPorCiudadano(@PathVariable Long idCiudadano) {
        List<EmpresaDTO> empresas = postulacionService.listarEmpresasPorCiudadano(idCiudadano);
        return ResponseEntity.ok(empresas);
    }
    
    @GetMapping("/mis-postulaciones")
    public ResponseEntity<List<PostulacionDTO>> misPostulaciones() {

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        List<PostulacionDTO> lista = postulacionService
                .listarPostulacionesCiudadano(username);

        return ResponseEntity.ok(lista);
    }
    
    
    @GetMapping("/oferta/{idOferta}/candidatos")
    public ResponseEntity<List<CandidatoDTO>> listarCandidatos(@PathVariable Long idOferta) {
        List<CandidatoDTO> candidatos = postulacionService.listarPostulantesDeOferta(idOferta);
        return ResponseEntity.ok(candidatos);
    }

    @PutMapping("/candidatos/estado/{idPostulacion}")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long idPostulacion,
                                                   @RequestParam String estado) {
        postulacionService.actualizarEstadoPostulacion(idPostulacion, estado);
        return ResponseEntity.ok("Estado actualizado correctamente");
    }
    
}
