package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.NoticiasDAO;
import com.modelos.Comentario;
import com.modelos.LoginRespuesta;
import com.modelos.Nota;
import com.modelos.Respuesta;
import com.modelos.Usuario;

@Service
public class NoticiasBS {

	@Autowired
	private NoticiasDAO noticiasDAO;
	
	public LoginRespuesta login(Usuario usuario) {
		return this.noticiasDAO.login(usuario);
	}
	
	public List<Nota> con_notas(){
		return this.noticiasDAO.con_notas();
	}
	
	public Nota con_nota(Long idNota){
		return this.noticiasDAO.con_nota(idNota);
	}
	
	public void alt_nota(Nota nota) {
		this.noticiasDAO.alt_nota(nota);
	}
	
	public List<Comentario> con_comentarios(Long idNota){
		return this.noticiasDAO.con_comentarios(idNota);
	}
	
	public Comentario con_comentario(Long idComentario) {
		return this.noticiasDAO.con_comentario(idComentario);
	}
	
	public void alt_comentario(Comentario comentario) {
		this.noticiasDAO.alt_comentario(comentario);
	}

	public List<Respuesta> con_respuestas(Long idComentario){
		return this.noticiasDAO.con_respuestas(idComentario);
	}
	
	public void alt_respuesta(Respuesta respuesta) {
		this.noticiasDAO.alt_respuesta(respuesta);
	}
	
	public void delete(Long id, int tipo) {
		this.noticiasDAO.delete(id, tipo);
	}
}
