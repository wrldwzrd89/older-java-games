/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.maze.effects;

import com.puttysoftware.rulemazer.Main;
import com.puttysoftware.rulemazer.objects.GhostAmulet;

public class Ghostly extends MazeEffect {
    // Constructor
    public Ghostly(final int newRounds) {
        super("Ghostly", newRounds);
    }

    @Override
    public void customTerminateLogic() {
        // Remove item that granted effect from inventory
        Main.getApplication().getGameManager().getObjectInventory()
                .removeItem(new GhostAmulet());
    }
}