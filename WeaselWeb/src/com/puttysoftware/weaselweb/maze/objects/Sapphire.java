/*  WeaselWeb: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.weaselweb.maze.objects;

import com.puttysoftware.weaselweb.WeaselWeb;
import com.puttysoftware.weaselweb.maze.generic.GenericScoreIncreaser;

public class Sapphire extends GenericScoreIncreaser {
    // Fields
    private static final long SCORE_INCREASE = 250L;

    // Constructors
    public Sapphire() {
        super();
    }

    @Override
    public String getName() {
        return "Sapphire";
    }

    @Override
    public String getPluralName() {
        return "Sapphires";
    }

    @Override
    public void postMoveActionHook() {
        WeaselWeb.getApplication().getGameManager()
                .addToScore(Sapphire.SCORE_INCREASE);
    }

    @Override
    public String getDescription() {
        return "Sapphires increase your score when picked up.";
    }
}
