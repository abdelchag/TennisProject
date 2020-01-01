/**
 * 
 */
package com.projects.ach.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.projects.ach.business.IAbstractGameBusiness;
import com.projects.ach.dao.IAbstractGameDao;
import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "gameBusiness")
public class GameBusinessImpl implements IAbstractGameBusiness {

	@Autowired
	private IPlayerDao playerDao;

	@Autowired
	@Qualifier(value = "gameDao")
	private IAbstractGameDao gameDao;

	@Override
	public Game startGame(Set set) {
		Game game = (Game) gameDao.createAbstractGame(set);
		return game;
	}

	@Override
	public void winPoint(AbstractGame abstractGame, Player playerWon) {
		Player playerWonGame = gameDao.getPlayer(abstractGame, playerWon.getName());
		Player playerLooseGame = gameDao.getOpponentPlayer(abstractGame, playerWon.getName());

		playerDao.addPointWinnerGame(playerWonGame);
		playerDao.addPointLooserGame(playerLooseGame);

		if (playerDao.isWinGame(playerWonGame)) {
			gameDao.putWinner(abstractGame, playerWonGame);
		}
	}

}
