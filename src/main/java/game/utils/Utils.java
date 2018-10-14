/**
 *
 */
package game.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.DomainValidator.ArrayType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.google.gson.JsonObject;

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

	public static String getReadmeFile() throws IOException {
		return getReadmeFile("README.md");
	}

	public static String htmlGameDocs(String readmeFile) throws IOException {
		return renderReadme(getReadmeFile(readmeFile));
	}

	/**
	 * Gets the readme file.
	 *
	 * @param filename the filename
	 * @return the readme file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String getReadmeFile(String fileName) throws IOException {

		File folder = new File("").getAbsoluteFile();
		boolean fileFound = false;

		log.info("All files contained within " + folder.getAbsolutePath());
		for (File f : folder.listFiles()) {
			if (StringUtils.equalsIgnoreCase(f.getName(), fileName))
				fileFound = true;
			log.info(f.getAbsolutePath() + "; " + f.getName() + "; found? " + fileFound);

		}

		if (fileFound) {
			File file = new File(folder.getAbsolutePath() + "/" + fileName);
			if (file.exists()) {
				// FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(new FileReader(fileName));

				try {
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();

					while (line != null) {
						sb.append(line);
						sb.append("\n");
						line = br.readLine();
					}
					return sb.toString();
				} finally {
					br.close();
				}
			}
			log.warn(fileName + " cannot be found.");
			return null;
		}
		return null;
	}

	public static String renderReadme() throws IOException {
		return renderReadme(getReadmeFile());
	}

	public static String renderReadme(String markdown) throws IOException {

		// set local vars
		final String uri = "https://api.github.com/markdown";
		final String methodValue = "POST";

		// request variables
		Map<String, String> headers = new HashMap<>();
		Map<String, String> paramsMap = new HashMap<>();

		// request headers
		headers.put(HttpHeaders.ACCEPT, "text/html");
		headers.put(HttpHeaders.ACCEPT_LANGUAGE, "en-US");
		headers.put(HttpHeaders.HOST, "api.github.com");
		headers.put(HttpHeaders.CONNECTION, "keep-alive");
		headers.put(HttpHeaders.USER_AGENT, "Java client");

		// request parameters (JSON for Github API)
		paramsMap.put("text", markdown);
		paramsMap.put("mode", "markdown");

		// build request body
		JsonObject requestBody = new JsonObject();

		for (String key : paramsMap.keySet()) {
			requestBody.addProperty(key, paramsMap.get(key));
		}

		HttpURLConnection con;

		String urlParameters = requestBody.toString();
		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

		URL myurl = new URL(uri);
		con = (HttpURLConnection) myurl.openConnection();

		con.setDoOutput(true);
		con.setRequestMethod(methodValue);

		for (String key : headers.keySet()) {
			con.setRequestProperty(key, headers.get(key));
		}

		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
			wr.write(postData);
		}

		StringBuilder content;

		try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

			String line;
			content = new StringBuilder();

			while ((line = in.readLine()) != null) {
				content.append(line);
				content.append(System.lineSeparator());
			}
		}

		con.disconnect();
		log.info(content.toString());

		// return content
		return content.toString();

	}

}
