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

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gtky.GettingToKnowYou;
import gtky.persistence.PlayerService;
import gtky.persistence.entities.Player;
import gtky.persistence.repositories.PlayerRepository;
import gtky.utils.GameMode;
import gtky.utils.Global;

/**
 * The Class Game.
 *
 * @author Michael Hoovler
 * @since  Oct 5, 2018
 * 
 *         PURPOSE
 * 
 *         --------
 * 
 *         The Game object is the type that gets returned upon a connection to
 *         the /gtky/game endpoint. Its members include:
 * 
 *         <ul>
 *         <l1> A Player object</li>
 *         <li>
 * 
 */
public class Game extends GettingToKnowYou {
	
	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(Game.class);
	/** ******* */

    // ************************************************ API FIELDS

    /** The leaderboard attribute of the Game object. */
    private Leaderboard leaderboard;

    /** The questions attribute of the Game object. */
    private Question question;

    // ************************************************ INTERNAL FIELDS
    
    /** The playerEmail attribute of the Game object. */
    private Player player;
    
    /** The gameMode attribute of the Game object. */
    private GameMode gameMode;

    // ************************************************ GETTERS
    
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public Question getQuestion() {
        return question;
    }
    
    protected Player getPlayer() {
        return player;
    }

    protected GameMode getGameMode() {
        return gameMode;
    }

    // ************************************************ SETTERS
    
    protected void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }
    
    protected void setPlayer(Player player) {
    	this.player = player;
    }
    
    

    public void setQuestion(Question question) {
        this.question = question;
    }

    // ************************************************ CONSTRUCTORS

    /**
     * Instantiates a new game.
     */
    public Game() {
        log.info("NOOP constructor...");
    }

    public Game(String playerEmail) throws IOException {
        setupGame(playerEmail, Global.DEFAULT_GAMEMODE, Global.DEFAULT_LEADLEN);
    }

    /**
     * Instantiates a new game.
     *
     * @param  playerEmail
     *                     the leaderboard email
     * @param  mode
     *                     the mode
     * @throws IOException
     *                     Signals that an I/O exception has occurred.
     */
    public Game(String playerEmail, int mode) throws IOException {
        setupGame(playerEmail, mode, Global.DEFAULT_LEADLEN);
    }

    /**
     * Instantiates a new game.
     *
     * @param  playerEmail
     *                     the leaderboard email
     * @param  mode
     *                     the mode
     * @throws IOException
     *                     Signals that an I/O exception has occurred.
     */
    public Game(String playerEmail, int mode, int leaderboardLength) throws IOException {
        setupGame(playerEmail, mode, leaderboardLength);
    }

    // ************************************************ INITIALIZE CLASS

    /**
     * Inits the game.
     *
     * @param  email
     *                     the email
     * @param  gameMode
     *                     the game mode
     * @throws IOException
     *                     Signals that an I/O exception has occurred.
     */
    protected Game setupGame(String email, int intGameMode, int leaderboardLength) throws IOException {

        log.info("METHOD: private void initGame()");

        // validate value for gameMode...
        GameMode[] modes = GameMode.values();
        int numModes = modes.length;

        // use NORMAL if out of bounds...
        GameMode gameMode = (intGameMode <= numModes && intGameMode >= 0) ? modes[intGameMode - 1] : GameMode.NORMAL;

        // ...and set it
        this.gameMode = gameMode;

        // would normally add pattern-based email validation...
        

        // retrieve leaderboard information from Player object previously built
        // from a leaderboard.json file
        this.leaderboard = new Leaderboard(leaderboardLength);

        // read profile JSON from configured URL as data for Questions list
        // this.profilePool = new ProfilePool().InitializeProfiles(mode);
        this.question = new Question(gameMode);

        // and record the correct answer for when the player validates it
        Player player = new Player(email);
        player.addQuestionAsked(this.question);
        this.player = player;
        //this.player.updatePlayer();
        //this.player = new Player(email);
        return this;
    }
    
    /**
	 * Setup player.
	 *
	 * @param playerEmail the player email
	 * @return the player
	 */
    protected Player setupPlayer(String playerEmail) {
    	String email = StringUtils.lowerCase(playerEmail);
		PlayerRepository playerRepo = new PlayerService().getPlayerRepository();
		Player player;
		// check if player exists
		if(playerRepo.existsById(email)) {
			player = playerRepo.findById(email).get();
			//ArrayList<Question> questionsAsked = player.getQuestionsAsked();
		} else {
			player = new Player(email);
		}
		
		return player;
		//this.player.addQuestionAsked(this.question);
    }
}
