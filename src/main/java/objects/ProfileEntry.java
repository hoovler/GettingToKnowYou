/* 
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or <architect@databuildr.com>
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted 
 * provided that the following conditions are met: 
 * 
 *   (1) Redistributions of source code must retain the above copyright notice this list of 
 *       conditions and the following disclaimer. 
 *   (2) Redistributions in binary form must reproduce the above copyright notice, this list 
 *       of conditions and the following disclaimer in the documentation and/or other materials 
 *       provided with the distribution. 
 *   (3) Neither the name of the copyright holder nor the names of its contributors may be 
 *       used to endorse or promote products derived from this software without specific prior 
 *       written permission.
 * 
 * Full license text is available at <https://opensource.org/licenses/BSD-3-Clause>
 */

package objects;

/**
 * The Class ProfileEntry.
 *
 * @author Michael Hoovler
 * @since Oct 4, 2018
 * 
 * Purpose: The container for employee-related information.
 * 				- Most data is from the source URL
 * 				- Some fields are derived
 *       
 * Details: After building the API model under the assumption that
 *          all objects (including sub-objects; IE: headshot) had
 *          unique IDs, I profiled the data more closely to discover
 *          that was not the case.  Per my data profile:
 *          
 *          type		num_objects num_valid_ids
 *          ------		------		------
 *          people		153			153
 *          images		153			153
 *          linkedin	16			0
 *          twitter		2			0
 *          google		1			0
 *          
 *          The data lead me to restructure the object model representing
 *          the JSON source with the following assumptions:
 *          
 *          (1) All primary objects are of 'type':'people'
 *            - Each 'people' type has a non-null unique ID
 *          (3) All 'people' objects have one, and only one, 'image' sub-type containing some content
 *            - Most 'image' subtypes have their own unique IDs
 *            - Those which don't are populated with default, placeholder information
 *          (5) All 'people' objects also contain one, and only one, 'socialLinks' object
 *            - All 'socialLinks' are of an array
 *            - Most 'socialLinks' subtypes are empty arrays 
 *            - Those 'socialLinks' subtypes with content 
 */
public class ProfileEntry {
	
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
	private boolean isMatt;
	
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
	public ProfileEntry(String id, String lastName, String firstName, String displayName, boolean isMatt, String photoUrl) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.displayName = displayName;
		this.isMatt = isMatt;
		this.photoUrl = photoUrl;
	}

	// ************************************************ GETTERS
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the display name.
	 *
	 * @return the display name
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Checks if is matt.
	 *
	 * @return true, if is matt
	 */
	public boolean isMatt() {
		return isMatt;
	}

	/**
	 * Gets the photo url.
	 *
	 * @return the photo url
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}
	
	// ************************************************ SETTERS

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the display name.
	 *
	 * @param displayName the new display name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Sets the matt.
	 *
	 * @param isMatt the new matt
	 */
	public void setMatt(boolean isMatt) {
		this.isMatt = isMatt;
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
