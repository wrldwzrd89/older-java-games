/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.generic;

import java.util.Random;

import com.puttysoftware.mazemode.Application;
import com.puttysoftware.mazemode.MazeMode;
import com.puttysoftware.mazemode.editor.MazeEditor;
import com.puttysoftware.mazemode.game.ObjectInventory;
import com.puttysoftware.mazemode.maze.MazeConstants;
import com.puttysoftware.mazemode.resourcemanagers.SoundConstants;
import com.puttysoftware.mazemode.resourcemanagers.SoundManager;

public abstract class GenericRandomTeleport extends MazeObject {
    // Fields
    private int randomRangeX;
    private int randomRangeY;
    private final Random generator;

    // Constructors
    public GenericRandomTeleport(final int newRandomRangeY,
            final int newRandomRangeX) {
        super(false);
        this.randomRangeX = newRandomRangeX;
        this.randomRangeY = newRandomRangeY;
        this.generator = new Random();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final GenericRandomTeleport other = (GenericRandomTeleport) obj;
        if (this.randomRangeX != other.randomRangeX) {
            return false;
        }
        if (this.randomRangeY != other.randomRangeY) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.randomRangeX;
        hash = 89 * hash + this.randomRangeY;
        return hash;
    }

    @Override
    public GenericRandomTeleport clone() {
        final GenericRandomTeleport copy = (GenericRandomTeleport) super.clone();
        copy.randomRangeX = this.randomRangeX;
        copy.randomRangeY = this.randomRangeY;
        return copy;
    }

    // Methods
    public int getDestinationRow() {
        if (this.randomRangeY == 0) {
            return 0;
        } else {
            int sign = this.generator.nextInt(2);
            final int value = this.generator.nextInt(this.randomRangeY + 1);
            if (sign == 0) {
                sign = -1;
            }
            return sign * value;
        }
    }

    public int getDestinationColumn() {
        if (this.randomRangeX == 0) {
            return 0;
        } else {
            int sign = this.generator.nextInt(2);
            final int value = this.generator.nextInt(this.randomRangeX + 1);
            if (sign == 0) {
                sign = -1;
            }
            return sign * value;
        }
    }

    public int getDestinationFloor() {
        final Application app = MazeMode.getApplication();
        return app.getGameManager().getPlayerManager().getPlayerLocationZ();
    }

    public int getDestinationLevel() {
        final Application app = MazeMode.getApplication();
        return app.getGameManager().getPlayerManager().getPlayerLocationW();
    }

    public int getRandomRowRange() {
        return this.randomRangeY;
    }

    public int getRandomColumnRange() {
        return this.randomRangeX;
    }

    @Override
    public int getLayer() {
        return MazeConstants.LAYER_OBJECT;
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        final Application app = MazeMode.getApplication();
        int dr, dc;
        do {
            dr = this.getDestinationRow();
            dc = this.getDestinationColumn();
        } while (!app.getGameManager().tryUpdatePositionRelative(dr, dc));
        app.getGameManager().updatePositionRelative(dr, dc);
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_TELEPORT);
    }

    @Override
    public void editorProbeHook() {
        MazeMode.getApplication().showMessage(this.getName() + ": Row Radius "
                + this.randomRangeY + ", Column Radius " + this.randomRangeX);
    }

    @Override
    public MazeObject editorPropertiesHook() {
        final MazeEditor me = MazeMode.getApplication().getEditor();
        final MazeObject mo = me
                .editTeleportDestination(MazeEditor.TELEPORT_TYPE_RANDOM);
        return mo;
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_RANDOM_TELEPORT);
    }

    @Override
    public int getCustomProperty(final int propID) {
        switch (propID) {
        case 1:
            return this.randomRangeX;
        case 2:
            return this.randomRangeY;
        default:
            return MazeObject.DEFAULT_CUSTOM_VALUE;
        }
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        switch (propID) {
        case 1:
            this.randomRangeX = value;
            break;
        case 2:
            this.randomRangeY = value;
            break;
        default:
            break;
        }
    }

    @Override
    public int getCustomFormat() {
        return 2;
    }
}
