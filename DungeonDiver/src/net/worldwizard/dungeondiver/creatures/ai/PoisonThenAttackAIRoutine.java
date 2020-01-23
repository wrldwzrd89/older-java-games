package net.worldwizard.dungeondiver.creatures.ai;

import net.worldwizard.dungeondiver.creatures.Creature;
import net.worldwizard.dungeondiver.creatures.spells.Spell;
import net.worldwizard.randomnumbers.RandomRange;

public class PoisonThenAttackAIRoutine extends AIRoutine {
    // Fields
    private int poisonRounds;
    private static final int POISON_CHANCE = 75;

    // Constructors
    public PoisonThenAttackAIRoutine() {
        this.poisonRounds = 0;
    }

    @Override
    public int getNextAction(final Creature c) {
        if (this.poisonRounds > 0) {
            this.poisonRounds--;
        }
        final Spell poison = c.getSpellBook().getSpellByID(0);
        final int cost = poison.getCost();
        final int currMP = c.getCurrentMP();
        if (cost <= currMP && this.poisonRounds == 0) {
            final RandomRange chance = new RandomRange(1, 100);
            if ((int) chance.generate() <= PoisonThenAttackAIRoutine.POISON_CHANCE) {
                this.poisonRounds = poison.getEffect().getInitialRounds();
                this.spell = poison;
                return AIRoutine.ACTION_CAST_SPELL;
            } else {
                this.spell = null;
                return AIRoutine.ACTION_ATTACK;
            }
        } else {
            this.spell = null;
            return AIRoutine.ACTION_ATTACK;
        }
    }
}
