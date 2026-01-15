	package com.daw.datamodel.dto;
	
	import java.time.LocalDate;
	
	import com.fasterxml.jackson.annotation.JsonFormat;
	
	public class CiudadanoDTO {
	
	    private Long idCiudadano;
	    
	    private String nombre;
	    
	    private String apellidos;
	    
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	    private LocalDate fechaNacimiento;
	    
	    private String calle;
	    
	    private Integer numero;
	    
	    private String localidad;
	    
	    private Integer codigoPostal;
	    
	    private String telefono;
	    
	    private String email;
	    
	    private String profesion;
	    
	    private String curriculumURL; 
	    
	    private Boolean validado;
	
	    
	    private String nombreUsuario;
	    private String contrasenaUsuario;
	
	    // Getters y Setters
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
	
	    public String getCurriculumURL() {
	        return curriculumURL;
	    }
	
	    public void setCurriculumURL(String curriculumURL) {
	        this.curriculumURL = curriculumURL;
	    }
	
	    public Boolean getValidado() {
	        return validado;
	    }
	
	    public void setValidado(Boolean validado) {
	        this.validado = validado;
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
	
		
	
	   
	}
	
