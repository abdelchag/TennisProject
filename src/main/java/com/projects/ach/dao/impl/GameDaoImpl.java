/**
 * 
 */
package com.projects.ach.dao.impl;

import com.projects.ach.model.Game;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
public class GameDaoImpl extends AbstractGameDaoImpl {

	@Override
	public Game initAbstractGame(Set set) {
		Game game = new Game();
		game.setSet(set);
		game.addPointPlayer1(Point.P0);
		game.addPointPlayer2(Point.P0);
		set.addGame(game);
		return game;
	}

}
