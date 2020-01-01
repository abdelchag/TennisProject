/**
 * 
 */
package com.projects.ach.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projects.ach.business.ISetBusiness;
import com.projects.ach.dao.IAbstractGameDao;
import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.dao.ISetDao;
import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "setBusiness")
public class SetBusinessImpl implements ISetBusiness {

	@Autowired
	private IPlayerDao playerDao;

	@Autowired
	private IAbstractGameDao gameDao;

	@Autowired
	private ISetDao setDao;

	@Override
	public Set startSet(String playerName1, String playerName2) {
		Player player1 = playerDao.createPlayer(playerName1);
		Player player2 = playerDao.createPlayer(playerName2);
		Set set = setDao.createSet(player1, player2);
		gameDao.createAbstractGame(set); // this ass game to set
		return set;
	}

	@Override
	public void scorePoint(Set set, Player playerWon) {
		Player playerWonSet = setDao.getPlayer(set, playerWon.getName());
		Player playerLooseSet = setDao.getOpponentPlayer(set, playerWon.getName());

		playerDao.addScoreWinnerSet(playerWonSet);
		playerDao.addScoreLooserSet(playerLooseSet);

		if (playerDao.isWinSet(playerWonSet)) {
			setDao.putWinner(set, playerWonSet);
		}
	}

	@Override
	public boolean isPassToTieBreak(Set set) {
		Integer lastScore1 = setDao.getLastScorePlayer(set, set.getPlayer1());
		Integer lastScore2 = setDao.getLastScorePlayer(set, set.getPlayer2());
		return lastScore1 == 6 && lastScore2 == 6;
	}

	@Override
	public boolean hasTieBreak(Set set) {
		return setDao.hasTieBreak(set);
	}

	@Override
	public AbstractGame getCurrentAbstractGame(Set set) {
		return setDao.getCurrentAbstractGame(set);
	}

}
