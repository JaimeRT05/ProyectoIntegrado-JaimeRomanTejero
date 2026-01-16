package com.daw.datamodel.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name="Postulacion")
public class Postulacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdPostulacion")
	private Long idPostulacion;
	
	@Column(name="FechaPostulacion", nullable=false)
	private LocalDateTime fechaPostulacion = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private EstadoPostulacion estado = EstadoPostulacion.PENDIENTE;
	
	//Relaciones
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
    @JoinColumn(name = "IdCiudadano")
    private Ciudadano ciudadano;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "IdOferta")
    private Oferta oferta;
    
   	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_curriculum", nullable = false)
	private Curriculum curriculum;


    // Getters y Setters
    
	public Long getIdPostulacion() {
		return idPostulacion;
	}

	public void setIdPostulacion(Long idPostulacion) {
		this.idPostulacion = idPostulacion;
	}

	public LocalDateTime getFechaPostulacion() {
		return fechaPostulacion;
	}

	public void setFechaPostulacion(LocalDateTime localDate) {
		this.fechaPostulacion = localDate;
	}

	public Ciudadano getCiudadano() {
		return ciudadano;
	}

	public void setCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}

	public EstadoPostulacion getEstado() {
		return estado;
	}

	public void setEstado(EstadoPostulacion estado) {
		this.estado = estado;
	}
	
	
	
    
}
