/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazerunner2.maze.objects;

import com.puttysoftware.mazerunner2.maze.abc.AbstractToggleWall;
import com.puttysoftware.mazerunner2.maze.utilities.ColorConstants;

public class RedWallOn extends AbstractToggleWall {
    // Constructors
    public RedWallOn() {
        super(true, ColorConstants.COLOR_RED);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Red Wall On";
    }

    @Override
    public String getPluralName() {
        return "Red Walls On";
    }

    @Override
    public String getDescription() {
        return "Red Walls On can NOT be walked through, and will change to Red Walls Off when a Red Button is pressed.";
    }
}