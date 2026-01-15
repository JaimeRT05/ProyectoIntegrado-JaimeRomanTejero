package com.daw.datamodel.dto;

public class AuthResponse {
    private String token;
    private String nombreUsuario;
    private String perfil;

    public AuthResponse(String token, String nombreUsuario, String perfil) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.perfil = perfil;
    }

    public String getToken() {
        return token;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getPerfil() {
        return perfil;
    }
}
