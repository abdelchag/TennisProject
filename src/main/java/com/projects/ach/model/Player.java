/**
 * 
 */
package com.projects.ach.model;

/**
 * @author ABDELCHAG
 *
 */
public class Player {

	private String name;
	private Set set;

	public Player() {
		set = new Set();
		set.setPlayer1(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

}
