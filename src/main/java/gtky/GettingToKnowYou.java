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
package gtky;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.DomainValidator.ArrayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gtky.persistence.entities.Player;
import gtky.utils.ExternalProperties;

/**
 * The Class GettingToKnowYou.
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 * 
 *        Purpose: To act as a parent class for all application objects, to
 *        include resource, supporting, and persistence objects.
 */
public class GettingToKnowYou {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(GettingToKnowYou.class);
	/** ******* */
	
	// objects used throughout the application
	protected static final HashMap<String, String> properties = ExternalProperties.getProperties();
	
	
	
	/**
	 * Generate random email.
	 * 
	 * Try to generate a believable random email address that is comprised of a
	 * user's last name intial and first 5 characters of a last name. Names will
	 * randomly generated using rules that try to make them seem like legitimate
	 * human names; excludes names of those from the Small Magellanic Cloud and
	 * Sweden.
	 * 
	 * Only the handle portion of the address will be believable. I am not trying to
	 * verify whether 'comcast.gov' is a real domain; however, I am pulling actual
	 * random top level domains prefixes, and using a small list of suffixes.
	 * 
	 * @return email Example output for 5 emails = {'m.pinra@comcast.gov',
	 *         'z.ybtxp@cool.us', 'o.qdhiz@boats.gov', 'w.bdwdy@orange.edu',
	 *         'x.fxojr@sohu.org'}
	 */
	protected String generateRandomEmail() {

		// build handle and domain with random alpha & alphanumeric strings
		String lastInitial = RandomStringUtils.randomAlphabetic(1);
		String firstFive = RandomStringUtils.randomAlphabetic(5);

		// get valid generic top-level domains
		String[] domainPrefixes = DomainValidator.getTLDEntries(ArrayType.GENERIC_RO);

		// essentially, this removes the potentially-naughty domain names
		ArrayList<String> cleanPrefixes = new ArrayList<String>();
		for (String domain : domainPrefixes) {
			if (StringUtils.containsNone(domain, "--")) {
				cleanPrefixes.add(domain);
			}
		}

		// and make our random selection
		String domain = cleanPrefixes.get((int) (Math.random() * cleanPrefixes.size()));

		// set a few top-level domain codes and grab a random one
		String[] codes = { "com", "org", "gov", "us", "tech", "edu" };
		String code = codes[(int) (Math.random() * codes.length)];

		// put it all together
		return StringUtils.lowerCase(String.format("%s.%s@%s.%s", lastInitial, firstFive, domain, code));
	}

	/**
	 * Generate random player.
	 *
	 * @return the player
	 */
	protected Player generateRandomPlayer() {
		return generateRandomPlayer(generateRandomEmail());
	}

	/**
	 * Generate random player.
	 *
	 * @param playerEmail the player email
	 * @return the player
	 */
	protected Player generateRandomPlayer(String playerEmail) {

		
		String email = StringUtils.isNotBlank(playerEmail) ? playerEmail : generateRandomEmail();
		log.info("generateRandomPlayer(); Player(randomEmail=" + email +")");
		// either passed-in email,

		double averageTimeWrongMS = Math.random() * (60000 - 500) * (0.86 / 0.99);
		double averageTimeRightMS = Math.random() * (60000 - 500) * (0.86 / 0.99);
		long numberRight = (long) (Math.random() * 100);
		long numberWrong = (long) (Math.random() * 100);

		return new Player(email, numberRight, numberWrong, averageTimeRightMS, averageTimeWrongMS);
	}

}
