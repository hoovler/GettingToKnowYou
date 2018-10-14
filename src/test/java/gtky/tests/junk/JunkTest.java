/**
 *
 */
package gtky.tests.junk;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import com.google.gson.JsonObject;

import game.utils.Utils;

/**
 * The Class StaticTests.
 *
 * @author Michael Hoovler
 * @since Oct 13, 2018
 */
public class JunkTest {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(JunkTest.class);

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// test the Github Markdown render feature
		try {
			log.info("testRenderMarkdownA(): " + testRenderMarkdownA());
			// log.info("testRenderMarkdownB(): " + testRenderMarkdownB());

			// read static file
			log.info("getReadmeFile(README.md): " + getReadmeFile("README.md"));
			log.info("testRenderMarkdownC(getReadmeFile(\"README.md\")) = "
					+ testRenderMarkdownC(getReadmeFile("README.md")));
			// log.info("testRenderMarkdownC(): " + testRenderMarkdownC());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test markdown.
	 *
	 * @throws URISyntaxException
	 * @throws IOException
	 */
	private static String testRenderMarkdownA() throws URISyntaxException, IOException {
		// set local vars
		final String uri = "https://api.github.com/markdown";
		final String methodValue = "POST";

		// request variables
		Map<String, String> headers = new HashMap<>();
		Map<String, String> cookiesMap = new HashMap<>();
		Map<String, String> paramsMap = new HashMap<>();

		// request headers
		headers.put(HttpHeaders.ACCEPT, "text/html");
		headers.put(HttpHeaders.ACCEPT_LANGUAGE, "en-US");
		headers.put(HttpHeaders.HOST, "api.github.com");
		headers.put(HttpHeaders.CONNECTION, "keep-alive");
		headers.put(HttpHeaders.USER_AGENT, "Java client");

		// request cookies
		// cookiesMap.put("dotcom_user", "hoovler");
		cookiesMap.put("logged_in", "yes");

		// request parameters (JSON for Github API)
		paramsMap.put("text", "**_Hola_ Mundo!**");
		paramsMap.put("mode", "markdown");

		// build request body
		JsonObject requestBody = new JsonObject();

		for (String key : paramsMap.keySet()) {
			requestBody.addProperty(key, paramsMap.get(key));
		}

		// make request
		// ***************************************************
		// From example: http://zetcode.com/java/getpostrequest/
		// ***************************************************
		// String urlParameters = "name=Jack&occupation=programmer";
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

		return content.toString();
	}

	/**
	 * Test render markdown B.
	 *
	 * @return the string
	 * @throws IOException
	 */
	private static String testRenderMarkdownB() throws IOException {
		return Utils.renderReadme();
	}

	/**
	 * Test render markdown C.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String testRenderMarkdownC(String markdown) throws IOException {
		final String uri = "https://api.github.com/markdown";
		final String methodValue = "POST";

		// request variables
		Map<String, String> headers = new HashMap<>();
		Map<String, String> cookiesMap = new HashMap<>();
		Map<String, String> paramsMap = new HashMap<>();

		// request headers
		headers.put(HttpHeaders.ACCEPT, "text/html");
		headers.put(HttpHeaders.ACCEPT_LANGUAGE, "en-US");
		headers.put(HttpHeaders.HOST, "api.github.com");
		headers.put(HttpHeaders.CONNECTION, "keep-alive");
		headers.put(HttpHeaders.USER_AGENT, "Java client");

		// request cookies
		// cookiesMap.put("dotcom_user", "hoovler");
		cookiesMap.put("logged_in", "yes");

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

		return content.toString();
	}

	/**
	 * Gets the readme file.
	 *
	 * @return the readme file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String getReadmeFile() throws IOException {
		return getReadmeFile("/README.md");
	}

	/**
	 * Gets the readme file.
	 *
	 * @param filename the filename
	 * @return the readme file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String getReadmeFile(String fileName) throws IOException {

		File folder = new File("").getAbsoluteFile();
		boolean fileFound = false;

		log.debug("All files contained within " + folder.getAbsolutePath());
		for (File f : folder.listFiles()) {
			if (StringUtils.equalsIgnoreCase(f.getName(), fileName))
				fileFound = true;
			log.debug(f.getAbsolutePath() + "; " + f.getName() + "; found? " + fileFound);

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

}
