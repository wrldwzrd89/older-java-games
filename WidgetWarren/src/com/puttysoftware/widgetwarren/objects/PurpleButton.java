/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericButton;

public class PurpleButton extends GenericButton {
    public PurpleButton() {
        super(new PurpleWallOff(), new PurpleWallOn());
    }

    @Override
    public String getName() {
        return "Purple Button";
    }

    @Override
    public String getPluralName() {
        return "Purple Buttons";
    }

    @Override
    public String getDescription() {
        return "Purple Buttons will cause all Purple Walls Off to become On, and all Purple Walls On to become Off.";
    }
}
