/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.generic.GenericWallTrap;

public class WallTrap14 extends GenericWallTrap {
    public WallTrap14() {
        super(14, new TrappedWall14());
    }

    @Override
    public String getDescription() {
        return "Wall Traps 14 disappear when stepped on, causing all Trapped Walls 14 to also disappear.";
    }
}
