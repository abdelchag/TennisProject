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
public class TieBreak extends AbstractGame {

	private List<Integer> scoresPlayer1;
	private List<Integer> scoresPlayer2;

	public TieBreak() {
		scoresPlayer1 = new LinkedList<>();
		scoresPlayer2 = new LinkedList<>();
	}

	public List<Integer> getScoresPlayer1() {
		return scoresPlayer1;
	}

	public void setScoresPlayer1(List<Integer> scoresPlayer1) {
		this.scoresPlayer1 = scoresPlayer1;
	}

	public List<Integer> getScoresPlayer2() {
		return scoresPlayer2;
	}

	public void setScoresPlayer2(List<Integer> scoresPlayer2) {
		this.scoresPlayer2 = scoresPlayer2;
	}

	public void addPointPlayer1(Integer point) {
		this.scoresPlayer1.add(point);
	}

	public void addPointPlayer2(Integer point) {
		this.scoresPlayer2.add(point);
	}

}
