/**
 * 
 */
package com.projects.ach.business;

import org.springframework.stereotype.Service;

import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Service
public interface IAbstractGameBusiness {

	public AbstractGame startGame(Set set);

	public void winPoint(AbstractGame abstractGame, Player playerWon);

}
