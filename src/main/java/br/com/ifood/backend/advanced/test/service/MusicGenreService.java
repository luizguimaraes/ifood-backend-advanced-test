package br.com.ifood.backend.advanced.test.service;

import java.util.List;

import br.com.ifood.backend.advanced.test.error.handling.GenreNotFoundException;

/**
 * Serviço responsável por sugerir gêneros musicais.
 */
public interface MusicGenreService {

	/**
	 * Sugere uma lista de gêneros musicais baseado em uma temperatura.
	 * 
	 * @param temperature
	 *            a temperatura base para realizar a sugestão.
	 * @return uma lista de gêneros
	 * @throws GenreNotFoundException
	 *             caso não seja possível sugerir pelo menos um gênero musical
	 *             baseado na temperatura.
	 */
	List<String> musicGenresByTemperature(double temperature) throws GenreNotFoundException;
}
