/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.generic.GenericSingleKey;

public class MagentaKey extends GenericSingleKey {
    // Constructors
    public MagentaKey() {
        super();
    }

    // Scriptability
    @Override
    public String getName() {
        return "Magenta Key";
    }

    @Override
    public String getPluralName() {
        return "Magenta Keys";
    }

    @Override
    public String getDescription() {
        return "Magenta Keys will unlock Magenta Locks, and can only be used once.";
    }
}