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
package gtky.models.supporting;

import java.io.IOException;
import java.util.ArrayList;

import gtky.GettingToKnowYou;
import gtky.utils.GameMode;
import gtky.utils.GtkyGlobals;

public class Question extends GettingToKnowYou {

    /** The featuredItem attribute of the Question object. */
    private String featuredItem;

    /** The selections attribute of the Question object. */
    private ArrayList<Selection> selections;

    // ************************************************ CONSTRUCTORS

    /**
     * Instantiates a new question.
     * 
     * If no GameMode, assumes GameMode.Normal
     *
     * @throws IOException
     *                     Signals that an I/O exception has occurred.
     */
    public Question() throws IOException {
        initQuestion(GameMode.NORMAL);
    }

    /**
     * Instantiates a new question.
     *
     * @param  gameMode
     *                     the game mode
     * @throws IOException
     *                     Signals that an I/O exception has occurred.
     */
    public Question(GameMode gameMode) throws IOException {
        initQuestion(gameMode);
    }

    // ************************************************ GETTERS

    public String getObject() {
        return featuredItem;
    }

    public ArrayList<Selection> getSelections() {
        return selections;
    }

    // ************************************************ PUBLIC SETTERS

    public void setObject(String object) {
        this.featuredItem = object;
    }

    public void setSelections(ArrayList<Selection> selections) {
        this.selections = selections;
    }

    // ************************************************ CLASS METHODS

    /**
     * void initQuestion(GameMode gameMode) Purpose: This method sets the Question
     * featuredItem's
     *
     * @param  gameMode
     *                     the game mode
     * @throws IOException
     *                     Signals that an I/O exception has occurred.
     */
    protected void initQuestion(GameMode gameMode) throws IOException {

        log.info("ENTER: initQuestion(GameMode gameMode)");
        // we don't want try setting values for nonexistent objects...
        this.featuredItem = new String();
        this.selections = new ArrayList<Selection>();

        // get pool of profiles based on game mode
        ProfilePool profiles = new ProfilePool(gameMode);
        // profilePool(gameMode);

        // get number of last index of profile pool; used multiple times
        int maxIndex = profiles.size() - 1;

        // matt modes already handled; dealing with reversed now...
        boolean isReversed = (gameMode.ordinal() % 2) == 0;

        // must be at least 6 profiles...
        if (maxIndex >= GtkyGlobals.NUM_SELECTIONS - 1) {

            // pick random profile that will be the featured matching answer
            int randomProfile = (int) (Math.random() * NUM_SELECTIONS);
            boolean isFeatured = false;

            // select random subset of 6 profiles to use...
            for (int i = 0; i < GtkyGlobals.NUM_SELECTIONS; i++) {

                // is featured?
                isFeatured = i == randomProfile;

                // get a random profile from the profile pool
                ProfileEntry profile = profiles.get((int) (Math.random() * maxIndex));

                // is mode reversed?
                if (isReversed) {

                    // ordinals are even, normal mode: 1 name, 6 faces
                    this.selections.add(new Selection(profile.getId(), profile.getPhotoUrl()));
                    if (isFeatured)
                        this.featuredItem = profile.getDisplayName();

                } else {

                    // ordinals are odd, reversed mode; 1 face, 6 names
                    this.selections.add(new Selection(profile.getId(), profile.getDisplayName()));
                    if (isFeatured)
                        this.featuredItem = profile.getPhotoUrl();

                } // end if(mode)
            } // end for(0:6)

        } else {

            log.warn("There aren't enough profiles from which to create options: maxIndex=" + String.valueOf(maxIndex)
                    + "; Number of profiles=" + String.valueOf(maxIndex + 1));
        }

    }

}
