/**
 * 
 */
package com.projects.ach.dao;

import org.springframework.stereotype.Repository;

import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Repository
public interface IAbstractGameDao {

	/**
	 * Create new game or tie break and attach it to this set
	 * @param set
	 * @return
	 */
	AbstractGame createAbstractGame(Set set);

	/**
	 * get player by name
	 * @param game : game or tie break where to search
	 * @param playerName : name of player
	 * @return player
	 */
	Player getPlayer(AbstractGame game, String playerName);

	/**
	 * get the opponent player by name of player
	 * @param abstractGame game or tie break where to search
	 * @param playerName name of player
	 * @return opponent player
	 */
	Player getOpponentPlayer(AbstractGame abstractGame, String playerName);

	/**
	 * Specify the winner of the game or tie break
	 * @param abstractGame
	 * @param player
	 */
	void putWinner(AbstractGame abstractGame, Player player);
}
