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
import game.models.play.Play;
import game.utils.Global;
import game.utils.Utils;

/**
 * The Spring Boot MVC root controller for the GettingToKnowYou API endpoints.
 * All endpoints for this application extent this class.
 *
 * GtKY uses a RESTful Web Services approach to present 'players' with one
 * multiple-choice question, the format for which is dependent upon the 'mode'
 * parameter passed to the endpoint defined in <code>PlayController</code>
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 */
@RestController
@RequestMapping("/play")
public class PlayController {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(PlayController.class);

	/** ******* */

	/**
	 * Index.
	 *
	 * @return the string
	 * @throws IOException
	 */
	@RequestMapping("/")
	public String index() throws IOException {
		return Utils.getReadmeHtml();
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
	@RequestMapping("/question")
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
	@RequestMapping("/answer")
	public Eval eval(@RequestParam(value = "email") final String playerEmail,
			@RequestParam(value = "optionId") final String optionId) throws ValidatorException, IOException {

		if (StringUtils.isNotBlank(playerEmail) && !EmailValidator.getInstance(true, true).isValid(playerEmail))
			return new Eval(true, Utils.getPropery(Global.k_invalid_email));

		if (new ProfilePool().getProfileIds().indexOf(optionId) < 0)
			return new Eval(true, Utils.getPropery(Global.k_invalid_id));

		return new Eval(playerEmail, optionId);
	}
}
