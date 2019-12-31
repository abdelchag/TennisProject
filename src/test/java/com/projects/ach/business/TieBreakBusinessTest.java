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

import com.projects.ach.business.impl.TieBreakBusinessImpl;
import com.projects.ach.dao.impl.PlayerDaoImpl;
import com.projects.ach.dao.impl.TieBreakDaoImpl;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TieBreakBusinessTest {

	@Mock
	private TieBreakDaoImpl tieBreakDao;

	@Mock
	private PlayerDaoImpl playerDao;

	@InjectMocks
	private TieBreakBusinessImpl tieBreakBusiness;

	private Player player1;
	private Player player2;
	private String playerName1;
	private String playerName2;
	private TieBreak tieBreak;
	private Set set;

	@Before
	public void init() {
		playerName1 = "PlayerTest1";
		playerName2 = "PlayerTest2";
		player1 = new Player();
		player2 = new Player();
		player1.setName(playerName1);
		player2.setName(playerName2);

		tieBreak = new TieBreak();
		tieBreak.getScoresPlayer1().add(0);
		tieBreak.getScoresPlayer2().add(0);

		set = new Set();
		set.setPlayer1(player1);
		set.setPlayer2(player2);
		set.setTieBreak(tieBreak);
		tieBreak.setSet(set);
		set.getScoresPlayer1().add(0);
		set.getScoresPlayer2().add(0);

	}

	@Test
	public void testStarttieBreak() {
		when(tieBreakDao.initAbstractGame(set)).thenReturn(tieBreak);

		TieBreak tieBreakInit = tieBreakBusiness.startGame(set);

		assertThat(tieBreakInit).isNotNull();
		assertThat(tieBreak.getPlayer1()).isEqualTo(player1);
		assertThat(tieBreak.getPlayer2()).isEqualTo(player2);
		assertThat(tieBreak.getScoresPlayer1()).hasSize(1);
		assertThat(tieBreak.getScoresPlayer2()).hasSize(1);
	}

	@Test
	public void testWinPointPlayer1NottieBreak() {
		when(tieBreakDao.getThisPlayer(tieBreak, playerName1)).thenReturn(player1);
		when(tieBreakDao.getOtherPlayer(tieBreak, playerName1)).thenReturn(player2);
		when(playerDao.isWinTieBreak(player1)).thenReturn(false);

		tieBreakBusiness.winPoint(tieBreak, player1);

		verify(playerDao, times(1)).addPointWinnerTieBreak(player1);
		verify(playerDao, times(1)).addPointLooserTieBreak(player2);
		verify(tieBreakDao, never()).putWinner(tieBreak, player1);
	}

	@Test
	public void testWinPointPlayer2NottieBreak() {
		when(tieBreakDao.getThisPlayer(tieBreak, playerName2)).thenReturn(player2);
		when(tieBreakDao.getOtherPlayer(tieBreak, playerName2)).thenReturn(player1);
		when(playerDao.isWinTieBreak(player2)).thenReturn(false);

		tieBreakBusiness.winPoint(tieBreak, player2);

		verify(playerDao, times(1)).addPointWinnerTieBreak(player2);
		verify(playerDao, times(1)).addPointLooserTieBreak(player1);
		verify(tieBreakDao, never()).putWinner(tieBreak, player1);
	}

	@Test
	public void testWinPointPlayer1AndtieBreak() {
		when(tieBreakDao.getThisPlayer(tieBreak, playerName1)).thenReturn(player1);
		when(tieBreakDao.getOtherPlayer(tieBreak, playerName1)).thenReturn(player2);
		when(playerDao.isWinTieBreak(player1)).thenReturn(true);

		tieBreakBusiness.winPoint(tieBreak, player1);

		verify(playerDao, times(1)).addPointWinnerTieBreak(player1);
		verify(playerDao, times(1)).addPointLooserTieBreak(player2);
		verify(tieBreakDao, times(1)).putWinner(tieBreak, player1);
	}

}
