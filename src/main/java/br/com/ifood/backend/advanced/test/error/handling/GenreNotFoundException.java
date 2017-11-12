package br.com.ifood.backend.advanced.test.error.handling;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception que representa o caso em que n�o foi poss�vel achar pelo menos um
 * g�nero a ser sugerido baseado na temperatura da localiza��o passada.
 */
@ResponseStatus(value = NOT_FOUND, reason = "cannot sugest a playlist for your location.")
public class GenreNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 4355297032640815487L;

}
