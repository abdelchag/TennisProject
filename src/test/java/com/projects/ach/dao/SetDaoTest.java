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
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

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
		Set set = setDao.initSet(player1, player2);
		assertThat(playerName1).isEqualTo(set.getPlayer1().getName());
		assertThat(playerName2).isEqualTo(set.getPlayer2().getName());
		assertThat(0).isEqualTo(set.getScoresPlayer1().get(0));
		assertThat(0).isEqualTo(set.getScoresPlayer2().get(0));
	}

	@Test
	public void testPutWinner() {
		Set set = setDao.initSet(player1, player2);
		setDao.putWinner(set, player1);
		assertThat(set.getWinner()).isNotNull();
		assertThat(set.getWinner()).isEqualTo(player1);
	}

	@Test
	public void testAddNewGame() {
		Set set = setDao.initSet(player1, player2);
		Game game = new Game();
		setDao.addNewGame(set, game);

		assertThat(set.getGames()).hasSize(1);
		assertThat(set.getCurrentGame()).isEqualTo(game);
	}

	@Test
	public void testGetThisPlayerExist() {
		Set set = setDao.initSet(player1, player2);
		Player player = setDao.getThisPlayer(set, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player1);
	}

	@Test
	public void testGetThisPlayerNotExist() {

		Set set = setDao.initSet(player1, player2);
		Player player = setDao.getThisPlayer(set, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testGetOtherPlayerExist() {
		Set set = setDao.initSet(player1, player2);
		Player player = setDao.getOtherPlayer(set, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player2);
	}

	@Test
	public void testGetOtherPlayerNotExist() {
		Set set = setDao.initSet(player1, player2);
		Player player = setDao.getThisPlayer(set, "PlayerTest3");
		assertThat(player).isNull();
	}

}
