/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericToggleWall;

public class WhiteWallOn extends GenericToggleWall {
    // Constructors
    public WhiteWallOn() {
        super(true);
    }

    // Scriptability
    @Override
    public String getName() {
        return "White Wall On";
    }

    @Override
    public String getPluralName() {
        return "White Walls On";
    }

    @Override
    public String getDescription() {
        return "White Walls On can NOT be walked through, and will change to White Walls Off when a White Button is pressed.";
    }
}