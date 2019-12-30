/**
 * 
 */
package com.projects.ach.business.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.projects.ach.business.IGameBusiness;
import com.projects.ach.dao.IGameDao;
import com.projects.ach.dao.IPlayerDao;
import com.projects.ach.model.Game;
import com.projects.ach.model.Player;

/**
 * @author ABDELCHAG
 *
 */
public class GameBusinessImpl implements IGameBusiness {
	
	@Autowired
	private IPlayerDao playerDao;
	
	@Autowired
	private IGameDao gameDao;
	

	@Override
	public Game startGame(String namePlayer1, String namePlayer2) {
		Player player1 = playerDao.initPlayer(namePlayer1);
		Player player2 = playerDao.initPlayer(namePlayer2);
		Game game = gameDao.initGame(player1, player2);
		return game;
	}


	@Override
	public void winPoint(Game game, Player playerWon) {
		Player playerWonGame = gameDao.getThisPlayer(game, playerWon.getName());
		Player playerLooseGame = gameDao.getOtherPlayer(game, playerWon.getName());
		
		playerDao.addPointWinner(playerWonGame);
		playerDao.addPointLooser(playerLooseGame);
		
		
		
	}
	
	



}
