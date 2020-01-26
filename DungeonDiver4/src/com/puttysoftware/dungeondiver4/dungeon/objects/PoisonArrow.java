/*  DungeonDiver4: A Dungeon-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: DungeonDiver4@worldwizard.net
 */
package com.puttysoftware.dungeondiver4.dungeon.objects;

import com.puttysoftware.dungeondiver4.dungeon.abc.AbstractTransientObject;
import com.puttysoftware.dungeondiver4.dungeon.utilities.ColorConstants;

public class PoisonArrow extends AbstractTransientObject {
    // Constructors
    public PoisonArrow() {
        super("Poison Arrow", ColorConstants.COLOR_GREEN);
    }
}