/**
 * 
 */
package com.projects.ach.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.projects.ach.dao.impl.GameDaoImpl;
import com.projects.ach.dao.impl.PlayerDaoImpl;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PlayerDaoTest {

	@InjectMocks
	private PlayerDaoImpl playerDao;

	@InjectMocks
	private GameDaoImpl gameDao;

	private Player player1;
	private Player player2;

	@Before
	public void init() {
		String playerTest1 = "PlayerTest1";
		String playerTest2 = "PlayerTest2";
		player1 = playerDao.initPlayer(playerTest1);
		player2 = playerDao.initPlayer(playerTest2);
		player1.getSet().setScoresPlayer2(new LinkedList<>());
		player1.getSet().getScoresPlayer2().add(0);
		player1.getSet().setPlayer2(player2);

		Game game = new Game();
		game.getPointsPlayer1().add(Point.P0);
		game.getPointsPlayer2().add(Point.P0);
		player1.getSet().addGame(game);

	}

	@Test
	public void testInitPlayer() {
		String playerTest = "PlayerTest";
		Player player = playerDao.initPlayer(playerTest);
		assertThat(player.getName()).isEqualTo(playerTest);
		assertThat(player.getSet()).isNotNull();
		assertThat(player.getSet().getPlayer1()).isEqualTo(player);
	}

	@Test
	public void testAddPointWinnerFirstPoint() {
		playerDao.addPointWinner(player1);
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(2);
		assertThat(Point.P15).isEqualTo(currentGame.getPointsPlayer1().get(1));
	}

	@Test
	public void testAddPointWinnerTwoPoint() {
		playerDao.addPointWinner(player1);
		playerDao.addPointWinner(player1);
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(3);
		assertThat(Point.P30).isEqualTo(currentGame.getPointsPlayer1().get(2));
	}

	@Test
	public void testAddPointWinnerADV() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.P40);
		currentGame.getPointsPlayer2().add(Point.P40);
		playerDao.addPointWinner(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(Point.PADV).isEqualTo(currentGame.getPointsPlayer1().get(currentGame.getPointsPlayer1().size() - 1));
	}

	@Test
	public void testAddPointWinnerADVAfterDEUCE() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.PDEUCE);
		currentGame.getPointsPlayer2().add(Point.PDEUCE);
		playerDao.addPointWinner(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(Point.PADV).isEqualTo(currentGame.getPointsPlayer1().get(currentGame.getPointsPlayer1().size() - 1));
	}

	@Test
	public void testAddPointWinnerDEUCE() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.P40);
		currentGame.getPointsPlayer2().add(Point.PADV);
		playerDao.addPointWinner(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(Point.PDEUCE)
				.isEqualTo(currentGame.getPointsPlayer1().get(currentGame.getPointsPlayer1().size() - 1));
	}

	@Test
	public void testAddPointLooserFirstPoint() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		playerDao.addPointLooser(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(2);
		assertThat(Point.P0).isEqualTo(currentGame.getPointsPlayer1().get(1));
	}

	@Test
	public void testAddPointLooserTwoPoint() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		playerDao.addPointLooser(player1);
		playerDao.addPointLooser(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(3);
		assertThat(Point.P0).isEqualTo(currentGame.getPointsPlayer1().get(2));
	}

	@Test
	public void testAddPointLooserDEUCE() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.PADV);
		currentGame.getPointsPlayer2().add(Point.PDEUCE);
		playerDao.addPointLooser(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(3);
		assertThat(Point.PDEUCE).isEqualTo(currentGame.getPointsPlayer1().get(2));
	}

	@Test
	public void testIsWinGameFalse() {
		playerDao.addPointWinner(player1);
		playerDao.addPointWinner(player1);
		boolean isWin = playerDao.isWinGame(player1);
		assertThat(isWin).isFalse();
	}

	@Test
	public void testIsWinGameTrue() {
		playerDao.addPointWinner(player1);
		playerDao.addPointWinner(player1);
		playerDao.addPointWinner(player1);
		playerDao.addPointWinner(player1);
		boolean isWin = playerDao.isWinGame(player1);
		assertThat(isWin).isTrue();
	}

	@Test
	public void testWinScoreOne() {
		playerDao.addScoreWinner(player1);
		Set set = player1.getSet();
		assertThat(set.getScoresPlayer1()).hasSize(2);
		assertThat(set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1)).isEqualTo(1);
	}

	@Test
	public void testWinScoreTwo() {
		playerDao.addScoreWinner(player1);
		playerDao.addScoreWinner(player1);
		Set set = player1.getSet();
		assertThat(set.getScoresPlayer1()).hasSize(3);
		assertThat(set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1)).isEqualTo(2);
	}

	@Test
	public void testLooseScoreOne() {
		playerDao.addScoreLooser(player1);
		Set set = player1.getSet();
		assertThat(set.getScoresPlayer1()).hasSize(2);
		assertThat(set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1)).isEqualTo(0);
	}

	@Test
	public void testLooseScoreTwo() {
		playerDao.addScoreLooser(player1);
		playerDao.addScoreLooser(player1);
		Set set = player1.getSet();
		assertThat(set.getScoresPlayer1()).hasSize(3);
		assertThat(set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1)).isEqualTo(0);
	}

	@Test
	public void testIsWinSetWith7To6() {
		player1.getSet().addScorePlayer1(7);
		player2.getSet().addScorePlayer2(6);
		boolean isWin = playerDao.isWinSet(player1);
		assertThat(isWin).isTrue();
	}

	@Test
	public void testIsWinSetWith6To2() {
		player1.getSet().addScorePlayer1(6);
		player2.getSet().addScorePlayer2(2);
		boolean isWin = playerDao.isWinSet(player1);
		assertThat(isWin).isTrue();
	}

	@Test
	public void testIsWinSetWith3To2() {
		player1.getSet().addScorePlayer1(3);
		player2.getSet().addScorePlayer2(2);
		boolean isWin = playerDao.isWinSet(player1);
		assertThat(isWin).isFalse();
	}

}
