/**
 * 
 */
package com.projects.ach.dao.impl;

import java.util.List;

import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;

/**
 * @author ABDELCHAG
 *
 */
public class PlayerDaoImpl implements IPlayerDao {

	@Override
	public Player initPlayer(String name) {
		Player player = new Player();
		player.setName(name);
		player.setGame(new Game());
		player.getGame().setPlayer1(player);
		player.getGame().getPointsPlayer1().add(Point.P0);
		return player;
	}

	@Override
	public void addPointWinner(Player player) {
		Game game = player.getGame();
		List<Point> pointPlayerWon = null;
		if (game.getPlayer1().getName().equals(player.getName())) {
			pointPlayerWon = game.getPointsPlayer1();
		} else {
			pointPlayerWon = game.getPointsPlayer2();
		}

		Point lastPointWinner = pointPlayerWon.get(pointPlayerWon.size() - 1);
		pointPlayerWon.add(lastPointWinner.getNextPoint());
	}

	@Override
	public void addPointLooser(Player player) {
		Game game = player.getGame();
		List<Point> pointPlayerLoose = null;
		if (game.getPlayer1().getName().equals(player.getName())) {
			pointPlayerLoose = game.getPointsPlayer1();
		} else {
			pointPlayerLoose = game.getPointsPlayer2();
		}

		Point lastPointLooser = pointPlayerLoose.get(pointPlayerLoose.size() - 1);
		pointPlayerLoose.add(lastPointLooser);

	}

	@Override
	public boolean isWinGame(Player player) {
		Game game = player.getGame();
		List<Point> pointPlayerWon = null;
		if (game.getPlayer1().getName().equals(player.getName())) {
			pointPlayerWon = game.getPointsPlayer1();
		} else {
			pointPlayerWon = game.getPointsPlayer2();
		}
		return pointPlayerWon.get(pointPlayerWon.size() - 1).equals(Point.PWIN);
	}

}
