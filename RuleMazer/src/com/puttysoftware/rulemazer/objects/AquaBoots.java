/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericBoots;

public class AquaBoots extends GenericBoots {
    // Constructors
    public AquaBoots() {
        super();
    }

    @Override
    public String getName() {
        return "Aqua Boots";
    }

    @Override
    public String getPluralName() {
        return "Pairs of Aqua Boots";
    }

    @Override
    public String getDescription() {
        return "Aqua Boots allow walking on water. Note that you can only wear one pair of boots at once.";
    }
}
