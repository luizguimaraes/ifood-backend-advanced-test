package br.com.ifood.backend.advanced.test.service;

import static java.lang.Double.parseDouble;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.ifood.backend.advanced.test.error.handling.CannotRetrieveTemperatureException;
import br.com.ifood.backend.advanced.test.error.handling.InvalidCityException;
import br.com.ifood.backend.advanced.test.error.handling.InvalidGeodingException;

/**
 * Implementação de {@link TemperatureService} utilizando a API do
 * openWeatherMap.
 * 
 * @see https://openweathermap.org/current
 */
@Service
class OpenWeatherTemperatureService implements TemperatureService {

	private static final Logger logger = LoggerFactory.getLogger(OpenWeatherTemperatureService.class);

	@Value("${openweathermap.urlLatLong}")
	private String urlLatLong;

	@Value("${openweathermap.urlCity}")
	private String urlCity;

	@Value("${openweathermap.apiId}")
	private String apiId;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@Cacheable("openWeather")
	public double temperatureByLatLong(final double latitude, final double longitude)
			throws InvalidGeodingException, CannotRetrieveTemperatureException {
		if (latitude > 90 || latitude < -90 || longitude > 180 || longitude < -180) {
			logger.error("Invalid coordinates: {},{}", latitude, longitude);

			throw new InvalidGeodingException();
		}

		logger.debug("Retrieving temperatures for coordinates: {},{}.", latitude, longitude);

		try {
			final double temperature = retrieveTemperature(
					restTemplate.getForEntity(urlLatLong, Map.class, createUriVariables(latitude, longitude)));

			logger.debug("Temperature {} for coordinates {},{}", temperature, latitude, longitude);

			return temperature;
		} catch (final HttpClientErrorException e) {
			logger.error("Cannot retrieve temperatures for coordinates.", e);

			throw new CannotRetrieveTemperatureException();
		}
	}

	@Override
	@Cacheable("openWeather")
	public double temperatureByCityName(final String cityName)
			throws InvalidCityException, CannotRetrieveTemperatureException

	{
		if (cityName == null || cityName.isEmpty()) {
			logger.error("City name is null or empty.");

			throw new InvalidCityException();
		}

		logger.debug("Retrieving temperatures for city: {}.", cityName);

		try {
			double temperature = retrieveTemperature(
					restTemplate.getForEntity(urlCity, Map.class, createUriVariables(cityName)));

			logger.debug("Temperature {} for city {}", temperature, cityName);

			return temperature;
		} catch (final HttpClientErrorException e) {
			logger.error("Cannot retrieve temperatures for city.", e);

			if (e.getStatusCode() == HttpStatus.BAD_REQUEST) {
				throw new InvalidCityException();
			}
			throw new CannotRetrieveTemperatureException();
		}
	}

	private Map<String, Object> createUriVariables(final double latitude, final double longitude) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("lat", latitude);
		uriVariables.put("lon", longitude);
		uriVariables.put("apiId", apiId);
		return uriVariables;
	}

	@SuppressWarnings("unchecked")
	private double retrieveTemperature(@SuppressWarnings("rawtypes") final ResponseEntity<Map> entity) {
		return parseDouble(((Map<String, ?>) entity.getBody().get("main")).get("temp").toString());
	}

	private Map<String, Object> createUriVariables(final String cityName) {
		final Map<String, Object> uriVariables = new HashMap<>();
		uriVariables.put("cityName", cityName);
		uriVariables.put("apiId", apiId);
		return uriVariables;
	}

}
