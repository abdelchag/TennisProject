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
public interface IGameDao {

	Game initGame(Set set);

	Player getThisPlayer(Game game, String playerName);

	Player getOtherPlayer(Game game, String playerName);

	void putWinner(Game game, Player player);
}
