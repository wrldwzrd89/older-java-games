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
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.MasterMaze;
import com.puttysoftware.mastermaze.maze.MazeConstants;
import com.puttysoftware.mastermaze.maze.generic.GenericBomb;

public class SmokeBomb extends GenericBomb {
    // Constants
    private static final int STUN_DURATION = 10;

    // Constructors
    public SmokeBomb() {
        super(1);
    }

    @Override
    public String getName() {
        return "Smoke Bomb";
    }

    @Override
    public String getPluralName() {
        return "Smoke Bombs";
    }

    @Override
    public String getDescription() {
        return "Smoke Bombs temporarily stun Monsters in an area of radius 3 centered on the target point.";
    }

    @Override
    public void useActionHook(final int x, final int y, final int z) {
        // Paralyze any monsters nearby
        MasterMaze
                .getApplication()
                .getMazeManager()
                .getMaze()
                .radialScanTimerAction(x, y, z, MazeConstants.LAYER_OBJECT,
                        GenericBomb.EFFECT_RADIUS, "Monster",
                        SmokeBomb.STUN_DURATION);
    }
}