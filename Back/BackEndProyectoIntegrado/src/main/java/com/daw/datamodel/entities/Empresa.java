package com.daw.datamodel.entities;

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
@Table(name="Empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdEmpresa")
	private Long idEmpresa;
	
	@Column(name="NombreCompleto", nullable=false)
	private String nombreCompleto;
	
	@Column(name="CIF", nullable=false)
	private String cif;
		
	@Column(name="ActividadEmpresa", nullable=false)
	private String actividadEmpresa;
	
	@Column(name="Calle", nullable=false)
	private String calle;
	
	@Column(name="Numero", nullable=false)
	private Integer numero;

	@Column(name="Poligono", nullable=false)
	private String poligono;

	@Column(name="Localidad", nullable=false)
	private String localidad;
	
	@Column(name="CodigoPostal", nullable=false)
	private Integer codigoPostal;
	
	@Column(name="Telefono", nullable=false)
	private String telefono;
	
	@Column(name="Email", nullable=false)
	private String email;
	
	@Column(name="DireccionWeb", nullable=false)
	private String direccionWeb;
	
	@Column(name="CodigoEmpresa", nullable=false, unique=true)
	private String codigoEmpresa;
	
	@Column(name="Validado", nullable=false)
	private Boolean validado = false;

	//Relaciones
	
	@OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Oferta> ofertas;
	
	@OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="IdUsuario")
	private Usuario usuario;

	
	// Getters y Setters
	
	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getActividadEmpresa() {
		return actividadEmpresa;
	}

	public void setActividadEmpresa(String actividadEmpresa) {
		this.actividadEmpresa = actividadEmpresa;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(String id) {
		this.codigoEmpresa = id;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getActividadaEmpresa() {
		return actividadEmpresa;
	}

	public void setActividadaEmpresa(String actividadaEmpresa) {
		this.actividadEmpresa = actividadaEmpresa;
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

	public String getDireccionWeb() {
		return direccionWeb;
	}

	public void setDireccionWeb(String direccionWeb) {
		this.direccionWeb = direccionWeb;
	}

	public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}


}
