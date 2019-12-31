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

	Player initPlayer(String name);

	void addPointWinnerGame(Player player);

	void addPointLooserGame(Player player);

	boolean isWinGame(Player player);

	void addScoreWinnerSet(Player player);

	void addScoreLooserSet(Player player);

	boolean isWinSet(Player player);

	void addPointWinnerTieBreak(Player player);

	void addPointLooserTieBreak(Player player);

	boolean isWinTieBreak(Player player);

}
