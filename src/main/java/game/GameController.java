/**
 *
 */
package game;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import game.utils.Utils;

/**
 * The Spring Boot MVC root controller for the GettingToKnowYou API endpoints.
 * All endpoints for this application extent this class.
 *
 * GtKY uses a RESTful Web Services approach to present 'players' with one
 * multiple-choice question, the format for which is dependent upon the 'mode'
 * parameter passed to the endpoint defined in <code>PlayController</code>
 *
 * @author Michael Hoovler
 * @since Oct 13, 2018
 */
@RestController
@RequestMapping("/game")
public class GameController {

	/**
	 * Returns the README.md as rendered HTML using the Github v3 API.
	 * <p>
	 * The following /markdown API params are set by this method:
	 * <ul>
	 * <li><code>text=</code><i>\<string found in
	 * /resources/static/README.md\></i>:</li>
	 * <li><code>mode="gfm"</code>: Stands for 'Github Flavored Markdown.</li>
	 * <li><code>context="github/" + </code><i>\<specific s\>:</li>
	 * </ul>
	 * </p>
	 *
	 * @return the readme
	 */
	@RequestMapping("/")
	public String usage() throws IOException {
		return Utils.renderReadme(Utils.getReadmeFile("README.md"));
	}

}