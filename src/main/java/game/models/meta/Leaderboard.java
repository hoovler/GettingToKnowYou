/*
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under
 * BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or
 * <architect@databuildr.com>
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * (1) Redistributions of source code must retain the above copyright notice
 * this list of conditions and the following disclaimer. (2) Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution. (3) Neither the name of the
 * copyright holder nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written
 * permission.
 *
 * Full license text is available at
 * <https://opensource.org/licenses/BSD-3-Clause>
 */
package game.models.meta;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.persistence.objects.Player;
import game.utils.Global;

public class Leaderboard {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(Leaderboard.class);
	/** ***************** */

	// ************************************************ API FIELDS
	private List<Player> leaders;

	// ************************************************ CONSTRUCTORS

	public Leaderboard() {
		this.leaders = new ArrayList<>();
		this.initLeaderboard(Global.DEFAULT_LEADLEN);
	}

	public Leaderboard(final int boardLength) {
		this.leaders = new ArrayList<>();
		this.initLeaderboard(boardLength);
	}

	// ************************************************ GETTERS

	public List<Player> getLeaders() {
		return this.leaders;
	}

	// ************************************************ SETTERS

	public void setLeaders(final List<Player> leaders) {
		this.leaders = leaders;
	}

	// ************************************************ INITIALIZE CLASS

	protected void initLeaderboard(final int boardLength) {

		log.info("METHOD: private String initLeaderboard()");
		// TODO: leaderboard data from persistent storage

		this.leaders = new ArrayList<>();
		// But for now, just return random player history info....
		log.info("initLeaderboard(): Generating " + boardLength + " random players for leaderboard...");
		for (int i = 0; i < boardLength; i++)
			this.leaders.add(new Player(true));

	}

}
