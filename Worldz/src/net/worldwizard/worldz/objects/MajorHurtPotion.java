/*  Worldz: A World-Exploring Game
Copyright (C) 2008-2010 Eric Ahnell


Any questions should be directed to the author via email at: worldz@worldwizard.net
 */
package net.worldwizard.worldz.objects;

import net.worldwizard.worldz.generic.GenericPotion;

public class MajorHurtPotion extends GenericPotion {
    // Fields
    private static final int MIN_HURT = -6;
    private static final int MAX_HURT = -50;

    // Constructors
    public MajorHurtPotion() {
        super(true, MajorHurtPotion.MAX_HURT, MajorHurtPotion.MIN_HURT);
    }

    @Override
    public String getName() {
        return "Major Hurt Potion";
    }

    @Override
    public String getPluralName() {
        return "Major Hurt Potions";
    }

    @Override
    public String getDescription() {
        return "Major Hurt Potions hurt you significantly when picked up.";
    }
}