package br.com.ifood.backend.advanced.test.service;

import static com.github.benmanes.caffeine.cache.Caffeine.newBuilder;
import static java.util.concurrent.TimeUnit.MINUTES;
import static org.springframework.http.HttpMethod.GET;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.benmanes.caffeine.cache.LoadingCache;

import br.com.ifood.backend.advanced.test.error.handling.CannotRetrieveMusicListException;
import br.com.ifood.backend.advanced.test.model.Playlist;
import br.com.ifood.backend.advanced.test.model.Track;

/**
 * Implementação de {@link PlaylistService} que obtém sugestões de músicas
 * através da API do Spotify.
 */
@Service
class SpotifyPlaylistService implements PlaylistService {

	private static final Logger logger = LoggerFactory.getLogger(SpotifyPlaylistService.class);

	@Value("${spotify.urlAuthorization}")
	private String urlAuthorization;

	@Value("${spotify.urlGenre}")
	private String urlGenre;

	@Value("${spotify.clientIdAndSecret}")
	private String clientIdAndSecret;

	@Autowired
	private RestTemplate restTemplate;

	private HttpEntity<?> authorizationRequest;

	private final LoadingCache<HttpEntity<?>, String> authorizationTokenCache = newBuilder().maximumSize(1)
			.expireAfterWrite(1, MINUTES).build(key -> authorizationToken());

	@PostConstruct
	public void init() {
		authorizationRequest = authorizationRequest();
	}

	/**
	 * Utiliza o serviço de recomendação do Spotify para sugerir {@link Track}s.
	 * 
	 * @see https://developer.spotify.com/web-api/console/get-recommendations/
	 */
	@Override
	@Cacheable("spotify")
	public Playlist retrievePlaylist(final List<String> playlistNames) throws CannotRetrieveMusicListException {
		logger.debug("Finding musics for genres: {}.", playlistNames);
		try {
			final Playlist playlist = restTemplate
					.exchange(urlGenre, GET, new HttpEntity<>(headers()), Playlist.class, uriVariables(playlistNames))
					.getBody();

			logger.debug("Found playlist for genres {}: {}.", playlistNames, playlist);

			return playlist;
		} catch (final HttpClientErrorException e) {
			logger.error("Exception while retrieving music list.", e);

			throw new CannotRetrieveMusicListException();
		}
	}

	private HttpHeaders headers() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + authorizationTokenCache.get(authorizationRequest));
		return headers;
	}

	private Map<String, Object> uriVariables(final List<String> name) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("genre", String.join(",", name));
		return uriVariables;
	}

	private String authorizationToken() {
		return (String) restTemplate.postForEntity(urlAuthorization, authorizationRequest, Map.class).getBody()
				.get("access_token");
	}

	/**
	 * Obtém o token de acesso ao serviços do Spotify.
	 * 
	 * @see https://developer.spotify.com/web-api/authorization-guide/ Client
	 *      Credentials Flow
	 */
	private HttpEntity<?> authorizationRequest() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + clientIdAndSecret);

		final MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "client_credentials");
		return new HttpEntity<>(body, headers);
	}
}
