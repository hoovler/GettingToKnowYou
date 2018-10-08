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

import gtky.GettingToKnowYou;
import gtky.models.supporting.Leaderboard;
import gtky.models.supporting.Question;
import gtky.persistence.Player;
import gtky.utils.GameMode;

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



    // ****************************************

    /** The playerEmail attribute of the Game object. */
    private Player player;

    /** The leaderboard attribute of the Game object. */
    private Leaderboard leaderboard;

    /** The questions attribute of the Game object. */
    private Question question;

    /** The gameMode attribute of the Game object. */
    private GameMode gameMode;

    // ************************************************ GETTERS

    public Player getPlayer() {
        return player;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    public Question getQuestions() {
        return question;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    // ************************************************ SETTERS
    public void setPlayerEmail(Player player) {
        this.player = player;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setLeaderboard(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    public void setQuestions(Question question) {
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
        initGame(playerEmail, GAMEMODE_DEFAULT, LEADERBOARD_LENGTH_DEFAULT);
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
        initGame(playerEmail, mode, LEADERBOARD_LENGTH_DEFAULT);
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
        initGame(playerEmail, mode, leaderboardLength);
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
    protected Game initGame(String email, int intGameMode, int leaderboardLength) throws IOException {

        log.info("METHOD: private void initGame()");

        // gameMode String to GameMode
        // this.gameMode = setGameMode(gameMode);
        GameMode gameMode = GameMode.values()[intGameMode - 1];

        this.gameMode = gameMode;

        // would normally add pattern-based email validation...
        this.player = new Player(email);

        // retrieve leaderboard information from Player object previously built
        // from a leaderboard.json file
        this.leaderboard = new Leaderboard(leaderboardLength);

        // read profile JSON from configured URL as data for Questions list
        // this.profilePool = new ProfilePool().InitializeProfiles(mode);

        this.question = new Question(gameMode);

        return this;
    }
}
