/**
 * 
 */
package com.projects.ach.model;

/**
 * @author ABDELCHAG
 *
 */
public abstract class AbstractGame {

	private Player winner;
	private Set set;

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

	public Player getPlayer1() {
		return this.getSet().getPlayer1();
	}

	public Player getPlayer2() {
		return this.getSet().getPlayer2();
	}

}
