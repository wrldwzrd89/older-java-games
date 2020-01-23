/*  Worldz: A World-Exploring Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: worldz@worldwizard.net
 */
package net.worldwizard.worldz.objects;

import net.worldwizard.worldz.generic.GenericWallTrap;

public class WallTrap6 extends GenericWallTrap {
    public WallTrap6() {
        super(6, new TrappedWall6());
    }

    @Override
    public String getDescription() {
        return "Wall Traps 6 disappear when stepped on, causing all Trapped Walls 6 to also disappear.";
    }
}
