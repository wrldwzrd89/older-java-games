/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.objects;

import com.puttysoftware.loopchute.Application;
import com.puttysoftware.loopchute.LoopChute;
import com.puttysoftware.loopchute.game.ObjectInventory;
import com.puttysoftware.loopchute.generic.ColorConstants;
import com.puttysoftware.loopchute.generic.GenericAntiObject;
import com.puttysoftware.loopchute.resourcemanagers.SoundConstants;
import com.puttysoftware.loopchute.resourcemanagers.SoundManager;

public class NoPlayer extends GenericAntiObject {
    // Constructors
    public NoPlayer() {
        super();
        this.setTemplateColor(ColorConstants.COLOR_NONE);
        this.setAttributeName("no");
        this.setAttributeTemplateColor(ColorConstants.COLOR_RED);
    }

    @Override
    public final String getBaseName() {
        return "player";
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        final Application app = LoopChute.getApplication();
        app.getGameManager().backUpPlayer(this);
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_WALK_FAILED);
    }

    @Override
    public String getName() {
        return "No Player";
    }

    @Override
    public String getPluralName() {
        return "No Players";
    }

    @Override
    public String getDescription() {
        return "No Players prevent players from attempting to pass through.";
    }
}