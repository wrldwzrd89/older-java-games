/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazerunner2.maze.objects;

import com.puttysoftware.mazerunner2.maze.abc.AbstractToggleWall;
import com.puttysoftware.mazerunner2.maze.utilities.ColorConstants;

public class CyanWallOff extends AbstractToggleWall {
    // Constructors
    public CyanWallOff() {
        super(false, ColorConstants.COLOR_CYAN);
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