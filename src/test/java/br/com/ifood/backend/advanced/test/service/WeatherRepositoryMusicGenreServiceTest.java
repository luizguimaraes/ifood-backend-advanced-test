package br.com.ifood.backend.advanced.test.service;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifood.backend.advanced.test.error.handling.GenreNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherRepositoryMusicGenreServiceTest {

	@Autowired
	private WeatherRepositoryMusicGenreService weatherRepositoryMusicGenreService;

	@Test(expected = GenreNotFoundException.class)
	public void testBelowAbsoluteZero() {
		weatherRepositoryMusicGenreService.musicGenresByTemperature(-273.16);
	}

	@Test
	public void testClassical() {
		Assert.assertEquals(weatherRepositoryMusicGenreService.musicGenresByTemperature(0), Arrays.asList("classical"));
	}

	@Test
	public void testRock() {
		Assert.assertEquals(weatherRepositoryMusicGenreService.musicGenresByTemperature(10), Arrays.asList("rock"));
	}

	@Test
	public void testPop() {
		Assert.assertEquals(weatherRepositoryMusicGenreService.musicGenresByTemperature(20), Arrays.asList("pop"));
	}

	@Test
	public void testParty() {
		Assert.assertEquals(weatherRepositoryMusicGenreService.musicGenresByTemperature(30), Arrays.asList("party"));
	}

	@Test(expected = GenreNotFoundException.class)
	public void testMoreThanAbsoluteHot() {
		weatherRepositoryMusicGenreService.musicGenresByTemperature(100000000000000000000000000000001d);
	}
}
