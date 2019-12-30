/**
 * 
 */
package com.projects.ach.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.projects.ach.business.IGameBusiness;
import com.projects.ach.dao.IGameDao;
import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
public class GameBusinessImpl implements IGameBusiness {

	@Autowired
	private IPlayerDao playerDao;

	@Autowired
	private IGameDao gameDao;

	@Override
	public Game startGame(Set set) {
		Game game = gameDao.initGame(set);
		return game;
	}

	@Override
	public void winPoint(Game game, Player playerWon) {
		Player playerWonGame = gameDao.getThisPlayer(game, playerWon.getName());
		Player playerLooseGame = gameDao.getOtherPlayer(game, playerWon.getName());

		playerDao.addPointWinner(playerWonGame);
		playerDao.addPointLooser(playerLooseGame);

		if (playerDao.isWinGame(playerWonGame)) {
			gameDao.putWinner(game, playerWonGame);
		}
	}

}
