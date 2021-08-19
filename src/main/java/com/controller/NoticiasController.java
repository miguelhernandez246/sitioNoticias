package com.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modelos.BorrarPeticion;
import com.modelos.Comentario;
import com.modelos.LoginRespuesta;
import com.modelos.Nota;
import com.modelos.Respuesta;
import com.modelos.Usuario;
import com.service.NoticiasBS;

@RestController
@RequestMapping("/noticias")
@Validated
@CrossOrigin
public class NoticiasController {

	@Autowired
	private NoticiasBS noticiasBS;
	
	@PostMapping("/login")
	public LoginRespuesta login(@Valid @RequestBody Usuario usuario) {
		return this.noticiasBS.login(usuario);
	}
	
	@GetMapping()
	public List<Nota> con_notas(){
		return this.noticiasBS.con_notas();
	}
	
	@GetMapping("/nota")
	public Nota con_nota(@RequestParam @Positive(message = " El idNota debe ser mayor que cero") Long idNota){
		return this.noticiasBS.con_nota(idNota);
	}
	
	@PostMapping()
	public int alt_nota(@Valid @RequestBody Nota nota) {
		this.noticiasBS.alt_nota(nota);
		return 1;
	}
	
	@GetMapping("/comentarios")
	public List<Comentario> con_comentarios(@RequestParam @Positive(message = " El idNota debe ser mayor que cero") Long idNota){
		return this.noticiasBS.con_comentarios(idNota);
	}
	
	@PostMapping("/comentario")
	public int alt_comentario(@Valid @RequestBody Comentario comentario) {
		this.noticiasBS.alt_comentario(comentario);
		return 1;
	}
	
	@GetMapping("/comentario")
	public Comentario con_comentario(@RequestParam @Positive(message = " El idcomentario debe ser mayor que cero") Long idComentario) {
		return this.noticiasBS.con_comentario(idComentario);
	}
	
	@GetMapping("/respuestas")
	public List<Respuesta> con_respuestas(@RequestParam @Positive(message = " El idComentario debe ser mayor que cero") Long idComentario){
		return this.noticiasBS.con_respuestas(idComentario);
	}
	
	@PostMapping("/respuesta")
	public int alt_respuesta(@Valid @RequestBody Respuesta respuesta) {
		this.noticiasBS.alt_respuesta(respuesta);
		return 1;
	}
	
	@PostMapping("/borrar")
	public int delete(@Valid @RequestBody BorrarPeticion respuesta) {
		this.noticiasBS.delete(respuesta.getId(),respuesta.getTipo());
		return 1;
	}
}
