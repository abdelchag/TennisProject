/**
 * 
 */
package com.projects.ach.dao.impl;

import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
public class TieBreakDaoImpl extends AbstractGameDaoImpl {

	@Override
	public TieBreak initAbstractGame(Set set) {
		TieBreak tieBreak = new TieBreak();
		tieBreak.setSet(set);
		tieBreak.addPointPlayer1(0);
		tieBreak.addPointPlayer2(0);
		set.setTieBreak(tieBreak);
		return tieBreak;
	}

}
