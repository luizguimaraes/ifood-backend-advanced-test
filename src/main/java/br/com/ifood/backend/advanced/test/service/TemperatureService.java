package br.com.ifood.backend.advanced.test.service;

import br.com.ifood.backend.advanced.test.error.handling.CannotRetrieveTemperatureException;
import br.com.ifood.backend.advanced.test.error.handling.InvalidCityException;
import br.com.ifood.backend.advanced.test.error.handling.InvalidGeodingException;

/**
 * Servi�o para obten��o da temperatura atual de uma localiza��o.
 */
public interface TemperatureService {

	/**
	 * Retorna a temperatura atual da coordenada em quest�o.
	 * 
	 * @param latitude
	 *            a latitude da coordenada.
	 * @param longitude
	 *            a longitude da coordenada.
	 * @return a temperatura atual da coordenada em quest�o.
	 * @throws InvalidGeodingException
	 *             caso as coordenadas n�o estejam dentro do range -90,-180 e
	 *             90,180.
	 * @throws CannotRetrieveTemperatureException
	 *             caso n�o seja poss�vel obter a temperatura atual para as
	 *             coordenadas.
	 */
	double temperatureByLatLong(double latitude, double longitude)
			throws InvalidGeodingException, CannotRetrieveTemperatureException;

	/**
	 * Retorna a temperatura atual da cidade em quest�o.
	 * 
	 * @param cityName
	 *            nome da cidade a ser consultada.
	 * @return a temperatura atual da cidade em quest�o.
	 * @throws InvalidCityException
	 *             caso n�o seja um nome de cidade v�lido.
	 * @throws CannotRetrieveTemperatureException
	 *             caso n�o seja poss�vel obter a temperatura atual para a cidade.
	 */
	double temperatureByCityName(String cityName) throws InvalidCityException, CannotRetrieveTemperatureException;

}