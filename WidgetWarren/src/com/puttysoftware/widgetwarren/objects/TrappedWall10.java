/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericTrappedWall;

public class TrappedWall10 extends GenericTrappedWall {
    public TrappedWall10() {
        super(10);
    }

    @Override
    public String getDescription() {
        return "Trapped Walls 10 disappear when any Wall Trap 10 is triggered.";
    }
}
