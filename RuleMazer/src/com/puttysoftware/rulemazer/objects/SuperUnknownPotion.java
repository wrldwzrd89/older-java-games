/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericPotion;

public class SuperUnknownPotion extends GenericPotion {
    // Fields
    private static final int MIN_EFFECT = -99;
    private static final int MAX_EFFECT = 99;

    // Constructors
    public SuperUnknownPotion() {
        super(true, SuperUnknownPotion.MIN_EFFECT,
                SuperUnknownPotion.MAX_EFFECT);
    }

    @Override
    public String getName() {
        return "Super Unknown Potion";
    }

    @Override
    public String getPluralName() {
        return "Super Unknown Potions";
    }

    @Override
    public String getDescription() {
        return "Super Unknown Potions might heal you almost fully or hurt you to the brink of death when picked up.";
    }
}