/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MazeRunnerII@worldwizard.net
 */
package com.puttysoftware.mazerunner2.maze.objects;

import com.puttysoftware.mazerunner2.creatures.StatConstants;
import com.puttysoftware.mazerunner2.maze.abc.AbstractPotion;
import com.puttysoftware.mazerunner2.resourcemanagers.ObjectImageConstants;

public class MajorHealPotion extends AbstractPotion {
    // Fields
    private static final int MIN_HEAL = 6;
    private static final int MAX_HEAL = 50;

    // Constructors
    public MajorHealPotion() {
        super(StatConstants.STAT_CURRENT_HP, true, MajorHealPotion.MIN_HEAL,
                MajorHealPotion.MAX_HEAL);
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_MAJOR_HEAL_POTION;
    }

    @Override
    public String getName() {
        return "Major Heal Potion";
    }

    @Override
    public String getPluralName() {
        return "Major Heal Potions";
    }

    @Override
    public String getDescription() {
        return "Major Heal Potions heal you significantly when picked up.";
    }
}