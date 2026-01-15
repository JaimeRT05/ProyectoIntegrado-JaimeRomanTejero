package com.daw.datamodel.dto;

public class UsuarioDTO {
    private Long id;
    private String nombreUsuario;
    private String contrasenaUsuario;
    private String perfil;
    
	public Long getId() {
		return id;
	}
	
	
    // Getters y setterS
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasenaUsuario() {
		return contrasenaUsuario;
	}
	public void setContrasenaUsuario(String contrasenaUsuario) {
		this.contrasenaUsuario = contrasenaUsuario;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	
    
    
}
