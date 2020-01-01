/**
 * 
 */
package com.projects.ach.dao;

import org.springframework.stereotype.Repository;

import com.projects.ach.model.Player;

/**
 * @author ABDELCHAG
 *
 */
@Repository
public interface IPlayerDao {

	/**
	 * Create new player
	 * @param name : player name
	 * @return player
	 */
	Player createPlayer(String name);

	/**
	 * Player win point in the game - add new point to game
	 * @param player : winner of the point
	 */
	void addPointWinnerGame(Player player);

	/**
	 * Player loose point in the game - add same point to game
	 * @param player : looser of the point
	 */
	void addPointLooserGame(Player player);

	/**
	 * Is this player won the last game
	 * @param player : player
	 * @return
	 */
	boolean isWinGame(Player player);

	/**
	 * Player win point in the set - add new point to set
	 * @param player : winner of the point
	 */
	void addScoreWinnerSet(Player player);

	/**
	 * Player loose point in the set - add same point to set
	 * @param player : looser of the point
	 */
	void addScoreLooserSet(Player player);

	/**
	 * Is this player won the set
	 * @param player : player
	 * @return
	 */
	boolean isWinSet(Player player);

	/**
	 * Player win point in the tie break - add new point to tie break
	 * @param player : winner of the point
	 */
	void addPointWinnerTieBreak(Player player);

	/**
	 * Player loose point in the tie break - add same point to tie break
	 * @param player : winner of the point
	 */
	void addPointLooserTieBreak(Player player);

	/**
	 * is this player won the tie break
	 * @param player : player
	 * @return
	 */
	boolean isWinTieBreak(Player player);

}
