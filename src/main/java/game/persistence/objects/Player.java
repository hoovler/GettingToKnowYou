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
package game.persistence.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Player.
 *
 * @author Michael Hoovler
 * @since Oct 10, 2018
 */
@Entity
@Table(name = "PLAYER")
public class Player {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	// **************************************************************** API FIELDS

	/** The id attribute of the Player object. */
	// *** START of in-mem database fields ***********
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	/** The playerEmail attribute of the Player object. */
	@Column(name = "EMAIL", unique = true)
	private String email;

	// private Long historyId;
	// *** END of in-mem database fields *************/

	/** The numberRight attribute of the Player object. */
	@Column(name = "NUM_RIGHT")
	private Long numRight;

	/** The numberWrong attribute of the Player object. */
	@Column(name = "NUM_WRONG")
	private Long numWrong;

	/** The averageTimeRightMS attribute of the Player object. */
	@Column(name = "RESPONSE_TIME_AVG")
	private Double responseTimeAvg;

	// CONSTRUCTORS
	public Player() {
		// I've been through the desert on a horse with no name...
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param playerEmail the player email
	 */
	public Player(String playerEmail) {
		this.initPlayer(playerEmail, false);
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param genRandomPlayer the gen random player
	 */
	public Player(boolean genRandomPlayer) {
		this.initPlayer(null, genRandomPlayer);
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param email         the email
	 * @param numRight      the num right
	 * @param numWrong      the num wrong
	 * @param avgAnswerTime the avg answer time
	 */
	public Player(String email, Long numRight, Long numWrong, Double responseTimeAvg) {
		this.setEmail(email);
		this.setNumRight(numRight);
		this.setNumWrong(numWrong);
		this.setResponseTimeAvg(responseTimeAvg);
	}

	// ** GETTERS **

	/**
	 * Gets the player email.
	 *
	 * @return the player email
	 */
	public String getPlayerEmail() {
		return this.email;
	}

	/**
	 * Gets the number right.
	 *
	 * @return the number right
	 */
	public Long getNumberRight() {
		return this.numRight;
	}

	/**
	 * Gets the number wrong.
	 *
	 * @return the number wrong
	 */
	public Long getNumberWrong() {
		return this.numWrong;
	}

	/**
	 * Gets the average time right MS.
	 *
	 * @return the average time right MS
	 */
	public Double getResponseTimeAvg() {
		return this.responseTimeAvg;
	}

	// ** SETTERS **

	/**
	 * Sets the player email.
	 *
	 * @param playerEmail the new player email
	 */
	public void setEmail(String email) {
		this.email = StringUtils.lowerCase(email);
	}

	/**
	 * Sets the number right.
	 *
	 * @param numberRight the new number right
	 */
	public void setNumRight(Long numRight) {
		this.numRight = numRight;
	}

	/**
	 * Sets the number wrong.
	 *
	 * @param numberWrong the new number wrong
	 */
	public void setNumWrong(Long numWrong) {
		this.numWrong = numWrong;
	}

	/**
	 * Sets the average time right MS.
	 *
	 * @param averageTimeRightMS the new average time right MS
	 */
	public void setResponseTimeAvg(Double responseTimeAvg) {
		this.responseTimeAvg = responseTimeAvg;
	}

	/**
	 * Update player.
	 */

	public void updatePlayer() {
		/*
		 * PlayerRepository playerRepo = new PlayerService().getPlayerRepository();
		 *
		 * // check if player exists if (playerRepo.existsById(this.playerEmail)) { //
		 * this.player = playerRepo.findById(playerEmail).get(); // ArrayList<Question>
		 * questionsAsked = player.getQuestionsAsked(); // update stats if it does
		 * updatePlayerStats(); } playerRepo.save(this);
		 */
	}

	/**
	 * Update player stats.
	 */
	public void updatePlayerStats() {
		// TODO: would be used for persistence
	}

	/**
	 * Inits the player.
	 *
	 * @param playerEmail the player email
	 * @param random      the random
	 */
	protected void initPlayer(String email, boolean random) {

		String emailLower = StringUtils.lowerCase(email);
		// TODO: call PlayerService, somehow...?s
		/*
		 * // if no email and not random, could be a problem... if
		 * (StringUtils.isBlank(emailLower) && !random) log.error("Impossible..."); else
		 * // if random if (random) { // pull a player from thin air... including random
		 * email! Player player = Utils.generateRandomPlayer(); this.responseTimeAvg =
		 * player.responseTimeAvg; this.numRight = player.numRight; this.numWrong =
		 * player.numWrong; this.email = player.email; } else {
		 *
		 * // TODO: check if player already exists...
		 *
		 * // PlayerService ps = new PlayerService();
		 *
		 * // or initialize everything this.responseTimeAvg = NumberUtils.DOUBLE_ZERO;
		 * this.numRight = NumberUtils.LONG_ZERO; this.numWrong = NumberUtils.LONG_ZERO;
		 * this.email = emailLower; }
		 */

	} // end method

	// **************************************************************** UTILITIES

	/**
	 * To json.
	 *
	 * @return the string
	 */
	public String toJson() {
		return String.format("Player ['id':'%s', 'email':'%s', 'numRight':'%s', 'numWrong':'%s', 'avgAnswerTime':'%s']",
				this.id, this.email, this.numRight, this.numWrong, this.responseTimeAvg);
	}

	/**
	 * Checks if is player new.
	 *
	 * @param playerEmail the player email
	 * @return true, if is player new
	 */
	public boolean isPlayerNew(String playerEmail) {

		// setup player repo object

		return true;
	}

	/**
	 * Gets the new player.
	 *
	 * @param playerEmail the player email
	 * @return the new player
	 */
	protected Player getNewPlayer(String playerEmail) {
		this.email = playerEmail;
		return this;
	}

	/**
	 * Gets the returning player.
	 *
	 * @param playerEmail the player email
	 * @return the returning player
	 */
	public Player getReturningPlayer(String playerEmail) {
		return this;
	}
}
