/**
 * 
 */
package com.projects.ach.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.projects.ach.dao.impl.GameDaoImpl;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GameDaoTest {

	@InjectMocks
	private GameDaoImpl gameDao;

	private Player player1;
	private Player player2;
	private String playerName1;
	private String playerName2;

	private Set set;

	@Before
	public void init() {
		playerName1 = "PlayerTest1";
		playerName2 = "PlayerTest2";
		player1 = new Player();
		player2 = new Player();
		player1.setName(playerName1);
		player2.setName(playerName2);

		set = new Set();
		set.setPlayer1(player1);
		set.setPlayer2(player2);
		set.getScoresPlayer1().add(0);
		set.getScoresPlayer2().add(0);

	}

	@Test
	public void testInitGame() {

		Game game = gameDao.initGame(set);

		assertThat(game.getPlayer1().getName()).isEqualTo(playerName1);
		assertThat(game.getPlayer2().getName()).isEqualTo(playerName2);
		assertThat(game.getPointsPlayer1()).hasSize(1);
		assertThat(game.getPointsPlayer2()).hasSize(1);
		assertThat(game.getPointsPlayer1().get(0)).isEqualTo(Point.P0);
		assertThat(game.getPointsPlayer2().get(0)).isEqualTo(Point.P0);

	}

	@Test
	public void testGetThisPlayerExist() {
		Game game = gameDao.initGame(set);
		Player player = gameDao.getThisPlayer(game, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player1);
	}

	@Test
	public void testGetThisPlayerNotExist() {
		Game game = gameDao.initGame(set);
		Player player = gameDao.getThisPlayer(game, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testGetOtherPlayerExist() {
		Game game = gameDao.initGame(set);
		Player player = gameDao.getOtherPlayer(game, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player2);
	}

	@Test
	public void testGetOtherPlayerNotExist() {
		Game game = gameDao.initGame(set);
		Player player = gameDao.getThisPlayer(game, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testPutWinner() {
		Game game = gameDao.initGame(set);
		gameDao.putWinner(game, player1);

		assertThat(game.getWinner()).isEqualTo(player1);
	}

}
