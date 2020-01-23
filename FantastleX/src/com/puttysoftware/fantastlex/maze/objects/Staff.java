/*  FantastleX: A Maze/RPG Hybrid Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: FantastleX@worldwizard.net
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractMultipleKey;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;
import com.puttysoftware.fantastlex.resourcemanagers.ObjectImageConstants;

public class Staff extends AbstractMultipleKey {
    // Constructors
    public Staff() {
        super(ColorConstants.COLOR_PURPLE);
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_STAFF;
    }

    @Override
    public String getName() {
        return "Staff";
    }

    @Override
    public String getPluralName() {
        return "Staves";
    }

    @Override
    public String getDescription() {
        return "Staves are the keys to Staff Walls.";
    }

    @Override
    public String getIdentifierV1() {
        return "Purple Crystal";
    }
}