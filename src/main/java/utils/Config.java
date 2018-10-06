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
package utils;

import org.springframework.beans.factory.annotation.Value;

/**
 * The Class Config.
 *
 * @author Michael Hoovler
 * @since Oct 3, 2018
 * 
 *        Purpose: <TODO: add purpose statement>
 */
public class Config {
	
	/** 
	 * DATASOURCE_URL config property;
	 * Default: https://www.willowtreeapps.com/api/v1.0/profiles 
	 */
	@Value( "${datasource.url:https://www.willowtreeapps.com/api/v1.0/profiles}" )
	public static String DATASOURCE_URL = "https://www.willowtreeapps.com/api/v1.0/profiles";
	
	
	/** 
	 * DATASOURCE_PROTOCOL config property;
	 * Default: https 
	 */
	@Value( "${datasource.protocol:https}" )
	public static String DATASOURCE_PROTOCOL = "https";
	
	/** 
	 * DATASOURCE_DOMAIN config property;
	 * Default: www.willowtreeapps.com 
	 */
	@Value( "${datasource.domain:www.willowtreeapps.com}" )
	public static String DATASOURCE_DOMAIN = "www.willowtreeapps.com";
	
	/** 
	 * DATASOURCE_PATH config property;
	 * Default: /api/v1.0/profiles
	 */
	@Value( "${datasource.path:/api/v1.0/profiles}" )
	public static String DATASOURCE_PATH = "/api/v1.0/profiles";
	
	/** 
	 * DATASOURCE_FORMAT config property;
	 * Default: json 
	 */
	@Value( "${datasource.format:json}" )
	public static String DATASOURCE_FORMAT = "json";
	
	/** 
	 * PROFILE_TYPE config property;
	 * Default: people
	 */
	@Value( "${profile.type.default:people}" )
	public static String PROFILE_TYPE = "people";
	
	/** 
	 * MAX_LEADERS config property;
	 * Default: 10
	 */
	@Value( "${leaderboard.length.max:10}" )
	public static int MAX_LEADERS = 10;
	
	/** 
	 * INDEX_FILE config property
	 * Default: index.html
	 */
	@Value( "${index.file:index.html}" )
	public static String INDEX_FILE = "index.html";
	
	/** 
	 * INDEX_DEFAULT config property
	 * Default: <h1>GtKY_API</h1>
	 */
	@Value( "${index.default:<h1>GtKY_API</h1>}" )
	public static String INDEX_DEFAULT = "<h1>Welcome to the <b><i>Getting to Know You (GtKY)</i>  API</b>!</h1>";
	
	/** 
	 * GAMEMODE_DEFAULT config property
	 * Default: 1
	 */
	@Value( "${gamemode.default:1" )
	public static String GAMEMODE_DEFAULT = "1";	
	
	// numbers for faces : names in questions
	public static int FACENAME_MIN = 1;
	public static int FACENAME_MAX = 6;
}
