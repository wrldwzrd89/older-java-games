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

import net.worldwizard.fantastle5.Fantastle5;
import net.worldwizard.fantastle5.PreferencesManager;
import net.worldwizard.fantastle5.game.ObjectInventory;
import net.worldwizard.fantastle5.generic.GenericUsableObject;
import net.worldwizard.fantastle5.generic.MazeObject;
import net.worldwizard.fantastle5.maze.Maze;

public class IceBomb extends GenericUsableObject {
    // Constants
    private static final int EFFECT_RADIUS = 3;

    // Constructors
    public IceBomb() {
        super(1);
    }

    @Override
    public String getName() {
        return "Ice Bomb";
    }

    @Override
    public String getPluralName() {
        return "Ice Bombs";
    }

    @Override
    public byte getObjectID() {
        return (byte) 0;
    }

    @Override
    public String getDescription() {
        return "Ice Bombs freeze Monsters and Barrier Generators in an area of radius 3 centered on the target point.";
    }

    @Override
    public boolean arrowHitAction(final int locX, final int locY,
            final int locZ, final int locW, final int dirX, final int dirY,
            final int arrowType, final ObjectInventory inv) {
        // Act as if bomb was used
        this.useAction(null, locX, locY, locZ, locW);
        // Destroy bomb
        Fantastle5.getApplication().getGameManager()
                .morph(new Empty(), locX, locY, locZ, locW);
        // Stop arrow
        return false;
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y,
            final int z, final int w) {
        // Freeze any monsters nearby
        if (Fantastle5.getApplication().getPrefsManager()
                .getSoundEnabled(PreferencesManager.SOUNDS_GAME)) {
            this.playUseSound();
        }
        Fantastle5
                .getApplication()
                .getMazeManager()
                .getMaze()
                .radialScanFreezeObjects(x, y, z, w, Maze.LAYER_OBJECT,
                        IceBomb.EFFECT_RADIUS);
    }

    @Override
    public void useHelper(final int x, final int y, final int z, final int w) {
        this.useAction(null, x, y, z, w);
    }

    @Override
    public String getUseSoundName() {
        return "explode";
    }
}