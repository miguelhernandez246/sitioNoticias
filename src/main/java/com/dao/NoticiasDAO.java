package com.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.modelos.Comentario;
import com.modelos.LoginRespuesta;
import com.modelos.Nota;
import com.modelos.Personal;
import com.modelos.Respuesta;
import com.modelos.Usuario;

@Repository
public class NoticiasDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String ESQUEMA = "sitioNoticias";
	
	public List<Nota> con_notas(){
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("con_notas").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlReturnResultSet("rs", new Nota()));
		
		Map<String, Object> resultado = simplejdbccall.execute();
		
		@SuppressWarnings("unchecked")
		List<Nota> notas = (List<Nota>) (resultado.get("rs")==null?new ArrayList<Nota>():resultado.get("rs"));
		
		return notas;
	}
	
	public void alt_nota(Nota nota) {
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("alt_notas").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("in_idPersonal", Types.BIGINT)).
				declareParameters(new SqlParameter("in_titulo", Types.VARCHAR)).
				declareParameters(new SqlParameter("in_contenido", Types.VARCHAR));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_idPersonal", nota.getPersona().getIdPersonal()).
				addValue("in_titulo", nota.getTitulo()).
				addValue("in_contenido", nota.getContenido());
		
		simplejdbccall.execute(parametrosEntrada);
		
	}
	
	public Nota con_nota(Long idNota){
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("con_nota").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("in_idNota", Types.BIGINT))
				.declareParameters(new SqlOutParameter("out_titulo", Types.VARCHAR))
				.declareParameters(new SqlOutParameter("out_contenido", Types.VARCHAR))
				.declareParameters(new SqlOutParameter("out_nombre", Types.VARCHAR))
				.declareParameters(new SqlOutParameter("out_fecha", Types.DATE));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_idNota", idNota);
		
		Map<String, Object> resultado = simplejdbccall.execute(parametrosEntrada);
		
		Nota nota = new Nota();
		
		nota.setTitulo(resultado.get("out_titulo").toString());
		nota.setContenido(resultado.get("out_contenido").toString());
		nota.setFecha(resultado.get("out_fecha").toString());
		nota.setPersona(new Personal(resultado.get("out_nombre").toString()));
		
		return nota;
	}
	
	public LoginRespuesta login(Usuario usuario) {
		
		Personal persona = new Personal();
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("con_usuarioLogin").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("in_usuario", Types.VARCHAR)).
				declareParameters(new SqlParameter("in_contrasena", Types.VARCHAR))
				.declareParameters(new SqlOutParameter("out_idUsuario", Types.BIGINT))
				.declareParameters(new SqlOutParameter("out_tipo", Types.INTEGER))
				.declareParameters(new SqlOutParameter("out_idPersonal", Types.BIGINT))
				.declareParameters(new SqlOutParameter("out_nombre", Types.VARCHAR));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_usuario", usuario.getUsuario()).
				addValue("in_contrasena", usuario.getContrasena());
		
		Map<String, Object> resultMap = simplejdbccall.execute(parametrosEntrada);
		
		usuario.setIdUsuario(Long.parseLong(resultMap.get("out_idUsuario").toString()));
		usuario.setTipoUsuario(Integer.parseInt(resultMap.get("out_tipo").toString()));
		persona.setIdPersonal(Long.parseLong(resultMap.get("out_idPersonal").toString()));
		persona.setNombre(resultMap.get("out_nombre").toString());
		
		return new LoginRespuesta(usuario,persona);
	}
	
	public List<Comentario> con_comentarios(Long idNota){
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("con_comentarios").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlReturnResultSet("rs", new Comentario())).
				declareParameters(new SqlParameter("in_idnota", Types.BIGINT));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_idnota", idNota);
		
		Map<String, Object> resultado = simplejdbccall.execute(parametrosEntrada);
		
		@SuppressWarnings("unchecked")
		List<Comentario> comentarios = (List<Comentario>) (resultado.get("rs")==null?new ArrayList<Comentario>():resultado.get("rs"));
		
		return comentarios;
	}
	
	public Comentario con_comentario(Long idComentario){
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("con_comentario").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("in_idComentario", Types.BIGINT))
				.declareParameters(new SqlOutParameter("out_comentario", Types.VARCHAR))
				.declareParameters(new SqlOutParameter("out_usuario", Types.VARCHAR))
				.declareParameters(new SqlOutParameter("out_tipoUsuario", Types.INTEGER))
				.declareParameters(new SqlOutParameter("out_fecha", Types.DATE));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_idComentario", idComentario);
		
		Map<String, Object> resultado = simplejdbccall.execute(parametrosEntrada);
		
		Comentario comentario = new Comentario();
		
		comentario.setComentario(resultado.get("out_comentario").toString());
		comentario.setUsuario(
				new Usuario(
						resultado.get("out_usuario").toString(),
						Integer.parseInt(resultado.get("out_tipoUsuario").toString())
						));
		comentario.setFecha(resultado.get("out_fecha").toString());
		
		return comentario;
	}
	
	public void alt_comentario(Comentario comentario) {
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("alt_comentario").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("in_idNota", Types.BIGINT)).
				declareParameters(new SqlParameter("in_idUsuario", Types.BIGINT)).
				declareParameters(new SqlParameter("in_comentario", Types.VARCHAR));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_idNota", comentario.getIdNota()).
				addValue("in_idUsuario", comentario.getUsuario().getIdUsuario()).
				addValue("in_comentario", comentario.getComentario());
		
		simplejdbccall.execute(parametrosEntrada);
	}
	
	public List<Respuesta> con_respuestas(Long idComentario){
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("con_respuestas").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlReturnResultSet("rs", new Respuesta())).
				declareParameters(new SqlParameter("in_idcomentario", Types.BIGINT));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_idcomentario", idComentario);
		
		Map<String, Object> resultado = simplejdbccall.execute(parametrosEntrada);
		
		@SuppressWarnings("unchecked")
		List<Respuesta> respuestas = (List<Respuesta>) (resultado.get("rs")==null?new ArrayList<Respuesta>():resultado.get("rs"));
		
		return respuestas;
	}
	
	public void alt_respuesta(Respuesta respuesta) {
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("alt_respuesta").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("in_idComentario", Types.BIGINT)).
				declareParameters(new SqlParameter("in_idUsuario", Types.BIGINT)).
				declareParameters(new SqlParameter("in_respuesta", Types.VARCHAR));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_idComentario", respuesta.getIdComentario()).
				addValue("in_idUsuario", respuesta.getUsuario().getIdUsuario()).
				addValue("in_respuesta", respuesta.getRespuesta());
		
		simplejdbccall.execute(parametrosEntrada);
	}
	
	public void delete(Long id, int tipo) {
		
		SimpleJdbcCall simplejdbccall = new SimpleJdbcCall(jdbcTemplate).
				withSchemaName(ESQUEMA).
				withProcedureName("delete_registros").
				withoutProcedureColumnMetaDataAccess().
				declareParameters(new SqlParameter("in_id", Types.BIGINT)).
				declareParameters(new SqlParameter("in_tipo", Types.INTEGER));
		
		SqlParameterSource parametrosEntrada = new MapSqlParameterSource().
				addValue("in_id", id).
				addValue("in_tipo", tipo);
		
		simplejdbccall.execute(parametrosEntrada);
	}
}
