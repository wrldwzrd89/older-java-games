/*  TallerTower: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.tallertower.creatures.monsters;

import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.randomrange.RandomRange;
import com.puttysoftware.tallertower.ai.map.MapAIRoutinePicker;
import com.puttysoftware.tallertower.ai.window.AbstractWindowAIRoutine;
import com.puttysoftware.tallertower.ai.window.VeryHardWindowAIRoutine;
import com.puttysoftware.tallertower.creatures.AbstractCreature;
import com.puttysoftware.tallertower.creatures.faiths.Faith;
import com.puttysoftware.tallertower.creatures.faiths.FaithManager;
import com.puttysoftware.tallertower.creatures.party.PartyManager;
import com.puttysoftware.tallertower.prefs.PreferencesManager;
import com.puttysoftware.tallertower.resourcemanagers.BossImageManager;
import com.puttysoftware.tallertower.spells.SpellBook;

public class BossMonster extends AbstractCreature {
    // Fields
    private static final int MINIMUM_STAT_VALUE_VERY_EASY = 100;
    private static final int MINIMUM_STAT_VALUE_EASY = 200;
    private static final int MINIMUM_STAT_VALUE_NORMAL = 400;
    private static final int MINIMUM_STAT_VALUE_HARD = 600;
    private static final int MINIMUM_STAT_VALUE_VERY_HARD = 900;
    private static final int STAT_MULT_VERY_EASY = 3;
    private static final int STAT_MULT_EASY = 4;
    private static final int STAT_MULT_NORMAL = 5;
    private static final int STAT_MULT_HARD = 8;
    private static final int STAT_MULT_VERY_HARD = 12;

    // Constructors
    BossMonster() {
        super(true, 1);
        this.setWindowAI(BossMonster.getInitialWindowAI());
        this.setMapAI(MapAIRoutinePicker.getNextRoutine());
        final SpellBook spells = new SystemMonsterSpellBook();
        spells.learnAllSpells();
        this.setSpellBook(spells);
        this.loadCreature();
    }

    // Methods
    @Override
    public String getFightingWhatString() {
        return "You're fighting The Boss";
    }

    @Override
    public String getName() {
        return "The Boss";
    }

    @Override
    public Faith getFaith() {
        return FaithManager.getFaith(0);
    }

    @Override
    public boolean checkLevelUp() {
        return false;
    }

    @Override
    protected void levelUpHook() {
        // Do nothing
    }

    @Override
    protected BufferedImageIcon getInitialImage() {
        return BossImageManager.getBossImage();
    }

    @Override
    public int getSpeed() {
        final int difficulty = PreferencesManager.getGameDifficulty();
        final int base = this.getBaseSpeed();
        if (difficulty == PreferencesManager.DIFFICULTY_VERY_EASY) {
            return (int) (base * AbstractCreature.SPEED_ADJUST_SLOWEST);
        } else if (difficulty == PreferencesManager.DIFFICULTY_EASY) {
            return (int) (base * AbstractCreature.SPEED_ADJUST_SLOW);
        } else if (difficulty == PreferencesManager.DIFFICULTY_NORMAL) {
            return (int) (base * AbstractCreature.SPEED_ADJUST_NORMAL);
        } else if (difficulty == PreferencesManager.DIFFICULTY_HARD) {
            return (int) (base * AbstractCreature.SPEED_ADJUST_FAST);
        } else if (difficulty == PreferencesManager.DIFFICULTY_VERY_HARD) {
            return (int) (base * AbstractCreature.SPEED_ADJUST_FASTEST);
        } else {
            return (int) (base * AbstractCreature.SPEED_ADJUST_NORMAL);
        }
    }

    // Helper Methods
    @Override
    public void loadCreature() {
        final int newLevel = PartyManager.getParty().getTowerLevel() + 6;
        this.setLevel(newLevel);
        this.setVitality(this.getInitialVitality());
        this.setCurrentHP(this.getMaximumHP());
        this.setIntelligence(this.getInitialIntelligence());
        this.setCurrentMP(this.getMaximumMP());
        this.setStrength(this.getInitialStrength());
        this.setBlock(this.getInitialBlock());
        this.setAgility(this.getInitialAgility());
        this.setLuck(this.getInitialLuck());
        this.setGold(0);
        this.setExperience(0);
        this.setAttacksPerRound(1);
        this.setSpellsPerRound(1);
        this.image = this.getInitialImage();
    }

    private int getInitialStrength() {
        final int min = BossMonster.getMinimumStatForDifficulty();
        final RandomRange r = new RandomRange(min,
                Math.max(
                        this.getLevel()
                                * BossMonster.getStatMultiplierForDifficulty(),
                        min));
        return r.generate();
    }

    private int getInitialBlock() {
        final int min = BossMonster.getMinimumStatForDifficulty();
        final RandomRange r = new RandomRange(min,
                Math.max(
                        this.getLevel()
                                * BossMonster.getStatMultiplierForDifficulty(),
                        min));
        return r.generate();
    }

    private int getInitialAgility() {
        final int min = BossMonster.getMinimumStatForDifficulty();
        final RandomRange r = new RandomRange(min,
                Math.max(
                        this.getLevel()
                                * BossMonster.getStatMultiplierForDifficulty(),
                        min));
        return r.generate();
    }

    private int getInitialVitality() {
        final int min = BossMonster.getMinimumStatForDifficulty();
        final RandomRange r = new RandomRange(min,
                Math.max(
                        this.getLevel()
                                * BossMonster.getStatMultiplierForDifficulty(),
                        min));
        return r.generate();
    }

    private int getInitialIntelligence() {
        final int min = BossMonster.getMinimumStatForDifficulty();
        final RandomRange r = new RandomRange(min,
                Math.max(
                        this.getLevel()
                                * BossMonster.getStatMultiplierForDifficulty(),
                        min));
        return r.generate();
    }

    private int getInitialLuck() {
        final int min = BossMonster.getMinimumStatForDifficulty();
        final RandomRange r = new RandomRange(min,
                Math.max(
                        this.getLevel()
                                * BossMonster.getStatMultiplierForDifficulty(),
                        min));
        return r.generate();
    }

    private static AbstractWindowAIRoutine getInitialWindowAI() {
        return new VeryHardWindowAIRoutine();
    }

    private static int getStatMultiplierForDifficulty() {
        final int difficulty = PreferencesManager.getGameDifficulty();
        if (difficulty == PreferencesManager.DIFFICULTY_VERY_EASY) {
            return BossMonster.STAT_MULT_VERY_EASY;
        } else if (difficulty == PreferencesManager.DIFFICULTY_EASY) {
            return BossMonster.STAT_MULT_EASY;
        } else if (difficulty == PreferencesManager.DIFFICULTY_NORMAL) {
            return BossMonster.STAT_MULT_NORMAL;
        } else if (difficulty == PreferencesManager.DIFFICULTY_HARD) {
            return BossMonster.STAT_MULT_HARD;
        } else if (difficulty == PreferencesManager.DIFFICULTY_VERY_HARD) {
            return BossMonster.STAT_MULT_VERY_HARD;
        } else {
            return BossMonster.STAT_MULT_NORMAL;
        }
    }

    private static int getMinimumStatForDifficulty() {
        final int difficulty = PreferencesManager.getGameDifficulty();
        if (difficulty == PreferencesManager.DIFFICULTY_VERY_EASY) {
            return BossMonster.MINIMUM_STAT_VALUE_VERY_EASY;
        } else if (difficulty == PreferencesManager.DIFFICULTY_EASY) {
            return BossMonster.MINIMUM_STAT_VALUE_EASY;
        } else if (difficulty == PreferencesManager.DIFFICULTY_NORMAL) {
            return BossMonster.MINIMUM_STAT_VALUE_NORMAL;
        } else if (difficulty == PreferencesManager.DIFFICULTY_HARD) {
            return BossMonster.MINIMUM_STAT_VALUE_HARD;
        } else if (difficulty == PreferencesManager.DIFFICULTY_VERY_HARD) {
            return BossMonster.MINIMUM_STAT_VALUE_VERY_HARD;
        } else {
            return BossMonster.MINIMUM_STAT_VALUE_NORMAL;
        }
    }
}
