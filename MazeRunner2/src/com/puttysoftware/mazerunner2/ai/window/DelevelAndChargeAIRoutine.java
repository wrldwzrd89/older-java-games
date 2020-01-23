package com.puttysoftware.mazerunner2.ai.window;

import com.puttysoftware.mazerunner2.creatures.AbstractCreature;
import com.puttysoftware.mazerunner2.spells.Spell;
import com.puttysoftware.randomrange.RandomRange;

public class DelevelAndChargeAIRoutine extends AbstractWindowAIRoutine {
    // Fields
    private int delevelRounds;
    private int chargeRounds;
    private static final int DELEVEL_CHANCE = 80;
    private static final int CHARGE_CHANCE = 80;

    // Constructors
    public DelevelAndChargeAIRoutine() {
        this.delevelRounds = 0;
        this.chargeRounds = 0;
    }

    @Override
    public int getNextAction(AbstractCreature c) {
        if (this.delevelRounds > 0) {
            this.delevelRounds--;
        }
        if (this.chargeRounds > 0) {
            this.chargeRounds--;
        }
        Spell which = null;
        RandomRange whichAction = new RandomRange(1, 2);
        RandomRange whichSpell = new RandomRange(1, 2);
        int action = whichAction.generate();
        if (action == 1) {
            if (whichSpell.generate() == 1) {
                which = c.getSpellBook().getSpellByID(2);
            } else {
                which = c.getSpellBook().getSpellByID(3);
            }
        } else {
            if (whichSpell.generate() == 1) {
                which = c.getSpellBook().getSpellByID(4);
            } else {
                which = c.getSpellBook().getSpellByID(5);
            }
        }
        int cost = which.getCost();
        int currMP = c.getCurrentMP();
        if (cost <= currMP) {
            RandomRange chance = new RandomRange(1, 100);
            if (action == 1) {
                if (this.delevelRounds == 0) {
                    if (chance.generate() <= DelevelAndChargeAIRoutine.DELEVEL_CHANCE) {
                        this.delevelRounds = which.getEffect()
                                .getInitialRounds();
                        this.spell = which;
                        return AbstractWindowAIRoutine.ACTION_CAST_SPELL;
                    } else {
                        this.spell = null;
                        return AbstractWindowAIRoutine.ACTION_ATTACK;
                    }
                } else {
                    this.spell = null;
                    return AbstractWindowAIRoutine.ACTION_ATTACK;
                }
            } else {
                if (this.chargeRounds == 0) {
                    if (chance.generate() <= DelevelAndChargeAIRoutine.CHARGE_CHANCE) {
                        this.chargeRounds = which.getEffect()
                                .getInitialRounds();
                        this.spell = which;
                        return AbstractWindowAIRoutine.ACTION_CAST_SPELL;
                    } else {
                        this.spell = null;
                        return AbstractWindowAIRoutine.ACTION_ATTACK;
                    }
                } else {
                    this.spell = null;
                    return AbstractWindowAIRoutine.ACTION_ATTACK;
                }
            }
        } else {
            this.spell = null;
            return AbstractWindowAIRoutine.ACTION_ATTACK;
        }
    }
}
