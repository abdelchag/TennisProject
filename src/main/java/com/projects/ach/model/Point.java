/**
 * 
 */
package com.projects.ach.model;

/**
 * @author ABDELCHAG
 *
 */
public enum Point {

	P0(0), P15(15), P30(30), P40(40), PWIN(0);

	private Integer value;

	public Integer getValue() {
		return value;
	}

	private Point(Integer value) {
		this.value = value;
	}

	public Point getNextPoint() {
		int ordinal = this.ordinal();
		Point[] allPoints = values();
		if(this == Point.PWIN){
			return null;
		}else{
			return allPoints[ordinal + 1];
		}

	}

}
