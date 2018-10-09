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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import gtky.GettingToKnowYou;
import gtky.utils.GameMode;
import gtky.utils.Global;

/**
 * The Class ProfilePool.
 *
 * @author Michael Hoovler
 * @since Oct 7, 2018
 * 
 *        Purpose: Extends ArrayList, setting all elements at once after reading
 *        the data source, parsing individual JSON entities, and creating the
 *        profile list based in the game mode.
 */
public class ProfilePool extends GettingToKnowYou {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(ProfilePool.class);
	/** ******* */

	/*
	 * ********************** API FIELDS **********************
	 */

	private ArrayList<ProfileEntry> profiles;

	/*
	 * ********************** LOCAL MEMBERS **********************
	 */

	private static final String DATASOURCE_URI = properties.get(Global.k_protocol) + properties.get(Global.k_datasource_uri);

	private static final String FIELD_ID = properties.get(Global.k_field_id);

	private static final String FIELD_FIRSTNAME = properties.get(Global.k_field_firstname);

	private static final String FIELD_LASTNAME = properties.get(Global.k_field_lastname);

	private static final String FIELD_HEADSHOT = properties.get(Global.k_field_headshot);

	private static final String FIELD_URL = properties.get(Global.k_field_headshot_url);

	private static final String FIELD_BONUS = properties.get(Global.k_bonus_field);

	private static final String BONUS_PREFIX = properties.get(Global.k_bonus_prefix);

	/*
	 * ********************** CONSTRUCTORS **********************
	 */

	public ProfilePool() {
		this.profiles = new ArrayList<ProfileEntry>();
	}

	/**
	 * Instantiates a new profile pool.
	 *
	 * @param gameMode the mode
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ProfilePool(GameMode gameMode) throws IOException {
		setLocalMembers();
		setProfiles(initProfiles(gameMode));
	}

	/*
	 * ********************** [G|S]etters **********************
	 */

	public ArrayList<ProfileEntry> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<ProfileEntry> profiles) {
		this.profiles = profiles;
	}

	/*
	 * ********************** LOCAL METHODS **********************
	 */
	
	protected void setLocalMembers() {
		
	}

	/**
	 * ArrayList\<ProfileEntry\> profilePool(GameMode mode)
	 *
	 * Purpose: To return a list of all the profiles with a valid 'headshot' URL, or
	 * a subset thereof for those with a 'firstName' starting with '[M|m]att', based
	 * on the mode featuredItem passed in.
	 * 
	 * This method uses Google's GSON JSON parsing to extract root-level JSON
	 * elements at the datasource, which is an array of JSON elements returned from
	 * accessing the API endpoint of the preconfigured URI.
	 * 
	 * @param GameMode mode; ~ The game mode, converted to a 'GameMode' featuredItem
	 * 
	 * @return ArrayList\<ProfileEntry\> profileEntries; ~ An ArrayList of
	 *         'ProfileEntry' objects that represent a subset of all profiles
	 *         available at the pre- configured URL.
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected ArrayList<ProfileEntry> initProfiles(GameMode gameMode) throws IOException {
		log.info("ENTER: ArrayList<ProfileEntry> InitializeProfiles(GameMode mode=" + gameMode.toString() + ")");

		ArrayList<ProfileEntry> profileEntries = new ArrayList<ProfileEntry>();

		// connect to the datasource...
		log.info("GtkyGlobals.DATASOURCE_URI=" + DATASOURCE_URI);
		URL url = new URL(DATASOURCE_URI);
		URLConnection req = url.openConnection();
		req.connect();

		// parse the root element out of the string...
		JsonParser jParse = new JsonParser();
		JsonElement root = jParse.parse(new InputStreamReader((InputStream) req.getContent()));

		// convert to an array of objects...
		JsonArray jsonProfiles = root.getAsJsonArray();

		// iterate through profile nodes...
		for (JsonElement jsonProfile : jsonProfiles) {

			// convert the profile entry into a JSON object
			JsonObject profile = jsonProfile.getAsJsonObject();

			// extract values
			String id = StringUtils.defaultIfBlank(profile.get(FIELD_ID).getAsString(), StringUtils.EMPTY);
			String firstName = StringUtils.defaultIfBlank(profile.get(FIELD_FIRSTNAME).getAsString(),
					StringUtils.EMPTY);
			String lastName = StringUtils.defaultIfBlank(profile.get(FIELD_LASTNAME).getAsString(), StringUtils.EMPTY);
			String displayName = lastName + ", " + firstName;

			// all elements have '$.headshot', but not all have '$.headshot.url'
			JsonElement urlObj = profile.get(FIELD_HEADSHOT).getAsJsonObject().get(FIELD_URL);
			String headshotUrl = "";

			// reset 'headshotUrl' if '$.headshot.url' exists...
			if (urlObj != null && !urlObj.isJsonNull()) {
				headshotUrl = urlObj.getAsString();
			}

			// calculate mattness...
			boolean isMatt = StringUtils.containsIgnoreCase(
					StringUtils.defaultIfBlank(profile.get(FIELD_BONUS).getAsString(), StringUtils.EMPTY),
					BONUS_PREFIX);

			// evaluate who gets added to the list...
			if (StringUtils.isAllBlank(firstName, lastName) || StringUtils.isBlank(headshotUrl)) {
				// log.info("Found profile with missing name OR no headshot image: SourceID = "
				// + id);
			} else if (gameMode.ordinal() >= 2) {
				// this is MATT MODE!
				// log.info("PROFILE ENTRIES: Matt GameMode!");

				// only add matts...
				if (isMatt) {
					// log.info("PROFILE ENTRIES: Matt found during MATT mode! Adding to
					// profileEntries list...");
					// ... if matt mode, just add matts!
					profileEntries.add(new ProfileEntry(id, lastName, firstName, displayName, isMatt, headshotUrl));
				}

			} else {
				// now we can insert the profile entry object into the list!
				profileEntries.add(new ProfileEntry(id, lastName, firstName, displayName, isMatt, headshotUrl));

			} // end else
		} // end for
			// and return it.
		log.info("EXIT: InitializeProfiles(); Number of profiles in ProfilePool object: "
				+ String.valueOf(profileEntries.size()));
		return profileEntries;
	} // end InitializeProfiles()

} // end class
