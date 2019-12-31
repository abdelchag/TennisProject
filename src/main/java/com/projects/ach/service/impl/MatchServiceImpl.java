/**
 * 
 */
package com.projects.ach.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.projects.ach.business.IAbstractGameBusiness;
import com.projects.ach.business.ISetBusiness;
import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;
import com.projects.ach.service.IMatchService;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "matchService")
public class MatchServiceImpl implements IMatchService {

	@Autowired
	@Qualifier(value = "gameBusiness")
	private IAbstractGameBusiness gameBusiness;

	@Autowired
	@Qualifier(value = "tieBreakBusiness")
	private IAbstractGameBusiness tieBreakBusiness;

	@Autowired
	private ISetBusiness setBusiness;

	@Override
	public Set startMatch(String playerName1, String playerName2) {
		Set set = setBusiness.startSet(playerName1, playerName2);
		return set;
	}

	@Override
	public boolean scorePoint(Set set, Player scorer) {
		boolean isTieBreak = set.getTieBreak() != null;
		AbstractGame currentGame = set.getCurrentGame();
		IAbstractGameBusiness currentGameBusiness = isTieBreak ? tieBreakBusiness : gameBusiness;

		currentGameBusiness.winPoint(currentGame, scorer);

		if (currentGame.getWinner() != null) {
			setBusiness.scorePoint(set, scorer);
			if (set.getWinner() != null) {
				return true;
			} else {
				IAbstractGameBusiness nextGameBusiness = setBusiness.isPassToTieBreak(set) ? tieBreakBusiness
						: gameBusiness;
				nextGameBusiness.startGame(set);
			}
		}
		return false;
	}

}
