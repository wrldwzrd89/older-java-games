/*  LaserTank: An Arena-Solving Game
 Copyright (C) 2008-2013 Eric Ahnell

 Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.lasertank.arena.abstractobjects;

import com.puttysoftware.lasertank.utilities.ArenaConstants;
import com.puttysoftware.lasertank.utilities.TypeConstants;

public abstract class AbstractTeleport extends AbstractArenaObject {
    // Constructors
    protected AbstractTeleport() {
        super(false);
        this.type.set(TypeConstants.TYPE_TELEPORT);
    }

    @Override
    public int getCustomProperty(final int propID) {
        return AbstractArenaObject.DEFAULT_CUSTOM_VALUE;
    }

    public abstract int getDestinationFloor();

    @Override
    public int getLayer() {
        return ArenaConstants.LAYER_LOWER_OBJECTS;
    }

    @Override
    public abstract void postMoveAction(final int dirX, final int dirY,
            int dirZ);

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }
}
