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
package gtky.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OverheadController {

	/**
	 * Index.
	 *
	 * @return the string
	 */
	@RequestMapping("/")
	public String index() {
		// need  to redirect to /gtky
		return "This will be a redirect to /gtky/";
	}
	
	@RequestMapping("/error")
	public String error() {
		return "This will be a somewhat helpful error page.  But not TOO helpful...";
	}
	
	
	
	
}
