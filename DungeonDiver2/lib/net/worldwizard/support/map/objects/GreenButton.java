/*  DungeonDiverII: A Map-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package net.worldwizard.support.map.objects;

import net.worldwizard.support.map.generic.GenericButton;
import net.worldwizard.support.map.generic.TemplateTransform;

public class GreenButton extends GenericButton {
    public GreenButton() {
        super(new GreenWallOff(), new GreenWallOn(),
                new TemplateTransform(0.0, 1.0, 0.0, ""));
    }

    @Override
    public String getName() {
        return "Green Button";
    }

    @Override
    public String getPluralName() {
        return "Green Buttons";
    }

    @Override
    public String getDescription() {
        return "Green Buttons will cause all Green Walls Off to become On, and all Green Walls On to become Off.";
    }
}
