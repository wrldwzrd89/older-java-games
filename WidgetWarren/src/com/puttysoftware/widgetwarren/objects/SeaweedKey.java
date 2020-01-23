/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericSingleKey;

public class SeaweedKey extends GenericSingleKey {
    // Constructors
    public SeaweedKey() {
        super();
    }

    // Scriptability
    @Override
    public String getName() {
        return "Seaweed Key";
    }

    @Override
    public String getPluralName() {
        return "Seaweed Keys";
    }

    @Override
    public String getDescription() {
        return "Seaweed Keys will unlock Seaweed Locks, and can only be used once.";
    }
}