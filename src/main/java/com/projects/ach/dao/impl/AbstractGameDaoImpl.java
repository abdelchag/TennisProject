/**
 * 
 */
package com.projects.ach.dao.impl;

import com.projects.ach.dao.IAbstractGameDao;
import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Player;

/**
 * @author ABDELCHAG
 *
 */
public abstract class AbstractGameDaoImpl implements IAbstractGameDao {

	@Override
	public Player getPlayer(AbstractGame game, String playerName) {
		if (playerName.equalsIgnoreCase(game.getPlayer1().getName())) {
			return game.getPlayer1();
		} else if (playerName.equalsIgnoreCase(game.getPlayer2().getName())) {
			return game.getPlayer2();
		}
		return null;
	}

	@Override
	public Player getOpponentPlayer(AbstractGame game, String playerName) {
		if (playerName.equalsIgnoreCase(game.getPlayer1().getName())) {
			return game.getPlayer2();
		} else if (playerName.equalsIgnoreCase(game.getPlayer2().getName())) {
			return game.getPlayer1();
		}
		return null;
	}

	@Override
	public void putWinner(AbstractGame game, Player player) {
		game.setWinner(player);

	}

}
