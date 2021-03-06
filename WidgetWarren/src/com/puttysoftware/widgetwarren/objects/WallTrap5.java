/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericWallTrap;

public class WallTrap5 extends GenericWallTrap {
    public WallTrap5() {
        super(5, new TrappedWall5());
    }

    @Override
    public String getDescription() {
        return "Wall Traps 5 disappear when stepped on, causing all Trapped Walls 5 to also disappear.";
    }
}
