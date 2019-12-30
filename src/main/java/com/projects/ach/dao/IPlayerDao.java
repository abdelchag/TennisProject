/**
 * 
 */
package com.projects.ach.dao;

import org.springframework.stereotype.Repository;

import com.projects.ach.model.Player;

/**
 * @author ABDELCHAG
 *
 */
@Repository
public interface IPlayerDao {

	Player initPlayer(String name);
	
	void addPointWinner(Player player);
	
	void addPointLooser(Player player);
	
}
