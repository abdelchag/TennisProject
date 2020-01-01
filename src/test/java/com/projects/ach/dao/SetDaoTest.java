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

import com.projects.ach.dao.impl.SetDaoImpl;
import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SetDaoTest {

	@InjectMocks
	private SetDaoImpl setDao;

	private Player player1;
	private Player player2;
	private String playerName1;
	private String playerName2;

	@Before
	public void init() {
		playerName1 = "playerTest1";
		playerName2 = "playerTest2";
		player1 = new Player();
		player1.setName(playerName1);
		player2 = new Player();
		player2.setName(playerName2);
	}

	@Test
	public void testInitSet() {
		Set set = setDao.createSet(player1, player2);
		assertThat(playerName1).isEqualTo(set.getPlayer1().getName());
		assertThat(playerName2).isEqualTo(set.getPlayer2().getName());
		assertThat(0).isEqualTo(set.getScoresPlayer1().get(0));
		assertThat(0).isEqualTo(set.getScoresPlayer2().get(0));
	}

	@Test
	public void testPutWinner() {
		Set set = setDao.createSet(player1, player2);
		setDao.putWinner(set, player1);
		assertThat(set.getWinner()).isNotNull();
		assertThat(set.getWinner()).isEqualTo(player1);
	}

	@Test
	public void testAddNewGame() {
		Set set = setDao.createSet(player1, player2);
		Game game = new Game();
		setDao.addNewGame(set, game);

		assertThat(set.getGames()).hasSize(1);
		assertThat(set.getCurrentAbstractGame()).isEqualTo(game);
	}

	@Test
	public void testGetThisPlayerExist() {
		Set set = setDao.createSet(player1, player2);
		Player player = setDao.getPlayer(set, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player1);
	}

	@Test
	public void testGetThisPlayerNotExist() {

		Set set = setDao.createSet(player1, player2);
		Player player = setDao.getPlayer(set, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testGetOtherPlayerExist() {
		Set set = setDao.createSet(player1, player2);
		Player player = setDao.getOpponentPlayer(set, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player2);
	}

	@Test
	public void testGetOtherPlayerNotExist() {
		Set set = setDao.createSet(player1, player2);
		Player player = setDao.getPlayer(set, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testGetLastScorePlayer0() {
		Set set = setDao.createSet(player1, player2);
		Integer score = setDao.getLastScorePlayer(set, player1);
		assertThat(score).isEqualTo(0);
	}

	@Test
	public void testGetLastScorePlayer5() {
		Set set = setDao.createSet(player1, player2);
		set.addScorePlayer1(5);
		Integer score = setDao.getLastScorePlayer(set, player1);
		assertThat(score).isEqualTo(5);
	}

	@Test
	public void testHasTieBreakNo() {
		Set set = setDao.createSet(player1, player2);
		boolean hasTieBreak = setDao.hasTieBreak(set);
		assertThat(hasTieBreak).isFalse();
	}

	@Test
	public void testHasTieBreakYes() {
		Set set = setDao.createSet(player1, player2);
		set.setTieBreak(new TieBreak());
		boolean hasTieBreak = setDao.hasTieBreak(set);
		assertThat(hasTieBreak).isTrue();
	}

	@Test
	public void testGetCurrentAbstractGameGame() {
		Set set = setDao.createSet(player1, player2);
		Game game = new Game();
		set.addGame(game);
		AbstractGame currentGame = setDao.getCurrentAbstractGame(set);
		assertThat(currentGame).isInstanceOf(Game.class);
		assertThat(currentGame).isEqualTo(game);
	}

	@Test
	public void testGetCurrentAbstractGameTieBreak() {
		Set set = setDao.createSet(player1, player2);
		Game game = new Game();
		set.addGame(game);
		TieBreak tieBreak = new TieBreak();
		set.setTieBreak(tieBreak);
		AbstractGame currentGame = setDao.getCurrentAbstractGame(set);
		assertThat(currentGame).isInstanceOf(TieBreak.class);
		assertThat(currentGame).isEqualTo(tieBreak);
	}

}
