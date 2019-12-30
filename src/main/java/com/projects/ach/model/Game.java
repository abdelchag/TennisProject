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
public class Game {

	private Player player1;
	private List<Point> pointsPlayer1;
	private Player player2;
	private List<Point> pointsPlayer2;
	private Player winner;

	public Game() {
		pointsPlayer1 = new LinkedList<>();
		pointsPlayer2 = new LinkedList<>();
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
		this.player1.setGame(this);
	}

	public List<Point> getPointsPlayer1() {
		return pointsPlayer1;
	}

	public void setPointsPlayer1(List<Point> pointsPlayer1) {
		this.pointsPlayer1 = pointsPlayer1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
		this.player2.setGame(this);
	}

	public List<Point> getPointsPlayer2() {
		return pointsPlayer2;
	}

	public void setPointsPlayer2(List<Point> pointsPlayer2) {
		this.pointsPlayer2 = pointsPlayer2;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

}
