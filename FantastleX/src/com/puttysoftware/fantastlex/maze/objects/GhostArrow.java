/*  FantastleX: A Maze/RPG Hybrid Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: FantastleX@worldwizard.net
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractTransientObject;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;

public class GhostArrow extends AbstractTransientObject {
    // Constructors
    public GhostArrow() {
        super("Ghost Arrow", ColorConstants.COLOR_GRAY);
    }
}