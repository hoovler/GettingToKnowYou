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
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gtky.GettingToKnowYou;
import gtky.models.supporting.ProfileEntry;
import gtky.models.supporting.ProfilePool;
import gtky.utils.GameMode;
import gtky.utils.Global;

public class Question extends GettingToKnowYou {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(Question.class);
	/** ******* */
	
	// ************************************************ API FIELDS
	
    /** The employee attribute of the Question object. */
    private Employee employee;

    /** The options attribute of the Question object. */
    private ArrayList<Option> options;
    
	// ************************************************ MEMBERS
    
    private static final String HEADSHOT_URL_PROTOCOL = properties.get(Global.k_protocol);
    
    private static final int NUM_SELECTIONS = Global.DEFAULT_NUM_SELECTIONS;

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

    public Employee getEmployee() {
        return employee;
    }

    public ArrayList<Option> getOptions() {
        return options;
    }

    // ************************************************ SETTERS

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    // ************************************************ CLASS METHODS

    /**
     * void initQuestion(GameMode gameMode) Purpose: This method sets the Question
     * employee's
     *
     * @param  gameMode
     *                     the game mode
     * @throws IOException
     *                     Signals that an I/O exception has occurred.
     */
    protected void initQuestion(GameMode gameMode) throws IOException {

        log.info("ENTER: initQuestion(GameMode gameMode)");
        // we don't want try setting values for nonexistent objects...
        this.employee = new Employee();
        this.options = new ArrayList<Option>();

        // get pool of profiles based on game mode
        ProfilePool profilePoolClass = new ProfilePool(gameMode);
        ArrayList<ProfileEntry> profiles = profilePoolClass.getProfiles();
        
        // profilePool(gameMode);

        // get number of last index of profile pool; used multiple times
        int maxIndex = profiles.size() - 1;

        // matt modes already handled; dealing with reversed now...
        boolean isReversed = (gameMode.ordinal() % 2) == 0;

        // must be at least 6 profiles...
        if (maxIndex >= NUM_SELECTIONS - 1) {

            // pick random profile that will be the employee profile used for answer
            int randomProfile = (int) (Math.random() * NUM_SELECTIONS);
            boolean useAsAnswer = false;
            int currentNum = maxIndex;

            // select random subset of 6 profiles to use...
            for (int i = 0; i < NUM_SELECTIONS; i++) {

                // get a random profile from the profile pool; currentNum gets decrimented at
                // end
                // of loop
                int randIndex = (int) (Math.random() * currentNum);
                ProfileEntry profile = profiles.get(randIndex);
                String profileId = profile.getId();

                // is answer? ternary here is actually easier to read than
                // 'useAsAnswer=i==randomProfile'
                useAsAnswer = (i == randomProfile) ? true : false;

                // is mode reversed?
                if (isReversed) {

                    // ordinals are even, normal mode: 6 mugs, 1 name
                    this.options.add(new Option(profile.getId(), HEADSHOT_URL_PROTOCOL + profile.getPhotoUrl()));
                    // if this is our lucky winner, set it as the employee
                    if (useAsAnswer)
                        this.employee = new Employee(profileId, profile.getDisplayName());

                } else {

                    // ordinals are odd, reversed mode; 1 face, 6 names
                    this.options.add(new Option(profile.getId(), profile.getDisplayName()));
                    // and likewise, random winner = set employee to the URL of the lovely mugshot
                    if (useAsAnswer)
                        this.employee = new Employee(profileId, HEADSHOT_URL_PROTOCOL + profile.getPhotoUrl());

                } // end if(mode)

                // remove this profile from the selection pool... found out what happens
                // otherwise while testing MATT mode
                profiles.remove(randIndex);
                currentNum--;
            } // end for(0:6)

        } else {

            log.warn("There aren't enough profiles from which to create options: maxIndex=" + String.valueOf(maxIndex)
                    + "; Number of profiles=" + String.valueOf(maxIndex + 1));
        }

    }

}
