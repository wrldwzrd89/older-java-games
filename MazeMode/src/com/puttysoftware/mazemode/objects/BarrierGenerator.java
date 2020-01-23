/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.MazeMode;
import com.puttysoftware.mazemode.game.GameManager;
import com.puttysoftware.mazemode.game.ObjectInventory;
import com.puttysoftware.mazemode.generic.ArrowTypeConstants;
import com.puttysoftware.mazemode.generic.GenericGenerator;

public class BarrierGenerator extends GenericGenerator {
    // Constructors
    public BarrierGenerator() {
        super();
    }

    @Override
    public String getName() {
        return "Barrier Generator";
    }

    @Override
    public String getPluralName() {
        return "Barrier Generators";
    }

    @Override
    public String getDescription() {
        return "Barrier Generators create Barriers. When hit or shot, they stop generating for a while, then resume generating.";
    }

    @Override
    protected boolean preMoveActionHook(final int dirX, final int dirY,
            final int dirZ, final int dirW) {
        return true;
    }

    @Override
    protected void arrowHitActionHook(final int locX, final int locY,
            final int locZ, final int arrowType, final ObjectInventory inv) {
        final GameManager gm = MazeMode.getApplication().getGameManager();
        if (arrowType == ArrowTypeConstants.ARROW_TYPE_ICE) {
            gm.morph(new IcedBarrierGenerator(), locX, locY, locZ);
        } else if (arrowType == ArrowTypeConstants.ARROW_TYPE_FIRE) {
            gm.morph(new EnragedBarrierGenerator(), locX, locY, locZ);
        } else if (arrowType == ArrowTypeConstants.ARROW_TYPE_POISON) {
            gm.morph(new PoisonedBarrierGenerator(), locX, locY, locZ);
        } else if (arrowType == ArrowTypeConstants.ARROW_TYPE_SHOCK) {
            gm.morph(new ShockedBarrierGenerator(), locX, locY, locZ);
        } else {
            this.preMoveAction(false, locX, locY, inv);
        }
    }
}
