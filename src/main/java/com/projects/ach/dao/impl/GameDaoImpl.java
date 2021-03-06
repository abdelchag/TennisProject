/**
 * 
 */
package com.projects.ach.dao.impl;

import org.springframework.stereotype.Component;

import com.projects.ach.model.Game;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "gameDao")
public class GameDaoImpl extends AbstractGameDaoImpl {

	@Override
	public Game createAbstractGame(Set set) {
		Game game = new Game();
		set.addGame(game);
		return game;
	}

}
