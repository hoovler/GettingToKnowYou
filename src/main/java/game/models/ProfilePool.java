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
package game.models;

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

import game.utils.Global;
import game.utils.Utils;

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
public class ProfilePool {

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

	private static final String DATASOURCE_URI = Utils.getPropery(Global.k_protocol)
			+ Utils.getPropery(Global.k_datasource_uri);

	private static final String FIELD_ID = Utils.getPropery(Global.k_field_id);

	private static final String FIELD_FIRSTNAME = Utils.getPropery(Global.k_field_firstname);

	private static final String FIELD_LASTNAME = Utils.getPropery(Global.k_field_lastname);

	private static final String FIELD_HEADSHOT = Utils.getPropery(Global.k_field_headshot);

	private static final String FIELD_URL = Utils.getPropery(Global.k_field_headshot_url);

	private static final String FIELD_BONUS = Utils.getPropery(Global.k_bonus_field);

	private static final String BONUS_PREFIX = Utils.getPropery(Global.k_bonus_prefix);

	/*
	 * ********************** CONSTRUCTORS **********************
	 */

	public ProfilePool() {
		setProfiles(new ArrayList<ProfileEntry>());
	}

	/**
	 * Instantiates a new profile pool.
	 *
	 * @param gameMode the mode
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ProfilePool(GameMode gameMode) throws IOException {
		setProfiles(this.initProfiles(gameMode));
	}

	/*
	 * ********************** [G|S]etters **********************
	 */

	public ArrayList<ProfileEntry> getProfiles() {
		return this.profiles;
	}

	public void setProfiles(ArrayList<ProfileEntry> profiles) {
		this.profiles = profiles;
	}

	/*
	 * ********************** LOCAL METHODS **********************
	 */

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

		ArrayList<ProfileEntry> profileEntries = new ArrayList<>();

		// iterate through profile nodes...
		for (JsonElement jsonProfile : getAllElements(DATASOURCE_URI)) {

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
			if (urlObj != null && !urlObj.isJsonNull())
				headshotUrl = urlObj.getAsString();

			// calculate mattness...
			boolean isMatt = StringUtils.containsIgnoreCase(
					StringUtils.defaultIfBlank(profile.get(FIELD_BONUS).getAsString(), StringUtils.EMPTY),
					BONUS_PREFIX);

			// evaluate who gets added to the list...
			if (StringUtils.isAllBlank(firstName, lastName) || StringUtils.isBlank(headshotUrl)) {
				// don't add to the profile list
			} else if (gameMode.ordinal() >= 2) {
				// ... if matt mode, just add matts!
				if (isMatt)
					profileEntries.add(new ProfileEntry(id, lastName, firstName, displayName, isMatt, headshotUrl));
			} else {
				profileEntries.add(new ProfileEntry(id, lastName, firstName, displayName, isMatt, headshotUrl));
			}
		} // end for
			// and return it.
		log.info(String.format("EXIT: InitializeProfiles(); Number of profiles in ProfilePool object: %s",
				String.valueOf(profileEntries.size())));
		return profileEntries;
	} // end InitializeProfiles()

	/**
	 * Gets the profile ids.
	 *
	 * @return the profile ids
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ArrayList<String> getProfileIds() throws IOException {
		ArrayList<String> ids = new ArrayList<>();

		// get all elements
		for (JsonElement profileElem : getAllElements(null)) {

			// convert the profile entry into a JSON object
			JsonObject profile = profileElem.getAsJsonObject();

			// extract values
			ids.add(StringUtils.defaultIfBlank(profile.get(FIELD_ID).getAsString(), StringUtils.EMPTY));
		}

		return ids;
	}

	/**
	 * Gets the all elements.
	 *
	 * @param sourceUri the source uri
	 * @return the all elements
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected JsonArray getAllElements(String sourceUri) throws IOException {

		// get default if source is null
		String uri = StringUtils.defaultIfBlank(sourceUri, DATASOURCE_URI);

		// connect to the datasource...
		URL url = new URL(uri);
		URLConnection req = url.openConnection();
		req.connect();

		// parse the root element out of the string...
		JsonParser jParse = new JsonParser();
		JsonElement root = jParse.parse(new InputStreamReader((InputStream) req.getContent()));

		// convert to an array of objects...
		return root.getAsJsonArray();
	}

} // end class
