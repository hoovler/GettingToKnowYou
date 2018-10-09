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
package gtky.models;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gtky.GettingToKnowYou;
import gtky.persistence.PlayerService;
import gtky.persistence.entities.Player;
import gtky.persistence.repositories.PlayerRepository;

/**
 * The Class Submission.
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 */
public class Submission extends GettingToKnowYou {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(Submission.class);
	/** ******* */
	
	// ************************************************ API FIELDS

	/** The correctAnwser attribute of the Submission object. */
	private boolean correctAnwser;

	/** The player attribute of the Submission object. */
	private Player player;

	// ** GETTERS **

	/**
	 * Checks if is correct anwser.
	 *
	 * @return true, if is correct anwser
	 */
	public boolean isCorrectAnwser() {
		return correctAnwser;
	}

	/**
	 * Gets the player.
	 *
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	// ** SETTERS **

	/**
	 * Sets the correct anwser.
	 *
	 * @param correctAnwser the new correct anwser
	 */
	public void setCorrectAnwser(boolean correctAnwser) {
		this.correctAnwser = correctAnwser;
	}

	/**
	 * Sets the player.
	 *
	 * @param player the new player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	// ************************************************ CONSTRUCTORS

	/**
	 * Instantiates a new submission.
	 *
	 * @param answerFeaturedItem the answer featured item
	 * @param answerSelection    the answer selection
	 */
	public Submission(String employeeId, String optionId) {
		initSubmission(null, employeeId, optionId);
	}

	/**
	 * Instantiates a new submission.
	 *
	 * @param playerEmail        the player email
	 * @param answerFeaturedItem the answer featured item
	 * @param answerSelection    the answer selection
	 */
	public Submission(String playerEmail, String employeeId, String optionId) {
		// now we can decide if we have a correct answer
		initSubmission(playerEmail, employeeId, optionId);

	}

	// ************************************************ CLASS METHODS

	protected void initSubmission(String playerEmail, String employeeId, String optionId) {

		PlayerRepository playerRepo = new PlayerService().getPlayerRepository();
		
		// create player object
		Player player;
		
		// check if player exists
		if(playerRepo.existsById(playerEmail)) {
			player = playerRepo.findById(playerEmail).get();
			ArrayList<Question> questionsAsked = player.getQuestionsAsked();
			
		} else {
			
		}
		
		
		//Player player = PlayerService.
		
		// now we can decide if we have a correct answer
		this.correctAnwser = isCorrect(employeeId, optionId);
	}

	/**
	 * Checks if is correct.
	 *
	 * @param faceUrl     the face url
	 * @param displayName the display name
	 * @return true, if is correct
	 */
	protected boolean isCorrect(String employeeId, String optionId) {

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
	protected void recordPlayerStats(String playerEmail) {

	}

}
