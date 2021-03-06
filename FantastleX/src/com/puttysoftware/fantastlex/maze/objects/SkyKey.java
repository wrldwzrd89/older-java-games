/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractSingleKey;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;

public class SkyKey extends AbstractSingleKey {
    // Constructors
    public SkyKey() {
        super(ColorConstants.COLOR_SKY);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Sky Key";
    }

    @Override
    public String getPluralName() {
        return "Sky Keys";
    }

    @Override
    public String getDescription() {
        return "Sky Keys will unlock Sky Locks, and can only be used once.";
    }
}