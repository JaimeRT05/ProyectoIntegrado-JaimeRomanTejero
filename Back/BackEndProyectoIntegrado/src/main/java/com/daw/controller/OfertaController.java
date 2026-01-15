package com.daw.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.datamodel.dto.CrearOfertaDTO;
import com.daw.datamodel.dto.DetalleOfertaDTO;
import com.daw.datamodel.dto.OfertaDTO;
import com.daw.datamodel.dto.OfertaPublicaDTO;
import com.daw.datamodel.entities.Empresa;
import com.daw.datamodel.entities.Oferta;
import com.daw.datamodel.entities.Postulacion;
import com.daw.datamodel.entities.Usuario;
import com.daw.service.EmpresaService;
import com.daw.service.OfertaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    @Autowired
    private OfertaService ofertaService;
    
    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/crearOferta")
    public ResponseEntity<OfertaDTO> crearOferta(@RequestBody @Valid CrearOfertaDTO dto) {
        try {
        	String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("Usuario logueado: " + nombreUsuario);
            System.out.println("DTO recibido: " + dto);
            Oferta oferta = ofertaService.crearOferta(dto);

            OfertaDTO ofertaDTO = new OfertaDTO();
            ofertaDTO.setIdOferta(oferta.getIdOferta());
            ofertaDTO.setTitulo(oferta.getTitulo());
            ofertaDTO.setDescripcion(oferta.getDescripcion());
            ofertaDTO.setPerfilProfesional(oferta.getPerfilProfesional());
            ofertaDTO.setTipoOferta(oferta.getTipoOferta());
            ofertaDTO.setDuracion(oferta.getDuracion());
            ofertaDTO.setCodigoOferta(oferta.getCodigoOferta());
            ofertaDTO.setFechaPublicacion(oferta.getFechaPublicacion());
            ofertaDTO.setEstado(oferta.getEstado());
            ofertaDTO.setIdEmpresa(oferta.getEmpresa().getIdEmpresa());

            return ResponseEntity.ok(ofertaDTO);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/listarOfertasDeUnaEmpresa")
    public ResponseEntity<List<OfertaDTO>> listarOfertasEmpresa() {

        try {
        	
        	org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        	if (auth == null) {
        	    System.out.println("Authentication es null");
        	} else {
        	    System.out.println("Authentication: " + auth);
        	    System.out.println("Nombre de usuario: " + auth.getName());
        	}
        	
        	String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("Usuario logueado: " + nombreUsuario);

            List<Oferta> ofertas = ofertaService.buscarOfertasPorEmpresa(nombreUsuario);

            List<OfertaDTO> dtoLista = ofertas.stream().map(oferta -> {
                OfertaDTO dto = new OfertaDTO();
                dto.setIdOferta(oferta.getIdOferta());
                dto.setTitulo(oferta.getTitulo());
                dto.setDescripcion(oferta.getDescripcion());
                dto.setPerfilProfesional(oferta.getPerfilProfesional());
                dto.setTipoOferta(oferta.getTipoOferta());
                dto.setDuracion(oferta.getDuracion());
                dto.setCodigoOferta(oferta.getCodigoOferta());
                dto.setFechaPublicacion(oferta.getFechaPublicacion());
                dto.setEstado(oferta.getEstado());
                dto.setIdEmpresa(oferta.getEmpresa().getIdEmpresa());
                return dto;
            }).toList();

            return ResponseEntity.ok(dtoLista);

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    	
    @PutMapping("/modificarOferta/{idOferta}")
    public ResponseEntity<OfertaDTO> modificarOferta(@PathVariable Long idOferta,
                                                     @RequestBody OfertaDTO dto) {
        try {
            String nombreUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
            Oferta oferta = ofertaService.modificarOfertaPorUsuario(idOferta, dto, nombreUsuario);

            OfertaDTO ofertaDTO = new OfertaDTO();
            ofertaDTO.setIdOferta(oferta.getIdOferta());
            ofertaDTO.setTitulo(oferta.getTitulo());
            ofertaDTO.setDescripcion(oferta.getDescripcion());
            ofertaDTO.setPerfilProfesional(oferta.getPerfilProfesional());
            ofertaDTO.setTipoOferta(oferta.getTipoOferta());
            ofertaDTO.setDuracion(oferta.getDuracion());
            ofertaDTO.setCodigoOferta(oferta.getCodigoOferta());
            ofertaDTO.setFechaPublicacion(oferta.getFechaPublicacion());
            ofertaDTO.setEstado(oferta.getEstado());
            ofertaDTO.setIdEmpresa(oferta.getEmpresa().getIdEmpresa());

            return ResponseEntity.ok(ofertaDTO);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/eliminarOferta/{idOferta}")
    public ResponseEntity<Map<String, String>> eliminarOferta(@PathVariable Long idOferta) {

        String nombreUsuario =
            SecurityContextHolder.getContext().getAuthentication().getName();

        ofertaService.eliminarOfertaPorUsuario(idOferta, nombreUsuario);

        return ResponseEntity.ok(Map.of("mensaje", "Oferta eliminada correctamente"));
    }
    
    @GetMapping("/{idOferta}/postulaciones")
    public ResponseEntity<List<Postulacion>> listarPostulaciones(@PathVariable Long idOferta) {
        try {
            List<Postulacion> postulaciones = ofertaService.listarPostulaciones(idOferta);
            return ResponseEntity.ok(postulaciones);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @GetMapping("/empresa/{idEmpresa}/pendientes")
    public ResponseEntity<List<OfertaDTO>> listarOfertasPendientes(@PathVariable Long idEmpresa) {
        List<Oferta> ofertas = ofertaService.obtenerOfertasPendientesVerificacion(idEmpresa);
        List<OfertaDTO> dtoList = new ArrayList<>();

        for (Oferta oferta : ofertas) {
            OfertaDTO dto = new OfertaDTO();
            dto.setIdOferta(oferta.getIdOferta());
            dto.setTitulo(oferta.getTitulo());
            dto.setDescripcion(oferta.getDescripcion());
            dto.setPerfilProfesional(oferta.getPerfilProfesional());
            dto.setTipoOferta(oferta.getTipoOferta());
            dto.setDuracion(oferta.getDuracion());
            dto.setCodigoOferta(oferta.getCodigoOferta());
            dto.setFechaPublicacion(oferta.getFechaPublicacion());
            dto.setEstado(oferta.getEstado()); 
            dto.setIdEmpresa(oferta.getEmpresa().getIdEmpresa());
            dtoList.add(dto);
        }

        return ResponseEntity.ok(dtoList);
    }
    
    @PutMapping("/finalizarOferta/{idOferta}")
    public ResponseEntity<String> finalizarOferta(@PathVariable Long idOferta) {
        try {
            ofertaService.finalizarOferta(idOferta);
            return ResponseEntity.ok("Oferta finalizada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping("/obtenerDetalleOferta/{idOferta}")
    public ResponseEntity<DetalleOfertaDTO> detalleOferta(@PathVariable Long idOferta) {
        try {
            DetalleOfertaDTO dto = ofertaService.obtenerDetalleOferta(idOferta);
            return ResponseEntity.ok(dto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/listarOfertas")
    public List<OfertaPublicaDTO> listarPublicas(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String palabraClave
    ) {
        return ofertaService.listarOfertasPublicas(tipo, palabraClave);
    }

    @GetMapping("/empresa/ofertas-pendientes")
    public ResponseEntity<List<OfertaDTO>> ofertasPendientes() {

        String nombreUsuario =
                SecurityContextHolder.getContext().getAuthentication().getName();

        List<Oferta> ofertas =
                ofertaService.obtenerOfertasPendientesVerificacionPorUsuario(nombreUsuario);

        List<OfertaDTO> dtoList = ofertas.stream().map(oferta -> {
            OfertaDTO dto = new OfertaDTO();
            dto.setIdOferta(oferta.getIdOferta());
            dto.setTitulo(oferta.getTitulo());
            dto.setFechaPublicacion(oferta.getFechaPublicacion());
            return dto;
        }).toList();

        return ResponseEntity.ok(dtoList);
    }
    
    @GetMapping("/ofertasConPostulaciones")
    public ResponseEntity<List<OfertaDTO>> listarOfertasConPostulaciones() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof Usuario usuario) {
            username = usuario.getNombreUsuario();
        } else {
            username = principal.toString();
        }

        Empresa empresa = empresaService.buscarPorNombreUsuario(username);

        List<OfertaDTO> ofertas = ofertaService.listarOfertasConPostulacionesPorEmpresa(empresa.getIdEmpresa());
        return ResponseEntity.ok(ofertas);
    }
 

 
    
}
