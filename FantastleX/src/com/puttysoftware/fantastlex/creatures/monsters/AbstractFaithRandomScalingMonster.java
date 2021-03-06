/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.creatures.monsters;

import com.puttysoftware.fantastlex.creatures.StatConstants;
import com.puttysoftware.fantastlex.creatures.party.PartyManager;
import com.puttysoftware.randomrange.RandomLongRange;
import com.puttysoftware.randomrange.RandomRange;

abstract class AbstractFaithRandomScalingMonster
        extends AbstractFaithRandomMonster {
    // Constructors
    AbstractFaithRandomScalingMonster() {
        super();
    }

    @Override
    public void loadMonster() {
        super.loadMonster();
        final int newLevel = PartyManager.getParty().getPartyMeanLevel();
        this.setLevel(newLevel);
        this.setVitality(this.getInitialVitality());
        this.setCurrentHP(this.getMaximumHP());
        this.setIntelligence(this.getInitialIntelligence());
        this.setCurrentMP(this.getMaximumMP());
        this.setStrength(this.getInitialStrength());
        this.setBlock(this.getInitialBlock());
        this.setAgility(this.getInitialAgility());
        this.setLuck(this.getInitialLuck());
        this.setGold(this.getInitialGold());
        this.setExperience((long) (this.getInitialExperience()
                * this.adjustForLevelDifference()));
        this.setAttacksPerRound(1);
        this.setSpellsPerRound(1);
        this.image = this.getInitialImage();
    }

    @Override
    public boolean scales() {
        return true;
    }

    private int getInitialStrength() {
        final RandomRange r = new RandomRange(1,
                Math.max(this.getLevel() * StatConstants.GAIN_STRENGTH, 1));
        return r.generate();
    }

    private int getInitialBlock() {
        final RandomRange r = new RandomRange(0,
                this.getLevel() * StatConstants.GAIN_BLOCK);
        return r.generate();
    }

    private long getInitialExperience() {
        long minvar, maxvar;
        minvar = (long) (this.getLevel()
                * AbstractMonster.MINIMUM_EXPERIENCE_RANDOM_VARIANCE);
        maxvar = (long) (this.getLevel()
                * AbstractMonster.MAXIMUM_EXPERIENCE_RANDOM_VARIANCE);
        final RandomLongRange r = new RandomLongRange(minvar, maxvar);
        final long expbase = PartyManager.getParty().getPartyMaxToNextLevel();
        final long factor = this.getBattlesToNextLevel();
        return expbase / factor + r.generate();
    }

    private int getToughness() {
        return this.getStrength() + this.getBlock() + this.getAgility()
                + this.getVitality() + this.getIntelligence() + this.getLuck();
    }

    private int getInitialGold() {
        final int min = 0;
        final int max = this.getToughness()
                * AbstractMonster.GOLD_TOUGHNESS_MULTIPLIER;
        final RandomRange r = new RandomRange(min, max);
        return r.generate();
    }

    private int getInitialAgility() {
        final RandomRange r = new RandomRange(1,
                Math.max(this.getLevel() * StatConstants.GAIN_AGILITY, 1));
        return r.generate();
    }

    private int getInitialVitality() {
        final RandomRange r = new RandomRange(1,
                Math.max(this.getLevel() * StatConstants.GAIN_VITALITY, 1));
        return r.generate();
    }

    private int getInitialIntelligence() {
        final RandomRange r = new RandomRange(0,
                this.getLevel() * StatConstants.GAIN_INTELLIGENCE);
        return r.generate();
    }

    private int getInitialLuck() {
        final RandomRange r = new RandomRange(0,
                this.getLevel() * StatConstants.GAIN_LUCK);
        return r.generate();
    }
}
