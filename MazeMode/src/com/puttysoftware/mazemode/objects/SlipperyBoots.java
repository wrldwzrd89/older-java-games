/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.generic.GenericBoots;

public class SlipperyBoots extends GenericBoots {
    // Constructors
    public SlipperyBoots() {
        super();
    }

    @Override
    public String getName() {
        return "Slippery Boots";
    }

    @Override
    public String getPluralName() {
        return "Pairs of Slippery Boots";
    }

    @Override
    public String getDescription() {
        return "Slippery Boots make all ground frictionless as you walk. Note that you can only wear one pair of boots at once.";
    }
}
