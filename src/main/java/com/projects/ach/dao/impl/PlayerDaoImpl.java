/**
 * 
 */
package com.projects.ach.dao.impl;

import java.util.List;

import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
public class PlayerDaoImpl implements IPlayerDao {

	@Override
	public Player initPlayer(String name) {
		Player player = new Player();
		player.setName(name);
		player.getSet().getScoresPlayer1().add(0);
		return player;
	}

	@Override
	public void addPointWinner(Player player) {
		Game game = player.getSet().getGames().get(player.getSet().getGames().size() - 1);

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
		Game game = player.getSet().getGames().get(player.getSet().getGames().size() - 1);
		List<Point> pointsPlayerLoose = null;
		List<Point> pointsPlayerWon = null;
		if (game.getPlayer1().getName().equals(player.getName())) {
			pointsPlayerLoose = game.getPointsPlayer1();
			pointsPlayerWon = game.getPointsPlayer2();
		} else {
			pointsPlayerLoose = game.getPointsPlayer2();
			pointsPlayerWon = game.getPointsPlayer1();
		}

		if (pointsPlayerWon.get(pointsPlayerWon.size() - 1) == Point.PDEUCE) {
			pointsPlayerLoose.add(Point.PDEUCE);
		} else {
			Point lastPointLooser = pointsPlayerLoose.get(pointsPlayerLoose.size() - 1);
			pointsPlayerLoose.add(lastPointLooser);
		}
	}

	@Override
	public boolean isWinGame(Player player) {
		Game game = player.getSet().getGames().get(player.getSet().getGames().size() - 1);
		List<Point> pointPlayerWon = null;
		if (game.getPlayer1().getName().equals(player.getName())) {
			pointPlayerWon = game.getPointsPlayer1();
		} else {
			pointPlayerWon = game.getPointsPlayer2();
		}
		return pointPlayerWon.get(pointPlayerWon.size() - 1).equals(Point.PWIN);
	}

	@Override
	public void addScoreWinner(Player player) {
		Set set = player.getSet();
		List<Integer> scoresWinner = null;
		if (player.getName().equalsIgnoreCase(set.getPlayer1().getName())) {
			scoresWinner = set.getScoresPlayer1();
		} else {
			scoresWinner = set.getScoresPlayer2();
		}
		Integer lastScore = scoresWinner.get(scoresWinner.size() - 1);
		scoresWinner.add(lastScore + 1);
	}

	@Override
	public void addScoreLooser(Player player) {
		Set set = player.getSet();
		List<Integer> scoresLooser = null;
		if (player.getName().equalsIgnoreCase(set.getPlayer1().getName())) {
			scoresLooser = set.getScoresPlayer1();
		} else {
			scoresLooser = set.getScoresPlayer2();
		}
		Integer lastScore = scoresLooser.get(scoresLooser.size() - 1);
		scoresLooser.add(lastScore);
	}

	@Override
	public boolean isWinSet(Player player) {
		Set set = player.getSet();
		Integer lastScoreWinner = null;
		Integer lastScoreLooser = null;
		if (player.getName().equalsIgnoreCase(set.getPlayer1().getName())) {
			lastScoreWinner = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
			lastScoreLooser = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		} else {
			lastScoreLooser = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
			lastScoreWinner = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		}
		return lastScoreWinner == 7 || (lastScoreWinner == 6 && lastScoreLooser <= 4);
	}
}
