/*  DungeonDiver4: A Dungeon-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: DungeonDiver4@worldwizard.net
 */
package com.puttysoftware.dungeondiver4.dungeon.objects;

import com.puttysoftware.dungeondiver4.DungeonDiver4;
import com.puttysoftware.dungeondiver4.dungeon.abc.AbstractScoreIncreaser;
import com.puttysoftware.dungeondiver4.dungeon.utilities.ColorConstants;

public class Amethyst extends AbstractScoreIncreaser {
    // Fields
    private static final long SCORE_INCREASE = 50L;

    // Constructors
    public Amethyst() {
        super(ColorConstants.COLOR_MAGENTA);
    }

    @Override
    public String getName() {
        return "Amethyst";
    }

    @Override
    public String getPluralName() {
        return "Amethysts";
    }

    @Override
    public void postMoveActionHook() {
        DungeonDiver4.getApplication().getGameManager()
                .addToScore(Amethyst.SCORE_INCREASE);
    }

    @Override
    public String getDescription() {
        return "Amethysts increase your score when picked up.";
    }
}
