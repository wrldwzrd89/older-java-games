/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: loopchute@puttysoftware.com
 */
package com.puttysoftware.loopchute.objects;

import com.puttysoftware.loopchute.generic.ArrowTypeConstants;
import com.puttysoftware.loopchute.generic.ColorConstants;
import com.puttysoftware.loopchute.generic.GenericBow;

public class ShockBow extends GenericBow {
    // Constants
    private static final int BOW_USES = 30;

    // Constructors
    public ShockBow() {
        super(ShockBow.BOW_USES, ArrowTypeConstants.ARROW_TYPE_SHOCK);
        this.setTemplateColor(ColorConstants.COLOR_LIGHT_YELLOW);
    }

    @Override
    public String getName() {
        return "Shock Bow";
    }

    @Override
    public String getPluralName() {
        return "Shock Bows";
    }

    @Override
    public String getDescription() {
        return "Shock Bows allow shooting of Shock Arrows, which energize Barrier Generators upon contact, and do everything normal arrows do.";
    }
}
