/*  BrainMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.brainmaze.objects;

import com.puttysoftware.brainmaze.generic.ColorConstants;
import com.puttysoftware.brainmaze.generic.GenericSingleKey;

public class Key extends GenericSingleKey {
    // Constructors
    public Key() {
        super(ColorConstants.COLOR_BLACK);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Key";
    }

    @Override
    public String getPluralName() {
        return "Keys";
    }

    @Override
    public String getDescription() {
        return "Keys unlock Locks, and can only be used once.";
    }
}