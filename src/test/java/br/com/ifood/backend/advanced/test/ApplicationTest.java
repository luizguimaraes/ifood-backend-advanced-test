package br.com.ifood.backend.advanced.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.ifood.backend.advanced.test.service.OpenWeatherTemperatureServiceTest;
import br.com.ifood.backend.advanced.test.service.SpotifyPlaylistServiceTest;
import br.com.ifood.backend.advanced.test.service.WeatherRepositoryMusicGenreServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ OpenWeatherTemperatureServiceTest.class, SpotifyPlaylistServiceTest.class,
		WeatherRepositoryMusicGenreServiceTest.class })
public class ApplicationTest {

}
