package com.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Respuesta implements RowMapper<Respuesta>{

	private Long idRespuesta;
	private Long idComentario;
	private String respuesta;
	private Usuario usuario;
	private String fecha;
	
	public Respuesta() {
		this.idRespuesta = 0L;
		this.idComentario = 0L;
		this.fecha = "";
		this.usuario = new Usuario();
		this.respuesta = "";
	}
	
	public Respuesta(Long idRespuesta,Long idComentario,String respuesta,String fecha,Usuario usuario) {
		this.idRespuesta = idRespuesta;
		this.idComentario = idComentario;
		this.fecha = fecha;
		this.usuario = usuario;
		this.respuesta = respuesta;
	}

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public Respuesta mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Respuesta(
				rs.getLong("idRespuesta"),
				rs.getLong("idComentario"),
				rs.getString("respuesta"),
				rs.getString("fecha"),
				new Usuario(
						rs.getString("usuario"),
						rs.getInt("tipo"),
						rs.getLong("idUsuario")));
	}
}
