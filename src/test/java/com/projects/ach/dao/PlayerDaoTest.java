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
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;

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
		player1.getGame().setPlayer2(player2);
		player1.getGame().setPointsPlayer2(new LinkedList<>());
		player1.getGame().getPointsPlayer2().add(Point.P0);
		player2.setGame(player1.getGame());
	}

	@Test
	public void testInitPlayer() {
		String playerTest = "PlayerTest";
		Player player = playerDao.initPlayer(playerTest);
		assertThat(player.getName()).isEqualTo(playerTest);
		assertThat(player.getGame()).isNotNull();
		assertThat(player.getGame().getPlayer1()).isEqualTo(player);
	}

	@Test
	public void testAddPointWinnerFirstPoint() {
		playerDao.addPointWinner(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player1.getGame().getPointsPlayer1()).hasSize(2);
		assertThat(Point.P15).isEqualTo(player1.getGame().getPointsPlayer1().get(1));
	}

	@Test
	public void testAddPointWinnerTwoPoint() {
		playerDao.addPointWinner(player1);
		playerDao.addPointWinner(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player1.getGame().getPointsPlayer1()).hasSize(3);
		assertThat(Point.P30).isEqualTo(player1.getGame().getPointsPlayer1().get(2));
	}

	@Test
	public void testAddPointWinnerADV() {
		player1.getGame().getPointsPlayer1().add(Point.P40);
		player1.getGame().getPointsPlayer2().add(Point.P40);
		playerDao.addPointWinner(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(Point.PADV)
				.isEqualTo(player1.getGame().getPointsPlayer1().get(player1.getGame().getPointsPlayer1().size() - 1));
	}
	
	@Test
	public void testAddPointWinnerADVAfterDEUCE() {
		player1.getGame().getPointsPlayer1().add(Point.PDEUCE);
		player1.getGame().getPointsPlayer2().add(Point.PDEUCE);
		playerDao.addPointWinner(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(Point.PADV)
				.isEqualTo(player1.getGame().getPointsPlayer1().get(player1.getGame().getPointsPlayer1().size() - 1));
	}
	
	@Test
	public void testAddPointWinnerDEUCE() {
		player1.getGame().getPointsPlayer1().add(Point.P40);
		player1.getGame().getPointsPlayer2().add(Point.PADV);
		playerDao.addPointWinner(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(Point.PDEUCE)
				.isEqualTo(player1.getGame().getPointsPlayer1().get(player1.getGame().getPointsPlayer1().size() - 1));
	}

	@Test
	public void testAddPointLooserFirstPoint() {
		playerDao.addPointLooser(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player1.getGame().getPointsPlayer1()).hasSize(2);
		assertThat(Point.P0).isEqualTo(player1.getGame().getPointsPlayer1().get(1));
	}

	@Test
	public void testAddPointLooserTwoPoint() {
		playerDao.addPointLooser(player1);
		playerDao.addPointLooser(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player1.getGame().getPointsPlayer1()).hasSize(3);
		assertThat(Point.P0).isEqualTo(player1.getGame().getPointsPlayer1().get(2));
	}
	
	@Test
	public void testAddPointLooserDEUCE() {
		player1.getGame().getPointsPlayer1().add(Point.PADV);
		player1.getGame().getPointsPlayer2().add(Point.PDEUCE);
		playerDao.addPointLooser(player1);
		assertThat(player1.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player1.getGame().getPointsPlayer1()).hasSize(3);
		assertThat(Point.PDEUCE).isEqualTo(player1.getGame().getPointsPlayer1().get(2));
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

}
