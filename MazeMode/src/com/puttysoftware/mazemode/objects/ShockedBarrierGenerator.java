/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.Application;
import com.puttysoftware.mazemode.MazeMode;
import com.puttysoftware.mazemode.game.ObjectInventory;
import com.puttysoftware.mazemode.generic.ArrowTypeConstants;
import com.puttysoftware.mazemode.generic.GenericGenerator;

public class ShockedBarrierGenerator extends GenericGenerator {
    // Fields
    private int SHOCK_CYCLES;
    private static final int SHOCK_LIMIT = 2;

    // Constructors
    public ShockedBarrierGenerator() {
        super();
        this.TIMER_DELAY = 2;
        this.SHOCK_CYCLES = 0;
    }

    @Override
    public String getName() {
        return "Shocked Barrier Generator";
    }

    @Override
    public String getPluralName() {
        return "Shocked Barrier Generators";
    }

    @Override
    public String getDescription() {
        return "Shocked Barrier Generators create Barriers. When hit or shot, they stop generating for a while, then resume generating MUCH faster than normal.";
    }

    @Override
    protected boolean preMoveActionHook(final int dirX, final int dirY,
            final int dirZ, final int dirW) {
        this.SHOCK_CYCLES++;
        if (this.SHOCK_CYCLES == ShockedBarrierGenerator.SHOCK_LIMIT) {
            final Application app = MazeMode.getApplication();
            final BarrierGenerator bg = new BarrierGenerator();
            app.getGameManager().morph(bg, dirX, dirY, dirZ);
            bg.timerExpiredAction(dirX, dirY);
        }
        return true;
    }

    @Override
    protected void arrowHitActionHook(final int locX, final int locY,
            final int locZ, final int arrowType, final ObjectInventory inv) {
        final Application app = MazeMode.getApplication();
        if (arrowType == ArrowTypeConstants.ARROW_TYPE_ICE) {
            app.getGameManager().morph(new IcedBarrierGenerator(), locX, locY,
                    locZ);
        } else if (arrowType == ArrowTypeConstants.ARROW_TYPE_POISON) {
            app.getGameManager().morph(new PoisonedBarrierGenerator(), locX,
                    locY, locZ);
        } else if (arrowType == ArrowTypeConstants.ARROW_TYPE_FIRE) {
            app.getGameManager().morph(new EnragedBarrierGenerator(), locX,
                    locY, locZ);
        } else {
            this.preMoveAction(false, locX, locY, inv);
        }
    }
}
