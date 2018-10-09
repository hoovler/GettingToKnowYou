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
package gtky.utils;

public class Global {
	// Constants declared internally
	public static final int DEFAULT_LEADLEN = 10;

    public static final int DEFAULT_GAMEMODE = 1;
    
    public static final int DEFAULT_NUM_SELECTIONS = 6;

    // keys in the external config file
    public static final String k_datasource_uri = "datasource.uri"; //$NON-NLS-1$
    
    public static final String k_default_email = "email.default"; //$NON-NLS-1$

    public static final String k_field_id = "datasource.field.id"; //$NON-NLS-1$

    public static final String k_field_firstname = "datasource.field.firstname"; //$NON-NLS-1$

    public static final String k_field_lastname = "datasource.field.lastname"; //$NON-NLS-1$

    public static final String k_field_headshot = "datasource.field.headshot"; //$NON-NLS-1$

    public static final String k_field_headshot_url = "headshot.field.url"; //$NON-NLS-1$
    
    public static final String k_protocol = "datasource.protocol"; //$NON-NLS-1$

    public static final String k_bonus_prefix = "bonus.field.value.prefix"; //$NON-NLS-1$

    public static final String k_bonus_field = "bonus.field.name"; //$NON-NLS-1$
}
