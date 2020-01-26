/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.objects;

import com.puttysoftware.loopchute.generic.ColorConstants;
import com.puttysoftware.loopchute.generic.GenericBoots;

public class RegularBoots extends GenericBoots {
    // Constructors
    public RegularBoots() {
        super(ColorConstants.COLOR_NONE);
    }

    @Override
    public String getName() {
        return "Regular Boots";
    }

    @Override
    public String getPluralName() {
        return "Pairs of Regular Boots";
    }

    @Override
    public String getDescription() {
        return "";
    }
}