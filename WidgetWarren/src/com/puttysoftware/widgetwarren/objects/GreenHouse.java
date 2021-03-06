/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericDirectTeleportTo;

public class GreenHouse extends GenericDirectTeleportTo {
    // Constructors
    public GreenHouse() {
        super();
    }

    @Override
    public String getName() {
        return "Green House";
    }

    @Override
    public String getPluralName() {
        return "Green Houses";
    }

    @Override
    public String getDescription() {
        return "Green Houses send you inside when walked on.";
    }
}