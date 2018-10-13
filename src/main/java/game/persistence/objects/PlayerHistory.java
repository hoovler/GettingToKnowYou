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
package game.persistence.objects;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PlayerHistory {

	// *** START of in-mem database fields ***********
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	/** The */
	@Column(name = "QUESTION_ID")
	private String questionId;

	@Column(name = "TIME_ASKED")
	private Instant timeAsked;

	@Column(name = "TIME_ANSWERED")
	private Instant timeAnswered;

	@Column(name = "WAS_RIGHT")
	private Boolean wasRight;

	public PlayerHistory() {
		// default constructor to meet JPA requirements
	}

}
