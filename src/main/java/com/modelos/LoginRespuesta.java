package com.modelos;

public class LoginRespuesta {

	private Usuario usuario;
	private Personal personal;
	
	public LoginRespuesta() {
		this.usuario = new Usuario();
		this.personal = new Personal();
	}
	
	public LoginRespuesta(Usuario usuario,Personal personal) {
		this.usuario = usuario;
		this.personal = personal;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
}
