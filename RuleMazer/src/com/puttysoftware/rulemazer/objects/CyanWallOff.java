/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericToggleWall;

public class CyanWallOff extends GenericToggleWall {
    // Constructors
    public CyanWallOff() {
        super(false);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Cyan Wall Off";
    }

    @Override
    public String getPluralName() {
        return "Cyan Walls Off";
    }

    @Override
    public String getDescription() {
        return "Cyan Walls Off can be walked through, and will change to Cyan Walls On when a Cyan Button is pressed.";
    }
}