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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gtky.GettingToKnowYou;

/**
 * The Class ProfileEntry.
 *
 * @author Michael Hoovler
 * @since Oct 4, 2018
 * 
 *        Purpose: The container for profile-related data.
 * 
 */
public class ProfileEntry extends GettingToKnowYou {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(ProfileEntry.class);
	/** ******* */

	// ************************************************ FIELDS

	/** The id attribute of the ProfileEntry object. */
	private String id;

	/** The lastName attribute of the ProfileEntry object. */
	private String lastName;

	/** The firstName attribute of the ProfileEntry object. */
	private String firstName;

	/** The displayName attribute of the ProfileEntry object. */
	private String displayName;

	/** The isMatt attribute of the ProfileEntry object. */
	private boolean isBonusMatch;

	/** The photoUrl attribute of the ProfileEntry object. */
	private String photoUrl;

	// ************************************************ CONSTRUCTORS

	/**
	 * Instantiates a new employee.
	 *
	 * @param id          the id
	 * @param lastName    the last name
	 * @param firstName   the first name
	 * @param displayName the display name
	 * @param isMatt      the is matt
	 * @param photoUrl    the photo url
	 */
	public ProfileEntry(String id,
						String lastName,
						String firstName,
						String displayName,
						boolean isMatt,
						String photoUrl) {

		log.info("CONSTRUCTOR ProfileEntry(id, last, first, display, isBonus, url)");
		this.setId(id);
		this.setLastName(lastName);
		this.setFirstName(firstName);
		this.setDisplayName(displayName);
		this.setMatt(isMatt);
		this.setPhotoUrl(photoUrl);

	}

	// ************************************************ [G|S]etters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isMatt() {
		return isBonusMatch;
	}

	public void setMatt(boolean isMatt) {
		this.isBonusMatch = isMatt;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * Sets the photo url.
	 *
	 * @param photoUrl the new photo url
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	// ************************************************ MEMBER METHODS

}
