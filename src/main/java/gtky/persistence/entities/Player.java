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
package gtky.persistence.entities;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gtky.GettingToKnowYou;
import gtky.models.Question;
import gtky.persistence.PlayerService;
import gtky.persistence.repositories.PlayerRepository;
/**
 * The Class Player.
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 * 
 *        The Player class the only object in the application that requires
 *        persistence, which allows a record of player statistics to be
 *        retained.
 * 
 *        TODO: I have not fully implemented this persistence, but I HAVE laid
 *        out the groundwork for it.
 * 
 *        - The Player class implements the abstract class
 *        gtky.persistence.PlayerRepository, the abstract methods for which must
 *        be implemented to correctly use the Player class such that it can be
 *        used as part of the Leaderboard object within the /gtky/game? endpoint
 *        response. Additionall, the methods of the Player class have been give
 *        the 'protected' access modifier so they can be overridden by any
 *        inheriting class.
 * 
 *        - The gtky.utils.LeaderboardReposity implements the
 *        JpaRepository<Player, String> interface, accepting the Player object
 *        as the record type, and Player.playerEmail as the key.
 */
@Entity
public class Player extends GettingToKnowYou {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	/** ******* */
	
	// **************************************************************** API FIELDS

	/** The playerEmail attribute of the Player object. */
	@Id
	private String playerEmail;

	/** The numberRight attribute of the Player object. */
	private long numberRight;

	/** The numberWrong attribute of the Player object. */
	private long numberWrong;

	/** The averageTimeRightMS attribute of the Player object. */
	private double averageTimeRightMS;

	/** The averageTimeWrongMS attribute of the Player object. */
	private double averageTimeWrongMS;

	/** The correct answer for the last question posed to this player. */
	private ArrayList<Question> questionsAsked;

	// ** GETTERS **

	/**
	 * Gets the player email.
	 *
	 * @return the player email
	 */
	public String getPlayerEmail() {
		return playerEmail;
	}

	public ArrayList<Question> getQuestionsAsked() {
		return questionsAsked;
	}

	/**
	 * Gets the number right.
	 *
	 * @return the number right
	 */
	public long getNumberRight() {
		return numberRight;
	}

	/**
	 * Gets the number wrong.
	 *
	 * @return the number wrong
	 */
	public long getNumberWrong() {
		return numberWrong;
	}

	/**
	 * Gets the average time to select right.
	 *
	 * @return the average time to select right
	 */
	public double getAverageTimeRightMS() {
		return averageTimeRightMS;
	}

	/**
	 * Gets the average time to select wrong.
	 *
	 * @return the average time to select wrong
	 */
	public double getAverageTimeWrongMS() {
		return averageTimeWrongMS;
	}

	// ** SETTERS **

	/**
	 * Sets the player email.
	 *
	 * @param playerEmail the new player email
	 */
	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = StringUtils.lowerCase(playerEmail);

	}

	/**
	 * Sets the number right.
	 *
	 * @param numberRight the new number right
	 */
	public void setNumberRight(long numberRight) {
		this.numberRight = numberRight;
	}

	/**
	 * Sets the number wrong.
	 *
	 * @param numberWrong the new number wrong
	 */
	public void setNumberWrong(long numberWrong) {
		this.numberWrong = numberWrong;
	}

	/**
	 * Sets the average time to select right.
	 *
	 * @param averageTimeRightMS the new average time to select right
	 */
	public void setAverageTimeRightMS(double averageTimeRightMS) {
		this.averageTimeRightMS = averageTimeRightMS;
	}

	/**
	 * Sets the average time to select wrong.
	 *
	 * @param averageTimeWrongMS the new average time to select wrong
	 */
	public void setAverageTimeWrongMS(double averageTimeWrongMS) {
		this.averageTimeWrongMS = averageTimeWrongMS;
	}

	/**
	 * Sets the previous.
	 *
	 * @param previous the new previous
	 */
	protected void setQuestionsAsked(ArrayList<Question> questions) {
		this.questionsAsked = questions;
	}

	public void addQuestionAsked(Question questionAsked) {
		this.questionsAsked.add(questionAsked);
	}

	// **************************************************************** CONSTRUCTORS

	public Player() {
		// I been through the desert on a horse with no name...
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param playerEmail the player email
	 */
	public Player(String playerEmail) {
		initPlayer(playerEmail, false);
	}

	public Player(boolean genRandomPlayer) {
		initPlayer(null, genRandomPlayer);
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param playerEmail        the player email
	 * @param numberRight        the number right
	 * @param numberWrong        the number wrong
	 * @param averageTimeRightMS the average time right MS
	 * @param averageTimeWrongMS the average time wrong MS
	 */
	public Player(String playerEmail, long numberRight, long numberWrong, double averageTimeRightMS,
			double averageTimeWrongMS) {

		setPlayerEmail(playerEmail);
		setNumberRight(numberRight);
		setNumberWrong(numberWrong);
		setAverageTimeRightMS(averageTimeRightMS);
		setAverageTimeWrongMS(averageTimeWrongMS);
	}

	// **************************************************************** CLASS
	// METHODS

	public void updatePlayer() {

		PlayerRepository playerRepo = new PlayerService().getPlayerRepository();

		// check if player exists
		if (playerRepo.existsById(this.playerEmail)) {
			// this.player = playerRepo.findById(playerEmail).get();
			// ArrayList<Question> questionsAsked = player.getQuestionsAsked();
			// update stats if it does
			updatePlayerStats();
		}
		playerRepo.save(this);

	}

	//
	public void updatePlayerStats() {

	}

	/**
	 * Inits the player.
	 */
	protected void initPlayer(String playerEmail, boolean random) {

		Player player;
		String email = StringUtils.lowerCase(playerEmail);

		// if no email and not random, could be a problem...
		if (StringUtils.isBlank(playerEmail) && !random) {
			log.warn("Impossible...");
		} else {
			// if random
			if (random) {
				// pull a player from thin air... including random email!
				player = generateRandomPlayer();
				this.averageTimeRightMS = player.averageTimeRightMS;
				this.averageTimeWrongMS = player.averageTimeWrongMS;
				this.numberRight = player.numberRight;
				this.numberWrong = player.numberWrong;
				this.playerEmail = player.playerEmail;

			} else {

				// or initialize everything
				this.averageTimeRightMS = 0;
				this.averageTimeWrongMS = 0;
				this.numberRight = 0;
				this.numberWrong = 0;
				this.playerEmail = email;
				this.questionsAsked = new ArrayList<Question>();
			}
		}

	} // end method

	// **************************************************************** UTILITIES

	/**
	 * To json.
	 *
	 * @return the string
	 */
	public String toJson() {
		return String.format(
				"Player ['id':'%s', 'playerEmail':'%s', 'numberRight':'%s', 'numberWrong':'%s', 'averageTimeRightMS':'%s', 'averageTimeWrongMS':'%s']",
				playerEmail, String.valueOf(numberRight), String.valueOf(numberWrong),
				String.valueOf(averageTimeRightMS), String.valueOf(averageTimeWrongMS));
	}

	protected boolean isPlayerNew(String playerEmail) {

		return true;
	}

	protected Player getNewPlayer(String playerEmail) {
		this.playerEmail = playerEmail;
		return this;
	}

	protected Player getReturningPlayer(String playerEmail) {
		return this;
	}

}
