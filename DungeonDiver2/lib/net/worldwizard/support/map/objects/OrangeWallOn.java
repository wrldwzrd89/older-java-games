/*  DungeonDiverII: A Map-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package net.worldwizard.support.map.objects;

import net.worldwizard.support.map.generic.GenericToggleWall;
import net.worldwizard.support.map.generic.TemplateTransform;

public class OrangeWallOn extends GenericToggleWall {
    // Constructors
    public OrangeWallOn() {
        super(true, new TemplateTransform(1.0, 0.5, 0.25, ""));
    }

    // Scriptability
    @Override
    public String getName() {
        return "Orange Wall On";
    }

    @Override
    public String getPluralName() {
        return "Orange Walls On";
    }

    @Override
    public String getDescription() {
        return "Orange Walls On can NOT be walked through, and will change to Orange Walls Off when a Orange Button is pressed.";
    }
}