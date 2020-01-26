/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.ColorConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericGround;
import com.puttysoftware.mastermaze.maze.generic.ObjectInventory;
import com.puttysoftware.mastermaze.resourcemanagers.ObjectImageConstants;
import com.puttysoftware.mastermaze.resourcemanagers.SoundConstants;
import com.puttysoftware.mastermaze.resourcemanagers.SoundManager;

public class BridgedWaterHorizontal extends GenericGround {
    // Constructors
    public BridgedWaterHorizontal() {
        super(ColorConstants.COLOR_WATER);
        this.setAttributeID(
                ObjectImageConstants.OBJECT_IMAGE_BRIDGE_HORIZONTAL);
        this.setAttributeTemplateColor(ColorConstants.COLOR_BRIDGE);
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        SoundManager.playSound(SoundConstants.SOUND_WALK);
    }

    @Override
    public String getName() {
        return "Bridged Water Horizontal";
    }

    @Override
    public String getPluralName() {
        return "Squares of Bridged Water Horizontal";
    }

    @Override
    public boolean overridesDefaultPostMove() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Bridged Water Horizontal, unlike Water, can be walked on.";
    }
}
