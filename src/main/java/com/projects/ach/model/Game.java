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

	private List<Point> pointsPlayer1;
	private List<Point> pointsPlayer2;
	private Player winner;
	private Set set;

	public Game() {
		pointsPlayer1 = new LinkedList<>();
		pointsPlayer2 = new LinkedList<>();
	}

	public Player getPlayer1() {
		return this.getSet().getPlayer1();
	}

	public List<Point> getPointsPlayer1() {
		return pointsPlayer1;
	}

	public void setPointsPlayer1(List<Point> pointsPlayer1) {
		this.pointsPlayer1 = pointsPlayer1;
	}

	public Player getPlayer2() {
		return this.getSet().getPlayer2();
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

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

}
