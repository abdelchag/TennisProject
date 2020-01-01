/**
 * 
 */
package com.projects.ach.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;
import com.projects.ach.utils.TennisUtils;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "playerDao")
public class PlayerDaoImpl implements IPlayerDao {

	@Override
	public Player createPlayer(String name) {
		Player player = new Player();
		player.setName(name);
		return player;
	}

	@Override
	public void addPointWinnerGame(Player player) {
		Game game = player.getSet().getLastGame();

		List<Point> pointsPlayerWon = TennisUtils.getPointsPlayerGame(game, player);
		List<Point> pointsPlayerLoose = TennisUtils.getPointsOpponentGame(game, player);
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
	public void addPointLooserGame(Player player) {
		Game game = player.getSet().getGames().get(player.getSet().getGames().size() - 1);
		List<Point> pointsPlayerLoose = TennisUtils.getPointsPlayerGame(game, player);
		List<Point> pointsPlayerWon = TennisUtils.getPointsOpponentGame(game, player);

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
		List<Point> pointPlayerWon = TennisUtils.getPointsPlayerGame(game, player);
		return pointPlayerWon.get(pointPlayerWon.size() - 1).equals(Point.PWIN);
	}

	@Override
	public void addScoreWinnerSet(Player player) {
		Set set = player.getSet();
		List<Integer> scoresWinner = TennisUtils.getScorePlayerSet(set, player);
		Integer lastScore = scoresWinner.get(scoresWinner.size() - 1);
		scoresWinner.add(lastScore + 1);
	}

	@Override
	public void addScoreLooserSet(Player player) {
		Set set = player.getSet();
		List<Integer> scoresLooser = TennisUtils.getScorePlayerSet(set, player);
		Integer lastScore = scoresLooser.get(scoresLooser.size() - 1);
		scoresLooser.add(lastScore);
	}

	@Override
	public boolean isWinSet(Player player) {
		Set set = player.getSet();
		if (set.getTieBreak() != null && set.getTieBreak().getWinner() != null) {
			return true;
		}
		Integer lastScoreWinner = null;
		Integer lastScoreLooser = null;
		if (player.getName().equalsIgnoreCase(set.getPlayer1().getName())) {
			lastScoreWinner = TennisUtils.getScorePlayerSet(set, set.getPlayer1())
					.get(set.getScoresPlayer1().size() - 1);
			lastScoreLooser = TennisUtils.getScorePlayerSet(set, set.getPlayer2())
					.get(set.getScoresPlayer2().size() - 1);
		} else {
			lastScoreLooser = TennisUtils.getScorePlayerSet(set, set.getPlayer1())
					.get(set.getScoresPlayer1().size() - 1);
			lastScoreWinner = TennisUtils.getScorePlayerSet(set, set.getPlayer2())
					.get(set.getScoresPlayer2().size() - 1);
		}
		return lastScoreWinner == 7 || (lastScoreWinner == 6 && lastScoreLooser <= 4);
	}

	@Override
	public void addPointWinnerTieBreak(Player player) {
		TieBreak tieBreak = player.getSet().getTieBreak();
		List<Integer> scoresWinner = TennisUtils.getPointPlayerTieBreak(tieBreak, player);
		Integer lastScore = scoresWinner.get(scoresWinner.size() - 1);
		scoresWinner.add(lastScore + 1);

	}

	@Override
	public void addPointLooserTieBreak(Player player) {
		TieBreak tieBreak = player.getSet().getTieBreak();
		List<Integer> scoreLooser = TennisUtils.getPointPlayerTieBreak(tieBreak, player);
		Integer lastScore = scoreLooser.get(scoreLooser.size() - 1);
		scoreLooser.add(lastScore);

	}

	@Override
	public boolean isWinTieBreak(Player player) {
		TieBreak tieBreak = player.getSet().getTieBreak();
		Integer lastScoreWinner = null;
		Integer lastScoreLooser = null;
		if (player.getName().equalsIgnoreCase(tieBreak.getPlayer1().getName())) {
			lastScoreWinner = TennisUtils.getPointPlayerTieBreak(tieBreak, tieBreak.getPlayer1())
					.get(tieBreak.getScoresPlayer1().size() - 1);
			lastScoreLooser = TennisUtils.getPointPlayerTieBreak(tieBreak, tieBreak.getPlayer2())
					.get(tieBreak.getScoresPlayer2().size() - 1);
		} else {
			lastScoreLooser = TennisUtils.getPointPlayerTieBreak(tieBreak, tieBreak.getPlayer1())
					.get(tieBreak.getScoresPlayer1().size() - 1);
			lastScoreWinner = TennisUtils.getPointPlayerTieBreak(tieBreak, tieBreak.getPlayer2())
					.get(tieBreak.getScoresPlayer2().size() - 1);
		}
		return lastScoreWinner >= 7 && lastScoreWinner >= lastScoreLooser + 2;
	}
}
