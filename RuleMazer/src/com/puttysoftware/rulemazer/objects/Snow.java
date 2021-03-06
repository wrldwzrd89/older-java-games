/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.generic.GenericGround;

public class Snow extends GenericGround {
    // Constructors
    public Snow() {
        super();
    }

    @Override
    public String getName() {
        return "Snow";
    }

    @Override
    public String getPluralName() {
        return "Squares of Snow";
    }

    @Override
    public String getDescription() {
        return "Snow is one of the many types of ground.";
    }
}