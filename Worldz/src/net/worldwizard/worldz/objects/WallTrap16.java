/*  Worldz: A World-Exploring Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: worldz@worldwizard.net
 */
package net.worldwizard.worldz.objects;

import net.worldwizard.worldz.generic.GenericWallTrap;

public class WallTrap16 extends GenericWallTrap {
    public WallTrap16() {
        super(16, new TrappedWall16());
    }

    @Override
    public String getDescription() {
        return "Wall Traps 16 disappear when stepped on, causing all Trapped Walls 16 to also disappear.";
    }
}
