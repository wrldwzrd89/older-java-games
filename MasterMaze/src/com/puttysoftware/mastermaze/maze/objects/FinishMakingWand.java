/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MasterMaze@worldwizard.net
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.ColorConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericWand;
import com.puttysoftware.mastermaze.resourcemanagers.SoundConstants;
import com.puttysoftware.mastermaze.resourcemanagers.SoundManager;

public class FinishMakingWand extends GenericWand {
    public FinishMakingWand() {
        super(ColorConstants.COLOR_GREEN);
    }

    @Override
    public String getName() {
        return "Finish-Making Wand";
    }

    @Override
    public String getPluralName() {
        return "Finish-Making Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
        this.useAction(new Finish(), x, y, z);
        SoundManager.playSound(SoundConstants.SOUND_CREATE);
    }

    @Override
    public String getDescription() {
        return "Finish-Making Wands will create a finish when used.";
    }
}
