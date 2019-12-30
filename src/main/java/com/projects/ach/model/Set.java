/**
 * 
 */
package com.projects.ach.model;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ABDELCHAG
 *
 */
public class Set {

	private Player player1;
	private List<Integer> scoresPlayer1;
	private Player player2;
	private List<Integer> scoresPlayer2;
	private List<Game> games;
	private Player winner;

	public Set() {
		games = new LinkedList<Game>();
		scoresPlayer1 = new LinkedList<>();
		scoresPlayer2 = new LinkedList<>();
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public List<Integer> getScoresPlayer1() {
		return scoresPlayer1;
	}

	public void setScoresPlayer1(List<Integer> scoresPlayer1) {
		this.scoresPlayer1 = scoresPlayer1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
		this.player2.setSet(this);
	}

	public List<Integer> getScoresPlayer2() {
		return scoresPlayer2;
	}

	public void setScoresPlayer2(List<Integer> scoresPlayer2) {
		this.scoresPlayer2 = scoresPlayer2;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public void addGame(Game game) {
		this.games.add(game);
		game.setSet(this);
	}

}
