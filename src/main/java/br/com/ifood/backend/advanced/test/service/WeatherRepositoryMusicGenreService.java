package br.com.ifood.backend.advanced.test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import br.com.ifood.backend.advanced.test.error.handling.GenreNotFoundException;
import br.com.ifood.backend.advanced.test.repository.WeatherRepository;

/**
 * Implementação de {@link MusicGenreService} que utiliza
 * {@link WeatherRepository} para sugerir gêneros.
 */
@Component
class WeatherRepositoryMusicGenreService implements MusicGenreService {

	private static final Logger logger = LoggerFactory.getLogger(WeatherRepositoryMusicGenreServiceTest.class);

	@Autowired
	private WeatherRepository weatherRepository;

	@Override
	@Cacheable("musicGenres")
	public List<String> musicGenresByTemperature(final double temperature) {
		logger.debug("Findind music genres for temperature {}", temperature);

		final List<String> playlistNames = weatherRepository.findMusicGenreByTemperature(temperature);
		if (playlistNames.isEmpty()) {
			logger.debug("No music genres found for temperature {}.", temperature);

			throw new GenreNotFoundException();
		}

		logger.debug("Music genres found for temperature {}: {}", temperature, playlistNames);

		return playlistNames;
	}

}
