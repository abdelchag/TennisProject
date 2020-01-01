/**
 * 
 */
package com.projects.ach.dao.impl;

import org.springframework.stereotype.Component;

import com.projects.ach.model.Set;
import com.projects.ach.model.TieBreak;

/**
 * @author ABDELCHAG
 *
 */
@Component(value = "tieBreakDao")
public class TieBreakDaoImpl extends AbstractGameDaoImpl {

	@Override
	public TieBreak createAbstractGame(Set set) {
		TieBreak tieBreak = new TieBreak();
		set.setTieBreak(tieBreak);
		return tieBreak;
	}

}
