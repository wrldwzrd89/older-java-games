/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericWall;
import com.puttysoftware.rulemazer.generic.TypeConstants;

public class OneWayWestWall extends GenericWall {
    public OneWayWestWall() {
        super(true, true, true, false, true, true, true, false);
    }

    @Override
    public String getName() {
        return "One-Way West Wall";
    }

    @Override
    public String getPluralName() {
        return "One-Way West Walls";
    }

    @Override
    public String getDescription() {
        return "One-Way West Walls allow movement through them only West.";
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_PLAIN_WALL);
        this.type.set(TypeConstants.TYPE_WALL);
    }
}
