package br.com.ifood.backend.advanced.test.error.handling;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception que representa o caso em que não foi possível achar pelo menos um
 * gênero a ser sugerido baseado na temperatura da localização passada.
 */
@ResponseStatus(value = NOT_FOUND, reason = "cannot sugest a playlist for your location.")
public class GenreNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4355297032640815487L;

}
