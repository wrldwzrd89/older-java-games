/*  BrainMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.brainmaze.objects;

import com.puttysoftware.brainmaze.game.ObjectInventory;
import com.puttysoftware.brainmaze.generic.ColorConstants;
import com.puttysoftware.brainmaze.generic.GenericGround;
import com.puttysoftware.brainmaze.resourcemanagers.SoundConstants;
import com.puttysoftware.brainmaze.resourcemanagers.SoundManager;

public class BridgedSlimeVertical extends GenericGround {
    // Constructors
    public BridgedSlimeVertical() {
        super(ColorConstants.COLOR_GREEN);
        this.setAttributeName("bridge_ns");
        this.setAttributeTemplateColor(ColorConstants.COLOR_NONE);
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_WALK);
    }

    @Override
    public String getName() {
        return "Bridged Slime Vertical";
    }

    @Override
    public String getPluralName() {
        return "Squares of Bridged Slime Vertical";
    }

    @Override
    public boolean overridesDefaultPostMove() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Bridged Slime Vertical, unlike Slime, can be walked on.";
    }
}
