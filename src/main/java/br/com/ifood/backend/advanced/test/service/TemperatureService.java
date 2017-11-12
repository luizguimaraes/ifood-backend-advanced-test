package br.com.ifood.backend.advanced.test.service;

import br.com.ifood.backend.advanced.test.error.handling.CannotRetrieveTemperatureException;
import br.com.ifood.backend.advanced.test.error.handling.InvalidCityException;
import br.com.ifood.backend.advanced.test.error.handling.InvalidGeodingException;

/**
 * Serviço para obtenção da temperatura atual de uma localização.
 */
public interface TemperatureService {

	/**
	 * Retorna a temperatura atual da coordenada em questão.
	 * 
	 * @param latitude
	 *            a latitude da coordenada.
	 * @param longitude
	 *            a longitude da coordenada.
	 * @return a temperatura atual da coordenada em questão.
	 * @throws InvalidGeodingException
	 *             caso as coordenadas não estejam dentro do range -90,-180 e
	 *             90,180.
	 * @throws CannotRetrieveTemperatureException
	 *             caso não seja possível obter a temperatura atual para as
	 *             coordenadas.
	 */
	double temperatureByLatLong(double latitude, double longitude)
			throws InvalidGeodingException, CannotRetrieveTemperatureException;

	/**
	 * Retorna a temperatura atual da cidade em questão.
	 * 
	 * @param cityName
	 *            nome da cidade a ser consultada.
	 * @return a temperatura atual da cidade em questão.
	 * @throws InvalidCityException
	 *             caso não seja um nome de cidade válido.
	 * @throws CannotRetrieveTemperatureException
	 *             caso não seja possível obter a temperatura atual para a cidade.
	 */
	double temperatureByCityName(String cityName) throws InvalidCityException, CannotRetrieveTemperatureException;

}