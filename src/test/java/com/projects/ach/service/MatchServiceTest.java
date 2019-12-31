/**
 * 
 */
package com.projects.ach.service;

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

import com.projects.ach.business.impl.GameBusinessImpl;
import com.projects.ach.business.impl.SetBusinessImpl;
import com.projects.ach.business.impl.TieBreakBusinessImpl;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;
import com.projects.ach.service.impl.MatchServiceImpl;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class MatchServiceTest {

	@Mock
	private GameBusinessImpl gameBusiness;

	@Mock
	private TieBreakBusinessImpl tieBreakBusiness;

	@Mock
	private SetBusinessImpl setBusiness;

	@InjectMocks
	private MatchServiceImpl matchService;

	private String playerName1;
	private String playerName2;
	private Set set;
	private Player player1;
	private Player player2;
	private Game game;
	private TieBreak tieBreak;

	@Before
	public void init() {
		playerName1 = "playerName1";
		playerName2 = "playerName2";
		player1 = new Player();
		player1.setName(playerName1);
		player2 = new Player();
		player2.setName(playerName2);

		game = new Game();
		game.addPointPlayer1(Point.P0);
		game.addPointPlayer2(Point.P0);

		set = new Set();
		set.setPlayer1(player1);
		set.setPlayer2(player2);
		set.addGame(game);
		set.addScorePlayer1(0);
		set.addScorePlayer2(0);

		tieBreak = new TieBreak();
		tieBreak.addPointPlayer1(0);
		tieBreak.addPointPlayer2(0);
	}

	@Test
	public void testStartMatch() {
		when(setBusiness.startSet(playerName1, playerName2)).thenReturn(set);
		Set started = matchService.startMatch(playerName1, playerName2);

		assertThat(started).isEqualTo(set);

	}

	@Test
	public void testScorePointGameSimple() {

		// when(setBusiness.isPassToTieBreak(set)).thenReturn(false);

		boolean isFinish = matchService.scorePoint(set, player1);

		verify(gameBusiness, times(1)).winPoint(game, player1);
		verify(tieBreakBusiness, never()).winPoint(tieBreak, player1);
		verify(setBusiness, never()).scorePoint(set, player1);
		verify(setBusiness, never()).isPassToTieBreak(set);
		verify(gameBusiness, never()).startGame(set);
		verify(tieBreakBusiness, never()).startGame(set);
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointTieBreakSimple() {

		set.setTieBreak(tieBreak);

		boolean isFinish = matchService.scorePoint(set, player1);

		verify(gameBusiness, never()).winPoint(game, player1);
		verify(tieBreakBusiness, times(1)).winPoint(tieBreak, player1);
		verify(setBusiness, never()).scorePoint(set, player1);
		verify(setBusiness, never()).isPassToTieBreak(set);
		verify(gameBusiness, never()).startGame(set);
		verify(tieBreakBusiness, never()).startGame(set);
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointGameWinnerStartGame() {
		game.setWinner(player1);

		when(setBusiness.isPassToTieBreak(set)).thenReturn(false);

		boolean isFinish = matchService.scorePoint(set, player1);

		verify(gameBusiness, times(1)).winPoint(game, player1);
		verify(tieBreakBusiness, never()).winPoint(tieBreak, player1);
		verify(setBusiness, times(1)).scorePoint(set, player1);
		verify(setBusiness, times(1)).isPassToTieBreak(set);
		verify(gameBusiness, times(1)).startGame(set);
		verify(tieBreakBusiness, never()).startGame(set);
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointGameWinnerStartTieBreak() {
		game.setWinner(player1);

		when(setBusiness.isPassToTieBreak(set)).thenReturn(true);

		boolean isFinish = matchService.scorePoint(set, player1);

		verify(gameBusiness, times(1)).winPoint(game, player1);
		verify(tieBreakBusiness, never()).winPoint(tieBreak, player1);
		verify(setBusiness, times(1)).scorePoint(set, player1);
		verify(setBusiness, times(1)).isPassToTieBreak(set);
		verify(gameBusiness, never()).startGame(set);
		verify(tieBreakBusiness, times(1)).startGame(set);
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointGameWinnerSetWinner() {
		game.setWinner(player1);
		set.setWinner(player1);

		boolean isFinish = matchService.scorePoint(set, player1);

		verify(gameBusiness, times(1)).winPoint(game, player1);
		verify(tieBreakBusiness, never()).winPoint(tieBreak, player1);
		verify(setBusiness, times(1)).scorePoint(set, player1);
		verify(setBusiness, never()).isPassToTieBreak(set);
		verify(gameBusiness, never()).startGame(set);
		verify(tieBreakBusiness, never()).startGame(set);
		assertThat(isFinish).isTrue();
	}

	@Test
	public void testScorePointTieBreakWinner() {

		tieBreak.setWinner(player1);
		set.setWinner(player1);
		set.setTieBreak(tieBreak);

		boolean isFinish = matchService.scorePoint(set, player1);

		verify(gameBusiness, never()).winPoint(game, player1);
		verify(tieBreakBusiness, times(1)).winPoint(tieBreak, player1);
		verify(setBusiness, times(1)).scorePoint(set, player1);
		verify(setBusiness, never()).isPassToTieBreak(set);
		verify(gameBusiness, never()).startGame(set);
		verify(tieBreakBusiness, never()).startGame(set);
		assertThat(isFinish).isTrue();
	}

}
