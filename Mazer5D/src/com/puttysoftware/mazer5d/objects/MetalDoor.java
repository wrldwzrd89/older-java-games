/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.generic.GenericSingleLock;
import com.puttysoftware.mazer5d.loaders.SoundConstants;
import com.puttysoftware.mazer5d.loaders.SoundManager;

public class MetalDoor extends GenericSingleLock {
    // Constructors
    public MetalDoor() {
        super(new MetalKey());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
            Mazer5D.getApplication().showMessage("You need a metal key");
        }
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_WALK_FAILED);
    }

    @Override
    public String getName() {
        return "Metal Door";
    }

    @Override
    public String getPluralName() {
        return "Metal Doors";
    }

    @Override
    public String getDescription() {
        return "Metal Doors require Metal Keys to open, or Metal Boots and a Metal Button.";
    }
}