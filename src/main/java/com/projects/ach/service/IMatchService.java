/**
 * 
 */
package com.projects.ach.service;

import org.springframework.stereotype.Service;

import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Service
public interface IMatchService {

	/**
	 * start new match with one set of two player and the first game
	 * @param playerName1
	 * @param playerName2
	 * @return started set
	 */
	Set startMatch(String playerName1, String playerName2);

	/**
	 * Score one additional point to the current party for given player and if true specify the winner of party and set, if necessary start new game or tie break 
	 * @param set
	 * @param scorer winner of point
	 * @return true if player won the set and the match
	 */
	boolean scorePoint(Set set, Player scorer);

}
