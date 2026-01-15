package com.daw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daw.datamodel.entities.Usuario;
import com.daw.datamodel.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario crearUsuario(String nombreUsuario, String contrasena, String perfil) throws Exception {
        if (usuarioRepository.existsByNombreUsuario(nombreUsuario)) {
            throw new Exception("El nombre de usuario ya está en uso");
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(nombreUsuario);
        usuario.setContrasenaUsuario(passwordEncoder.encode(contrasena)); // Encriptamos la contraseña
        usuario.setPerfil(perfil);

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
}
