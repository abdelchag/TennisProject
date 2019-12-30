/**
 * 
 */
package com.projects.ach.model;

/**
 * @author ABDELCHAG
 *
 */
public enum Point {

	P0("0"), P15("15"), P30("30"), P40("40"), PWIN("0"), PADV("ADV"), PDEUCE("DEUCE");

	private String value;

	public String getValue() {
		return value;
	}

	private Point(String value) {
		this.value = value;
	}

	public Point getNextPoint() {
		int ordinal = this.ordinal();
		Point[] allPoints = values();
		if (ordinal >= 4) {
			return null;
		} else {
			return allPoints[ordinal + 1];
		}

	}

}
