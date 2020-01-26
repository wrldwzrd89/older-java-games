/*  LaserTank: An Arena-Solving Game
 Copyright (C) 2008-2013 Eric Ahnell

 Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.lasertank.arena.objects;

import com.puttysoftware.lasertank.arena.abstractobjects.AbstractTriggerButton;
import com.puttysoftware.lasertank.utilities.MaterialConstants;

public class UniversalTriggerButton extends AbstractTriggerButton {
    // Constructors
    public UniversalTriggerButton() {
        super(new UniversalTriggerButtonDoor(), true);
        this.setMaterial(MaterialConstants.MATERIAL_DEFAULT);
    }

    @Override
    public final int getStringBaseID() {
        return 114;
    }
}