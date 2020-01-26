/*  Worldz: A World-Exploring Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: worldz@worldwizard.net
 */
package net.worldwizard.worldz.objects;

import net.worldwizard.worldz.generic.GenericTrappedWall;

public class TrappedWall0 extends GenericTrappedWall {
    public TrappedWall0() {
        super(0);
    }

    @Override
    public String getDescription() {
        return "Trapped Walls 0 disappear when any Wall Trap 0 is triggered.";
    }
}