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
package game.models.play;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.models.GameMode;
import game.persistence.objects.Player;
import game.utils.Global;

/**
 * The Class Game.
 *
 * @author Michael Hoovler
 * @since Oct 10, 2018
 */
public class Play {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(Play.class);

	// ************************************************ API FIELDS

	/** The question attribute of the Game object. */
	private Question question;

	// ************************************************ INTERNAL FIELDS

	/** The player attribute of the Game object. */
	private Player player;

	/** The gameMode attribute of the Game object. */
	private GameMode gameMode;

	// ************************************************ CONSTRUCTORS

	/**
	 * Instantiates a new game.
	 *
	 * @throws IOException
	 */
	public Play() throws IOException {
		this.player = new Player();
		this.question = new Question();
	}

	/**
	 * Instantiates a new game.
	 *
	 * @param playerEmail the player email
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Play(String playerEmail) throws IOException {
		this.setupGame(playerEmail, Global.DEFAULT_GAMEMODE);
	}

	/**
	 * Instantiates a new game.
	 *
	 * @param playerEmail       the player email
	 * @param mode              the mode
	 * @param leaderboardLength the leaderboard length
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Play(final String playerEmail, int mode) throws IOException {
		this.setupGame(playerEmail, mode);
	}

	// ************************************************ GETTERS

	/**
	 * Gets the question.
	 *
	 * @return the question
	 */
	public Question getQuestion() {
		return this.question;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	protected Player getPlayer() {
		return this.player;
	}

	/**
	 * Gets the game mode.
	 *
	 * @return the game mode
	 */
	protected GameMode getGameMode() {
		return this.gameMode;
	}

	// ************************************************ SETTERS

	/**
	 * Sets the question.
	 *
	 * @param question the new question
	 */
	public void setQuestion(final Question question) {
		this.question = question;
	}

	/**
	 * Sets the game mode.
	 *
	 * @param gameMode the new game mode
	 */
	protected void setGameMode(final GameMode gameMode) {
		this.gameMode = gameMode;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	protected void setPlayer(final Player player) {
		this.player = player;
	}

	// ************************************************ INITIALIZE CLASS

	/**
	 * Setup game.
	 *
	 * @param email             the email
	 * @param intGameMode       the int game mode
	 * @param leaderboardLength the leaderboard length
	 * @return the game
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected Play setupGame(String email, int intGameMode) throws IOException {

		log.info("METHOD: private void initGame()");

		// validate value for gameMode...
		final GameMode[] modes = GameMode.values();
		final int numModes = modes.length;

		// use NORMAL if out of bounds...
		final GameMode gameMode = intGameMode <= numModes && intGameMode >= 0 ? modes[intGameMode - 1]
				: GameMode.NORMAL;

		// ...and set it
		this.gameMode = gameMode;

		// read profile JSON from configured URL as data for Questions list
		// this.profilePool = new ProfilePool().InitializeProfiles(mode);
		this.question = new Question(gameMode);

		// and record the correct answer for when the player validates it
		this.player = new Player(email);
		// player.addQuestionAsked(this.question);
		// this.player = player;
		// this.player.updatePlayer();
		// this.player = new Player(email);
		return this;
	}

	/**
	 * The string returned if there's an API validation error.
	 *
	 * While an unorthodox way to return error info through the API response, this
	 * works in a pinch.
	 */
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Creates a new game object with only an error.
	 *
	 * @param isError      the is error
	 * @param errorMessage the error message
	 */
	public Play(boolean isError, String errorMessage) {
		setError(errorMessage);
	}
}
