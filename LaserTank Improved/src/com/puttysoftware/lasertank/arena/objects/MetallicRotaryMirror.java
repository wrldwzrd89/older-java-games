/*  LaserTank: An Arena-Solving Game
 Copyright (C) 2008-2013 Eric Ahnell

 Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.lasertank.arena.objects;

import com.puttysoftware.lasertank.LaserTank;
import com.puttysoftware.lasertank.arena.abstractobjects.AbstractArenaObject;
import com.puttysoftware.lasertank.arena.abstractobjects.AbstractReactionWall;
import com.puttysoftware.lasertank.resourcemanagers.SoundConstants;
import com.puttysoftware.lasertank.resourcemanagers.SoundManager;
import com.puttysoftware.lasertank.utilities.Direction;
import com.puttysoftware.lasertank.utilities.DirectionResolver;
import com.puttysoftware.lasertank.utilities.MaterialConstants;

public class MetallicRotaryMirror extends AbstractReactionWall {
    // Constructors
    public MetallicRotaryMirror() {
        super();
        this.setDirection(Direction.NORTHEAST);
        this.setDiagonalOnly(true);
        this.setMaterial(MaterialConstants.MATERIAL_METALLIC);
    }

    @Override
    public boolean doLasersPassThrough() {
        return true;
    }

    @Override
    public final int getStringBaseID() {
        return 66;
    }

    @Override
    public Direction laserEnteredActionHook(final int locX, final int locY,
            final int locZ, final int dirX, final int dirY, final int laserType,
            final int forceUnits) {
        final Direction dir = DirectionResolver.resolveRelativeInvert(dirX,
                dirY);
        if (AbstractArenaObject.hitReflectiveSide(dir)) {
            // Reflect laser
            return this.getDirection();
        } else {
            // Rotate mirror
            this.toggleDirection();
            SoundManager.playSound(SoundConstants.SOUND_ROTATE);
            return Direction.NONE;
        }
    }

    @Override
    public Direction laserExitedAction(final int locX, final int locY,
            final int locZ, final int dirX, final int dirY,
            final int laserType) {
        // Finish reflecting laser
        SoundManager.playSound(SoundConstants.SOUND_REFLECT);
        final Direction oldlaser = DirectionResolver.resolveRelativeInvert(locX,
                locY);
        final Direction currdir = this.getDirection();
        if (oldlaser == Direction.NORTH) {
            if (currdir == Direction.NORTHWEST) {
                return Direction.WEST;
            } else if (currdir == Direction.NORTHEAST) {
                return Direction.EAST;
            }
        } else if (oldlaser == Direction.SOUTH) {
            if (currdir == Direction.SOUTHWEST) {
                return Direction.WEST;
            } else if (currdir == Direction.SOUTHEAST) {
                return Direction.EAST;
            }
        } else if (oldlaser == Direction.WEST) {
            if (currdir == Direction.SOUTHWEST) {
                return Direction.SOUTH;
            } else if (currdir == Direction.NORTHWEST) {
                return Direction.NORTH;
            }
        } else if (oldlaser == Direction.EAST) {
            if (currdir == Direction.SOUTHEAST) {
                return Direction.SOUTH;
            } else if (currdir == Direction.NORTHEAST) {
                return Direction.NORTH;
            }
        }
        return Direction.NONE;
    }

    @Override
    public boolean rangeActionHook(final int locX, final int locY,
            final int locZ, final int dirX, final int dirY, final int rangeType,
            final int forceUnits) {
        // Rotate mirror
        this.toggleDirection();
        SoundManager.playSound(SoundConstants.SOUND_ROTATE);
        LaserTank.getApplication().getArenaManager().getArena()
                .markAsDirty(locX + dirX, locY + dirY, locZ);
        return true;
    }
}
