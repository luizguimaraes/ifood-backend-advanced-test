package br.com.ifood.backend.advanced.test.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifood.backend.advanced.test.model.Playlist;
import br.com.ifood.backend.advanced.test.service.MusicGenreService;
import br.com.ifood.backend.advanced.test.service.PlaylistService;
import br.com.ifood.backend.advanced.test.service.TemperatureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller responsável por expor os serviços de sugestão de playlist.
 */
@Api("Sugestão de Paylist")
@RestController
public class PlaylistSugestionController {

	@Autowired
	private TemperatureService temperatureService;
	@Autowired
	private MusicGenreService playlistNameService;
	@Autowired
	private PlaylistService playlistService;

	/**
	 * Serviço responsável por sugerir uma playlist baseada na temperatura atual de
	 * uma determinada coordenada geografica.
	 * 
	 * @param lat
	 *            a latitude dessa coordenada.
	 * @param lon
	 *            a longitude dessa coordenada.
	 * @return uma playlist com as músicas sugeridas.
	 */
	@ApiOperation(value = "Sugestão de Playlist baseada em coordenadas geográficas", produces = APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Playlist.class),
			@ApiResponse(code = 503, message = "Serviço Indisponível"),
			@ApiResponse(code = 400, message = "Coordenadas Inválidas"),
			@ApiResponse(code = 404, message = "Não foi possível sugerir") })
	@RequestMapping(value = "/latlon", method = GET)
	public Playlist playlistByLatLong(@ApiParam(value = "Latitude", required = true) @RequestParam final double lat,
			@ApiParam(value = "Longitude", required = true) @RequestParam final double lon) {
		return playlistByTemperature(temperatureService.temperatureByLatLong(lat, lon));
	}

	/**
	 * Serviço responsável por sugerir uma playlist baseada na temperatura atual de
	 * uma determinada cidade.
	 * 
	 * @param cityName
	 *            o nome da cidade.
	 * @return uma playlist com as músicas sugeridas.
	 */
	@ApiOperation(value = "Sugestão de Playlist baseada no nome de uma cidade", produces = APPLICATION_JSON_VALUE)
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Playlist.class),
			@ApiResponse(code = 503, message = "Serviço Indisponível"),
			@ApiResponse(code = 400, message = "Cidade Inválida"),
			@ApiResponse(code = 404, message = "Não foi possível sugerir") })
	@RequestMapping(value = "/city", method = GET)
	public Playlist playlistByCityName(
			@ApiParam(value = "Nome da Cidade", required = true) @RequestParam final String cityName) {
		return playlistByTemperature(temperatureService.temperatureByCityName(cityName));
	}

	private Playlist playlistByTemperature(final double temperature) {
		return playlistService.retrievePlaylist(playlistNameService.musicGenresByTemperature(temperature));
	}
}
