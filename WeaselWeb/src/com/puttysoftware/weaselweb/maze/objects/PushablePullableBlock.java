/*  WeaselWeb: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.weaselweb.maze.objects;

import com.puttysoftware.weaselweb.maze.generic.GenericMovableObject;

public class PushablePullableBlock extends GenericMovableObject {
    // Constructors
    public PushablePullableBlock() {
        super(true, true);
    }

    @Override
    public String getName() {
        return "Pushable/Pullable Block";
    }

    @Override
    public String getPluralName() {
        return "Pushable/Pullable Blocks";
    }

    @Override
    public String getDescription() {
        return "Pushable/Pullable Blocks can be both pushed and pulled.";
    }
}
