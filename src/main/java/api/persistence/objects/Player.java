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
package api.persistence.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import api.shared.utils.Utils;

/**
 * The Class Player.
 *
 * @author Michael Hoovler
 * @since Oct 10, 2018
 */
@Entity
public class Player {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(Player.class);
	// **************************************************************** API FIELDS

	/** The id attribute of the Player object. */
	// *** START of in-mem database fields ***********
	@Id
	@GeneratedValue
	private Long id;

	/** The playerEmail attribute of the Player object. */
	@Column(unique = true)
	private String playerEmail;

	// private Long historyId;
	// *** END of in-mem database fields *************/

	/** The numberRight attribute of the Player object. */
	@Column
	private Long numberRight;

	/** The numberWrong attribute of the Player object. */
	@Column
	private Long numberWrong;

	/** The averageTimeRightMS attribute of the Player object. */
	@Column
	private Double averageTimeRightMS;

	/** The averageTimeWrongMS attribute of the Player object. */
	@Column
	private Double averageTimeWrongMS;

	// CONSTRUCTORS
	public Player() {
		// I've been through the desert on a horse with no name...
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param playerEmail the player email
	 */
	public Player(final String playerEmail) {
		this.initPlayer(playerEmail, false);
	}

	/**
	 * Instantiates a new player.
	 *
	 * @param genRandomPlayer the gen random player
	 */
	public Player(final boolean genRandomPlayer) {
		this.initPlayer(null, genRandomPlayer);
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
	public Player(final String playerEmail, final Long numberRight, final Long numberWrong,
			final Double averageTimeRightMS, final Double averageTimeWrongMS) {
		this.setPlayerEmail(playerEmail);
		this.setNumberRight(numberRight);
		this.setNumberWrong(numberWrong);
		this.setAverageTimeRightMS(averageTimeRightMS);
		this.setAverageTimeWrongMS(averageTimeWrongMS);
	}

	// ** GETTERS **

	/**
	 * Gets the player email.
	 *
	 * @return the player email
	 */
	public String getPlayerEmail() {
		return this.playerEmail;
	}

	/**
	 * Gets the number right.
	 *
	 * @return the number right
	 */
	public Long getNumberRight() {
		return this.numberRight;
	}

	/**
	 * Gets the number wrong.
	 *
	 * @return the number wrong
	 */
	public Long getNumberWrong() {
		return this.numberWrong;
	}

	/**
	 * Gets the average time right MS.
	 *
	 * @return the average time right MS
	 */
	public Double getAverageTimeRightMS() {
		return this.averageTimeRightMS;
	}

	/**
	 * Gets the average time wrong MS.
	 *
	 * @return the average time wrong MS
	 */
	public Double getAverageTimeWrongMS() {
		return this.averageTimeWrongMS;
	}

	// ** SETTERS **

	/**
	 * Sets the player email.
	 *
	 * @param playerEmail the new player email
	 */
	public void setPlayerEmail(final String playerEmail) {
		this.playerEmail = StringUtils.lowerCase(playerEmail);
	}

	/**
	 * Sets the number right.
	 *
	 * @param numberRight the new number right
	 */
	public void setNumberRight(final Long numberRight) {
		this.numberRight = numberRight;
	}

	/**
	 * Sets the number wrong.
	 *
	 * @param numberWrong the new number wrong
	 */
	public void setNumberWrong(final Long numberWrong) {
		this.numberWrong = numberWrong;
	}

	/**
	 * Sets the average time right MS.
	 *
	 * @param averageTimeRightMS the new average time right MS
	 */
	public void setAverageTimeRightMS(final Double averageTimeRightMS) {
		this.averageTimeRightMS = averageTimeRightMS;
	}

	/**
	 * Sets the average time wrong MS.
	 *
	 * @param averageTimeWrongMS the new average time wrong MS
	 */
	public void setAverageTimeWrongMS(final Double averageTimeWrongMS) {
		this.averageTimeWrongMS = averageTimeWrongMS;
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
	protected void initPlayer(final String playerEmail, final boolean random) {
		Player player;
		final String email = StringUtils.lowerCase(playerEmail);

		// if no email and not random, could be a problem...
		if (StringUtils.isBlank(playerEmail) && !random)
			log.warn("Impossible...");
		else // if random
		if (random) {
			// pull a player from thin air... including random email!
			player = Utils.generateRandomPlayer();
			this.averageTimeRightMS = player.averageTimeRightMS;
			this.averageTimeWrongMS = player.averageTimeWrongMS;
			this.numberRight = player.numberRight;
			this.numberWrong = player.numberWrong;
			this.playerEmail = player.playerEmail;
		} else {
			// or initialize everything
			this.averageTimeRightMS = NumberUtils.DOUBLE_ZERO;
			this.averageTimeWrongMS = NumberUtils.DOUBLE_ZERO;
			this.numberRight = NumberUtils.LONG_ZERO;
			this.numberWrong = NumberUtils.LONG_ZERO;
			this.playerEmail = email;
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
				this.id, this.playerEmail, this.numberRight, this.numberWrong, this.averageTimeRightMS,
				this.averageTimeWrongMS);
	}

	/**
	 * Checks if is player new.
	 *
	 * @param playerEmail the player email
	 * @return true, if is player new
	 */
	protected boolean isPlayerNew(final String playerEmail) {
		return true;
	}

	/**
	 * Gets the new player.
	 *
	 * @param playerEmail the player email
	 * @return the new player
	 */
	protected Player getNewPlayer(final String playerEmail) {
		this.playerEmail = playerEmail;
		return this;
	}

	/**
	 * Gets the returning player.
	 *
	 * @param playerEmail the player email
	 * @return the returning player
	 */
	protected Player getReturningPlayer(final String playerEmail) {
		return this;
	}
}
