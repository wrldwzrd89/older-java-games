/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericBoots;

public class RegularBoots extends GenericBoots {
    // Constructors
    public RegularBoots() {
        super();
    }

    @Override
    public String getName() {
        return "Regular Boots";
    }

    @Override
    public String getPluralName() {
        return "Pairs of Regular Boots";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
