package com.daw.datamodel.dto;

import java.time.LocalDate;

public class CandidatoDTO {
    private Long idPostulacion;
    private String nombreCiudadano;
    private Long idCurriculum; 
    private LocalDate fechaPostulacion;
    private String estado;
	public Long getIdPostulacion() {
		return idPostulacion;
	}
	public void setIdPostulacion(Long idPostulacion) {
		this.idPostulacion = idPostulacion;
	}
	public String getNombreCiudadano() {
		return nombreCiudadano;
	}
	public void setNombreCiudadano(String nombreCiudadano) {
		this.nombreCiudadano = nombreCiudadano;
	}
	public Long getIdCurriculum() {
		return idCurriculum;
	}
	public void setIdCurriculum(Long idCurriculum) {
		this.idCurriculum = idCurriculum;
	}
	public LocalDate getFechaPostulacion() {
		return fechaPostulacion;
	}
	public void setFechaPostulacion(LocalDate fechaPostulacion) {
		this.fechaPostulacion = fechaPostulacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	} 


    
 
    
    
}
