/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: mazer5d@worldwizard.net
 */
package com.puttysoftware.mastermaze.maze.generic;

import com.puttysoftware.mastermaze.resourcemanagers.ObjectImageConstants;

public abstract class GenericProgrammableKey extends GenericSingleKey {
    // Fields
    private String color;

    // Constructors
    protected GenericProgrammableKey(final String newColor, final int tc) {
        super(tc);
        this.color = newColor;
    }

    @Override
    public GenericProgrammableKey clone() {
        final GenericProgrammableKey copy = (GenericProgrammableKey) super.clone();
        copy.color = this.color;
        return copy;
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_CRYSTAL;
    }

    @Override
    public String getName() {
        return this.color + " Crystal";
    }

    @Override
    public String getPluralName() {
        return this.color + " Crystals";
    }

    @Override
    public String getDescription() {
        return this.color
                + " Crystals may open Crystal Walls, and can be used only once.";
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_PROGRAMMABLE_KEY);
        this.type.set(TypeConstants.TYPE_SINGLE_KEY);
        this.type.set(TypeConstants.TYPE_KEY);
        this.type.set(TypeConstants.TYPE_INVENTORYABLE);
        this.type.set(TypeConstants.TYPE_CONTAINABLE);
    }
}