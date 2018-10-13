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
package game.models;

/**
 * The Class Employee.
 *
 * @author Michael Hoovler
 * @since Oct 8, 2018
 */
public class Employee {

	/** The id attribute of the Employee object. */
	private String id;

	/** The value attribute of the Employee object. */
	private String value;

	public Employee() {
		// empty constructor
	}

	/**
	 * Creates a new featured item with an ID.
	 *
	 * @param id the item id
	 */
	public Employee(String id) {
		setId(id);
	}

	/**
	 * Instantiates a new featured item.
	 *
	 * @param id    the item id
	 * @param value the item value
	 */
	public Employee(String id, String value) {
		setId(id);
		setValue(value);
	}

	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the item value.
	 *
	 * @return the item value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the item id.
	 *
	 * @param id the new item id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the item value.
	 *
	 * @param value the new item value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
