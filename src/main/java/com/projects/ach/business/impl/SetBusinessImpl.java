/**
 * 
 */
package com.projects.ach.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.projects.ach.business.ISetBusiness;
import com.projects.ach.dao.IGameDao;
import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.dao.ISetDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
public class SetBusinessImpl implements ISetBusiness {

	@Autowired
	private IPlayerDao playerDao;

	@Autowired
	private IGameDao gameDao;

	@Autowired
	private ISetDao setDao;

	@Override
	public Set startSet(String playerName1, String playerName2) {
		Player player1 = playerDao.initPlayer(playerName1);
		Player player2 = playerDao.initPlayer(playerName2);
		Set set = setDao.initSet(player1, player2);
		Game firstGame = gameDao.initGame(set);
		set.addGame(firstGame);
		return set;
	}

	@Override
	public void scorePoint(Set set, Player playerWon) {
		Player playerWonSet = setDao.getThisPlayer(set, playerWon.getName());
		Player playerLooseSet = setDao.getOtherPlayer(set, playerWon.getName());

		playerDao.addScoreWinner(playerWonSet);
		playerDao.addScoreLooser(playerLooseSet);

		if (playerDao.isWinSet(playerWonSet)) {
			setDao.putWinner(set, playerWonSet);
		}
	}

}
