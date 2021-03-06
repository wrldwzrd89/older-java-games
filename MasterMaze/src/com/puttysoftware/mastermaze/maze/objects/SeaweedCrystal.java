/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.ColorConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericProgrammableKey;

public class SeaweedCrystal extends GenericProgrammableKey {
    // Constructors
    public SeaweedCrystal() {
        super("Seaweed", ColorConstants.COLOR_SEAWEED);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Seaweed Crystal";
    }

    @Override
    public String getPluralName() {
        return "Seaweed Crystals";
    }

    @Override
    public String getDescription() {
        return "Seaweed Crystals will open Seaweed Crystal Walls.";
    }

    @Override
    public final String getIdentifierV1() {
        return "";
    }
}