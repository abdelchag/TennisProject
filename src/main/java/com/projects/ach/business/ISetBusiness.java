/**
 * 
 */
package com.projects.ach.business;

import org.springframework.stereotype.Service;

import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Service

public interface ISetBusiness {
	Set startSet(String playerName1, String playerName2);

	void scorePoint(Set set, Player playerWon);

}
