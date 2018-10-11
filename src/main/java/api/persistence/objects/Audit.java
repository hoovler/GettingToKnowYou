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
package api.persistence.objects;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Audit {

	// *** START of in-mem database fields ***********
	@Id
	@GeneratedValue
	private Long id;

	@Column
	private Long playerId;

	@Column
	private Instant timeAsked;

	@Column
	private Instant timeAnswered;

	@Column
	private String answerId;

	@Column
	private String givenId;

	public Audit() {
		// default constructor to meet JPA requirements
	}
}
