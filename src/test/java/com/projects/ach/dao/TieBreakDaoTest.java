/**
 * 
 */
package com.projects.ach.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.projects.ach.dao.impl.TieBreakDaoImpl;
import com.projects.ach.model.Player;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TieBreakDaoTest extends AbstractGameDaoInitTest {

	@InjectMocks
	private TieBreakDaoImpl tieBreakDao;

	@Test
	public void testGetThisPlayerExist() {
		TieBreak TieBreak = tieBreakDao.createAbstractGame(set);
		Player player = tieBreakDao.getPlayer(TieBreak, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player1);
	}

	@Test
	public void testGetThisPlayerNotExist() {
		TieBreak TieBreak = tieBreakDao.createAbstractGame(set);
		Player player = tieBreakDao.getPlayer(TieBreak, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testGetOtherPlayerExist() {
		TieBreak TieBreak = tieBreakDao.createAbstractGame(set);
		Player player = tieBreakDao.getOpponentPlayer(TieBreak, playerName1);
		assertThat(player).isNotNull();
		assertThat(player).isEqualTo(player2);
	}

	@Test
	public void testGetOtherPlayerNotExist() {
		TieBreak TieBreak = tieBreakDao.createAbstractGame(set);
		Player player = tieBreakDao.getPlayer(TieBreak, "PlayerTest3");
		assertThat(player).isNull();
	}

	@Test
	public void testPutWinner() {
		TieBreak TieBreak = tieBreakDao.createAbstractGame(set);
		tieBreakDao.putWinner(TieBreak, player1);

		assertThat(TieBreak.getWinner()).isEqualTo(player1);
	}

}
