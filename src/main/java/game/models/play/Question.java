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
package game.models.play;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.models.Employee;
import game.models.GameMode;
import game.models.ProfileEntry;
import game.models.ProfilePool;
import game.utils.Global;
import game.utils.Utils;

public class Question {
	/** Logging. */
	private static final Logger log = LoggerFactory.getLogger(Question.class);

	// ************************************************ API FIELDS

	/** The employee attribute of the Question object. */
	private Employee employee;

	/** The options attribute of the Question object. */
	private ArrayList<Option> options;

	/** * MEMBERS. */

	private static final String HEADSHOT_URL_PROTOCOL = Utils.getPropery(Global.k_protocol);

	private static final int NUM_SELECTIONS = Global.DEFAULT_NUM_SELECTIONS;

	private final Random random = new Random();

	// ************************************************ CONSTRUCTORS

	/**
	 * Instantiates a new question.
	 *
	 * If no GameMode, assumes GameMode.Normal
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Question() throws IOException {
		this.employee = new Employee();
		this.options = new ArrayList<>();
	}

	/**
	 * Instantiates a new question.
	 *
	 * @param gameMode the game mode
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Question(GameMode gameMode) throws IOException {
		this.initQuestion(gameMode);
	}

	/** * GETTERS. */

	public Employee getEmployee() {
		return this.employee;
	}

	public ArrayList<Option> getOptions() {
		return this.options;
	}

	/** * SETTERS. */

	public void setEmployee(final Employee employee) {
		this.employee = employee;
	}

	public void setOptions(final ArrayList<Option> options) {
		this.options = options;
	}

	// ************************************************ CLASS METHODS

	/**
	 * Create an object containing a 'subject' and six 'options' from which to
	 * choose. Each element of the return object - both 'subject' and 'options' - is
	 * composed of two string: an ID and the value of the element. For example, in
	 * normal mode, the object will contain an employee name, and six image URLs
	 * from which to select a matching headshot.
	 *
	 * I know this is a complicated method, and were this a regular application, I'd
	 * work on refactoring it to reduce complexity - which usually increases
	 * reusability.
	 *
	 * @param gameMode GameMode
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void initQuestion(final GameMode gameMode) throws IOException {
		log.info("ENTER: initQuestion(GameMode gameMode)");
		// we don't want try setting values for nonexistent objects...
		this.employee = new Employee();
		this.options = new ArrayList<>();

		// get pool of profiles based on game mode
		final ProfilePool profilePoolClass = new ProfilePool(gameMode);
		final ArrayList<ProfileEntry> profiles = profilePoolClass.getProfiles();

		// get number of last index of profile pool; used multiple times
		final int maxIndex = profiles.size() - 1;

		// matt modes already handled; dealing with reversed now...
		final boolean isReversed = gameMode.ordinal() % 2 == 0;

		// must be at least 6 profiles...
		if (maxIndex >= NUM_SELECTIONS - 1) {
			// pick random profile that will be the employee profile used for answer
			final int randomProfile = this.random.nextInt(NUM_SELECTIONS);
			boolean useAsAnswer = false;
			int currentNum = maxIndex;

			// select random subset of 6 profiles to use...
			for (int i = 0; i < NUM_SELECTIONS; i++) {
				// get a random profile from the profile pool
				final int randIndex = this.random.nextInt(currentNum);
				final ProfileEntry profile = profiles.get(randIndex);
				final String profileId = profile.getId();

				// is answer? (i hate the format, but its simplest)
				useAsAnswer = i == randomProfile;

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

				// rookie mistake - must remove this profile from the selection pool
				profiles.remove(randIndex);
				currentNum--;
			} // end for(0:6)
		} else {
			log.warn("There aren't enough profiles from which to create options: maxIndex=" + maxIndex
					+ "; Number of profiles=" + (maxIndex + 1));
		}
	}
}
