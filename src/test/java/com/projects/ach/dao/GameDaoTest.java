/**
 * 
 */
package com.projects.ach.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.projects.ach.dao.impl.GameDaoImpl;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GameDaoTest extends AbstractGameDaoInitTest {

	@InjectMocks
	private GameDaoImpl gameDao;

	@Test
	public void testCreateGame() {

		Game game = gameDao.createAbstractGame(set);

		assertThat(game.getPlayer1().getName()).isEqualTo(playerName1);
		assertThat(game.getPlayer2().getName()).isEqualTo(playerName2);
		assertThat(game.getPointsPlayer1()).hasSize(1);
		assertThat(game.getPointsPlayer2()).hasSize(1);
		assertThat(game.getPointsPlayer1().get(0)).isEqualTo(Point.P0);
		assertThat(game.getPointsPlayer2().get(0)).isEqualTo(Point.P0);

	}

	@Test
	public void testGetThisPlayerExist() {
		Game game = gameDao.createAbstractGame(set);
		Player player = gameDao.getPlayer(game, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player1);
	}

	@Test
	public void testGetThisPlayerNotExist() {
		Game game = gameDao.createAbstractGame(set);
		Player player = gameDao.getPlayer(game, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testGetOtherPlayerExist() {
		Game game = gameDao.createAbstractGame(set);
		Player player = gameDao.getOpponentPlayer(game, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player2);
	}

	@Test
	public void testGetOtherPlayerNotExist() {
		Game game = gameDao.createAbstractGame(set);
		Player player = gameDao.getPlayer(game, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testPutWinner() {
		Game game = gameDao.createAbstractGame(set);
		gameDao.putWinner(game, player1);

		assertThat(game.getWinner()).isEqualTo(player1);
	}

}
