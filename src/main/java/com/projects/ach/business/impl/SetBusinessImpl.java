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
		Player player1 = playerDao.initPlayer(playerName1);
		Player player2 = playerDao.initPlayer(playerName2);
		Set set = setDao.initSet(player1, player2);
		gameDao.initAbstractGame(set);
		return set;
	}

	@Override
	public void scorePoint(Set set, Player playerWon) {
		Player playerWonSet = setDao.getThisPlayer(set, playerWon.getName());
		Player playerLooseSet = setDao.getOtherPlayer(set, playerWon.getName());

		playerDao.addScoreWinnerSet(playerWonSet);
		playerDao.addScoreLooserSet(playerLooseSet);

		if (playerDao.isWinSet(playerWonSet)) {
			setDao.putWinner(set, playerWonSet);
		}
	}

	@Override
	public boolean isPassToTieBreak(Set set) {
		Integer lastScore1 = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
		Integer lastScore2 = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		return lastScore1 == 6 && lastScore2 == 6;
	}

}
