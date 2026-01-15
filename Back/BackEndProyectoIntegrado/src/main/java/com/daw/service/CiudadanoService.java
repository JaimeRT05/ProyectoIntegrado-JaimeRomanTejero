package com.daw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daw.datamodel.dto.ActualizarCiudadanoDTO;
import com.daw.datamodel.dto.CiudadanoDTO;
import com.daw.datamodel.entities.Ciudadano;
import com.daw.datamodel.entities.Usuario;
import com.daw.datamodel.repository.CiudadanoRepository;
import com.daw.datamodel.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CiudadanoService {

    @Autowired
    private CiudadanoRepository ciudadanoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Ciudadano crearCiudadano(CiudadanoDTO dto) {

        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setContrasenaUsuario(passwordEncoder.encode(dto.getContrasenaUsuario()));
        usuario.setPerfil("CIUDADANO");
        usuarioRepository.save(usuario);

        // Crear ciudadano
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setNombre(dto.getNombre());
        ciudadano.setApellidos(dto.getApellidos());
        ciudadano.setFechaNacimiento(dto.getFechaNacimiento());
        ciudadano.setCalle(dto.getCalle());
        ciudadano.setNumero(dto.getNumero());
        ciudadano.setLocalidad(dto.getLocalidad());
        ciudadano.setCodigoPostal(dto.getCodigoPostal());
        ciudadano.setTelefono(dto.getTelefono());
        ciudadano.setEmail(dto.getEmail());
        ciudadano.setProfesion(dto.getProfesion());
        ciudadano.setValidado(false);

        // Asociar usuario
        ciudadano.setUsuario(usuario);

        return ciudadanoRepository.save(ciudadano);
    }

    public Ciudadano buscarPorId(Long id) {
        return ciudadanoRepository.findById(id).orElse(null);
    }
    
    @Transactional
    public Ciudadano actualizarCiudadano(Long idCiudadano, ActualizarCiudadanoDTO dto) {

        Ciudadano ciudadano = ciudadanoRepository.findById(idCiudadano)
                .orElseThrow(() -> new RuntimeException("No existe ciudadano con ID: " + idCiudadano));

        if (dto.getNombre() != null) ciudadano.setNombre(dto.getNombre());
        if (dto.getApellidos() != null) ciudadano.setApellidos(dto.getApellidos());
        if (dto.getCalle() != null) ciudadano.setCalle(dto.getCalle());
        if (dto.getNumero() != null) ciudadano.setNumero(dto.getNumero());
        if (dto.getLocalidad() != null) ciudadano.setLocalidad(dto.getLocalidad());
        if (dto.getCodigoPostal() != null) ciudadano.setCodigoPostal(dto.getCodigoPostal());
        if (dto.getTelefono() != null) ciudadano.setTelefono(dto.getTelefono());
        if (dto.getProfesion() != null) ciudadano.setProfesion(dto.getProfesion());

        return ciudadanoRepository.save(ciudadano);
    }

    
    
    public Ciudadano buscarPorNombreUsuario(String nombreUsuario) {
        Optional<Ciudadano> opt = ciudadanoRepository.findByUsuarioNombreUsuario(nombreUsuario);
        return opt.orElseThrow(() -> new RuntimeException("Ciudadano no encontrado"));
    }

}
