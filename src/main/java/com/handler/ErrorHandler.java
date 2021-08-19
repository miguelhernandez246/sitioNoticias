package com.handler;

import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.modelos.Error;

@ControllerAdvice
public class ErrorHandler extends DefaultHandlerExceptionResolver {
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);
	
	/**
	 * Este metodo sive para atrapar los errores de las validaciones de los objetos
	 * de entrada
	 * 
	 * @param e Excepción que fue arrojada
	 * @return ResponseEntity con status 400
	 */
	@ExceptionHandler({ BindException.class })
	protected ResponseEntity<Error> bindException(BindingResult bindingResult, BindException e) {
		LOGGER.error("Error de validación", e);
		String mensaje = bindingResult.getAllErrors().get(0).getDefaultMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(mensaje, null));
	}

	/**
	 * Este metodo sive para atrapar los errores de las validaciones de los objetos
	 * de entrada
	 * 
	 * @param e Excepción que fue arrojada
	 * @return ResponseEntity con status 400
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	protected ResponseEntity<Error> methodArgumentNotValidException(MethodArgumentNotValidException e) {
		LOGGER.error("Error de validación", e);
		String mensaje = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(mensaje, null));
	}
	
	/**
	 * Este metodo sive para atrapar los errores de las validaciones de los objetos
	 * de entrada
	 * 
	 * @param e Excepción que fue arrojada
	 * @return ResponseEntity con status 400
	 */
	@ExceptionHandler({ javax.validation.ConstraintViolationException.class })
	protected ResponseEntity<Error> ConstraintViolationException(javax.validation.ConstraintViolationException e) {
		LOGGER.error("Error de validación", e);
		String mensaje = e.getMessage();
		if(mensaje.contains(",")) {

			mensaje = mensaje.substring(mensaje.indexOf(":")+1,mensaje.indexOf(","));
		}else {
			mensaje = mensaje.substring(mensaje.indexOf(":")+1);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error(mensaje, null));
	}

	/**
	 * Atrapa la excepción arrojada cuando falta un parámetro obligatorio en la
	 * petición GET
	 * 
	 * @param e Excepción que fue arrojada
	 * @return ResponseEntity con status 400
	 */
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	protected ResponseEntity<Error> missingServletRequestParameterException(MissingServletRequestParameterException e) {
		LOGGER.error("Error en petición", e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Error("Parámetro faltante: " + e.getParameterName(), null));
	}

	/**
	 * Metodo para atrapar los errores de base de datos
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ UncategorizedSQLException.class, SQLException.class })
	protected ResponseEntity<Error> sqlException(SQLException e) {
		LOGGER.error("Error en base de datos", e);
		Error error = new Error(e.getMessage(), null);

		if (e.getSQLState() == "45000" || e.getErrorCode() == 1644) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
		}
	}

	/**
	 * Excepción cuando falla algo en alguna petición a otro servicio
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ HttpClientErrorException.class })
	protected ResponseEntity<Error> httpClientErrorException(HttpClientErrorException e) {
		LOGGER.error("Error al consumir servicio", e);
		
		Error error;
		ObjectMapper objectMapper = new ObjectMapper();
		
		if(e.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
			try {
				error = objectMapper.readValue(e.getResponseBodyAsByteArray(), Error.class);
				return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new Error(error.getMensaje(), e.toString()));
			} catch (IOException e1) {
				LOGGER.error("Error de Jackson", e);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body(new Error("Error de Jackson", e1.toString()));
			}
			
		}
		
		if(e.getStatusCode() == HttpStatus.FORBIDDEN) {
			
			error = new Error("Error al consumir servicio",e.toString());
			
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error(error.getMensaje(), e.toString()));
		}
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Error("Ocurrió un error inesperado", e.toString()));
	}
	
	/**
	 * Método encargado de manejar las excepciones genéricas
	 * 
	 * @param e
	 * @return ResponseEntity con status 500
	 */
	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Error> exception(Exception e) {
		LOGGER.error("Error inesperado", e);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new Error("Ocurrió un error inesperado", e.toString()));
	}
}

