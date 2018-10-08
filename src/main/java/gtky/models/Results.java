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

public class Results extends GettingToKnowYou {

    /** The answer attribute of the Results object. */
    private boolean correctAnwser;

    public boolean isCorrectAnwser() {
        return correctAnwser;
    }

    public void setCorrectAnwser(boolean correctAnwser) {
        this.correctAnwser = correctAnwser;
    }

    /**
     * Instantiates a new results.
     *
     * @param  answerFeaturedItem
     *                            the answer featured item
     * @param  answerSelection
     *                            the answer selection
     * @param  playerEmail
     *                            the player email
     * @throws IOException
     *                            Signals that an I/O exception has occurred.
     */
    public Results(String playerEmail, String answerFeaturedItem, String answerSelection) {

        // now we can decide if we have a correct answer
        this.correctAnwser = isCorrect(answerFeaturedItem, answerSelection);
    }

    protected boolean isCorrect(String faceUrl, String displayName) {
        return true;
    }

    protected void recordPlayerStats(String playerEmail) {

    }

}
