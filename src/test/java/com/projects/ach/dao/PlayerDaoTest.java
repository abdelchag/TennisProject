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
import com.projects.ach.model.TieBreak;

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

		TieBreak tieBreak = new TieBreak();
		tieBreak.addPointPlayer1(0);
		tieBreak.addPointPlayer2(0);
		tieBreak.setSet(player1.getSet());
		player1.getSet().setTieBreak(tieBreak);

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
	public void testAddPointWinnerGameFirstPoint() {
		playerDao.addPointWinnerGame(player1);
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(2);
		assertThat(Point.P15).isEqualTo(currentGame.getPointsPlayer1().get(1));
	}

	@Test
	public void testAddPointWinnerGameTwoPoint() {
		playerDao.addPointWinnerGame(player1);
		playerDao.addPointWinnerGame(player1);
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(3);
		assertThat(Point.P30).isEqualTo(currentGame.getPointsPlayer1().get(2));
	}

	@Test
	public void testAddPointWinnerGameADV() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.P40);
		currentGame.getPointsPlayer2().add(Point.P40);
		playerDao.addPointWinnerGame(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(Point.PADV).isEqualTo(currentGame.getPointsPlayer1().get(currentGame.getPointsPlayer1().size() - 1));
	}

	@Test
	public void testAddPointWinnerGameADVAfterDEUCE() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.PDEUCE);
		currentGame.getPointsPlayer2().add(Point.PDEUCE);
		playerDao.addPointWinnerGame(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(Point.PADV).isEqualTo(currentGame.getPointsPlayer1().get(currentGame.getPointsPlayer1().size() - 1));
	}

	@Test
	public void testAddPointWinnerGameDEUCE() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.P40);
		currentGame.getPointsPlayer2().add(Point.PADV);
		playerDao.addPointWinnerGame(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(Point.PDEUCE)
				.isEqualTo(currentGame.getPointsPlayer1().get(currentGame.getPointsPlayer1().size() - 1));
	}

	@Test
	public void testAddPointLooserGameFirstPoint() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		playerDao.addPointLooserGame(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(2);
		assertThat(Point.P0).isEqualTo(currentGame.getPointsPlayer1().get(1));
	}

	@Test
	public void testAddPointLooserGameTwoPoint() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		playerDao.addPointLooserGame(player1);
		playerDao.addPointLooserGame(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(3);
		assertThat(Point.P0).isEqualTo(currentGame.getPointsPlayer1().get(2));
	}

	@Test
	public void testAddPointLooserGameDEUCE() {
		Game currentGame = player1.getSet().getGames().get(player1.getSet().getGames().size() - 1);
		currentGame.getPointsPlayer1().add(Point.PADV);
		currentGame.getPointsPlayer2().add(Point.PDEUCE);
		playerDao.addPointLooserGame(player1);
		assertThat(currentGame.getPointsPlayer1()).isNotNull();
		assertThat(currentGame.getPointsPlayer1()).hasSize(3);
		assertThat(Point.PDEUCE).isEqualTo(currentGame.getPointsPlayer1().get(2));
	}

	@Test
	public void testIsWinGameFalse() {
		playerDao.addPointWinnerGame(player1);
		playerDao.addPointWinnerGame(player1);
		boolean isWin = playerDao.isWinGame(player1);
		assertThat(isWin).isFalse();
	}

	@Test
	public void testIsWinGameTrue() {
		playerDao.addPointWinnerGame(player1);
		playerDao.addPointWinnerGame(player1);
		playerDao.addPointWinnerGame(player1);
		playerDao.addPointWinnerGame(player1);
		boolean isWin = playerDao.isWinGame(player1);
		assertThat(isWin).isTrue();
	}

	@Test
	public void testWinScoreSetOne() {
		playerDao.addScoreWinnerSet(player1);
		Set set = player1.getSet();
		assertThat(set.getScoresPlayer1()).hasSize(2);
		assertThat(set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1)).isEqualTo(1);
	}

	@Test
	public void testWinScoreSetTwo() {
		playerDao.addScoreWinnerSet(player1);
		playerDao.addScoreWinnerSet(player1);
		Set set = player1.getSet();
		assertThat(set.getScoresPlayer1()).hasSize(3);
		assertThat(set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1)).isEqualTo(2);
	}

	@Test
	public void testLooseScoreSetOne() {
		playerDao.addScoreLooserSet(player1);
		Set set = player1.getSet();
		assertThat(set.getScoresPlayer1()).hasSize(2);
		assertThat(set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1)).isEqualTo(0);
	}

	@Test
	public void testLooseScoreSetTwo() {
		playerDao.addScoreLooserSet(player1);
		playerDao.addScoreLooserSet(player1);
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

	@Test
	public void testWinSetWithTieBreak() {
		TieBreak tieBreak = new TieBreak();
		tieBreak.setWinner(player1);
		player1.getSet().setTieBreak(tieBreak);
		boolean isWin = playerDao.isWinSet(player1);
		assertThat(isWin).isTrue();
	}

	@Test
	public void testWinScoreTieBreakOne() {
		playerDao.addPointWinnerTieBreak(player1);
		TieBreak tieBreak = player1.getSet().getTieBreak();
		assertThat(tieBreak.getScoresPlayer1()).hasSize(2);
		assertThat(tieBreak.getScoresPlayer1().get(tieBreak.getScoresPlayer1().size() - 1)).isEqualTo(1);
	}

	@Test
	public void testWinScoreTieBreakTwo() {
		playerDao.addPointWinnerTieBreak(player1);
		playerDao.addPointWinnerTieBreak(player1);
		TieBreak tieBreak = player1.getSet().getTieBreak();
		assertThat(tieBreak.getScoresPlayer1()).hasSize(3);
		assertThat(tieBreak.getScoresPlayer1().get(tieBreak.getScoresPlayer1().size() - 1)).isEqualTo(2);
	}

	@Test
	public void testLooseScoreTieBreakOne() {
		playerDao.addPointLooserTieBreak(player1);
		TieBreak tieBreak = player1.getSet().getTieBreak();
		assertThat(tieBreak.getScoresPlayer1()).hasSize(2);
		assertThat(tieBreak.getScoresPlayer1().get(tieBreak.getScoresPlayer1().size() - 1)).isEqualTo(0);
	}

	@Test
	public void testLooseScoreTieBreakTwo() {
		playerDao.addPointLooserTieBreak(player1);
		playerDao.addPointLooserTieBreak(player1);
		TieBreak tieBreak = player1.getSet().getTieBreak();
		assertThat(tieBreak.getScoresPlayer1()).hasSize(3);
		assertThat(tieBreak.getScoresPlayer1().get(tieBreak.getScoresPlayer1().size() - 1)).isEqualTo(0);
	}

	@Test
	public void testIsWinTieBreakWith3To2() {
		player1.getSet().getTieBreak().addPointPlayer1(3);
		player2.getSet().getTieBreak().addPointPlayer2(2);
		boolean isWin = playerDao.isWinTieBreak(player1);
		assertThat(isWin).isFalse();
	}

	@Test
	public void testIsWinTieBreakWith7To5() {
		player1.getSet().getTieBreak().addPointPlayer1(7);
		player2.getSet().getTieBreak().addPointPlayer2(5);
		boolean isWin = playerDao.isWinTieBreak(player1);
		assertThat(isWin).isTrue();
	}

	@Test
	public void testIsWinTieBreakWith7To6() {
		player1.getSet().getTieBreak().addPointPlayer1(7);
		player2.getSet().getTieBreak().addPointPlayer2(6);
		boolean isWin = playerDao.isWinTieBreak(player1);
		assertThat(isWin).isFalse();
	}

	@Test
	public void testIsWinTieBreakWith8To6() {
		player1.getSet().getTieBreak().addPointPlayer1(7);
		player2.getSet().getTieBreak().addPointPlayer2(6);
		boolean isWin = playerDao.isWinTieBreak(player1);
		assertThat(isWin).isFalse();
	}

}
