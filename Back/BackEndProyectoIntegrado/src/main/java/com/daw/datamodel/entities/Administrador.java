package com.daw.datamodel.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Administrador")
public class Administrador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="IdAdministrador")
	private Long idAdministrador;
	
	@Column(name="NombreAdmin", nullable=false)
	private String nombreAdmin;
	
	@Column(name="ContrasenaAdmin", nullable=false)
	private String contrasenaAdmin;

	//Relaciones
	
	@OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name="IdUsuario")
	private Usuario usuario;

	
	//Getters y Setters 
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdAdministrador() {
		return idAdministrador;
	}

	public void setIdAdministrador(Long idAdministrador) {
		this.idAdministrador = idAdministrador;
	}

	public String getNombreAdmiN() {
		return nombreAdmin;
	}

	public void setNombreAdmiN(String nombreAdmiN) {
		this.nombreAdmin = nombreAdmiN;
	}

	public String getContrasenaAdmin() {
		return contrasenaAdmin;
	}

	public void setContrasenaAdmin(String contrasenaAdmin) {
		this.contrasenaAdmin = contrasenaAdmin;
	}
	
	
	

}