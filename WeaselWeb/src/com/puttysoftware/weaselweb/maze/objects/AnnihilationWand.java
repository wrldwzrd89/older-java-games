/*  WeaselWeb: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.weaselweb.maze.objects;

import com.puttysoftware.weaselweb.maze.generic.GenericWand;
import com.puttysoftware.weaselweb.resourcemanagers.SoundConstants;
import com.puttysoftware.weaselweb.resourcemanagers.SoundManager;

public class AnnihilationWand extends GenericWand {
    // Constructors
    public AnnihilationWand() {
        super();
    }

    @Override
    public String getName() {
        return "Annihilation Wand";
    }

    @Override
    public String getPluralName() {
        return "Annihilation Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
        this.useAction(new Empty(), x, y, z);
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_DESTROY);
    }

    @Override
    public String getDescription() {
        return "Annihilation Wands will destroy any object (not ground) when used, except the Void or a Sealing Wall.";
    }
}
