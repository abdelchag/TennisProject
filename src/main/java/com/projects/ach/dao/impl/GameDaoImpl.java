/**
 * 
 */
package com.projects.ach.dao.impl;

import com.projects.ach.dao.IGameDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;

/**
 * @author ABDELCHAG
 *
 */
public class GameDaoImpl implements IGameDao {

	@Override
	public Game initGame(Player player1, Player player2) {
		Game game = new Game();
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		game.getPointsPlayer1().add(Point.P0);
		game.getPointsPlayer2().add(Point.P0);
		return game;
	}

	@Override
	public Player getThisPlayer(Game game, String playerName) {
		if(playerName.equalsIgnoreCase(game.getPlayer1().getName())){
			return game.getPlayer1();
		}else if(playerName.equalsIgnoreCase(game.getPlayer2().getName())){
			return game.getPlayer2();
		}
		return null;
	}

	@Override
	public Player getOtherPlayer(Game game, String playerName) {
		if(playerName.equalsIgnoreCase(game.getPlayer1().getName())){
			return game.getPlayer2();
		}else if(playerName.equalsIgnoreCase(game.getPlayer2().getName())){
			return game.getPlayer1();
		}
		return null;
	}

	@Override
	public void putWinner(Game game, Player player) {
		game.setWinner(player);
		
	}

}
