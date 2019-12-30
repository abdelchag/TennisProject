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

	@Test
	public void testInitPlayer() {
		String playerTest = "PlayerTest";
		Player player = playerDao.initPlayer(playerTest);
		assertThat(player.getName()).isEqualTo(playerTest);
		assertThat(player.getGame()).isNotNull();
		assertThat(player.getGame().getPlayer1()).isEqualTo(player);
	}
	
	@Test
	public void testAddPointWinnerFirstPoint(){
		String playerTest = "PlayerTest";
		Player player = playerDao.initPlayer(playerTest);
		playerDao.addPointWinner(player);
		assertThat(player.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player.getGame().getPointsPlayer1()).hasSize(2);
		assertThat(Point.P15).isEqualTo(player.getGame().getPointsPlayer1().get(1));
	}
	
	
	@Test
	public void testAddPointWinnerTwoPoint(){
		String playerTest = "PlayerTest";
		Player player = playerDao.initPlayer(playerTest);
		playerDao.addPointWinner(player);
		playerDao.addPointWinner(player);
		assertThat(player.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player.getGame().getPointsPlayer1()).hasSize(3);
		assertThat(Point.P30).isEqualTo(player.getGame().getPointsPlayer1().get(2));
	}
	
	@Test
	public void testAddPointLooserFirstPoint(){
		String playerTest = "PlayerTest";
		Player player = playerDao.initPlayer(playerTest);
		playerDao.addPointLooser(player);
		assertThat(player.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player.getGame().getPointsPlayer1()).hasSize(2);
		assertThat(Point.P0).isEqualTo(player.getGame().getPointsPlayer1().get(1));
	}
	
	@Test
	public void testAddPointLooserTwoPoint(){
		String playerTest = "PlayerTest";
		Player player = playerDao.initPlayer(playerTest);
		playerDao.addPointLooser(player);
		playerDao.addPointLooser(player);
		assertThat(player.getGame().getPointsPlayer1()).isNotNull();
		assertThat(player.getGame().getPointsPlayer1()).hasSize(3);
		assertThat(Point.P0).isEqualTo(player.getGame().getPointsPlayer1().get(2));
	}
	
}
