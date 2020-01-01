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
public class Game extends AbstractGame {

	private List<Point> pointsPlayer1;
	private List<Point> pointsPlayer2;

	public Game() {
		pointsPlayer1 = new LinkedList<>();
		pointsPlayer2 = new LinkedList<>();
		addPointPlayer1(Point.P0);
		addPointPlayer2(Point.P0);
	}

	public List<Point> getPointsPlayer1() {
		return pointsPlayer1;
	}

	public void setPointsPlayer1(List<Point> pointsPlayer1) {
		this.pointsPlayer1 = pointsPlayer1;
	}

	public List<Point> getPointsPlayer2() {
		return pointsPlayer2;
	}

	public void setPointsPlayer2(List<Point> pointsPlayer2) {
		this.pointsPlayer2 = pointsPlayer2;
	}

	public void addPointPlayer1(Point point) {
		this.pointsPlayer1.add(point);
	}

	public void addPointPlayer2(Point point) {
		this.pointsPlayer2.add(point);
	}

}
