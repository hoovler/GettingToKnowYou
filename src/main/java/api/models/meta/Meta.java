/**
 *
 */
package api.models.meta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael
 *
 */
public class Meta {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(Meta.class);
	/** ******* */
	// I'm sure this is an unorthodox way to return error info through the API
	// response, but it works in a pinch
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Meta(boolean isError, String errorMessage) {
		// isError isn't really used - just meant as a way to ensure distinct
		// constructor signatures
		setError(errorMessage);
	}

	private Leaderboard leaderboard;

	public Meta() {
		this.leaderboard = new Leaderboard();
	}

	public Meta(int leadlen) {
		this.leaderboard = new Leaderboard(leadlen);
	}

	/**
	 * @return the leaderboard
	 */
	public Leaderboard getLeaderboard() {
		return this.leaderboard;
	}

	public void setLeaderboard(final Leaderboard leaderboard) {
		this.leaderboard = leaderboard;
	}
}
