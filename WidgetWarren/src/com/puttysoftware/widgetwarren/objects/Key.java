/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericSingleKey;

public class Key extends GenericSingleKey {
    // Constructors
    public Key() {
        super();
    }

    // Scriptability
    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public String getPluralName() {
        return "Keys";
    }

    @Override
    public String getDescription() {
        return "Keys unlock Locks, and can only be used once.";
    }
}