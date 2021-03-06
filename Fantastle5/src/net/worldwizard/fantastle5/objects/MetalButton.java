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
package net.worldwizard.fantastle5.objects;

import net.worldwizard.fantastle5.Application;
import net.worldwizard.fantastle5.Fantastle5;
import net.worldwizard.fantastle5.Messager;
import net.worldwizard.fantastle5.PreferencesManager;
import net.worldwizard.fantastle5.game.ObjectInventory;
import net.worldwizard.fantastle5.generic.GenericField;
import net.worldwizard.fantastle5.generic.MazeObject;
import net.worldwizard.fantastle5.generic.TypeConstants;
import net.worldwizard.fantastle5.maze.Maze;
import net.worldwizard.fantastle5.resourcemanagers.SoundManager;

public class MetalButton extends GenericField {
    // Fields
    private final int targetRow;
    private final int targetCol;
    private final int targetFloor;
    private final int targetLevel;

    // Constructors
    public MetalButton() {
        super(false, new MetalBoots());
        this.targetRow = 0;
        this.targetCol = 0;
        this.targetFloor = 0;
        this.targetLevel = 0;
    }

    public MetalButton(final int tRow, final int tCol, final int tFloor,
            final int tLevel) {
        super(false, new MetalBoots());
        this.targetRow = tRow;
        this.targetCol = tCol;
        this.targetFloor = tFloor;
        this.targetLevel = tLevel;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final MetalButton other = (MetalButton) obj;
        if (this.targetRow != other.targetRow) {
            return false;
        }
        if (this.targetCol != other.targetCol) {
            return false;
        }
        if (this.targetFloor != other.targetFloor) {
            return false;
        }
        if (this.targetLevel != other.targetLevel) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.targetRow;
        hash = 79 * hash + this.targetCol;
        hash = 79 * hash + this.targetFloor;
        hash = 79 * hash + this.targetLevel;
        return hash;
    }

    public int getTargetRow() {
        return this.targetRow;
    }

    public int getTargetColumn() {
        return this.targetCol;
    }

    public int getTargetFloor() {
        return this.targetFloor;
    }

    public int getTargetLevel() {
        return this.targetLevel;
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        // Do nothing
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        if (inv.isItemThere(this.getKey())) {
            final Application app = Fantastle5.getApplication();
            final MazeObject there = app.getMazeManager().getMazeObject(
                    this.getTargetRow(), this.getTargetColumn(),
                    this.getTargetFloor(), this.getTargetLevel(),
                    this.getLayer());
            if (there != null) {
                if (there.getName().equals(new MetalDoor().getName())) {
                    app.getGameManager().morph(new Empty(), this.getTargetRow(),
                            this.getTargetColumn(), this.getTargetFloor(),
                            this.getTargetLevel());
                } else {
                    app.getGameManager().morph(new MetalDoor(),
                            this.getTargetRow(), this.getTargetColumn(),
                            this.getTargetFloor(), this.getTargetLevel());
                }
            }
            if (app.getPrefsManager()
                    .getSoundEnabled(PreferencesManager.SOUNDS_GAME)) {
                this.playMoveSuccessSound();
            }
        } else {
            if (Fantastle5.getApplication().getPrefsManager()
                    .getSoundEnabled(PreferencesManager.SOUNDS_GAME)) {
                SoundManager.playSoundAsynchronously("walk");
            }
        }
    }

    @Override
    public String getName() {
        return "Metal Button";
    }

    @Override
    public String getPluralName() {
        return "Metal Buttons";
    }

    @Override
    public boolean isConditionallySolid(final ObjectInventory inv) {
        return this.isSolid();
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie,
            final int dirX, final int dirY, final ObjectInventory inv) {
        return this.isDirectionallySolid(ie, dirX, dirY);
    }

    @Override
    public byte getObjectID() {
        return (byte) 5;
    }

    @Override
    public int getLayer() {
        return Maze.LAYER_OBJECT;
    }

    @Override
    public void editorProbeHook() {
        Messager.showMessage(this.getName() + ": Target ("
                + (this.targetCol + 1) + "," + (this.targetRow + 1) + ","
                + (this.targetFloor + 1) + "," + (this.targetLevel + 1) + ")");
    }

    @Override
    public MazeObject editorPropertiesHook() {
        return Fantastle5.getApplication().getEditor().editMetalButtonTarget();
    }

    @Override
    public String getDescription() {
        return "Metal Buttons will not trigger without Metal Boots.";
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_BUTTON);
        this.type.set(TypeConstants.TYPE_FIELD);
        this.type.set(TypeConstants.TYPE_INFINITE_LOCK);
        this.type.set(TypeConstants.TYPE_LOCK);
    }

    @Override
    public String getMoveSuccessSoundName() {
        return "button";
    }

    @Override
    public boolean defersSetProperties() {
        return true;
    }

    @Override
    public boolean hasAdditionalProperties() {
        return true;
    }
}
