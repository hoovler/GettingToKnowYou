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
package gtky.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class GtkyGlobals.
 *
 * @author Michael Hoovler
 * @since  Oct 3, 2018
 * 
 *         Purpose:
 */
public interface GtkyGlobals {

    /** Experimenting with using a logger throughout inheriting classes */
    public static final Logger log = LoggerFactory.getLogger(GtkyGlobals.class);

    public static final String DATASOURCE_URI = "https://www.willowtreeapps.com/api/v1.0/profiles";

    public static final int LEADERBOARD_LENGTH_DEFAULT = 10;

    public static final String s_LEADERBOARD_LENGTH_DEFAULT = "10";

    public static final int GAMEMODE_DEFAULT = 1;

    public static final String s_GAMEMODE_DEFAULT = "1";

    public static final String DEFAULT_PLAYER_EMAIL = "foo@bar.com";

    public static final String DATASOURCE_FIELDNAME_ID = "id";

    public static final String DATASOURCE_FIELDNAME_FIRSTNAME = "firstName";

    public static final String DATASOURCE_FIELDNAME_LASTNAME = "lastName";

    public static final String DATASOURCE_FIELDNAME_HEADSHOT = "headshot";

    public static final String DATASOURCE_FIELDNAME_HEADSHOT_URL = "url";

    public static final String EMPTY_STRING = "";

    public static final String BONUS_FIRSTNAME_PREFIX = "matt";

    public static final int NUM_SELECTIONS = 6;

}
