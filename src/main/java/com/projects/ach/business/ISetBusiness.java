/**
 * 
 */
package com.projects.ach.business;

import org.springframework.stereotype.Service;

import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Service

public interface ISetBusiness {

	/**
	 * Start new Set for two player with first game
	 * @param playerName1
	 * @param playerName2
	 * @return created set
	 */
	Set startSet(String playerName1, String playerName2);

	/**
	 * Add new point to winner of set, Add same point to looser of set, and if true put the player like winner of the set
	 * @param set
	 * @param playerWon winner
	 */
	void scorePoint(Set set, Player playerWon);

	/**
	 * if score of the set is 6 vs 6, say that we must pass to tie break
	 * @param set
	 * @return true if score of set is 6 vs 6
	 */
	boolean isPassToTieBreak(Set set);

	/**
	 * is given set has tie break
	 * @param set
	 * @return true if set has tie break
	 */
	boolean hasTieBreak(Set set);

	/**
	 * get current party (game or tie break)
	 * @param set
	 * @return current party
	 */
	AbstractGame getCurrentAbstractGame(Set set);

}
