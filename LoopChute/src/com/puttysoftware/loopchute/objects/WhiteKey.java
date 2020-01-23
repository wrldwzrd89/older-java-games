/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.objects;

import com.puttysoftware.loopchute.generic.ColorConstants;
import com.puttysoftware.loopchute.generic.GenericSingleKey;

public class WhiteKey extends GenericSingleKey {
    // Constructors
    public WhiteKey() {
        super(ColorConstants.COLOR_WHITE);
    }

    // Scriptability
    @Override
    public String getName() {
        return "White Key";
    }

    @Override
    public String getPluralName() {
        return "White Keys";
    }

    @Override
    public String getDescription() {
        return "White Keys will unlock White Locks, and can only be used once.";
    }
}