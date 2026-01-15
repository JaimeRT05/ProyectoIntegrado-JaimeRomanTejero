package com.daw.datamodel.dto;

import java.time.LocalDateTime;

public class PostulacionDTO {

    private Long idPostulacion;
    private LocalDateTime fechaPostulacion;

    private Long idCiudadano;
    private String nombreCiudadano;
    private String apellidosCiudadano;
    private String profesionCiudadano;
    private String curriculumURL;

    private Long idOferta;
    private String tituloOferta;
    
    private String nombreEmpresa;
    private String tipoOferta;
    private String perfilProfesional;

    // ====== Getters y Setters ======

    public Long getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Long idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public LocalDateTime getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDateTime fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public Long getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(Long idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public String getNombreCiudadano() {
        return nombreCiudadano;
    }

    public void setNombreCiudadano(String nombreCiudadano) {
        this.nombreCiudadano = nombreCiudadano;
    }

    public String getApellidosCiudadano() {
        return apellidosCiudadano;
    }

    public void setApellidosCiudadano(String apellidosCiudadano) {
        this.apellidosCiudadano = apellidosCiudadano;
    }

    public String getProfesionCiudadano() {
        return profesionCiudadano;
    }

    public void setProfesionCiudadano(String profesionCiudadano) {
        this.profesionCiudadano = profesionCiudadano;
    }

    public String getCurriculumURL() {
        return curriculumURL;
    }

    public void setCurriculumURL(String curriculumURL) {
        this.curriculumURL = curriculumURL;
    }

    public Long getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Long idOferta) {
        this.idOferta = idOferta;
    }

    public String getTituloOferta() {
        return tituloOferta;
    }

    public void setTituloOferta(String tituloOferta) {
        this.tituloOferta = tituloOferta;
    }

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public String getPerfilProfesional() {
		return perfilProfesional;
	}

	public void setPerfilProfesional(String perfilProfesional) {
		this.perfilProfesional = perfilProfesional;
	}
    
    
    
    
    
}
