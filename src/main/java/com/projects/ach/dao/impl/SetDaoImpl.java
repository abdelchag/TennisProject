/**
 * 
 */
package com.projects.ach.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.ach.dao.ISetDao;
import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;
import com.projects.ach.utils.TennisUtils;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "setDao")
public class SetDaoImpl implements ISetDao {

	@Override
	public Set createSet(Player player1, Player player2) {
		Set set = new Set();
		set.setPlayer1(player1);
		set.setPlayer2(player2);
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
	public Player getPlayer(Set set, String playerName) {
		if (playerName.equalsIgnoreCase(set.getPlayer1().getName())) {
			return set.getPlayer1();
		} else if (playerName.equalsIgnoreCase(set.getPlayer2().getName())) {
			return set.getPlayer2();
		}
		return null;
	}

	@Override
	public Player getOpponentPlayer(Set set, String playerName) {
		if (playerName.equalsIgnoreCase(set.getPlayer1().getName())) {
			return set.getPlayer2();
		} else if (playerName.equalsIgnoreCase(set.getPlayer2().getName())) {
			return set.getPlayer1();
		}
		return null;
	}

	@Override
	public Integer getLastScorePlayer(Set set, Player player) {
		List<Integer> scores = TennisUtils.getScorePlayerSet(set, player);
		return scores.get(scores.size() - 1);
	}

	@Override
	public boolean hasTieBreak(Set set) {
		return set.getTieBreak() != null;
	}

	@Override
	public AbstractGame getCurrentAbstractGame(Set set) {
		return set.getCurrentAbstractGame();
	}

}
