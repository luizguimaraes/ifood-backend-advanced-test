package br.com.ifood.backend.advanced.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ifood.backend.advanced.test.model.Weather;

/**
 * Repositório de {@link Weather}.
 */
@Repository
public interface WeatherRepository extends PagingAndSortingRepository<Weather, Long> {

	/**
	 * Retorna uma lista de gêneros musicais em que o parâmetro temperature esteja
	 * dentro das faixas de temperatura de {@link Weather}.
	 * 
	 * @param temperature
	 *            temperatura a ser consultada.
	 * @return uma lista de gêneros musicais em que o parâmetro temperature esteja
	 *         dentro das faixas de temperatura de {@link Weather}.
	 */
	@Query("select w.musicGenre from Weather w where w.temperatureIntervalStart <= :temperature and w.temperatureIntervalEnd > :temperature order by name")
	List<String> findMusicGenreByTemperature(@Param("temperature") double temperature);
}
