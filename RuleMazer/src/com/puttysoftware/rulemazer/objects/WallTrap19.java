/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericWallTrap;

public class WallTrap19 extends GenericWallTrap {
    public WallTrap19() {
        super(19, new TrappedWall19());
    }

    @Override
    public String getDescription() {
        return "Wall Traps 19 disappear when stepped on, causing all Trapped Walls 19 to also disappear.";
    }
}
