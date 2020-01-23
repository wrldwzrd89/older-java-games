/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractProgrammableKey;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;

public class BlueCrystal extends AbstractProgrammableKey {
    // Constructors
    public BlueCrystal() {
        super("Blue", ColorConstants.COLOR_BLUE);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Blue Crystal";
    }

    @Override
    public String getPluralName() {
        return "Blue Crystals";
    }

    @Override
    public String getDescription() {
        return "Blue Crystals will open Blue Crystal Walls.";
    }

    @Override
    public final String getIdentifierV1() {
        return "";
    }
}