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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gtky.GettingToKnowYou;

/**
 * The Class Option.
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 * 
 *        <h2>PURPOSE</h2>
 *        <p>
 *        To...
 *        </p>
 */
public class Option extends GettingToKnowYou {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(Option.class);
	/** ******* */

	/** The id attribute of the Option object. */
	private String id;

	/** The value attribute of the Option object. */
	private String value;

	/**
	 * Instantiates a new selection.
	 *
	 * @param id    the selection id
	 * @param value the selection value
	 */
	public Option(String id, String value) {

		log.info("CONSTRUCTOR Option(String id, String value){}");

		this.setId(id);
		this.setValue(value);
	}

	/**
	 * Gets the selection id.
	 *
	 * @return the selection id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the selection value.
	 *
	 * @return the selection value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the selection id.
	 *
	 * @param id the new selection id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the selection value.
	 *
	 * @param value the new selection value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
