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
public interface IAbstractGameBusiness {

	/**
	 * Start new game or tie break in given set
	 * @param set
	 * @return started game or tie break
	 */
	public AbstractGame startGame(Set set);

	/**
	 * Add new point to winner of game/tie break, Add same point to looser of game/tie break, and if true put the player like winner of the game
	 * @param abstractGame game or tie break
	 * @param playerWon winner
	 */
	public void winPoint(AbstractGame abstractGame, Player playerWon);

}
