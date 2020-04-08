/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.generic;

import com.puttysoftware.mazer5d.Mazer5D;

public abstract class GenericWand extends GenericUsableObject {
    // Fields
    private static final long SCORE_USE = 5L;

    // Constructors
    protected GenericWand() {
        super(1);
    }

    @Override
    public abstract String getName();

    @Override
    public void useAction(final MazeObject mo, final int x, final int y,
            final int z) {
        final Mazer5D app = Mazer5D.getApplication();
        app.getGameManager().morph(mo, x, y, z);
        Mazer5D.getApplication().getGameManager()
                .addToScore(GenericWand.SCORE_USE);
    }

    @Override
    public abstract void useHelper(int x, int y, int z);

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_WAND);
        this.type.set(TypeConstants.TYPE_USABLE);
        this.type.set(TypeConstants.TYPE_INVENTORYABLE);
        this.type.set(TypeConstants.TYPE_CONTAINABLE);
    }
}
