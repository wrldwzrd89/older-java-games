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
import net.worldwizard.fantastle5.generic.GenericTeleport;
import net.worldwizard.fantastle5.generic.MazeObject;

public class Finish extends GenericTeleport {
    // Constructors
    public Finish() {
        super(0, 0, 0, 0);
    }

    public Finish(final int destLevel) {
        super(0, 0, 0, destLevel);
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        final Application app = Fantastle5.getApplication();
        if (app.getPrefsManager()
                .getSoundEnabled(PreferencesManager.SOUNDS_GAME)) {
            this.playMoveSuccessSound();
        }
        if (app.getGameManager().isLevelAbove()) {
            Messager.showDialog("Level Solved!");
            app.getGameManager().solvedLevel();
        } else {
            Messager.showDialog("Maze Solved!");
            app.getGameManager().solvedMaze();
        }
    }

    @Override
    public String getName() {
        return "Finish";
    }

    @Override
    public String getPluralName() {
        return "Finishes";
    }

    @Override
    public void editorProbeHook() {
        Messager.showMessage(this.getName());
    }

    @Override
    public MazeObject editorPropertiesHook() {
        return null;
    }

    @Override
    public byte getObjectID() {
        return (byte) 1;
    }

    @Override
    public String getMoveSuccessSoundName() {
        return "finish";
    }

    @Override
    public String getDescription() {
        return "Finishes lead to the next level, if one exists. Otherwise, entering one solves the maze.";
    }

    @Override
    public int getCustomFormat() {
        if (Fantastle5.getApplication().getMazeManager().maze3Compatible()) {
            // Emulate older format bug
            return 4;
        } else {
            return 0;
        }
    }
}