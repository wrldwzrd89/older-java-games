/*  Fantastle: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

Any questions should be directed to the author via email at: fantastle@worldwizard.net
 */
package net.worldwizard.fantastle5.generic;

import net.worldwizard.fantastle5.Fantastle5;
import net.worldwizard.fantastle5.PreferencesManager;
import net.worldwizard.fantastle5.game.ObjectInventory;
import net.worldwizard.fantastle5.maze.Maze;
import net.worldwizard.fantastle5.objects.MasterTrappedWall;

public abstract class GenericWallTrap extends MazeObject {
    // Fields
    private int number;
    private GenericTrappedWall trigger;
    private final GenericTrappedWall masterTrigger = new MasterTrappedWall();
    protected static final int NUMBER_MASTER = -1;

    // Constructors
    protected GenericWallTrap(final int newNumber,
            final GenericTrappedWall newTrigger) {
        super(false);
        this.number = newNumber;
        this.trigger = newTrigger;
    }

    @Override
    public GenericWallTrap clone() {
        final GenericWallTrap copy = (GenericWallTrap) super.clone();
        copy.number = this.number;
        copy.trigger = this.trigger.clone();
        return copy;
    }

    public int getNumber() {
        return this.number;
    }

    public GenericTrappedWall getTrigger() {
        return this.trigger;
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        final int currLevel = Fantastle5.getApplication().getGameManager()
                .getPlayerManager().getPlayerLocationW();
        Fantastle5.getApplication().getGameManager().decay();
        Fantastle5.getApplication().getMazeManager().getMaze()
                .findAllMatchingObjectsAndDecay(currLevel, this.masterTrigger);
        if (this.number == GenericWallTrap.NUMBER_MASTER) {
            Fantastle5.getApplication().getMazeManager().getMaze()
                    .masterTrapTrigger(currLevel);
        } else {
            Fantastle5.getApplication().getMazeManager().getMaze()
                    .findAllMatchingObjectsAndDecay(currLevel, this);
            Fantastle5.getApplication().getMazeManager().getMaze()
                    .findAllMatchingObjectsAndDecay(currLevel, this.trigger);
        }
        Fantastle5.getApplication().getGameManager().redrawMaze();
        if (Fantastle5.getApplication().getPrefsManager()
                .getSoundEnabled(PreferencesManager.SOUNDS_GAME)) {
            MazeObject.playWallTrapSound();
        }
    }

    @Override
    public String getName() {
        if (this.number != GenericWallTrap.NUMBER_MASTER) {
            return "Wall Trap " + this.number;
        } else {
            return "Master Wall Trap";
        }
    }

    @Override
    public String getGameName() {
        return "Wall Trap";
    }

    @Override
    public String getPluralName() {
        if (this.number != GenericWallTrap.NUMBER_MASTER) {
            return "Wall Traps " + this.number;
        } else {
            return "Master Wall Traps";
        }
    }

    @Override
    public int getLayer() {
        return Maze.LAYER_OBJECT;
    }

    @Override
    public byte getGroupID() {
        return (byte) 27;
    }

    @Override
    public byte getObjectID() {
        return (byte) this.number;
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_WALL_TRAP);
    }

    @Override
    public String getMoveSuccessSoundName() {
        return "walltrap";
    }

    @Override
    public int getCustomProperty(final int propID) {
        return MazeObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }
}