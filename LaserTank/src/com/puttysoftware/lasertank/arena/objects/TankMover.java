/*  LaserTank: An Arena-Solving Game
 Copyright (C) 2008-2013 Eric Ahnell

 Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.lasertank.arena.objects;

import com.puttysoftware.lasertank.arena.abstractobjects.AbstractMover;
import com.puttysoftware.lasertank.utilities.Direction;
import com.puttysoftware.lasertank.utilities.TypeConstants;

public class TankMover extends AbstractMover {
    // Constructors
    public TankMover() {
        super();
        this.setDirection(Direction.NORTH);
        this.setFrameNumber(1);
        this.type.set(TypeConstants.TYPE_MOVER);
    }

    @Override
    public final int getStringBaseID() {
        return 37;
    }
}