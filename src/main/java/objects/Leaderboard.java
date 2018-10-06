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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * The Class Leaderboard.
 *
 * @author Michael Hoovler
 * @since Oct 4, 2018
 * 
 *        Purpose: <TODO: add purpose statement>
 */
@Entity
public class Leaderboard {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String playerEmail;
	
	private Long numberRight;
	
	private Long numberWrong;
	
	private Long averageTimeToSelectRight;
	
	private Long averageTimeToSelectWrong;
	
	
	
	/**
	 * Instantiates a new leaderboard.
	 */
	public Leaderboard() {
		super();
	}
	
	public Leaderboard(String playerEmail) {
		super();
		this.playerEmail = playerEmail;
	}
	
	// ************************************************ SETTERS

	public void setId(Long id) {
		this.id = id;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public void setNumberRight(Long numberRight) {
		this.numberRight = numberRight;
	}

	public void setNumberWrong(Long numberWrong) {
		this.numberWrong = numberWrong;
	}

	public void setAverageTimeToSelectRight(Long averageTimeToSelectRight) {
		this.averageTimeToSelectRight = averageTimeToSelectRight;
	}

	public void setAverageTimeToSelectWrong(Long averageTimeToSelectWrong) {
		this.averageTimeToSelectWrong = averageTimeToSelectWrong;
	}

	// ************************************************ GETTERS

	public Long getId() {
		return id;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public Long getNumberRight() {
		return numberRight;
	}

	public Long getNumberWrong() {
		return numberWrong;
	}

	public Long getAverageTimeToSelectRight() {
		return averageTimeToSelectRight;
	}

	public Long getAverageTimeToSelectWrong() {
		return averageTimeToSelectWrong;
	}

	// ************************************************ UTILITIES
	
	public String toJson() {
		return String.format(
				"Leaderboard ['id':'%s', 'playerEmail':'%s', 'numberRight':'%s', 'numberWrong':'%s', 'averageTimeToSelectRight':'%s', 'averageTimeToSelectWrong':'%s']",
				id, playerEmail, numberRight, numberWrong, averageTimeToSelectRight, averageTimeToSelectWrong);
	}
}
