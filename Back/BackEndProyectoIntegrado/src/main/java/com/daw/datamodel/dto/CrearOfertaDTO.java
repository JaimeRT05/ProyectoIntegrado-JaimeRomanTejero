package com.daw.datamodel.dto;

public class CrearOfertaDTO {
	
	private String titulo;

    private String descripcion;

    private String perfilProfesional;

    private String tipoOferta; 

    private String duracion;
    
    // Getters y settersç
    
    

	public String getDescripcion() {
		return descripcion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPerfilProfesional() {
		return perfilProfesional;
	}

	public void setPerfilProfesional(String perfilProfesional) {
		this.perfilProfesional = perfilProfesional;
	}

	public String getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	} 

   
}
