/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MasterMaze@worldwizard.net
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.ColorConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericMultipleLock;
import com.puttysoftware.mastermaze.resourcemanagers.ObjectImageConstants;

public class ShieldWall extends GenericMultipleLock {
    // Constructors
    public ShieldWall() {
        super(new Shield(), ColorConstants.COLOR_ORANGE);
    }

    @Override
    public int getAttributeID() {
        return ObjectImageConstants.OBJECT_IMAGE_SHIELD;
    }

    @Override
    public String getName() {
        return "Shield Wall";
    }

    @Override
    public String getPluralName() {
        return "Shield Walls";
    }

    @Override
    public String getDescription() {
        return "Shield Walls are impassable without enough Shields.";
    }

    @Override
    public String getIdentifierV1() {
        return "Orange Crystal Wall";
    }
}