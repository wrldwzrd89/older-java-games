/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericPassThroughObject;

public class SealedFinish extends GenericPassThroughObject {
    // Constructors
    public SealedFinish() {
        super();
    }

    @Override
    public String getName() {
        return "Sealed Finish";
    }

    @Override
    public String getPluralName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }
}