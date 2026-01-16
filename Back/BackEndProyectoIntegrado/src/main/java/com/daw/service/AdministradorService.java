package com.daw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daw.datamodel.dto.AdminDTO;
import com.daw.datamodel.entities.Administrador;
import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Empresa;
import com.daw.datamodel.entities.Curriculum;
import com.daw.datamodel.entities.Oferta;
import com.daw.datamodel.entities.Usuario;
import com.daw.datamodel.entities.Postulacion;
import com.daw.datamodel.repository.AdministradorRepository;
import com.daw.datamodel.repository.CiudadanoRepository;
import com.daw.datamodel.repository.EmpresaRepository;
import com.daw.datamodel.repository.OfertaRepository;
import com.daw.datamodel.repository.PostulacionRepository;
import com.daw.datamodel.repository.UsuarioRepository;
import com.daw.datamodel.repository.CurriculumRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdministradorService {

	@Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    @Autowired
    private OfertaRepository ofertaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PostulacionRepository postulacionRepository;

    @Autowired 
    private AdministradorRepository administradorRepository;

    @Autowired
    private CurriculumRepository curriculumRepository;

     @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Administrador crearAdministrador(AdminDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setContrasenaUsuario(passwordEncoder.encode(dto.getContrasenaUsuario()));
        usuario.setPerfil("ADMIN");

        usuarioRepository.save(usuario);

        Administrador admin = new Administrador();
        admin.setNombreAdmin(dto.getNombreAdmin());
        admin.setContrasenaAdmin(dto.getContrasenaAdmin());
        admin.setUsuario(usuario);

        usuario.setAdministrador(admin);

        return administradorRepository.save(admin);
    }

    public List<Empresa> obtenerEmpresasPendientes() {
        return empresaRepository.findByValidadoFalse();
    }
    
    public List<Ciudadano> obtenerCiudadanosPendientes() {
    	return ciudadanoRepository.findByValidadoFalse();
    }

    public Empresa validarEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        if (empresa.getValidado()) {
            throw new RuntimeException("La empresa ya está validada");
        }
        empresa.setValidado(true);
        return empresaRepository.save(empresa);
    }
    
    public Ciudadano validarCiudadano(Long idCiudadano) {
        Ciudadano ciudadano = ciudadanoRepository.findById(idCiudadano)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));
        if (ciudadano.getValidado()) {
            throw new RuntimeException("El ciudadano ya está validado");
        }
        ciudadano.setValidado(true);
        return ciudadanoRepository.save(ciudadano);
    }

    public Empresa desvalidarEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        if (!empresa.getValidado()) {
            throw new RuntimeException("La empresa ya está desactivada");
        }
        empresa.setValidado(false);
        return empresaRepository.save(empresa);
    }
    
    public Ciudadano desvalidarCiudadano(Long idCiudadano) {
        Ciudadano ciudadano = ciudadanoRepository.findById(idCiudadano)
                .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));
        if (!ciudadano.getValidado()) {
            throw new RuntimeException("El ciudadano ya está desactivado");
        }
        ciudadano.setValidado(false);
        return ciudadanoRepository.save(ciudadano);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }
    
    public List<Ciudadano> listarCiudadanos() {
        return ciudadanoRepository.findAll();
    }
    
    public List<Oferta> listarOfertas() {
		return ofertaRepository.findAll();
	}

    @Transactional
    public void eliminarEmpresa(Long idEmpresa) {

        Empresa empresa = empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        Usuario usuario = empresa.getUsuario();

        empresaRepository.delete(empresa);

        if (usuario != null) {
            usuarioRepository.delete(usuario);
        }
    }
    
    @Transactional
public void eliminarCiudadano(Long idCiudadano) {

    Ciudadano ciudadano = ciudadanoRepository.findById(idCiudadano)
            .orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));

    if (ciudadano.getPostulaciones() != null) {
        for (Postulacion p : ciudadano.getPostulaciones()) {
            postulacionRepository.delete(p); 
        }
    }

    if (ciudadano.getCurriculums() != null) {
        for (Curriculum c : ciudadano.getCurriculums()) {
            curriculumRepository.delete(c); 
        }
    }

    Usuario usuario = ciudadano.getUsuario();
    if (usuario != null) {
        usuarioRepository.delete(usuario);
    }

    ciudadanoRepository.delete(ciudadano);
}
    
    public void eliminarOferta(Long idOferta) {
        Oferta oferta = ofertaRepository.findById(idOferta)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada"));
        
        postulacionRepository.deleteByOfertaIdOferta(idOferta);

        ofertaRepository.delete(oferta);
    }

	
	
}
