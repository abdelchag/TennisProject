/**
 * 
 */
package com.projects.ach.business;

import org.springframework.stereotype.Service;

import com.projects.ach.model.Game;
import com.projects.ach.model.Player;

/**
 * @author ABDELCHAG
 *
 */
@Service
public interface IGameBusiness {
	
	public Game startGame(String namePlayer1, String namePlayer2);
	
	public void winPoint(Game game, Player playerWon);
	
}
