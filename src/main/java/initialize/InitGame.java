/* 
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or <architect@databuildr.com>
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met: 
 * 
 *   (1) Redistributions of source code must retain the above copyright notice this list of 
 *       conditions and the following disclaimer. 
 *   (2) Redistributions in binary form must reproduce the above copyright notice, this list 
 *       of conditions and the following disclaimer in the documentation and/or other materials 
 *       provided with the distribution. 
 *   (3) Neither the name of the copyright holder nor the names of its contributors may be 
 *       used to endorse or promote products derived from this software without specific prior 
 *       written permission.
 * 
 * Full license text is available at <https://opensource.org/licenses/BSD-3-Clause>
 */
package initialize;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import objects.Game;

@Configuration
public class InitGame {

	private static Logger log = LoggerFactory.getLogger(InitGame.class);
	
	@Bean
	public Game initializeGame(String email, String gameMode) throws IOException {
		Game game = new Game("", "");
		log.info("public Game initializeGame(String email, String gameMode)");

		// gameMode String to Mode
		game.setGameMode(gameMode);

		// would normally add pattern-based email validation...		
		game.setPlayerEmail(email);

		// retrieve leaderboard information from Leaderboard object previously built
		// from a leaderboard.json file
		//game.setLeaderboard(initLeaderboard(email));
		// TODO: InitLeaderboard

		// read profile JSON from given URL as data for Questions list
		//ArrayList<ProfileEntry> profileEntries = initProfileData();
		// TODO: InitProfiles

		// TODO: InitQuestions()
		switch (game.getGameMode()) {

		case REVERSED:
		case REVERSED_MATT:
			//setQuestions(profileEntries, Config.FACENAME_MIN, Config.FACENAME_MAX);
		case NORMAL:
		case NORMAL_MATT:
		default:
			//setQuestions(profileEntries, Config.FACENAME_MAX, Config.FACENAME_MIN);
		}
		return game;
	}
}
