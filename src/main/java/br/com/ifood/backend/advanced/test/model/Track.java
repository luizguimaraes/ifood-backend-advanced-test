package br.com.ifood.backend.advanced.test.model;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.core.style.ToStringCreator;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa uma faixa musical. É composta pelo nome da faixa, nome
 * do album e a lista de nomes dos artistas.
 */
public class Track {

	private String name;
	private String albumName;
	private List<String> artists;

	public String getName() {
		return name;
	}

	public String getAlbumName() {
		return albumName;
	}

	public List<String> getArtists() {
		return Collections.unmodifiableList(artists);
	}

	@JsonProperty("album")
	private void unpackAlbum(final Map<String, Object> album) {
		this.albumName = (String) album.get("name");
	}

	@JsonProperty("artists")
	private void unpackArtists(final List<Map<String, Object>> artists) {
		this.artists = artists.stream().map(artist -> (String) artist.get("name")).collect(toList());
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("name", name).append("albumName", albumName).append("artists", artists)
				.toString();
	}
}
