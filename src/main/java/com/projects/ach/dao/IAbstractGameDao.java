/**
 * 
 */
package com.projects.ach.dao;

import org.springframework.stereotype.Repository;

import com.projects.ach.model.AbstractGame;
import com.projects.ach.model.Player;
import com.projects.ach.model.Set;

/**
 * @author ABDELCHAG
 *
 */
@Repository
public interface IAbstractGameDao {

	AbstractGame createAbstractGame(Set set);

	Player getPlayer(AbstractGame game, String playerName);

	Player getOpponentPlayer(AbstractGame game, String playerName);

	void putWinner(AbstractGame game, Player player);
}
