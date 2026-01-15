package com.daw.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.datamodel.dto.AuthRequest;
import com.daw.datamodel.dto.AuthResponse;
import com.daw.security.CustomUserDetailsService;
import com.daw.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Endpoint de login: autentica usuario y genera JWT.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authRequest.getNombreUsuario(),
                    authRequest.getContrasenaUsuario()
                )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }

        // Obtener el usuario desde la BD
        var usuario = userDetailsService.getUsuario(authRequest.getNombreUsuario());

        // Determinar perfil igual que en JwtUtil
        String perfil = usuario.getCiudadano() != null ? "CIUDADANO" :
                        usuario.getEmpresa() != null ? "EMPRESA" :
                        usuario.getAdministrador() != null ? "ADMIN" : "USER";

        // Generar token JWT
        final String token = jwtUtil.generateToken(usuario);

        // ✅ Devolver respuesta completa
        return ResponseEntity.ok(
            new AuthResponse(token, usuario.getNombreUsuario(), perfil)
        );
    }

    /**
     * Endpoint para obtener el perfil del usuario logueado.
     */
    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Token faltante o inválido");
            }

            String token = authHeader.substring(7);
            String nombreUsuario = jwtUtil.extractUsername(token);

            // Validar token
            if (nombreUsuario == null || nombreUsuario.isEmpty()) {
                return ResponseEntity.status(401).body("Token inválido");
            }

            var usuario = userDetailsService.getUsuario(nombreUsuario);

            // Recuperar el perfil del token (más fiable)
            String perfil = jwtUtil.extractPerfil(token);

            // Crear respuesta JSON
            Map<String, Object> perfilResponse = new HashMap<>();
            perfilResponse.put("nombreUsuario", usuario.getNombreUsuario());
            perfilResponse.put("perfil", perfil);

            return ResponseEntity.ok(perfilResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("Usuario no autenticado");
        }
    }
}
