/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.MazeMode;
import com.puttysoftware.mazemode.game.ObjectInventory;
import com.puttysoftware.mazemode.generic.GenericSingleLock;
import com.puttysoftware.mazemode.resourcemanagers.SoundConstants;
import com.puttysoftware.mazemode.resourcemanagers.SoundManager;

public class YellowLock extends GenericSingleLock {
    // Constructors
    public YellowLock() {
        super(new YellowKey());
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
            MazeMode.getApplication().showMessage("You need a yellow key");
        }
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_WALK_FAILED);
    }

    @Override
    public String getName() {
        return "Yellow Lock";
    }

    @Override
    public String getPluralName() {
        return "Yellow Locks";
    }

    @Override
    public String getDescription() {
        return "Yellow Locks require Yellow Keys to open.";
    }
}