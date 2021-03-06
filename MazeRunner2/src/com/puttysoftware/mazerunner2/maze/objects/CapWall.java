/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MazeRunnerII@worldwizard.net
 */
package com.puttysoftware.mazerunner2.maze.objects;

import com.puttysoftware.mazerunner2.maze.abc.AbstractMultipleLock;
import com.puttysoftware.mazerunner2.maze.utilities.ColorConstants;
import com.puttysoftware.mazerunner2.resourcemanagers.ObjectImageConstants;

public class CapWall extends AbstractMultipleLock {
    // Constructors
    public CapWall() {
        super(new Cap(), ColorConstants.COLOR_RED);
    }

    @Override
    public int getAttributeID() {
        return ObjectImageConstants.OBJECT_IMAGE_CAP;
    }

    @Override
    public String getName() {
        return "Cap Wall";
    }

    @Override
    public String getPluralName() {
        return "Cap Walls";
    }

    @Override
    public String getDescription() {
        return "Cap Walls are impassable without enough Caps.";
    }

    @Override
    public String getIdentifierV1() {
        return "Red Crystal Wall";
    }
}