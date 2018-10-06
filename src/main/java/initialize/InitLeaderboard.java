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
package initialize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import objects.Leaderboard;

@Configuration
public class InitLeaderboard {
	private static Logger log = LoggerFactory.getLogger(InitLeaderboard.class);
	
	@Bean
	public Leaderboard InitializeLeaderboard(String email) {
		
		log.info("METHOD: private String initLeaderboard()");
		Leaderboard leaderboard = new Leaderboard(email);

		// TODO: leaderboard data from persistent storage
		// String content = readFile("test.txt", StandardCharsets.UTF_8);
		// But for now, just return SOMETHING...
		
		
		leaderboard.setAverageTimeToSelectRight(123L);

		return leaderboard;
	}
}
