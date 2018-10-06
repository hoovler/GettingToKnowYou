/* 
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or <architect@databuildr.com>
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met: (1) Redistributions of source code must retain the above copyright notice this list of conditions and the following disclaimer. (2) Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. (3) Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * 
 * Full license text is available at <https://opensource.org/licenses/BSD-3-Clause>
 */
package framework;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import objects.Game;
import utils.Config;

/**
 * The Class GameController.
 *
 * @author Michael Hoovler
 * @since Oct 4, 2018
 * 
 *        Purpose: <TODO: add purpose statement>
 */
@RestController
@RequestMapping("/api")
public class GameController {
	private static final Logger log = LoggerFactory.getLogger(GameController.class);
	
	@RequestMapping("/")
	public String index() {
		log.info("RequestMapping index()");
		log.info("Config.INDEX_DEFAULT = " + Config.INDEX_DEFAULT);
		return Config.INDEX_DEFAULT;
	}

	/**
	 * Game.
	 *
	 * @param playerEmail the player email
	 * @return the game
	 * @throws IOException 
	 */
	@RequestMapping("/game")
	public Game game(
			@RequestParam(value = "email") String playerEmail, 
			@RequestParam(value = "gameMode", defaultValue = "") String gameMode) throws IOException {
		
		log.info("RequestMapping game(String, String)");
		log.info("Parameters: playerEmail="+playerEmail+"; gameMode="+gameMode);
		Game aGame = new Game(playerEmail, gameMode);
		return aGame;
	}
}
