/*  FantastleX: A Maze/RPG Hybrid Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: FantastleX@worldwizard.net
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractMultipleKey;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;
import com.puttysoftware.fantastlex.resourcemanagers.ObjectImageConstants;

public class Shield extends AbstractMultipleKey {
    // Constructors
    public Shield() {
        super(ColorConstants.COLOR_ORANGE);
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_SHIELD;
    }

    @Override
    public String getName() {
        return "Shield";
    }

    @Override
    public String getPluralName() {
        return "Shields";
    }

    @Override
    public String getDescription() {
        return "Shields are the keys to Shield Walls.";
    }

    @Override
    public String getIdentifierV1() {
        return "Orange Crystal";
    }
}