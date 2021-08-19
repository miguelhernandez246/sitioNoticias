package com.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Comentario implements RowMapper<Comentario>{

	private Long idComentario;
	private Long idNota;
	private Usuario usuario;
	private String comentario;
	private String fecha;
	
	public Comentario() {
		this.idComentario = 0L;
		this.idNota = 0L;
		this.usuario = new Usuario();
		this.comentario = "";
		this.fecha = "";
	}
	
	public Comentario(Long idComentario,Long idNota,Usuario usuario,String comentario,String fecha) {
		this.idComentario = idComentario;
		this.idNota = idNota;
		this.usuario = usuario;
		this.comentario = comentario;
		this.fecha = fecha;
	}

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public Long getIdNota() {
		return idNota;
	}

	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public Comentario mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Comentario(
				rs.getLong("idComentario"),
				rs.getLong("idNota"),
				new Usuario(
						rs.getString("usuario"),
						rs.getInt("tipo"),
						rs.getLong("idUsuario")),
				rs.getString("comentario"),
				rs.getString("fecha"));
	}
}
