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
package game.utils;

import java.util.HashMap;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigProperties {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(ConfigProperties.class);
	/** ******* */

	private static final String BUNDLE_NAME = "config"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public static HashMap<String, String> getProperties() {

		log.info("ENTERING ExternalProperties.HashMap<String, String> getProperties() {}");
		// new map that will contain the key/value pairs from the properties file
		HashMap<String, String> properties = new HashMap<>();

		for (String key : RESOURCE_BUNDLE.keySet()) {
			properties.put(key, RESOURCE_BUNDLE.getString(key));
		}
		return properties;
	}

}
