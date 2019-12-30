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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.projects.ach.business.impl.GameBusinessImpl;
import com.projects.ach.dao.impl.GameDaoImpl;
import com.projects.ach.dao.impl.PlayerDaoImpl;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GameBusinessTest {
	
	@Mock
	private GameDaoImpl gameDao;
	
	private Player player1;
	private Player player2;
	private String playerName1;
	private String playerName2;
	private Game game;
	
	@Before
	public void init(){
		playerName1 = "PlayerTest1";
		playerName2 = "PlayerTest2";
		player1 = new Player();
		player2 = new Player();
		player1.setName(playerName1);
		player2.setName(playerName2);
		
		game = new Game();
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.getPointsPlayer1().add(Point.P0);
		game.getPointsPlayer2().add(Point.P0);
	}
	
	@Mock
	private PlayerDaoImpl playerDao;
	
	@InjectMocks
	private GameBusinessImpl gameBusiness;
	
	@Test
	public void testStartGame(){
		when(playerDao.initPlayer(playerName1)).thenReturn(player1);
		when(playerDao.initPlayer(playerName2)).thenReturn(player2);
		when(gameDao.initGame(player1, player2)).thenReturn(game);
		
		Game gameInit = gameBusiness.startGame(playerName1, playerName2);
		
		assertThat(gameInit).isNotNull();
		assertThat(game.getPlayer1()).isEqualTo(player1);
		assertThat(game.getPlayer2()).isEqualTo(player2);
		assertThat(game.getPointsPlayer1()).hasSize(1);
		assertThat(game.getPointsPlayer2()).hasSize(1);
	}
	
	@Test
	public void testWinPointPlayer1NotGame(){
		when(gameDao.getThisPlayer(game, playerName1)).thenReturn(player1);
		when(gameDao.getOtherPlayer(game, playerName1)).thenReturn(player2);
		when(playerDao.isWinGame(player1)).thenReturn(false);
		
		gameBusiness.winPoint(game, player1);
		
		verify(playerDao, times(1)).addPointWinner(player1);
		verify(playerDao, times(1)).addPointLooser(player2);
		verify(gameDao, never()).putWinner(game, player1);
	}
	
	
	@Test
	public void testWinPointPlayer2NotGame(){
		when(gameDao.getThisPlayer(game, playerName2)).thenReturn(player2);
		when(gameDao.getOtherPlayer(game, playerName2)).thenReturn(player1);
		when(playerDao.isWinGame(player2)).thenReturn(false);
		
		gameBusiness.winPoint(game, player2);
		
		verify(playerDao, times(1)).addPointWinner(player2);
		verify(playerDao, times(1)).addPointLooser(player1);
		verify(gameDao, never()).putWinner(game, player1);
	}

	@Test
	public void testWinPointPlayer1AndGame(){
		when(gameDao.getThisPlayer(game, playerName1)).thenReturn(player1);
		when(gameDao.getOtherPlayer(game, playerName1)).thenReturn(player2);
		when(playerDao.isWinGame(player1)).thenReturn(true);
		
		gameBusiness.winPoint(game, player1);
		
		verify(playerDao, times(1)).addPointWinner(player1);
		verify(playerDao, times(1)).addPointLooser(player2);
		verify(gameDao, times(1)).putWinner(game, player1);
	}
	
	
}
