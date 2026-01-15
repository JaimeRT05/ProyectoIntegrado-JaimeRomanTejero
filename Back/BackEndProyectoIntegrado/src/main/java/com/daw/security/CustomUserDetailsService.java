package com.daw.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daw.datamodel.entities.Usuario;
import com.daw.datamodel.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (usuario.getCiudadano() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CIUDADANO"));
        } else if (usuario.getEmpresa() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_EMPRESA"));
        } else if (usuario.getAdministrador() != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
        }

        return new MyUserDetails(usuario, authorities);
    }

    // Método para obtener la entidad Usuario completa desde el username
    public Usuario getUsuario(String username) {
        return usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
    
    
    
    
}
	