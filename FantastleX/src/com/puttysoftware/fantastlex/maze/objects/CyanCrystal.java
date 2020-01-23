/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractProgrammableKey;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;

public class CyanCrystal extends AbstractProgrammableKey {
    // Constructors
    public CyanCrystal() {
        super("Cyan", ColorConstants.COLOR_CYAN);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Cyan Crystal";
    }

    @Override
    public String getPluralName() {
        return "Cyan Crystals";
    }

    @Override
    public String getDescription() {
        return "Cyan Crystals will open Cyan Crystal Walls.";
    }

    @Override
    public final String getIdentifierV1() {
        return "";
    }
}