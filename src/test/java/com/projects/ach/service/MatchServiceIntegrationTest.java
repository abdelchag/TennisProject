/**
 * 
 */
package com.projects.ach.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.projects.ach.model.Game;
import com.projects.ach.model.Player;
import com.projects.ach.model.Point;
import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchServiceIntegrationTest {

	@Autowired
	private IMatchService matchService;

	private String playerName1 = "playerName1";
	private String playerName2 = "playerName2";

	@Test
	public void testStartMatch() {
		Set set = matchService.startMatch(playerName1, playerName2);
		assertThat(set.getPlayer1().getName()).isEqualTo(playerName1);
		assertThat(set.getPlayer2().getName()).isEqualTo(playerName2);
		assertThat(set.getScoresPlayer1()).hasSize(1);
		assertThat(set.getScoresPlayer2()).hasSize(1);
		assertThat(set.getTieBreak()).isNull();
		assertThat(set.getGames()).hasSize(1);
		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(1);
		assertThat(current.getPointsPlayer2()).hasSize(1);
	}

	@Test
	public void testScorePoint15To0() {
		Set set = matchService.startMatch(playerName1, playerName2);
		boolean isFinish = matchService.scorePoint(set, set.getPlayer1());
		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(2);
		assertThat(current.getPointsPlayer2()).hasSize(2);
		assertThat(current.getPointsPlayer1().get(1)).isEqualTo(Point.P15);
		assertThat(current.getPointsPlayer2().get(1)).isEqualTo(Point.P0);
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePoint30to15() {
		Set set = matchService.startMatch(playerName1, playerName2);
		boolean isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());

		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(4);
		assertThat(current.getPointsPlayer2()).hasSize(4);
		assertThat(current.getPointsPlayer1().get(3)).isEqualTo(Point.P30);
		assertThat(current.getPointsPlayer2().get(3)).isEqualTo(Point.P15);
		assertThat(isFinish).isFalse();

	}

	@Test
	public void testScorePointADV() {
		Set set = matchService.startMatch(playerName1, playerName2);
		boolean isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());

		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(8);
		assertThat(current.getPointsPlayer2()).hasSize(8);
		assertThat(current.getPointsPlayer1().get(7)).isEqualTo(Point.PADV);
		assertThat(current.getPointsPlayer2().get(7)).isEqualTo(Point.P40);
		assertThat(isFinish).isFalse();

	}

	@Test
	public void testScorePointDEUCE() {
		Set set = matchService.startMatch(playerName1, playerName2);
		boolean isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());

		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(9);
		assertThat(current.getPointsPlayer2()).hasSize(9);
		assertThat(current.getPointsPlayer1().get(8)).isEqualTo(Point.PDEUCE);
		assertThat(current.getPointsPlayer2().get(8)).isEqualTo(Point.PDEUCE);
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointADVAfterDEUCE() {
		Set set = matchService.startMatch(playerName1, playerName2);
		boolean isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());

		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(set.getGames()).hasSize(1);
		assertThat(current.getPointsPlayer1()).hasSize(10);
		assertThat(current.getPointsPlayer2()).hasSize(10);
		assertThat(current.getPointsPlayer1().get(9)).isEqualTo(Point.PADV);
		assertThat(current.getPointsPlayer2().get(9)).isEqualTo(Point.PDEUCE);
		assertThat(current.getWinner()).isNull();
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointWinGameNormal() {
		Set set = matchService.startMatch(playerName1, playerName2);
		boolean isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer1());

		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(1);
		assertThat(current.getPointsPlayer2()).hasSize(1);
		assertThat(current.getPointsPlayer1().get(0)).isEqualTo(Point.P0);
		assertThat(current.getPointsPlayer2().get(0)).isEqualTo(Point.P0);
		assertThat(set.getGames()).hasSize(2);

		Game previous = set.getGames().get(0);
		assertThat(previous.getPointsPlayer1()).hasSize(6);
		assertThat(previous.getPointsPlayer2()).hasSize(6);
		assertThat(previous.getPointsPlayer1().get(5)).isEqualTo(Point.PWIN);
		assertThat(previous.getPointsPlayer2().get(5)).isEqualTo(Point.P15);
		assertThat(previous.getWinner()).isEqualTo(set.getPlayer1());
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointWinGameDEUCE() {
		Set set = matchService.startMatch(playerName1, playerName2);
		boolean isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer2());
		isFinish = matchService.scorePoint(set, set.getPlayer1());
		isFinish = matchService.scorePoint(set, set.getPlayer1());

		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(1);
		assertThat(current.getPointsPlayer2()).hasSize(1);
		assertThat(current.getPointsPlayer1().get(0)).isEqualTo(Point.P0);
		assertThat(current.getPointsPlayer2().get(0)).isEqualTo(Point.P0);
		assertThat(set.getGames()).hasSize(2);

		Game previous = set.getGames().get(0);
		assertThat(previous.getPointsPlayer1()).hasSize(11);
		assertThat(previous.getPointsPlayer2()).hasSize(11);
		assertThat(previous.getPointsPlayer1().get(10)).isEqualTo(Point.PWIN);
		assertThat(previous.getPointsPlayer2().get(10)).isEqualTo(Point.PDEUCE);
		assertThat(previous.getWinner()).isEqualTo(set.getPlayer1());
		assertThat(isFinish).isFalse();
	}

	@Test
	public void testScorePointWinSet6To0() {
		Set set = matchService.startMatch(playerName1, playerName2);
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer1());
		assertThat(set.getGames()).hasSize(6);
		for (Game game : set.getGames()) {
			assertThat(game.getWinner()).isEqualTo(set.getPlayer1());
		}

		assertThat(set.getWinner()).isNotNull();
		assertThat(set.getWinner()).isEqualTo(set.getPlayer1());
		Integer lastScore1 = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
		Integer lastScore2 = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		assertThat(lastScore1).isEqualTo(6);
		assertThat(lastScore2).isEqualTo(0);

	}

	@Test
	public void testScorePointWinSet7To5() {
		Set set = matchService.startMatch(playerName1, playerName2);
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer1());
		assertThat(set.getGames()).hasSize(12);

		assertThat(set.getWinner()).isNotNull();
		assertThat(set.getWinner()).isEqualTo(set.getPlayer1());

		Integer lastScore1 = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
		Integer lastScore2 = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		assertThat(lastScore1).isEqualTo(7);
		assertThat(lastScore2).isEqualTo(5);

	}

	@Test
	public void testScorePoint6To5() {
		Set set = matchService.startMatch(playerName1, playerName2);
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		assertThat(set.getGames()).hasSize(12);

		assertThat(set.getWinner()).isNull();

		Integer lastScore1 = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
		Integer lastScore2 = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		assertThat(lastScore1).isEqualTo(6);
		assertThat(lastScore2).isEqualTo(5);

		Game current = (Game) set.getCurrentAbstractGame();
		assertThat(current.getPointsPlayer1()).hasSize(1);
		assertThat(current.getPointsPlayer2()).hasSize(1);

	}

	@Test
	public void testScorePoint6To6() {
		Set set = matchService.startMatch(playerName1, playerName2);
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		assertThat(set.getGames()).hasSize(12);
		assertThat(set.getTieBreak()).isNotNull();

		assertThat(set.getWinner()).isNull();

		Integer lastScore1 = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
		Integer lastScore2 = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		assertThat(lastScore1).isEqualTo(6);
		assertThat(lastScore2).isEqualTo(6);

		TieBreak current = (TieBreak) set.getCurrentAbstractGame();
		assertThat(current.getScoresPlayer1()).hasSize(1);
		assertThat(current.getScoresPlayer2()).hasSize(1);

	}

	@Test
	public void testScorePoint7To6() {
		Set set = matchService.startMatch(playerName1, playerName2);
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winGame(set, set.getPlayer1());
		winGame(set, set.getPlayer2());
		winTieBreak(set, set.getPlayer1());
		assertThat(set.getGames()).hasSize(12);
		assertThat(set.getTieBreak()).isNotNull();
		assertThat(set.getTieBreak().getWinner()).isNotNull();
		assertThat(set.getTieBreak().getWinner()).isEqualTo(set.getPlayer1());

		assertThat(set.getWinner()).isNotNull();
		assertThat(set.getWinner()).isEqualTo(set.getPlayer1());

		Integer lastScore1 = set.getScoresPlayer1().get(set.getScoresPlayer1().size() - 1);
		Integer lastScore2 = set.getScoresPlayer2().get(set.getScoresPlayer2().size() - 1);
		assertThat(lastScore1).isEqualTo(7);
		assertThat(lastScore2).isEqualTo(6);
	}

	private void winGame(Set set, Player winner) {
		for (int i = 0; i < 4; i++) {
			matchService.scorePoint(set, winner);
		}
	}

	private void winTieBreak(Set set, Player winner) {
		for (int i = 0; i < 7; i++) {
			matchService.scorePoint(set, winner);
		}
	}

}
