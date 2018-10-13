/*
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or <architect@databuildr.com>
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met: (1) Redistributions of source code must retain the above copyright notice this list of conditions and the following disclaimer. (2) Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution. (3) Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * Full license text is available at <https://opensource.org/licenses/BSD-3-Clause>
 */
package game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Class Application.
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 *
 *        PURPOSE
 *
 *        --------
 *
 *        This class initializes the Spring framework, creating all the
 *        requisite beans and whatnot to build the REST API endpoint services.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "api.persistence")
public class Application {

	/** Logging */
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	/** ******* */

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("======= APPLICATION INITIALIZED =======");
	}
}
