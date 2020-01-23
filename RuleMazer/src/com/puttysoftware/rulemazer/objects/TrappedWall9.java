/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericTrappedWall;

public class TrappedWall9 extends GenericTrappedWall {
    public TrappedWall9() {
        super(9);
    }

    @Override
    public String getDescription() {
        return "Trapped Walls 9 disappear when any Wall Trap 9 is triggered.";
    }
}
