/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.objects;

import com.puttysoftware.loopchute.generic.ColorConstants;
import com.puttysoftware.loopchute.generic.GenericCheckKey;

public class RedCrystal extends GenericCheckKey {
    // Constructors
    public RedCrystal() {
        super(ColorConstants.COLOR_RED);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Red Crystal";
    }

    @Override
    public String getPluralName() {
        return "Red Crystals";
    }

    @Override
    public String getDescription() {
        return "Red Crystals will open Red Crystal Walls.";
    }

    @Override
    public String getBaseName() {
        return "Crystal";
    }
}