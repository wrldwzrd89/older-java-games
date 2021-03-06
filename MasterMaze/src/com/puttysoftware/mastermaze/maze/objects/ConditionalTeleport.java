/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.GenericConditionalTeleport;
import com.puttysoftware.mastermaze.resourcemanagers.ObjectImageConstants;

public class ConditionalTeleport extends GenericConditionalTeleport {
    // Constructors
    public ConditionalTeleport() {
        super(ObjectImageConstants.OBJECT_IMAGE_CONDITIONAL);
    }

    @Override
    public String getName() {
        return "Conditional Teleport";
    }

    @Override
    public String getPluralName() {
        return "Conditional Teleports";
    }

    @Override
    public String getDescription() {
        return "Conditional Teleports send you to one of two predetermined destinations when stepped on, depending on how many Sun or Moon Stones are in your inventory.";
    }
}