package br.com.ifood.backend.advanced.test.model;

import java.util.Collections;
import java.util.List;

import org.springframework.core.style.ToStringCreator;

/**
 * Classe que representa o resultado da sugestão. Uma playlist é definida como
 * uma lista de {@link Track}s.
 */
public class Playlist {

	private List<Track> tracks;

	public List<Track> getTracks() {
		return Collections.unmodifiableList(tracks);
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("tracks", tracks).toString();
	}
}
