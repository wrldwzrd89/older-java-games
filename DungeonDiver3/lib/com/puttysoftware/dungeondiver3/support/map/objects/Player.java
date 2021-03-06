/*  DungeonDiver3: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver3.support.map.objects;

import java.awt.Color;

import com.puttysoftware.dungeondiver3.support.creatures.PartyManager;
import com.puttysoftware.dungeondiver3.support.map.Map;
import com.puttysoftware.dungeondiver3.support.map.generic.GenericCharacter;
import com.puttysoftware.dungeondiver3.support.map.generic.TemplateTransform;

public class Player extends GenericCharacter {
    // Constructors
    public Player() {
        super();
    }

    @Override
    public String getName() {
        return "Player";
    }

    @Override
    public String getPluralName() {
        return "Players";
    }

    @Override
    public String gameRenderHook(final int x, final int y, final int z,
            final Map map) {
        return PartyManager.getParty().getLeader().getRace().getName();
    }

    @Override
    public TemplateTransform getGameTemplateTransform() {
        final Color faithColor = PartyManager.getParty().getLeader().getFaith()
                .getColor();
        final double tr = faithColor.getRed() / 256.0;
        final double tg = faithColor.getGreen() / 256.0;
        final double tb = faithColor.getBlue() / 256.0;
        return new TemplateTransform(tr, tg, tb);
    }

    @Override
    public String getDescription() {
        return "This is you - the Player.";
    }

    @Override
    public boolean hideFromHelp() {
        return true;
    }

    // Random Generation Rules
    @Override
    public boolean isRequired() {
        return true;
    }

    @Override
    public int getMinimumRequiredQuantity(final Map map) {
        return 1;
    }

    @Override
    public int getMaximumRequiredQuantity(final Map map) {
        return 1;
    }
}