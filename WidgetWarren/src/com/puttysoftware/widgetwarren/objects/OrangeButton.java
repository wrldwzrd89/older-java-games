/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.generic.GenericButton;

public class OrangeButton extends GenericButton {
    public OrangeButton() {
        super(new OrangeWallOff(), new OrangeWallOn());
    }

    @Override
    public String getName() {
        return "Orange Button";
    }

    @Override
    public String getPluralName() {
        return "Orange Buttons";
    }

    @Override
    public String getDescription() {
        return "Orange Buttons will cause all Orange Walls Off to become On, and all Orange Walls On to become Off.";
    }
}
