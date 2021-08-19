package com.modelos;

public class Usuario {

	private Long idUsuario;
	private String usuario;
	private String contrasena;
	private Integer tipoUsuario;
	
	public Usuario() {
		this.idUsuario = 0L;
		this.usuario = "";
		this.contrasena = "";
		this.tipoUsuario = 0;
	}
	
	public Usuario(String usuario,Integer tipoUsuario) {
		this.idUsuario = 0L;
		this.usuario = usuario;
		this.contrasena = "";
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario(String usuario,Integer tipoUsuario,Long idUsuario) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.contrasena = "";
		this.tipoUsuario = tipoUsuario;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Integer getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(Integer tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
