/**
 * 
 */
package com.projects.ach.dao.impl;

import com.projects.ach.dao.ISetDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
public class SetDaoImpl implements ISetDao {

	@Override
	public Set initSet(Player player1, Player player2) {
		Set set = new Set();
		set.setPlayer1(player1);
		set.setPlayer2(player2);
		set.addScorePlayer1(0);
		set.addScorePlayer2(0);
		return set;
	}

	@Override
	public void putWinner(Set set, Player playerWon) {
		set.setWinner(playerWon);
	}

	@Override
	public void addNewGame(Set set, Game game) {
		set.addGame(game);
	}

	@Override
	public Player getThisPlayer(Set set, String playerName) {
		if (playerName.equalsIgnoreCase(set.getPlayer1().getName())) {
			return set.getPlayer1();
		} else if (playerName.equalsIgnoreCase(set.getPlayer2().getName())) {
			return set.getPlayer2();
		}
		return null;
	}

	@Override
	public Player getOtherPlayer(Set set, String playerName) {
		if (playerName.equalsIgnoreCase(set.getPlayer1().getName())) {
			return set.getPlayer2();
		} else if (playerName.equalsIgnoreCase(set.getPlayer2().getName())) {
			return set.getPlayer1();
		}
		return null;
	}

}
