/**
 * 
 */
package com.projects.ach.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.projects.ach.business.impl.SetBusinessImpl;
import com.projects.ach.dao.impl.GameDaoImpl;
import com.projects.ach.dao.impl.PlayerDaoImpl;
import com.projects.ach.dao.impl.SetDaoImpl;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class SetBusinessTest {

	@Mock
	private PlayerDaoImpl playerDao;

	@Mock
	private GameDaoImpl gameDao;

	@Mock
	private SetDaoImpl setDao;

	@InjectMocks
	private SetBusinessImpl setBusiness;

	private Player player1;
	private Player player2;
	private Game game;
	private Set set;
	private String playerName1;
	private String playerName2;

	@Before
	public void init() {
		playerName1 = "playerName1";
		playerName2 = "playerName2";
		player1 = new Player();
		player1.setName(playerName1);
		player2 = new Player();
		player2.setName(playerName2);
		set = new Set();
		set.setPlayer1(player1);
		set.setPlayer2(player2);
		set.addScorePlayer1(0);
		set.addScorePlayer2(0);
		game = new Game();
		game.addPointPlayer1(Point.P0);
		game.addPointPlayer2(Point.P0);
		set.addGame(game);
	}

	@Test
	public void testStartSet() {
		when(playerDao.initPlayer(playerName1)).thenReturn(player1);
		when(playerDao.initPlayer(playerName2)).thenReturn(player2);
		when(setDao.initSet(player1, player2)).thenReturn(set);
		when(gameDao.initAbstractGame(set)).thenReturn(game);

		Set setStarted = setBusiness.startSet(playerName1, playerName2);

		assertThat(setStarted).isEqualTo(set);
		assertThat(set.getPlayer1()).isEqualTo(player1);
		assertThat(set.getPlayer2()).isEqualTo(player2);
		assertThat(set.getCurrentGame()).isEqualTo(game);
	}

	@Test
	public void testScorePointPlayer1NotSet() {
		when(setDao.getThisPlayer(set, playerName1)).thenReturn(player1);
		when(setDao.getOtherPlayer(set, playerName1)).thenReturn(player2);
		when(playerDao.isWinSet(player1)).thenReturn(false);

		setBusiness.scorePoint(set, player1);

		verify(playerDao, times(1)).addScoreWinnerSet(player1);
		verify(playerDao, times(1)).addScoreLooserSet(player2);
		verify(setDao, never()).putWinner(set, player1);
	}

	@Test
	public void testScorePointPlayer2NotSet() {
		when(setDao.getThisPlayer(set, playerName2)).thenReturn(player2);
		when(setDao.getOtherPlayer(set, playerName2)).thenReturn(player1);
		when(playerDao.isWinSet(player2)).thenReturn(false);

		setBusiness.scorePoint(set, player2);

		verify(playerDao, times(1)).addScoreWinnerSet(player2);
		verify(playerDao, times(1)).addScoreLooserSet(player1);
		verify(setDao, never()).putWinner(set, player2);
	}

	@Test
	public void testScorePointPlayer1AndSet() {
		when(setDao.getThisPlayer(set, playerName1)).thenReturn(player1);
		when(setDao.getOtherPlayer(set, playerName1)).thenReturn(player2);
		when(playerDao.isWinSet(player1)).thenReturn(true);

		setBusiness.scorePoint(set, player1);

		verify(playerDao, times(1)).addScoreWinnerSet(player1);
		verify(playerDao, times(1)).addScoreLooserSet(player2);
		verify(setDao, times(1)).putWinner(set, player1);
	}

}
