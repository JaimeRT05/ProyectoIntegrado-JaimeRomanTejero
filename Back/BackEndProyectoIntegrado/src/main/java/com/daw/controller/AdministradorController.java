package com.daw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Empresa;
import com.daw.datamodel.entities.Oferta;
import com.daw.service.AdministradorService;

@RestController
@RequestMapping("/api/admin")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;
   
    //@PreAuthorize("hasRole('ADMIN')") HAY QUE PONER ESTO PARA QUE SOLO EL ROL ADMIN PUEDA HACERLO
    @GetMapping("/listarEmpresas/pendientes")
    public ResponseEntity<List<Empresa>> listarEmpresasPendientes() {
        List<Empresa> empresasPendientes = administradorService.obtenerEmpresasPendientes();
        return ResponseEntity.ok(empresasPendientes);
    }
    
    @GetMapping("/listarCiudadanos/pendientes")
    public ResponseEntity<List<Ciudadano>> listarCiudadanosPendientes() {
        List<Ciudadano> ciudadanosPendientes = administradorService.obtenerCiudadanosPendientes();
        return ResponseEntity.ok(ciudadanosPendientes);
    }

    @PutMapping("/ValidarEmpresas/{id}")
    public ResponseEntity<Empresa> validarEmpresa(@PathVariable Long id) {
        Empresa empresa = administradorService.validarEmpresa(id);
        return ResponseEntity.ok(empresa);
    }
    
    @PutMapping("/ValidarCiudadano/{id}")
    public ResponseEntity<Ciudadano> validarCiudadano(@PathVariable Long id) {
    	Ciudadano ciudadano = administradorService.validarCiudadano(id);
        return ResponseEntity.ok(ciudadano);
    }

    @PutMapping("/desvalidarEmpresas/{id}/desvalidar")
    public ResponseEntity<Empresa> desvalidarEmpresa(@PathVariable Long id) {
        Empresa empresa = administradorService.desvalidarEmpresa(id);
        return ResponseEntity.ok(empresa);
    }
    
    @PutMapping("/desvalidarCiudadano/{id}/desvalidar")
    public Ciudadano desvalidarCiudadano(@PathVariable Long id) {
        return administradorService.desvalidarCiudadano(id);
    }
    
    @GetMapping("/listarEmpresas")
    public List<Empresa> listarEmpresas() {
        return administradorService.listarEmpresas();
    }
    
    @GetMapping("/listarCiudadanos")
    public List<Ciudadano> listarCiudadanos() {
        return administradorService.listarCiudadanos();
    }
    
    @GetMapping("/listarOfertas")
    public List<Oferta> listarOfertas() {
    	return administradorService.listarOfertas();
    }
    
   
    
    //ELIMINACIONES
    
    @DeleteMapping("/eliminarEmpresa/{id}")
    public ResponseEntity<String> eliminarEmpresa(@PathVariable Long id) {
        try {
            administradorService.eliminarEmpresa(id);
            return ResponseEntity.ok("Empresa eliminada correctamente");
        } catch (RuntimeException e) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/eliminarCiudadanos/{id}")
    public ResponseEntity<String> eliminarCiudadano(@PathVariable Long id) {
        try {
            administradorService.eliminarCiudadano(id);
            return ResponseEntity.ok("Ciudadano eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/eliminarOfertas/{id}")
    public ResponseEntity<String> eliminarOferta(@PathVariable Long id) {
        try {
            administradorService.eliminarOferta(id);
            return ResponseEntity.ok("Oferta eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    
	
}
