/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.ColorConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericButton;

public class CyanButton extends GenericButton {
    public CyanButton() {
        super(new CyanWallOff(), new CyanWallOn(), ColorConstants.COLOR_CYAN);
    }

    @Override
    public String getName() {
        return "Cyan Button";
    }

    @Override
    public String getPluralName() {
        return "Cyan Buttons";
    }

    @Override
    public String getDescription() {
        return "Cyan Buttons will cause all Cyan Walls Off to become On, and all Cyan Walls On to become Off.";
    }
}
