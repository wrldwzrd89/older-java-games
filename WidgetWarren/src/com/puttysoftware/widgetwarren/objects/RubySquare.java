/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericMultipleKey;

public class RubySquare extends GenericMultipleKey {
    // Constructors
    public RubySquare() {
        super();
    }

    @Override
    public String getName() {
        return "Ruby Square";
    }

    @Override
    public String getPluralName() {
        return "Ruby Squares";
    }

    @Override
    public String getDescription() {
        return "Ruby Squares are the keys to Ruby Walls.";
    }
}