package br.com.ifood.backend.advanced.test.error.handling;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception lançada caso ocorra algum erro ao acessar o serviço de sugestão de
 * playlist.
 */
@ResponseStatus(value = SERVICE_UNAVAILABLE, reason = "cannot retrieve music for the sugested playlist.")
public class CannotRetrieveMusicListException extends RuntimeException {

	private static final long serialVersionUID = -1695381603381879279L;

}
