/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MazeRunnerII@worldwizard.net
 */
package com.puttysoftware.mazerunner2.maze.objects;

import com.puttysoftware.mazerunner2.maze.abc.AbstractPlug;
import com.puttysoftware.mazerunner2.resourcemanagers.ObjectImageConstants;

public class RPlug extends AbstractPlug {
    // Constructors
    public RPlug() {
        super('R');
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_R_PLUG;
    }
}