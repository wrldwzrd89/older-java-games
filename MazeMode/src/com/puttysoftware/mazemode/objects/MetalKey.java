/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.generic.GenericSingleKey;

public class MetalKey extends GenericSingleKey {
    // Constructors
    public MetalKey() {
        super();
    }

    // Scriptability
    @Override
    public String getName() {
        return "Metal Key";
    }

    @Override
    public String getPluralName() {
        return "Metal Keys";
    }

    @Override
    public String getDescription() {
        return "Metal Keys will open Metal Doors, and can only be used once.";
    }
}