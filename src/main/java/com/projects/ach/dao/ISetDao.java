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

	Set createSet(Player player1, Player player2);

	void putWinner(Set set, Player playerWon);

	void addNewGame(Set set, Game game);

	Player getPlayer(Set set, String playerName);

	Player getOpponentPlayer(Set set, String playerName);

	Integer getLastScorePlayer(Set set, Player player);

	boolean hasTieBreak(Set set);

	AbstractGame getCurrentAbstractGame(Set set);

}
