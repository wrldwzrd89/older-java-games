/*  DungeonDiver4: A Dungeon-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: DungeonDiver4@worldwizard.net
 */
package com.puttysoftware.dungeondiver4.dungeon.objects;

import com.puttysoftware.dungeondiver4.dungeon.abc.AbstractMultipleLock;
import com.puttysoftware.dungeondiver4.dungeon.utilities.ColorConstants;
import com.puttysoftware.dungeondiver4.resourcemanagers.ObjectImageConstants;

public class BracerWall extends AbstractMultipleLock {
    // Constructors
    public BracerWall() {
        super(new Bracer(), ColorConstants.COLOR_CYAN);
    }

    @Override
    public int getAttributeID() {
        return ObjectImageConstants.OBJECT_IMAGE_BRACERS;
    }

    @Override
    public String getName() {
        return "Bracer Wall";
    }

    @Override
    public String getPluralName() {
        return "Bracer Walls";
    }

    @Override
    public String getDescription() {
        return "Bracer Walls are impassable without enough Bracers.";
    }

    @Override
    public String getIdentifierV1() {
        return "Cyan Crystal Wall";
    }
}