package br.com.ifood.backend.advanced.test.service;

import java.util.List;

import br.com.ifood.backend.advanced.test.error.handling.CannotRetrieveMusicListException;
import br.com.ifood.backend.advanced.test.model.Playlist;
import br.com.ifood.backend.advanced.test.model.Track;

/**
 * Servi�o respons�vel por sugerir {@link Playlist}.
 */
public interface PlaylistService {

	/**
	 * Sugere uma {@link Playlist} baseado em uma lista de g�neros.
	 * 
	 * @param genreNames
	 *            uma lista de g�neros musicais.
	 * 
	 * @return uma {@link Playlist} com {@link Track}s dos g�neros passados.
	 * @throws CannotRetrieveMusicListException
	 *             caso n�o seja poss�vel obter uma {@link Playlist}.
	 */
	Playlist retrievePlaylist(List<String> genreNames) throws CannotRetrieveMusicListException;

}
