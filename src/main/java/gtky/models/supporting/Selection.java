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
package gtky.models.supporting;

import gtky.GettingToKnowYou;

/**
 * The Class Selection.
 *
 * @author Michael Hoovler
 * @since  Oct 8, 2018
 * 
 *         <h2>PURPOSE</h2>
 *         <p>
 *         To...
 *         </p>
 */
public class Selection extends GettingToKnowYou {

    /** The selectionId attribute of the Selection object. */
    private String selectionId;

    /** The selectionValue attribute of the Selection object. */
    private String selectionValue;

    /**
     * Instantiates a new selection.
     *
     * @param selectionId
     *                       the selection id
     * @param selectionValue
     *                       the selection value
     */
    public Selection(String selectionId, String selectionValue) {
        super();
        this.selectionId = selectionId;
        this.selectionValue = selectionValue;
    }

    /**
     * Gets the selection id.
     *
     * @return the selection id
     */
    public String getSelectionId() {
        return selectionId;
    }

    /**
     * Gets the selection value.
     *
     * @return the selection value
     */
    public String getSelectionValue() {
        return selectionValue;
    }

    /**
     * Sets the selection id.
     *
     * @param selectionId
     *                    the new selection id
     */
    public void setSelectionId(String selectionId) {
        this.selectionId = selectionId;
    }

    /**
     * Sets the selection value.
     *
     * @param selectionValue
     *                       the new selection value
     */
    public void setSelectionValue(String selectionValue) {
        this.selectionValue = selectionValue;
    }

}
