package br.com.ifood.backend.advanced.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ifood.backend.advanced.test.error.handling.InvalidCityException;
import br.com.ifood.backend.advanced.test.error.handling.InvalidGeodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenWeatherTemperatureServiceTest {

	@Autowired
	private OpenWeatherTemperatureService openWeatherTemperatureService;

	@Test(expected = InvalidGeodingException.class)
	public void testInvalidGeocoding() {
		openWeatherTemperatureService.temperatureByLatLong(1000, 1000);
	}

	@Test
	public void testvalidGeocoding() {
		openWeatherTemperatureService.temperatureByLatLong(0, 0);
	}

	@Test(expected = InvalidCityException.class)
	public void testInvalidCity() {
		openWeatherTemperatureService.temperatureByCityName("");
	}

	@Test
	public void testvalidCity() {
		openWeatherTemperatureService.temperatureByCityName("sao paulo");
	}
}
