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

import net.worldwizard.fantastle5.creatures.StatConstants;
import net.worldwizard.fantastle5.generic.GenericPotion;

public class MinorHurtPotion extends GenericPotion {
    // Fields
    private static final int MIN_HURT = -1;
    private static final int MAX_HURT = -5;

    // Constructors
    public MinorHurtPotion() {
        super(StatConstants.STAT_CURRENT_HP, true, MinorHurtPotion.MAX_HURT,
                MinorHurtPotion.MIN_HURT);
    }

    @Override
    public String getName() {
        return "Minor Hurt Potion";
    }

    @Override
    public String getPluralName() {
        return "Minor Hurt Potions";
    }

    @Override
    public byte getObjectID() {
        return (byte) 2;
    }

    @Override
    public String getDescription() {
        return "Minor Hurt Potions hurt you slightly when picked up.";
    }
}