/**
 * 
 */
package com.projects.ach.business;

import org.springframework.stereotype.Service;

import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Service
public interface IGameBusiness {

	public Game startGame(Set set);

	public void winPoint(Game game, Player playerWon);

}
