/*  FantastleX: A Maze/RPG Hybrid Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: FantastleX@worldwizard.net
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractMultipleKey;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;
import com.puttysoftware.fantastlex.resourcemanagers.ObjectImageConstants;

public class Move extends AbstractMultipleKey {
    // Constructors
    public Move() {
        super(ColorConstants.COLOR_WHITE);
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_SMALL_MOVE;
    }

    @Override
    public String getName() {
        return "Move";
    }

    @Override
    public String getPluralName() {
        return "Moves";
    }

    @Override
    public String getDescription() {
        return "Moves are the keys to Move Walls.";
    }

    @Override
    public String getIdentifierV1() {
        return "White Crystal";
    }
}