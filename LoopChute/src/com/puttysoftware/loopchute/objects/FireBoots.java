/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.objects;

import com.puttysoftware.loopchute.generic.ColorConstants;
import com.puttysoftware.loopchute.generic.GenericBoots;

public class FireBoots extends GenericBoots {
    // Constructors
    public FireBoots() {
        super(ColorConstants.COLOR_ORANGE);
    }

    @Override
    public String getName() {
        return "Fire Boots";
    }

    @Override
    public String getPluralName() {
        return "Pairs of Fire Boots";
    }

    @Override
    public String getDescription() {
        return "Fire Boots allow walking on lava. Note that you can only wear one pair of boots at once.";
    }
}
