/**
 * 
 */
package com.projects.ach.dao;

import org.junit.Before;

import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
public class AbstractGameDaoInitTest {

	protected Player player1;
	protected Player player2;
	protected String playerName1;
	protected String playerName2;

	protected Set set;

	@Before
	public void init() {
		playerName1 = "PlayerTest1";
		playerName2 = "PlayerTest2";
		player1 = new Player();
		player2 = new Player();
		player1.setName(playerName1);
		player2.setName(playerName2);

		set = new Set();
		set.setPlayer1(player1);
		set.setPlayer2(player2);
		set.getScoresPlayer1().add(0);
		set.getScoresPlayer2().add(0);

	}

}
