/**
 * 
 */
package com.projects.ach.dao;

import org.springframework.stereotype.Repository;

import com.projects.ach.model.Game;
import com.projects.ach.model.Player;

/**
 * @author ABDELCHAG
 *
 */
@Repository
public interface IGameDao {
	
	Game initGame(Player player1, Player player2);
	
	Player getThisPlayer(Game game, String playerName);
	
	Player getOtherPlayer(Game game, String playerName);

}
