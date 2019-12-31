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

	Set startMatch(String playerName1, String playerName2);

	boolean scorePoint(Set set, Player scorer);

}
