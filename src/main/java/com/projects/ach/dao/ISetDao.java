/**
 * 
 */
package com.projects.ach.dao;

import org.springframework.stereotype.Repository;

import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Repository
public interface ISetDao {

	/**
	 * Create new Set for given two player
	 * @param player1
	 * @param player2
	 * @return created set
	 */
	Set createSet(Player player1, Player player2);

	/**
	 * Specify the winner of the set
	 * @param set
	 * @param playerWon winner
	 */
	void putWinner(Set set, Player playerWon);

	/**
	 * Add new game to set
	 * @param set
	 * @param game
	 */
	void addNewGame(Set set, Game game);

	/**
	 * get player by given name
	 * @param set where to search
	 * @param playerName
	 * @return player
	 */
	Player getPlayer(Set set, String playerName);

	/**
	 * get opponent by given player name
	 * @param set
	 * @param playerName
	 * @return opponent player
	 */
	Player getOpponentPlayer(Set set, String playerName);

	/**
	 * Get last score of given player
	 * @param set
	 * @param player
	 * @return last score (point)
	 */
	Integer getLastScorePlayer(Set set, Player player);

	/**
	 * is given set has tie break
	 * @param set
	 * @return
	 */
	boolean hasTieBreak(Set set);

	/**
	 * get current party (game or tie break) of given set
	 * @param set
	 * @return
	 */
	AbstractGame getCurrentAbstractGame(Set set);

}
