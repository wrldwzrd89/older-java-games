/*  DungeonDiver4: A Dungeon-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: dungeonr5d@worldwizard.net
 */
package com.puttysoftware.dungeondiver4.dungeon.abc;

import com.puttysoftware.dungeondiver4.dungeon.DungeonConstants;
import com.puttysoftware.dungeondiver4.dungeon.utilities.DungeonObjectInventory;
import com.puttysoftware.dungeondiver4.dungeon.utilities.TypeConstants;
import com.puttysoftware.dungeondiver4.resourcemanagers.ObjectImageConstants;

public abstract class AbstractLightModifier extends AbstractDungeonObject {
    // Constructors
    protected AbstractLightModifier(final int tc) {
        super(true, false);
        this.setTemplateColor(tc);
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_GEM;
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final DungeonObjectInventory inv) {
        // Do nothing
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_LIGHT_MODIFIER);
    }

    @Override
    public int getLayer() {
        return DungeonConstants.LAYER_OBJECT;
    }

    @Override
    public int getCustomProperty(final int propID) {
        return AbstractDungeonObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }

    public static int getEffectRadius() {
        return 1;
    }
}
