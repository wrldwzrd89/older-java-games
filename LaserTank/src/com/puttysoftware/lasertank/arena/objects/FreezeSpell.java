/*  LaserTank: An Arena-Solving Game
 Copyright (C) 2008-2013 Eric Ahnell

 Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.lasertank.arena.objects;

import com.puttysoftware.lasertank.LaserTank;
import com.puttysoftware.lasertank.arena.abstractobjects.AbstractSpell;
import com.puttysoftware.lasertank.resourcemanagers.SoundConstants;
import com.puttysoftware.lasertank.resourcemanagers.SoundManager;

public class FreezeSpell extends AbstractSpell {
    // Constructors
    public FreezeSpell() {
        super();
    }

    // Scriptability
    @Override
    public void postMoveAction(final int dirX, final int dirY, final int dirZ) {
        SoundManager.playSound(SoundConstants.SOUND_FREEZE_SPELL);
        LaserTank.getApplication().getArenaManager().getArena()
                .fullScanFreezeGround();
        LaserTank.getApplication().getGameManager().morph(new Empty(), dirX,
                dirY, dirZ, this.getLayer());
    }

    @Override
    public final int getStringBaseID() {
        return 142;
    }
}