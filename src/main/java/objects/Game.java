/* 
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or <architect@databuildr.com>
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met: 
 * 
 *   (1) Redistributions of source code must retain the above copyright notice this list of 
 *       conditions and the following disclaimer. 
 *   (2) Redistributions in binary form must reproduce the above copyright notice, this list 
 *       of conditions and the following disclaimer in the documentation and/or other materials 
 *       provided with the distribution. 
 *   (3) Neither the name of the copyright holder nor the names of its contributors may be 
 *       used to endorse or promote products derived from this software without specific prior 
 *       written permission.
 * 
 * Full license text is available at <https://opensource.org/licenses/BSD-3-Clause>
 */
package objects;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import enumerations.Mode;
import utils.Config;

/**
 * The Class Game.
 *
 * @author Michael Hoovler
 * @since Oct 5, 2018
 * 
 *        Purpose: <TODO: add purpose statement>
 */
@Entity
public class Game {

	// TODO TASK: TEMPORARY ***************************************************
	private ArrayList<ProfileEntry> sourceProfiles;
	public void setSourceProfiles(ArrayList<ProfileEntry> sourceProfiles) {
		this.sourceProfiles = sourceProfiles;
	}
	public ArrayList<ProfileEntry> getSourceProfiles() {
		return this.sourceProfiles;
	}
	// ************************************************************************
	
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(Game.class);

	/** The gameSessionId attribute of the Game object. */
	// ************************************************ API FIELDS
	private String gameSessionId;

	/** The playerEmail attribute of the Game object. */
	private String playerEmail;

	/** The leaderboard attribute of the Game object. */
	private Leaderboard leaderboard;

	/** The questions attribute of the Game object. */
	private ArrayList<Question> questions;

	/** The gameMode attribute of the Game object. */
	private Mode gameMode;

	// ************************************************ CONSTRUCTOR

	/**
	 * Instantiates a new game.
	 *
	 * @param playerEmail the player email
	 * @param mode        the mode
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Game(String playerEmail, String mode) throws IOException {

		log.info("CONSTRUCTOR: Game(String playerEmail=" + playerEmail + ", String mode=" + mode + ")");
		initGame(playerEmail, mode);
	}

	/**
	 * Gets the game session id.
	 *
	 * @return the game session id
	 */
	// ************************************************ SETTERS
	public String getGameSessionId() {
		return gameSessionId;
	}

	/**
	 * Gets the player email.
	 *
	 * @return the player email
	 */
	public String getPlayerEmail() {
		return playerEmail;
	}

	/**
	 * Gets the leaderboard.
	 *
	 * @return the leaderboard
	 */
	public Leaderboard getLeaderboard() {
		return leaderboard;
	}

	/**
	 * Gets the questions.
	 *
	 * @return the questions
	 */
	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * Gets the game mode.
	 *
	 * @return the game mode
	 */
	public Mode getGameMode() {
		return gameMode;
	}

	/**
	 * Sets the player email.
	 *
	 * @param playerEmail the new player email
	 */
	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	/**
	 * Sets the leaderboard.
	 *
	 * @param leaderboard the new leaderboard
	 */
	public void setLeaderboard(Leaderboard leaderboard) {
		this.leaderboard = leaderboard;
	}

	/**
	 * Sets the questions.
	 *
	 * @param questions the new questions
	 */
	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	// ************************************************ INITIALIZE CLASS

	/**
	 * Inits the game.
	 *
	 * @param email    the email
	 * @param gameMode the game mode
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void initGame(String email, String gameMode) throws IOException {

		log.info("METHOD: private void initGame()");

		// gameMode String to Mode
		setGameMode(gameMode);

		// would normally add pattern-based email validation...
		this.playerEmail = email;

		// retrieve leaderboard information from Leaderboard object previously built
		// from a leaderboard.json file
		this.leaderboard = initLeaderboard(email);

		// read profile JSON from given URL as data for Questions list
		ArrayList<ProfileEntry> profileEntries = initProfileData();
		this.sourceProfiles = profileEntries;

		switch (this.gameMode) {

		case REVERSED:
		case REVERSED_MATT:
			setQuestions(profileEntries, Config.FACENAME_MIN, Config.FACENAME_MAX);
		case NORMAL:
		case NORMAL_MATT:
		default:
			setQuestions(profileEntries, Config.FACENAME_MAX, Config.FACENAME_MIN);
		}

	}

	// ************************************************ PRIVATE METHODS

	/**
	 * Inits the game mode.
	 *
	 * @param gameMode the game mode
	 * @return the mode
	 */
	public void setGameMode(String gameMode) {

		log.info("initGameMode(): Enter...");

		// set it based on string value ...
		switch (gameMode) {
		case "2":
			this.gameMode = Mode.REVERSED;
		case "3":
			this.gameMode = Mode.NORMAL_MATT;
		case "4":
			this.gameMode = Mode.REVERSED_MATT;
		case "1":
		default:
			this.gameMode = Mode.NORMAL;
		}
	}

	/**
	 * Inits the leaderboard.
	 *
	 * @param email the email
	 * @return the leaderboard
	 */
	private Leaderboard initLeaderboard(String email) {
		log.info("METHOD: private String initLeaderboard()");
		Leaderboard leaderboard = new Leaderboard(email);

		// TODO: leaderboard data from persistent storage
		// String content = readFile("test.txt", StandardCharsets.UTF_8);

		return leaderboard;
	}

	/**
	 * Inits the profile data.
	 * 
	 * If "MATT" mode, either normal or reversed, only Matts are added to the
	 * returned list.
	 *
	 * @return the array list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private ArrayList<ProfileEntry> initProfileData() throws IOException {

		log.info("initProfileData(): Enter...");

		ArrayList<ProfileEntry> profileEntries = new ArrayList<ProfileEntry>();

		// connect to the datasource...
		log.info("Config.DATASOURCE_URL=" + Config.DATASOURCE_URL);
		URL url = new URL(Config.DATASOURCE_URL);
		URLConnection req = url.openConnection();
		req.connect();

		// log
		log.info("initProfileData(): JSON from URL = " + req.getContent().toString());

		// parse the root element out of the string...
		JsonParser jParse = new JsonParser();
		JsonElement root = jParse.parse(new InputStreamReader((InputStream) req.getContent()));

		// convert to an array of objects...
		JsonArray jsonProfiles = root.getAsJsonArray();

		// ArrayList<ProfileEntry> profiles = new ArrayList<ProfileEntry>();

		for (JsonElement jsonProfile : jsonProfiles) {

			// convert the profile entry into a JSON object
			JsonObject profile = jsonProfile.getAsJsonObject();

			// extract values
			String id = StringUtils.defaultIfBlank(profile.get("id").getAsString(), "");
			String firstName = StringUtils.defaultIfBlank(profile.get("firstName").getAsString(), "");
			String lastName = StringUtils.defaultIfBlank(profile.get("lastName").getAsString(), "");
			String displayName = lastName + ", " + firstName;
			// String headshotUrl =
			// StringUtils.defaultIfEmpty(profile.get("headshot").getAsJsonObject().get("url").getAsString(),
			// "");
			String headshotUrl = "http://foo.bar/foobar";
			boolean isMatt = StringUtils.containsIgnoreCase(firstName, "matt");

			// evaluate whether or not to even add the entry to the list
			if (StringUtils.isAllBlank(firstName, lastName) || StringUtils.isBlank(headshotUrl)) {

				// we can do nothing with a missing name or missing headshot url

			} else if (this.gameMode.ordinal() >= 2 && !isMatt) {

				// likewise, there's no point creating an object if this is matt mode, and the
				// name isn't matt

			} else {

				// now we can insert the profile entry object into the list!
				profileEntries.add(new ProfileEntry(id, lastName, firstName, displayName, isMatt, headshotUrl));
			}

		}

		// and return it.
		return profileEntries;
	}

	/**
	 * Sets the questions.
	 *
	 * @param profileEntries the profile entries
	 * @param gameMode       the game mode
	 */
	private void setQuestions(ArrayList<ProfileEntry> profileEntries, int numberFaces, int numberNames) {

		log.info("METHOD: private ArrayList<Question> initQuestions()");

		ArrayList<Question> questions = new ArrayList<Question>();

		// 1. pick random ProfileEntry as the correct answer
		// int numEntries = profileEntries.size();

		int randomIndex = (int) (Math.random() * profileEntries.size() - 1);
		log.info("randomIndex=" + String.valueOf(randomIndex));

		// profileEntries.get();
		// profileEntries.get(randomIndex);
		// 2.

		this.questions = questions;
	}

}
