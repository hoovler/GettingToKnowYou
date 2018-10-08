/*
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under
 * BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or
 * <architect@databuildr.com>
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
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
package gtky.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gtky.models.Game;
import gtky.models.Results;
import gtky.utils.GtkyGlobals;

/**
 * The Class GameController.
 *
 * @author Michael Hoovler
 * @since  Oct 8, 2018
 * 
 *         <h2>PURPOSE</h2>
 *         <p>
 *         To...
 *         </p>
 * 
 *         <h2>TASKS</h2>
 *         <ul>
 *         <li>TODO: Generate repeatable, bi-directional hash with
 *         Question.featuredItem and
 *         Question.selections.get(<right-answer>)</li>
 *         <li>TODO: Ensure Game object has methods set from new instances of
 *         supporting objects</li>
 *         <li>TODO: Finish Implementing ResultsController endpoint chain</li>
 *         <li>TODO: Clean up code, comment stuff, commit to git, and
 *         submit!</li>
 *         </ul>
 * 
 */
@RestController
@RequestMapping("/gtky")
public class GameController {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(GameController.class);

    /**
     * Index.
     *
     * @return the string
     */
    @RequestMapping("/")
    public String index() {
        return "hello.html";
    }
    
    @RequestMapping("/error")
    public String error() {
        return "error.html";
    }

    /**
     * Game.
     *
     * @param  playerEmail
     *                           the player email
     * @param  gameMode
     *                           the game mode
     * @param  leaderboardLength
     *                           the leaderboard length
     * @return                   the game
     * @throws IOException
     *                           Signals that an I/O exception has occurred.
     */
    @RequestMapping("/game")
    public Game game(@RequestParam(value = "email") String playerEmail,
            @RequestParam(value = "gameMode", defaultValue = GtkyGlobals.s_GAMEMODE_DEFAULT) int gameMode,
            @RequestParam(value = "leaderboardLength",
                    defaultValue = GtkyGlobals.s_LEADERBOARD_LENGTH_DEFAULT) int leaderboardLength) throws IOException {

        log.info("ENDPOINT CONTROLLER: /gtky/game?email=" + playerEmail + "&gameMode=" + gameMode
                + "&leaderboardLength=" + leaderboardLength);
        return new Game(playerEmail, gameMode, leaderboardLength);
    }

    /**
     * Results.
     *
     * @param  playerEmail
     *                      the player email
     * @param  featuredItem
     *                      the featured item
     * @param  selectionId
     *                      the selection id
     * @return              the results
     */
    @RequestMapping("/game/submit")
    public Results results(@RequestParam(value = "email") String playerEmail,
            @RequestParam(value = "featuredItem") String featuredItem,
            @RequestParam(value = "selectionId") String selectionId) {

        return new Results(playerEmail, featuredItem, selectionId);
    }

}
