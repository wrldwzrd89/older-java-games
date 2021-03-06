/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.WidgetWarren;
import com.puttysoftware.widgetwarren.game.ObjectInventory;
import com.puttysoftware.widgetwarren.generic.GenericTimeModifier;
import com.puttysoftware.widgetwarren.resourcemanagers.SoundConstants;
import com.puttysoftware.widgetwarren.resourcemanagers.SoundManager;

public class HalfHourglass extends GenericTimeModifier {
    // Fields
    private static final long SCORE_GRAB = 5L;

    // Constructors
    public HalfHourglass() {
        super();
    }

    @Override
    public String getName() {
        return "Half Hourglass";
    }

    @Override
    public String getPluralName() {
        return "Half Hourglasses";
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        WidgetWarren.getApplication().getGameManager().decay();
        WidgetWarren.getApplication().getMazeManager().getMaze()
                .extendTimerByInitialValueHalved();
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_GRAB);
        WidgetWarren.getApplication().getGameManager()
                .addToScore(HalfHourglass.SCORE_GRAB);
    }

    @Override
    public String getDescription() {
        return "Half Hourglasses extend the time to solve the current level by half the initial value.";
    }
}
