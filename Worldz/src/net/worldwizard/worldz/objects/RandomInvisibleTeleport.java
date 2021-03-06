/*  Worldz: A World-Exploring Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: worldz@worldwizard.net
 */
package net.worldwizard.worldz.objects;

import net.worldwizard.worldz.generic.GenericRandomInvisibleTeleport;

public class RandomInvisibleTeleport extends GenericRandomInvisibleTeleport {
    // Constructors
    public RandomInvisibleTeleport() {
        super(0, 0);
    }

    public RandomInvisibleTeleport(final int newRandomRangeY,
            final int newRandomRangeX) {
        super(newRandomRangeY, newRandomRangeX);
    }

    // Scriptability
    @Override
    public String getName() {
        return "Random Invisible Teleport";
    }

    @Override
    public String getGameName() {
        return "Empty";
    }

    @Override
    public String getPluralName() {
        return "Random Invisible Teleports";
    }

    @Override
    public String getDescription() {
        return "Random Invisible Teleports are both random and invisible.";
    }
}