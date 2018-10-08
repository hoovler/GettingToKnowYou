/*
 * Copyright (c) 2018 Data BuildR, LLC - Released as open source under
 * BSD-3-Clause
 *
 * Written by Michael Hoovler - <hoovlermichael@gmail.com> and/or
 * <architect@databuildr.com>
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * (1) Redistributions of source code must retain the above copyright notice
 * this list of conditions and the following disclaimer. (2) Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution. (3) Neither the name of the
 * copyright holder nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written
 * permission.
 * 
 * Full license text is available at
 * <https://opensource.org/licenses/BSD-3-Clause>
 */
package gtky;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.DomainValidator.ArrayType;

import gtky.persistence.Player;
import gtky.utils.GtkyGlobals;

/**
 * The Class GettingToKnowYou.
 *
 * @author Michael Hoovler
 * @since  Oct 8, 2018
 * 
 *         Purpose: To act as a parent class for all application objects, to
 *         include resource, supporting, and persistence objects.
 */
public class GettingToKnowYou implements GtkyGlobals {
    
    /**
     * Generate random email.
     *
     * @return the string
     */
    protected String generateRandomEmail() {

        // build handle and domain with random alpha & alphanumeric strings
        String handle = RandomStringUtils.randomAlphanumeric(4, 10);
        String domain = RandomStringUtils.randomAlphanumeric(3, 10);

        // get valid generic top-level domains and grab a random one
        String[] gTLDs = DomainValidator.getTLDEntries(ArrayType.GENERIC_RO);
        String gTld = gTLDs[(int) (Math.random() * gTLDs.length)];

        // put it all together
        return String.format("%s@%s.%s", handle, domain, gTld);
    }

    /**
     * Generate random player.
     *
     * @return the player
     */
    protected Player generateRandomPlayer() {
        return generateRandomPlayer(generateRandomEmail());
    }

    /**
     * Generate random player.
     *
     * @param  playerEmail
     *                     the player email
     * @return             the player
     */
    protected Player generateRandomPlayer(String playerEmail) {

        String email = StringUtils.isNotBlank(playerEmail) ? playerEmail : generateRandomEmail();
        // either passed-in email,

        double averageTimeWrongMS = Math.random() * (60000 - 500) * (0.86 / 0.99);
        double averageTimeRightMS = Math.random() * (60000 - 500) * (0.86 / 0.99);
        long numberRight = (long) (Math.random() * 100);
        long numberWrong = (long) (Math.random() * 100);

        return new Player(email, numberRight, numberWrong, averageTimeRightMS, averageTimeWrongMS);
    }

}
