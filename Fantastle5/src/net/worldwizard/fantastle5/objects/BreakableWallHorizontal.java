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
import net.worldwizard.fantastle5.PreferencesManager;
import net.worldwizard.fantastle5.generic.GenericWall;
import net.worldwizard.fantastle5.generic.MazeObject;
import net.worldwizard.fantastle5.maze.Maze;

public class BreakableWallHorizontal extends GenericWall {
    // Constructors
    public BreakableWallHorizontal() {
        super(true, true);
    }

    @Override
    public void chainReactionAction(final int x, final int y, final int z,
            final int w) {
        if (Fantastle5.getApplication().getPrefsManager()
                .getSoundEnabled(PreferencesManager.SOUNDS_GAME)) {
            this.playChainReactSound();
        }
        BreakableWallHorizontal.doChainReact(x, y, z, w);
    }

    private static void doChainReact(final int x, final int y, final int z,
            final int w) {
        final Application app = Fantastle5.getApplication();
        BreakableWallHorizontal curr = null;
        try {
            curr = (BreakableWallHorizontal) app.getMazeManager()
                    .getMazeObject(x, y, z, w, Maze.LAYER_OBJECT);
        } catch (final ClassCastException cce) {
            // We're not a breakable wall horizontal, so abort
            return;
        }
        String mo4Name, mo6Name, invalidName, currName;
        invalidName = new EmptyVoid().getName();
        currName = curr.getName();
        final MazeObject mo4 = app.getMazeManager().getMazeObject(x - 1, y, z,
                w, Maze.LAYER_OBJECT);
        try {
            mo4Name = mo4.getName();
        } catch (final NullPointerException np) {
            mo4Name = invalidName;
        }
        final MazeObject mo6 = app.getMazeManager().getMazeObject(x + 1, y, z,
                w, Maze.LAYER_OBJECT);
        try {
            mo6Name = mo6.getName();
        } catch (final NullPointerException np) {
            mo6Name = invalidName;
        }
        app.getGameManager().morph(new Empty(), x, y, z, w);
        if (mo4Name.equals(currName)) {
            BreakableWallHorizontal.doChainReact(x - 1, y, z, w);
        }
        if (mo6Name.equals(currName)) {
            BreakableWallHorizontal.doChainReact(x + 1, y, z, w);
        }
    }

    @Override
    public String getName() {
        return "Breakable Wall Horizontal";
    }

    @Override
    public String getGameName() {
        return "Wall";
    }

    @Override
    public String getPluralName() {
        return "Breakable Walls Horizontal";
    }

    @Override
    public byte getObjectID() {
        return (byte) 0;
    }

    @Override
    public String getDescription() {
        return "Breakable Walls Horizontal disintegrate when touched, causing other Breakable Walls Horizontal nearby to also disintegrate.";
    }

    @Override
    public String getChainReactSoundName() {
        return "crack";
    }
}
