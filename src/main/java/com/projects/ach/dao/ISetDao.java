/**
 * 
 */
package com.projects.ach.dao;

import org.springframework.stereotype.Repository;

import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Repository
public interface ISetDao {

	Set initSet(Player player1, Player player2);

	void putWinner(Set set, Player playerWon);

	void addNewGame(Set set, Game game);

	Player getThisPlayer(Set set, String playerName);

	Player getOtherPlayer(Set set, String playerName);

}
