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
package objects;

import java.util.List;

import enumerations.Mode;

public class Question {

	private List<String> imageUrls;
	
	private List<String> employeeNames;
	
	private int correctSelection;
	
	// ************************************************ CONSTRUCTORS
	
	public Question() {
		initQuestion(null);
	}
	
	public Question(Mode gameMode) {
		initQuestion(gameMode);
	}
	
	// ************************************************ GETTERS

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public List<String> getEmployeeNames() {
		return employeeNames;
	}

	// ************************************************ SETTERS
	
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public void setEmployeeNames(List<String> employeeNames) {
		this.employeeNames = employeeNames;
	}
	
	// ************************************************ CLASS METHODS
	private void initQuestion(Mode gameMode) {
		// TODO: populate the two object lists based on mode passed in
		
	}
 	
}
