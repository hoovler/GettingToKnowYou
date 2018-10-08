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
package gtky.persistence;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class Player.
 *
 * @author Michael Hoovler
 * @since  Oct 8, 2018
 * 
 *         PURPOSE
 * 
 *         --------
 * 
 *         The Player class the only object in the application that requires
 *         persistence, which allows a record of player statistics to be
 *         retained.
 * 
 *         ========
 * 
 *         NOTE
 * 
 *         --------
 * 
 *         TODO: I have not fully implemented this persistence, but I HAVE laid
 *         out the groundwork for it.
 * 
 *         - The Player class implements the abstract class
 *         gtky.persistence.PlayerPersistence, the abstract methods for which
 *         must be implemented to correctly use the Player class such that it
 *         can be used as part of the Leaderboard object within the /gtky/game?
 *         endpoint response. Additionall, the methods of the Player class have
 *         been give the 'protected' access modifier so they can be overridden
 *         by any inheriting class.
 * 
 *         - The gtky.utils.LeaderboardReposity implements the
 *         JpaRepository<Player, String> interface, accepting the Player object
 *         as the record type, and Player.playerEmail as the key.
 * 
 *         ========
 */
@Entity
public class Player extends PlayerPersistence {

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

    public Player() {

    }

    /**
     * Instantiates a new player.
     *
     * @param playerEmail
     *                    the player email
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
     * @param playerEmail
     *                           the player email
     * @param numberRight
     *                           the number right
     * @param numberWrong
     *                           the number wrong
     * @param averageTimeRightMS
     *                           the average time right MS
     * @param averageTimeWrongMS
     *                           the average time wrong MS
     */
    public Player(String playerEmail,
            long numberRight,
            long numberWrong,
            double averageTimeRightMS,
            double averageTimeWrongMS) {

        this.playerEmail = playerEmail;
        this.numberRight = numberRight;
        this.numberWrong = numberWrong;
        this.averageTimeRightMS = averageTimeRightMS;
        this.averageTimeWrongMS = averageTimeWrongMS;
    }

    // ************************************************ SETTERS

    /**
     * Sets the player email.
     *
     * @param playerEmail
     *                    the new player email
     */
    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    /**
     * Sets the number right.
     *
     * @param numberRight
     *                    the new number right
     */
    public void setNumberRight(Long numberRight) {
        this.numberRight = numberRight;
    }

    /**
     * Sets the number wrong.
     *
     * @param numberWrong
     *                    the new number wrong
     */
    public void setNumberWrong(Long numberWrong) {
        this.numberWrong = numberWrong;
    }

    /**
     * Sets the average time to select right.
     *
     * @param averageTimeRightMS
     *                           the new average time to select right
     */
    public void setAverageTimeToSelectRight(Long averageTimeToSelectRight) {
        this.averageTimeRightMS = averageTimeToSelectRight;
    }

    /**
     * Sets the average time to select wrong.
     *
     * @param averageTimeWrongMS
     *                           the new average time to select wrong
     */
    public void setAverageTimeToSelectWrong(Long averageTimeToSelectWrong) {
        this.averageTimeWrongMS = averageTimeToSelectWrong;
    }

    // ************************************************ GETTERS

    /**
     * Gets the player email.
     *
     * @return the player email
     */
    public String getPlayerEmail() {
        return playerEmail;
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

    // ************************************************ UTILITIES

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

    // ************************************************ CLASS METHODS

    /**
     * Inits the player.
     */
    protected void initPlayer(String playerEmail, boolean random) {

        Player player;

        // the nested ternary actually works, but isn't very readable :(
        // player = random ? generateRandomPlayer() : ((isPlayerNew(playerEmail)) ?
        // getNewPlayer(playerEmail) : getReturningPlayer(playerEmail));

        if (StringUtils.isBlank(playerEmail) && !random) {
            log.warn("");
        } else {
            // so here's the long-hand ...
            if (random) {
                // pull a player from thin air... ok for playerEmail to be null, it'll be
                // randomized
                player = generateRandomPlayer();
            } else {
                // ... with a non-nested ternary! :D
                player = (isPlayerNew(playerEmail)) ? getNewPlayer(playerEmail) : getReturningPlayer(playerEmail);
            }

            this.averageTimeRightMS = player.averageTimeRightMS;
            this.averageTimeWrongMS = player.averageTimeWrongMS;
            this.numberRight = player.numberRight;
            this.numberWrong = player.numberWrong;
            this.playerEmail = player.playerEmail;

        }
    } // fin

    /*
     * TODO: Always returns TRUE until implemented...
     * 
     * @see gtky.persistence.PlayerPersistence#isPlayerNew(java.lang.String)
     */
    @Override
    protected boolean isPlayerNew(String playerEmail) {
        return true;
    }

    /*
     * TODO: Implement persistence features
     * 
     * @see gtky.persistence.PlayerPersistence#addNewPlayer(java.lang.String)
     */
    @Override
    protected Player getNewPlayer(String playerEmail) {
        this.playerEmail = playerEmail;
        return this;
    }

    /*
     * TODO: DO NOT USE until implemented..
     * 
     * @see gtky.persistence.PlayerPersistence#getReturningPlayer(java.lang.String)
     */
    @Override
    protected Player getReturningPlayer(String playerEmail) {
        return this;
    }

    /*
     * TODO: DO NOT USE until implemented..
     * 
     * @see gtky.persistence.PlayerPersistence#getPlayerStats()
     */
    @Override
    protected HashMap<String, Long> getPlayerStats() {
        return null;
    }

}
