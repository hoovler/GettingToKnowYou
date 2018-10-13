/**
 *
 */
package game.utils;

import java.util.ArrayList;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.DomainValidator.ArrayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.persistence.objects.Player;

/**
 * The Class Utils.
 *
 * @author Michael Hoovler
 * @since Oct 10, 2018
 */
public class Utils {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(Utils.class);

	/**
	 * Gets an external property value from 'messages.properties'
	 *
	 * @param key the key
	 * @return the propery
	 */
	public static String getPropery(final String key) {
		return ExternalProperties.getString(key);
	}

	public boolean isValidSubmissionId(String id) {
		/*
		 * this is where, if persistence we working, I'd query the 'Audit' object's
		 * table for the the ID passed into the /submit endpoint in order to see whether
		 * the submitting player was ever asked a question with
		 */

		return true;
	}

	/**
	 * Generate random email.
	 *
	 * @return the string
	 */
	public static String generateRandomEmail() {

		// build handle and domain with random alpha & alphanumeric strings
		final String lastInitial = RandomStringUtils.randomAlphabetic(1);
		final String firstFive = RandomStringUtils.randomAlphabetic(5);

		// get valid generic top-level domains
		final String[] domainPrefixes = DomainValidator.getTLDEntries(ArrayType.GENERIC_RO);

		// essentially, this removes the potentially-naughty domain names
		final ArrayList<String> cleanPrefixes = new ArrayList<>();
		for (final String domain : domainPrefixes)
			if (StringUtils.containsNone(domain, "--"))
				cleanPrefixes.add(domain);

		// and make our random selection
		final String domain = cleanPrefixes.get(new Random().nextInt(cleanPrefixes.size()));

		// set a few top-level domain codes and grab a random one
		final String[] codes = { "com", "org", "gov", "us", "tech", "edu" };
		final String code = codes[(int) (Math.random() * codes.length)];

		// put it all together
		return StringUtils.lowerCase(String.format("%s.%s@%s.%s", lastInitial, firstFive, domain, code));
	}

	/**
	 * Generate random player.
	 *
	 * @return the player
	 */
	public static Player generateRandomPlayer() {
		return generateRandomPlayer(generateRandomEmail());
	}

	/**
	 * Generate random player.
	 *
	 * @param playerEmail the player email
	 * @return the player
	 */
	public static Player generateRandomPlayer(final String playerEmail) {

		final String email = StringUtils.isNotBlank(playerEmail) ? playerEmail : generateRandomEmail();
		log.info("generateRandomPlayer(); Player(randomEmail=" + email + ")");
		// either passed-in email,

		final Double responseTimeAvg = Double.valueOf(Math.random() * (60000 - 500) * (0.86 / 0.99));
		final Long numberRight = Long.valueOf((long) Math.random() * 100);
		final Long numberWrong = Long.valueOf((long) Math.random() * 100);

		return new Player(email, numberRight, numberWrong, responseTimeAvg);
	}

}
