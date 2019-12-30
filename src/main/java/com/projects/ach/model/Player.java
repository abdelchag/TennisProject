/**
 * 
 */
package com.projects.ach.model;

import java.util.Collections;

/**
 * @author ABDELCHAG
 *
 */
public class Player {

	private String name;
	private Game game;

	public Player() {
		game = new Game();
		game.setSet(new Set());
		game.getSet().setPlayer1(this);
		game.getSet().setGames(Collections.singletonList(game));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
