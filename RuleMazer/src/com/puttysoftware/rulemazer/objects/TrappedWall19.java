/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericTrappedWall;

public class TrappedWall19 extends GenericTrappedWall {
    public TrappedWall19() {
        super(19);
    }

    @Override
    public String getDescription() {
        return "Trapped Walls 19 disappear when any Wall Trap 19 is triggered.";
    }
}