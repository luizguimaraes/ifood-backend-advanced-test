package br.com.ifood.backend.advanced.test.error.handling;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada caso as coordadas geográficas não sejam válidas.
 */
@ResponseStatus(value = BAD_REQUEST, reason = "invalid geocoding.")
public class InvalidGeodingException extends RuntimeException {

	private static final long serialVersionUID = 45066194634682485L;

}
