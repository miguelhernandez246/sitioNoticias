package com.modelos;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.jdbc.core.RowMapper;

public class Nota implements RowMapper<Nota> {

	private Long idNota;
	@Valid
	private Personal persona;
	@NotBlank(message = "El titulo no puede estar vacio")
	private String titulo;
	@NotBlank(message = "El titulo no puede estar vacio")
	private String contenido;
	private String fecha;
	
	public Nota() {
		this.idNota = 0L;
		this.persona = new Personal();
		this.titulo = "";
		this.contenido = "";
		this.fecha = "";
	}
	
	public Nota(
			Long id,
			Personal persona,
			String titulo,
			String contenido,
			String fecha
	) {
		this.idNota = id;
		this.persona = persona;
		this.titulo = titulo;
		this.contenido = contenido;
		this.fecha = fecha;
	}

	public Long getIdNota() {
		return idNota;
	}

	public void setIdNota(Long idNota) {
		this.idNota = idNota;
	}

	public Personal getPersona() {
		return persona;
	}

	public void setPersona(Personal persona) {
		this.persona = persona;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@Override
	public Nota mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return new Nota(rs.getLong("idNota"),
				new Personal(
						rs.getLong("idPersonal"),
						rs.getString("nombre"),
						rs.getString("paterno"),
						rs.getString("materno")),
				rs.getString("titulo"),
				rs.getString("contenido"),
				rs.getString("fecha"));
	}
}
