/*  WeaselWeb: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.weaselweb.maze.generic;

public abstract class GenericKey extends GenericInventoryableObject {
    // Fields
    private boolean infinite;

    // Constructors
    protected GenericKey(final boolean infiniteUse) {
        super(false, 0);
        this.infinite = infiniteUse;
        this.setType(TypeConstants.TYPE_KEY);
    }

    @Override
    public GenericKey clone() {
        final GenericKey copy = (GenericKey) super.clone();
        copy.infinite = this.infinite;
        return copy;
    }

    public boolean isInfinite() {
        return this.infinite;
    }

    @Override
    public abstract String getName();

    @Override
    public int getCustomProperty(final int propID) {
        return MazeObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }
}
