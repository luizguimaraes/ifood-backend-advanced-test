package br.com.ifood.backend.advanced.test.service;

import java.util.List;

import br.com.ifood.backend.advanced.test.error.handling.CannotRetrieveMusicListException;
import br.com.ifood.backend.advanced.test.model.Playlist;
import br.com.ifood.backend.advanced.test.model.Track;

/**
 * Serviço responsável por sugerir {@link Playlist}.
 */
public interface PlaylistService {

	/**
	 * Sugere uma {@link Playlist} baseado em uma lista de gêneros.
	 * 
	 * @param genreNames
	 *            uma lista de gêneros musicais.
	 * 
	 * @return uma {@link Playlist} com {@link Track}s dos gêneros passados.
	 * @throws CannotRetrieveMusicListException
	 *             caso não seja possível obter uma {@link Playlist}.
	 */
	Playlist retrievePlaylist(List<String> genreNames) throws CannotRetrieveMusicListException;

}
