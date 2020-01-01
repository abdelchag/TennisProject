/**
 * 
 */
package com.projects.ach.utils;

import java.util.List;

import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
public class TennisUtils {

	public static List<Point> getPointsPlayerGame(Game game, Player player) {
		if (game.getPlayer1().getName().equals(player.getName())) {
			return game.getPointsPlayer1();
		} else {
			return game.getPointsPlayer2();
		}
	}

	public static List<Point> getPointsOpponentGame(Game game, Player player) {
		if (game.getPlayer1().getName().equals(player.getName())) {
			return game.getPointsPlayer2();
		} else {
			return game.getPointsPlayer1();
		}
	}

	public static List<Integer> getScorePlayerSet(Set set, Player player) {
		if (player.getName().equalsIgnoreCase(set.getPlayer1().getName())) {
			return set.getScoresPlayer1();
		} else {
			return set.getScoresPlayer2();
		}
	}

	public static List<Integer> getPointPlayerTieBreak(TieBreak tieBreak, Player player) {
		if (player.getName().equalsIgnoreCase(tieBreak.getPlayer1().getName())) {
			return tieBreak.getScoresPlayer1();
		} else {
			return tieBreak.getScoresPlayer2();
		}
	}

}
