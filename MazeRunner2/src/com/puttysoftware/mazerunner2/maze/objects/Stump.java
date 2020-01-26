/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MazeRunnerII@worldwizard.net
 */
package com.puttysoftware.mazerunner2.maze.objects;

import com.puttysoftware.mazerunner2.maze.abc.AbstractWall;
import com.puttysoftware.mazerunner2.maze.utilities.ColorConstants;
import com.puttysoftware.mazerunner2.maze.utilities.MazeObjectInventory;
import com.puttysoftware.mazerunner2.resourcemanagers.ObjectImageConstants;

public class Stump extends AbstractWall {
    // Constructors
    public Stump() {
        super(ColorConstants.COLOR_BROWN);
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_STUMP;
    }

    @Override
    public String getName() {
        return "Stump";
    }

    @Override
    public String getPluralName() {
        return "Stumps";
    }

    @Override
    public boolean arrowHitAction(int locX, int locY, int locZ, int dirX,
            int dirY, int arrowType, MazeObjectInventory inv) {
        return true;
    }

    @Override
    public String getDescription() {
        return "Stumps stop movement, but not arrows, which pass over them unimpeded.";
    }
}