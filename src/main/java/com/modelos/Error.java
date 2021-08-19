package com.modelos;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Error {
	
	private String mensaje;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String excepcion;
	private int codigo;
	
	
	public Error() {
		this.mensaje = "";
		this.excepcion = "";
		this.codigo = 0;
	}
	
	public Error(String msj) {
		this.mensaje = msj;
		this.excepcion = "0";
		this.codigo = 0;
	}
	
	public Error(String mensaje, String excepcion) {
		this.mensaje = mensaje;
		this.excepcion = excepcion;
		this.codigo = 0;
	}
	
	public Error(String mensaje, String excepcion, int cod) {
		this.mensaje = mensaje;
		this.excepcion = excepcion;
		this.codigo = cod;
	}

	public String getMensaje() {
		return mensaje;
	}


	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public String getExcepcion() {
		return excepcion;
	}


	public void setExcepcion(String excepcion) {
		this.excepcion = excepcion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	
}

