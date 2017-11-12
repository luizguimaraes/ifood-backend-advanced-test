package br.com.ifood.backend.advanced.test.service;

import java.util.List;

import br.com.ifood.backend.advanced.test.error.handling.GenreNotFoundException;

/**
 * Servi�o respons�vel por sugerir g�neros musicais.
 */
public interface MusicGenreService {

	/**
	 * Sugere uma lista de g�neros musicais baseado em uma temperatura.
	 * 
	 * @param temperature
	 *            a temperatura base para realizar a sugest�o.
	 * @return uma lista de g�neros
	 * @throws GenreNotFoundException
	 *             caso n�o seja poss�vel sugerir pelo menos um g�nero musical
	 *             baseado na temperatura.
	 */
	List<String> musicGenresByTemperature(double temperature) throws GenreNotFoundException;
}
