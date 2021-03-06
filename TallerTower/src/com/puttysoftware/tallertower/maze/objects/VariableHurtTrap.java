/*  TallerTower: An RPG
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: TallerTower@worldwizard.net
 */
package com.puttysoftware.tallertower.maze.objects;

import com.puttysoftware.randomrange.RandomRange;
import com.puttysoftware.tallertower.TallerTower;
import com.puttysoftware.tallertower.creatures.party.PartyManager;
import com.puttysoftware.tallertower.game.GameLogicManager;
import com.puttysoftware.tallertower.maze.abc.AbstractTrap;
import com.puttysoftware.tallertower.resourcemanagers.ObjectImageConstants;
import com.puttysoftware.tallertower.resourcemanagers.SoundConstants;
import com.puttysoftware.tallertower.resourcemanagers.SoundManager;

public class VariableHurtTrap extends AbstractTrap {
    // Fields
    private static final int MIN_DAMAGE = 1;

    // Constructors
    public VariableHurtTrap() {
        super(ObjectImageConstants.OBJECT_IMAGE_VARIABLE_HURT_TRAP);
    }

    @Override
    public String getName() {
        return "Variable Hurt Trap";
    }

    @Override
    public String getPluralName() {
        return "Variable Hurt Traps";
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX,
            final int dirY) {
        int maxDamage = PartyManager.getParty().getLeader().getMaximumHP() / 5;
        if (maxDamage < VariableHurtTrap.MIN_DAMAGE) {
            maxDamage = VariableHurtTrap.MIN_DAMAGE;
        }
        final RandomRange damageDealt = new RandomRange(
                VariableHurtTrap.MIN_DAMAGE, maxDamage);
        PartyManager.getParty().getLeader().doDamage(damageDealt.generate());
        SoundManager.playSound(SoundConstants.SOUND_BARRIER);
        TallerTower.getApplication().getGameManager();
        GameLogicManager.decay();
    }

    @Override
    public String getDescription() {
        return "Variable Hurt Traps hurt you when stepped on, then disappear.";
    }
}