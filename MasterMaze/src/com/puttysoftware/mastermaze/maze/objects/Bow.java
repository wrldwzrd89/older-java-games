/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MasterMaze@worldwizard.net
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.ArrowTypeConstants;
import com.puttysoftware.mastermaze.maze.generic.ColorConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericBow;

public class Bow extends GenericBow {
    // Constants
    private static final int BOW_USES = -1;

    // Constructors
    public Bow() {
        super(Bow.BOW_USES, ArrowTypeConstants.ARROW_TYPE_PLAIN,
                ColorConstants.COLOR_BROWN);
    }

    @Override
    public String getName() {
        return "Bow";
    }

    @Override
    public String getPluralName() {
        return "Bows";
    }

    @Override
    public String getDescription() {
        return "Bows shoot an unlimited supply of normal arrows.";
    }

    @Override
    public final String getIdentifierV1() {
        return "";
    }
}
