/*  FantastleReboot: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlereboot.ai.map;

import java.awt.Point;

import com.puttysoftware.fantastlereboot.ai.AIContext;
import com.puttysoftware.fantastlereboot.ai.AIRoutine;
import com.puttysoftware.fantastlereboot.battle.Battle;
import com.puttysoftware.fantastlereboot.creatures.Creature;
import com.puttysoftware.randomrange.RandomRange;

class VeryEasyMapAIRoutine extends AbstractMapAIRoutine {
    // Fields
    private final RandomRange randMove;
    private int failedMoveAttempts;
    private int[] roundsRemaining;
    private static final int CAST_SPELL_CHANCE = 5;
    private static final int STEAL_CHANCE = 1;
    private static final int DRAIN_CHANCE = 5;
    private static final int HEAL_THRESHOLD = 5;
    private static final int MAX_VISION = 2;
    private static final int FLEE_CHANCE = 40;

    // Constructor
    public VeryEasyMapAIRoutine() {
        super();
        this.randMove = new RandomRange(-1, 1);
        this.failedMoveAttempts = 0;
    }

    @Override
    public int getNextAction(final Creature c, final AIContext ac) {
        if (this.roundsRemaining == null) {
            this.roundsRemaining = new int[ac.getCharacter().getCreature()
                    .getSpellBook().getSpellCount()];
        }
        if (this.spellCheck(ac)) {
            // Cast a spell
            return AIRoutine.ACTION_CAST_SPELL;
        } else {
            Point there = ac.isEnemyNearby();
            if (there != null) {
                if (CommonMapAIRoutines.check(ac,
                        VeryEasyMapAIRoutine.STEAL_CHANCE, Battle.AP_STEAL)) {
                    // Steal
                    return AIRoutine.ACTION_STEAL;
                } else if (CommonMapAIRoutines.check(ac,
                        VeryEasyMapAIRoutine.DRAIN_CHANCE, Battle.AP_DRAIN)) {
                    // Drain MP
                    return AIRoutine.ACTION_DRAIN;
                } else {
                    // Something hostile is nearby, so attack it
                    if (ac.getCharacter().canAct(Battle.AP_MOVE)) {
                        this.moveX = there.x;
                        this.moveY = there.y;
                        return AIRoutine.ACTION_MOVE;
                    } else {
                        this.failedMoveAttempts = 0;
                        return AIRoutine.ACTION_END_TURN;
                    }
                }
            } else {
                if (CommonMapAIRoutines.check(ac,
                        VeryEasyMapAIRoutine.FLEE_CHANCE, Battle.AP_MOVE)) {
                    // Flee
                    final Point awayDir = ac.runAway();
                    if (awayDir == null) {
                        // Wander randomly
                        this.moveX = this.randMove.generate();
                        this.moveY = this.randMove.generate();
                        // Don't attack self
                        while (this.moveX == 0 && this.moveY == 0) {
                            this.moveX = this.randMove.generate();
                            this.moveY = this.randMove.generate();
                        }
                    } else {
                        this.moveX = awayDir.x;
                        this.moveY = awayDir.y;
                    }
                    return AIRoutine.ACTION_MOVE;
                } else {
                    // Look further
                    for (int x = CommonMapAIRoutines.MIN_VISION
                            + 1; x <= VeryEasyMapAIRoutine.MAX_VISION; x++) {
                        there = ac.isEnemyNearby(x, x);
                        if (there != null) {
                            // Found something hostile, move towards it
                            if (this.lastResult == false) {
                                this.failedMoveAttempts++;
                                if (this.failedMoveAttempts >= CommonMapAIRoutines.STUCK_THRESHOLD) {
                                    // We're stuck!
                                    this.failedMoveAttempts = 0;
                                    return AIRoutine.ACTION_END_TURN;
                                }
                                // Last move failed, try to move around object
                                final RandomRange randTurn = new RandomRange(0,
                                        1);
                                final int rt = randTurn.generate();
                                if (rt == 0) {
                                    there = CommonMapAIRoutines.turnRight45(
                                            this.moveX, this.moveY);
                                } else {
                                    there = CommonMapAIRoutines
                                            .turnLeft45(this.moveX, this.moveY);
                                }
                                this.moveX = there.x;
                                this.moveY = there.y;
                            } else {
                                this.moveX = (int) Math.signum(there.x);
                                this.moveY = (int) Math.signum(there.y);
                            }
                            break;
                        }
                    }
                }
                if (ac.getCharacter().canAct(Battle.AP_MOVE)) {
                    if (there == null) {
                        // Wander randomly
                        this.moveX = this.randMove.generate();
                        this.moveY = this.randMove.generate();
                        // Don't attack self
                        while (this.moveX == 0 && this.moveY == 0) {
                            this.moveX = this.randMove.generate();
                            this.moveY = this.randMove.generate();
                        }
                    }
                    return AIRoutine.ACTION_MOVE;
                } else {
                    this.failedMoveAttempts = 0;
                    return AIRoutine.ACTION_END_TURN;
                }
            }
        }
    }

    private boolean spellCheck(final AIContext ac) {
        final RandomRange random = new RandomRange(1, 100);
        final int chance = random.generate();
        if (chance <= VeryEasyMapAIRoutine.CAST_SPELL_CHANCE) {
            final int maxIndex = CommonMapAIRoutines.getMaxCastIndex(ac);
            if (maxIndex > -1) {
                if (ac.getCharacter().canAct(Battle.AP_CAST_SPELL)) {
                    // Select a random spell to cast
                    final RandomRange randomSpell = new RandomRange(0,
                            maxIndex);
                    final int randomSpellID = randomSpell.generate();
                    if (randomSpellID == CommonMapAIRoutines.SPELL_INDEX_HEAL) {
                        // Healing spell was selected - is healing needed?
                        if (ac.getCharacter().getCreature()
                                .getCurrentHP() > ac.getCharacter()
                                        .getCreature().getMaximumHP()
                                        * VeryEasyMapAIRoutine.HEAL_THRESHOLD
                                        / 100) {
                            // Do not need healing
                            return false;
                        }
                    }
                    if (this.roundsRemaining[randomSpellID] == 0) {
                        this.spell = ac.getCharacter().getCreature()
                                .getSpellBook().getSpellByID(randomSpellID);
                        this.roundsRemaining[randomSpellID] = this.spell
                                .getEffect().getInitialRounds();
                        return true;
                    } else {
                        // Spell selected already active
                        return false;
                    }
                } else {
                    // Can't cast any more spells
                    return false;
                }
            } else {
                // Not enough MP to cast anything
                return false;
            }
        } else {
            // Not casting a spell
            return false;
        }
    }

    @Override
    public void newRoundHook() {
        if (this.roundsRemaining != null) {
            // Decrement effect counters
            for (int z = 0; z < this.roundsRemaining.length; z++) {
                if (this.roundsRemaining[z] > 0) {
                    this.roundsRemaining[z]--;
                }
            }
        }
    }
}
