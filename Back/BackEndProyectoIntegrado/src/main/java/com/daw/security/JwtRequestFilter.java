package com.daw.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.daw.datamodel.entities.Usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String path = request.getServletPath();
        System.out.println("JwtRequestFilter activado para URL: " + path);

        // Ignorar rutas públicas
        if (path.startsWith("/api/auth/login") ||
            path.startsWith("/api/ciudadanos/crearCiudadano") ||
            path.startsWith("/api/empresas/crearEmpresa")) {
            chain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization header: " + authorizationHeader);

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
            } catch (Exception e) {
                logger.error("JWT inválido: " + e.getMessage());
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Obtener el UserDetails y el Usuario real
            var userDetails = userDetailsService.loadUserByUsername(username);
            if (userDetails instanceof MyUserDetails myUserDetails) {
                Usuario usuario = myUserDetails.getUsuario();

                // Validar token y asignar roles correctamente
                if (jwtUtil.validateToken(jwt, usuario)) {
                    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    if (usuario.getEmpresa() != null) authorities.add(new SimpleGrantedAuthority("ROLE_EMPRESA"));
                    if (usuario.getCiudadano() != null) authorities.add(new SimpleGrantedAuthority("ROLE_CIUDADANO"));
                    if (usuario.getAdministrador() != null) authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                            		usuario,  // Usuario como principal
                                    null,
                                    authorities
                            );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // >>> Depuración: mostrar usuario autenticado y roles
                    System.out.println("Usuario autenticado: " + usuario.getNombreUsuario() + " con roles: " + authorities);

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        chain.doFilter(request, response);
    }
}
