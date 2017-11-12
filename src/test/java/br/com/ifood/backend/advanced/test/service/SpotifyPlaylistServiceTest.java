package br.com.ifood.backend.advanced.test.service;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpotifyPlaylistServiceTest {

	@Autowired
	private SpotifyPlaylistService spotifyPlaylistService;

	@Test
	public void testClassical() {
		assertFalse(spotifyPlaylistService.retrievePlaylist(singletonList("classical")).getTracks().isEmpty());
	}

	@Test
	public void testRock() {
		assertFalse(spotifyPlaylistService.retrievePlaylist(singletonList("rock")).getTracks().isEmpty());
	}

	@Test
	public void testPop() {
		assertFalse(spotifyPlaylistService.retrievePlaylist(singletonList("pop")).getTracks().isEmpty());
	}

	@Test
	public void testParty() {
		assertFalse(spotifyPlaylistService.retrievePlaylist(singletonList("party")).getTracks().isEmpty());
	}
}
