/*
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under
 * BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or
 * <architect@databuildr.com>
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
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
package game.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import game.GameController;
import game.models.meta.Meta;
import game.utils.Global;
import game.utils.Utils;

/**
 * The Spring Boot MVC controller for the GtKY API endpoints.
 *
 * GtKY uses a RESTful Web Services approach to present 'players' with one
 * multiple-choice question, the format for which is dependent upon the 'mode'
 * parameter passed to the /game/play endpoint.
 *
 * This <b>GameController</b> class has a total of three (3) endpoints:
 *
 * <ul>
 * <li><b>1 <i>/game/play?</i></b> Returns a <b>Game</b> object, composed of a
 * subject and six options from which to select a match.</li>
 * <li><b>2 <i>/game/submit?</i></b> Returns an <b>Eval</b> object,</li>
 * <li><b>3 <i>/game/meta?</i></b> Returns a <b>Meta</b> object,</li>
 * </ul>
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 */
@RestController
@RequestMapping("/meta")
public class MetaController extends GameController {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(MetaController.class);

	/** ******* */

	/**
	 * Index.
	 *
	 * @return the string
	 * @throws IOException
	 */
	@Override
	@RequestMapping("/usage")
	public String usage() throws IOException {
		return super.usage();
	}

	/**
	 * Initially serves to return a leaderboard object, but can be extended to serve
	 * up other objects which provide a metacontext around the other game objects.
	 *
	 * @param leaderboardLength
	 * @return A Meta object.
	 */
	@RequestMapping("/leaderboard")
	public Meta leaderboard(@RequestParam(value = "leadlen", defaultValue = "10") int leaderboardLength) {

		if (leaderboardLength < 0)
			return new Meta(true, Utils.getPropery(Global.k_invalid_id));

		return new Meta(leaderboardLength);
	}

}
