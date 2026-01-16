package com.daw.datamodel.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Ciudadano")
public class Ciudadano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdCiudadano")
	private Long idCiudadano;
	
	@Column(name="Nombre", nullable=false)
	private String nombre;
	
	@Column(name="Apellidos", nullable=false)
	private String apellidos;
	
	@Column(name="FechaNacimiento", nullable=false)
	private LocalDate fechaNacimiento;
	
	@Column(name="Calle", nullable=false)
	private String calle;
	
	@Column(name="Numero", nullable=false)
	private Integer numero;
	
	@Column(name="Localidad", nullable=false)
	private String localidad;
	
	@Column(name="CodigoPostal", nullable=false)
	private Integer codigoPostal;
	
	@Column(name="Telefono", nullable=false)
	private String telefono;
	
	@Column(name="Email", nullable=false)
	private String email;
	
	@Column(name="Profesion", nullable=false)
	private String profesion;
	
	@Column(name="Validado")
	private Boolean validado = false;
	
	//Relaciones
	
	@OneToMany(mappedBy = "ciudadano", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Postulacion> postulaciones;
	
	@OneToOne ( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="IdUsuario")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "ciudadano", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Curriculum> curriculums = new ArrayList<>();

	
	//Getters y Setters
	
	public Long getIdCiudadano() {
		return idCiudadano;
	}

	public void setIdCiudadano(Long idCiudadano) {
		this.idCiudadano = idCiudadano;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
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

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
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

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public List<Postulacion> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(List<Postulacion> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	public List<Curriculum> getCurriculums() {
		return curriculums;
	}

	public void setCurriculums(List<Curriculum> curriculums) {
		this.curriculums = curriculums;
	}
	
	
	
}
