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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.ValidatorException;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import game.models.ProfilePool;
import game.models.eval.Eval;
import game.models.meta.Meta;
import game.models.play.Play;
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
@RequestMapping("/game")
public class MetaController {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(MetaController.class);

	/** ******* */

	/**
	 * Index.
	 *
	 * @return the string
	 */
	@RequestMapping("/")
	public String index() {
		return "This is where you would find API usage info!!";
	}

	/**
	 * Returns an instance of the main name game, which consists of a Question
	 * object, itself containing a subject and six options from which to select the
	 * matching object.
	 *
	 * @param playerEmail Must be a valid email address.
	 * @param gameMode    A number from 1 to 4:
	 *                    <ul>
	 *                    <li>1: Normal Mode (1 name, 6 images)</li>
	 *                    <li>2: Reversed Mode (6 images, 1 name)</li>
	 *                    <li>3: Normal MATT! Mode (Normal Mode for Matts)</li>
	 *                    <li>4: Reversed MATT! Mode (Reversed Mode for Matts)</li>
	 *                    </ul>
	 * @return A Game object.
	 * @throws IOException        Signals that an I/O exception has occurred.
	 * @throws ValidatorException for unexpected parameter values.
	 */
	@RequestMapping("/play")
	public Play play(@RequestParam(value = "email") final String playerEmail,
			@RequestParam(value = "mode", defaultValue = "1") final int gameMode)
			throws IOException, ValidatorException {

		// validate input
		if (StringUtils.isNotBlank(playerEmail) && !EmailValidator.getInstance(true, true).isValid(playerEmail))
			return new Play(true, Utils.getPropery(Global.k_invalid_email));

		if (gameMode < 1 || gameMode > 4)
			return new Play(true, Utils.getPropery(Global.k_invalid_mode));

		return new Play(playerEmail, gameMode);
	}

	/**
	 * The REST endpoint that accepts the player's email, and the ID of their guess,
	 * and will eventually provide the right information as to whether the answer is
	 * correct or not.
	 *
	 * @param playerEmail the player email
	 * @param employeeId  the employee id
	 * @param optionId    the option id
	 * @return A Submission object
	 * @throws IOException
	 */
	@RequestMapping("/submit")
	public Eval eval(@RequestParam(value = "email") final String playerEmail,
			@RequestParam(value = "optionId") final String optionId) throws ValidatorException, IOException {

		if (StringUtils.isNotBlank(playerEmail) && !EmailValidator.getInstance(true, true).isValid(playerEmail))
			return new Eval(true, Utils.getPropery(Global.k_invalid_email));

		if (new ProfilePool().getProfileIds().indexOf(optionId) < 0)
			return new Eval(true, Utils.getPropery(Global.k_invalid_id));

		return new Eval(playerEmail, optionId);
	}

	/**
	 * Initially serves to return a leaderboard object, but can be extended to serve
	 * up other objects which provide a metacontext around the other game objects.
	 *
	 * @param leaderboardLength
	 * @return A Meta object.
	 */
	@RequestMapping("/meta")
	public Meta leaderboard(@RequestParam(value = "leadlen", defaultValue = "10") int leaderboardLength) {

		if (leaderboardLength < 0)
			return new Meta(true, Utils.getPropery(Global.k_invalid_id));

		return new Meta(leaderboardLength);
	}

}
