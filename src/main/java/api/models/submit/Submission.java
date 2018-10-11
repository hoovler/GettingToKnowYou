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
package api.models.submit;

import java.io.IOException;

import api.persistence.objects.Player;
import api.shared.objects.ProfilePool;

/**
 * The Class Submission.
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 */
public class Submission {

	// ************************************************ API FIELDS
	private String error;

	/** The correctAnwser attribute of the Submission object. */
	private boolean correctAnwser;

	/** The player attribute of the Submission object. */
	private Player player;

	// ************************************************ CONSTRUCTORS

	public Submission(boolean isError, String errorMessage) {
		// isError isn't really used - just meant as a way to ensure distinct
		// constructor signatures
		setError(errorMessage);
	}

	/**
	 * Instantiates a new submission.
	 *
	 * @param answerFeaturedItem the answer featured item
	 * @param answerSelection    the answer selection
	 */
	public Submission(final String employeeId, final String optionId) {
		this.initSubmission(null, employeeId, optionId);
	}

	/**
	 * Instantiates a new submission.
	 *
	 * @param playerEmail        the player email
	 * @param answerFeaturedItem the answer featured item
	 * @param answerSelection    the answer selection
	 */
	public Submission(final String playerEmail, final String employeeId, final String optionId) {
		// now we can decide if we have a correct answer
		this.initSubmission(playerEmail, employeeId, optionId);

	}

	// ** GETTERS **

	public String getError() {
		return error;
	}

	/**
	 * Checks if is correct anwser.
	 *
	 * @return true, if is correct anwser
	 */
	public boolean isCorrectAnwser() {
		return this.correctAnwser;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return this.player;
	}

	// ** SETTERS **

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * Sets the correct anwser.
	 *
	 * @param correctAnwser the new correct anwser
	 */
	public void setCorrectAnwser(final boolean correctAnwser) {
		this.correctAnwser = correctAnwser;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(final Player player) {
		this.player = player;
	}

	// ************************************************ CLASS METHODS

	protected void initSubmission(final String playerEmail, final String employeeId, final String optionId) {

		/*
		 * PlayerRepository playerRepo = new PlayerService().getPlayerRepository();
		 *
		 * // create player object Player player;
		 *
		 * // check if player exists if(playerRepo.existsById(playerEmail)) { player =
		 * playerRepo.findById(playerEmail).get(); ArrayList<Question> questionsAsked =
		 * player.getQuestionsAsked();
		 *
		 * } else {
		 *
		 * }
		 */

		// Player player = PlayerService.

		// now we can decide if we have a correct answer
		this.correctAnwser = this.isCorrect(employeeId, optionId);
	}

	/**
	 * Checks if is correct.
	 *
	 * @param faceUrl     the face url
	 * @param displayName the display name
	 * @return true, if is correct
	 */
	protected boolean isCorrect(final String employeeId, final String optionId) {

		// ensure IDs match...

		// and that the ID is a valid one from the data source

		// need to create mode-independent check...
		return true;
	}

	/**
	 * Record player stats.
	 *
	 * @param playerEmail the player email
	 */
	protected void recordPlayerStats(final String playerEmail) {

	}

	// STATIC METHODS

	public static boolean idExists(String id) throws IOException {
		return new ProfilePool().getProfileIds().indexOf(id) > 0;

	}

}
