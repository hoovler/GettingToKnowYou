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
package gtky.persistence;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gtky.persistence.repositories.PlayerRepository;

@Repository
@Transactional
public class PlayerService {
	
	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(PlayerService.class);
	/** ******* */
	
	/** The playerRepo attribute of the PlayerLoader object. */
    @Autowired
    private PlayerRepository playerRepository;

	@Autowired
	public void setPlayerRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Autowired
	public PlayerRepository getPlayerRepository() {
		log.info("ENTER PlayerService.getPlayerRepository(){}");
		return playerRepository;
	}
}
