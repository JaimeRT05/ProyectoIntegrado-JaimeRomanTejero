package com.daw.datamodel.dto;

import jakarta.validation.constraints.NotBlank;

public class ActualizarEmpresaDTO {

	@NotBlank
	private String nombreCompleto;
	
	@NotBlank
    private String cif;
	
    @NotBlank
    private String actividadEmpresa;
    
    @NotBlank
    private String calle;
    
    @NotBlank
    private Integer numero;
    
    @NotBlank
    private String poligono;
    
    @NotBlank
    private String localidad;
    
    @NotBlank
    private Integer codigoPostal;
    
    @NotBlank
    private String telefono;
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String direccionWeb;
    
    @NotBlank
    public ActualizarEmpresaDTO() {}
    
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getActividadEmpresa() {
		return actividadEmpresa;
	}
	public void setActividadEmpresa(String actividadEmpresa) {
		this.actividadEmpresa = actividadEmpresa;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getPoligono() {
		return poligono;
	}
	public void setPoligono(String poligono) {
		this.poligono = poligono;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccionWeb() {
		return direccionWeb;
	}
	public void setDireccionWeb(String direccionWeb) {
		this.direccionWeb = direccionWeb;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
    
    
	
}
