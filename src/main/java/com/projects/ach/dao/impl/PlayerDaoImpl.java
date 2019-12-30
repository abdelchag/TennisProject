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

		List<Point> pointsPlayerWon = null;
		List<Point> pointsPlayerLoose = null;
		if (game.getPlayer1().getName().equals(player.getName())) {
			pointsPlayerWon = game.getPointsPlayer1();
			pointsPlayerLoose = game.getPointsPlayer2();
		} else {
			pointsPlayerWon = game.getPointsPlayer2();
			pointsPlayerLoose = game.getPointsPlayer1();
		}

		Point lastPointWinner = pointsPlayerWon.get(pointsPlayerWon.size() - 1);
		Point lastPointLooser = pointsPlayerLoose.get(pointsPlayerLoose.size() - 1);
		if ((lastPointWinner == Point.P40 && lastPointLooser == Point.P40)
				|| (lastPointWinner == Point.PDEUCE && lastPointLooser == Point.PDEUCE)) {
			pointsPlayerWon.add(Point.PADV);
		} else if (lastPointWinner == Point.PADV) {
			pointsPlayerWon.add(Point.PWIN);
		} else if (lastPointWinner == Point.P40 && lastPointLooser == Point.PADV) {
			pointsPlayerWon.add(Point.PDEUCE);
		} else {
			pointsPlayerWon.add(lastPointWinner.getNextPoint());
		}
	}

	@Override
	public void addPointLooser(Player player) {
		Game game = player.getGame();
		List<Point> pointsPlayerLoose = null;
		List<Point> pointsPlayerWon = null;
		if (game.getPlayer1().getName().equals(player.getName())) {
			pointsPlayerLoose = game.getPointsPlayer1();
			pointsPlayerWon = game.getPointsPlayer2();
		} else {
			pointsPlayerLoose = game.getPointsPlayer2();
			pointsPlayerWon = game.getPointsPlayer1();
		}

		if(pointsPlayerWon.get(pointsPlayerWon.size() - 1) == Point.PDEUCE){
			pointsPlayerLoose.add(Point.PDEUCE);
		}else{
			Point lastPointLooser = pointsPlayerLoose.get(pointsPlayerLoose.size() - 1);
			pointsPlayerLoose.add(lastPointLooser);
		}
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
