package br.com.ifood.backend.advanced.test.error.handling;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada caso ocorra algum erro ao acessar o serviço de temperatura.
 */
@ResponseStatus(value = SERVICE_UNAVAILABLE, reason = "cannot retrieve temperature for your location.")
public class CannotRetrieveTemperatureException extends RuntimeException {

	private static final long serialVersionUID = -4356655320705620462L;

}
