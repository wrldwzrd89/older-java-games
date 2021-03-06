/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MasterMaze@worldwizard.net
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.MasterMaze;
import com.puttysoftware.mastermaze.maze.generic.ColorConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericScoreIncreaser;

public class Ruby extends GenericScoreIncreaser {
    // Fields
    private static final long SCORE_INCREASE = 100L;

    // Constructors
    public Ruby() {
        super(ColorConstants.COLOR_LIGHT_RED);
    }

    @Override
    public String getName() {
        return "Ruby";
    }

    @Override
    public String getPluralName() {
        return "Rubys";
    }

    @Override
    public void postMoveActionHook() {
        MasterMaze.getApplication().getGameManager()
                .addToScore(Ruby.SCORE_INCREASE);
    }

    @Override
    public String getDescription() {
        return "Rubys increase your score when picked up.";
    }
}
