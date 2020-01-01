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
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "tieBreakBusiness")
public class TieBreakBusinessImpl implements IAbstractGameBusiness {

	@Autowired
	private IPlayerDao playerDao;

	@Autowired
	@Qualifier(value = "tieBreakDao")
	private IAbstractGameDao tieBreakDao;

	@Override
	public TieBreak startGame(Set set) {
		TieBreak tieBreak = (TieBreak) tieBreakDao.createAbstractGame(set);
		return tieBreak;
	}

	@Override
	public void winPoint(AbstractGame abstractGame, Player playerWon) {
		Player playerWonGame = tieBreakDao.getPlayer(abstractGame, playerWon.getName());
		Player playerLooseGame = tieBreakDao.getOpponentPlayer(abstractGame, playerWon.getName());

		playerDao.addPointWinnerTieBreak(playerWonGame);
		playerDao.addPointLooserTieBreak(playerLooseGame);

		if (playerDao.isWinTieBreak(playerWonGame)) {
			tieBreakDao.putWinner(abstractGame, playerWonGame);
		}

	}

}
