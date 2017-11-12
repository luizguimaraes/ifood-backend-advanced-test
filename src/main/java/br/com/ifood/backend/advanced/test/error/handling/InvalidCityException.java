package br.com.ifood.backend.advanced.test.error.handling;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada caso o nome da cidade não seja válido.
 */
@ResponseStatus(value = BAD_REQUEST, reason = "city not found.")
public class InvalidCityException extends RuntimeException {

	private static final long serialVersionUID = 4406882654503213525L;

}
