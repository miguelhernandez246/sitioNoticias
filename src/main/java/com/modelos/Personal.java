package com.modelos;

import java.util.Date;

import javax.validation.constraints.Positive;

public class Personal {

	@Positive(message = "El idPersonal debe ser mayor que cero")
	private Long idPersonal;
	private String nombre;
	private String paterno;
	private String materno;
	private String direccion;
	private Date fechaIngreso;
	private Usuario usuario;
	
	public Personal() {
		this.idPersonal = 0L;
		this.nombre = "";
		this.paterno = "";
		this.materno = "";
		this.direccion = "";
		this.fechaIngreso = null;
		this.usuario = new Usuario();
	}
	
	public Personal(String nombre) {
		this.idPersonal = 0L;
		this.nombre = nombre;
		this.paterno = "";
		this.materno = "";
		this.direccion = "";
		this.fechaIngreso = null;
		this.usuario = new Usuario();
	}
	
	public Personal(Long id,String nombre,String paterno, String materno) {
		this.idPersonal = id;
		this.nombre = nombre;
		this.paterno = paterno;
		this.materno = materno;
		this.direccion = "";
		this.fechaIngreso = null;
		this.usuario = new Usuario();
	}

	public Long getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(Long idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
