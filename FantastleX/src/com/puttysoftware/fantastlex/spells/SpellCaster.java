/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.spells;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.fantastlex.FantastleX;
import com.puttysoftware.fantastlex.battle.BattleTarget;
import com.puttysoftware.fantastlex.battle.map.MapBattleDefinitions;
import com.puttysoftware.fantastlex.creatures.AbstractCreature;
import com.puttysoftware.fantastlex.creatures.party.PartyManager;
import com.puttysoftware.fantastlex.effects.Effect;
import com.puttysoftware.fantastlex.resourcemanagers.SoundConstants;
import com.puttysoftware.fantastlex.resourcemanagers.SoundManager;

public class SpellCaster {
    // Fields
    private static boolean NO_SPELLS_FLAG = false;
    private static final String[] powers = new String[] { "1", "2", "3", "4",
            "5", "6", "7", "8", "9", "X" };

    // Private Constructor
    private SpellCaster() {
        // Do nothing
    }

    public static boolean selectAndCastSpell(final AbstractCreature caster) {
        boolean result = false;
        SpellCaster.NO_SPELLS_FLAG = false;
        final Spell s = SpellCaster.selectSpell(caster);
        if (s != null) {
            result = SpellCaster.castSpell(s, caster);
            if (!result && !SpellCaster.NO_SPELLS_FLAG) {
                CommonDialogs.showErrorDialog(
                        "You try to cast a spell, but realize you don't have enough MP!",
                        "Select Spell");
            }
        }
        return result;
    }

    public static boolean castSpell(final Spell cast,
            final AbstractCreature caster) {
        if (cast != null) {
            final int casterMP = caster.getCurrentMP();
            final int cost = cast.getCost();
            if (casterMP >= cost) {
                // Cast Spell
                caster.drain(cost);
                final Effect b = cast.getEffect();
                // Play spell's associated sound effect, if it has one
                final int snd = cast.getSound();
                SoundManager.playSound(snd);
                b.resetEffect();
                final AbstractCreature target = SpellCaster.resolveTarget(cast,
                        caster.getTeamID());
                if (target.isEffectActive(b)) {
                    target.extendEffect(b, b.getInitialRounds());
                } else {
                    b.restoreEffect();
                    target.applyEffect(b);
                }
                return true;
            } else {
                // Not enough MP
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean selectAndCastSpell(final AbstractCreature caster,
            final int teamID, final boolean aiEnabled,
            final MapBattleDefinitions battle) {
        boolean result = false;
        SpellCaster.NO_SPELLS_FLAG = false;
        final Spell s = SpellCaster.selectSpell(caster);
        if (s != null) {
            final int power = SpellCaster.selectSpellPower();
            if (power != -1) {
                result = SpellCaster.castSpellWithPower(s, power, caster,
                        teamID, aiEnabled, battle);
                if (!result && !SpellCaster.NO_SPELLS_FLAG) {
                    CommonDialogs.showErrorDialog(
                            "You try to cast a spell, but realize you don't have enough MP!",
                            "Select Spell");
                }
            }
        }
        return result;
    }

    private static Spell selectSpell(final AbstractCreature caster) {
        final SpellBook book = caster.getSpellBook();
        if (book != null) {
            final String[] names = book.getAllSpellNames();
            final String[] displayNames = book.getAllSpellNamesWithCosts();
            if (names != null && displayNames != null) {
                // Play casting spell sound
                SoundManager.playSound(SoundConstants.SOUND_SPELL);
                String dialogResult = null;
                dialogResult = CommonDialogs.showInputDialog(
                        "Select a Spell to Cast", "Select Spell", displayNames,
                        displayNames[0]);
                if (dialogResult != null) {
                    int index;
                    for (index = 0; index < displayNames.length; index++) {
                        if (dialogResult.equals(displayNames[index])) {
                            break;
                        }
                    }
                    return book.getSpellByName(names[index]);
                } else {
                    return null;
                }
            } else {
                SpellCaster.NO_SPELLS_FLAG = true;
                CommonDialogs.showErrorDialog(
                        "You try to cast a spell, but realize you don't know any!",
                        "Select Spell");
                return null;
            }
        } else {
            SpellCaster.NO_SPELLS_FLAG = true;
            CommonDialogs.showErrorDialog(
                    "You try to cast a spell, but realize you don't know any!",
                    "Select Spell");
            return null;
        }
    }

    private static int selectSpellPower() {
        String dialogResult = null;
        dialogResult = CommonDialogs.showInputDialog(
                "Select a Spell Power Level", "Select Power",
                SpellCaster.powers, SpellCaster.powers[0]);
        if (dialogResult != null) {
            int index;
            for (index = 0; index < SpellCaster.powers.length; index++) {
                if (dialogResult.equals(SpellCaster.powers[index])) {
                    break;
                }
            }
            return index + 1;
        } else {
            return -1;
        }
    }

    public static boolean castSpell(final Spell cast,
            final AbstractCreature caster, final int teamID,
            final boolean aiEnabled, final MapBattleDefinitions battle) {
        if (cast != null) {
            final int casterMP = caster.getCurrentMP();
            final int cost = cast.getCost();
            if (casterMP >= cost) {
                // Cast Spell
                caster.drain(cost);
                final Effect eff = cast.getEffect();
                eff.setSource(caster);
                // Play spell's associated sound effect, if it has one
                SoundManager.playSound(cast.getSound());
                eff.resetEffect();
                final AbstractCreature[] targets = SpellCaster
                        .resolveTarget(cast, caster, teamID, aiEnabled, battle);
                for (final AbstractCreature target : targets) {
                    if (target.isEffectActive(eff)) {
                        target.extendEffect(eff, eff.getInitialRounds());
                    } else {
                        eff.restoreEffect();
                        target.applyEffect(eff);
                    }
                }
                return true;
            } else {
                // Not enough MP
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean castSpellWithPower(final Spell cast, final int power,
            final AbstractCreature caster, final int teamID,
            final boolean aiEnabled, final MapBattleDefinitions battle) {
        if (cast != null) {
            final int casterMP = caster.getCurrentMP();
            final int cost = cast.getCostForPower(power);
            if (casterMP >= cost) {
                // Cast Spell
                caster.drain(cost);
                final Effect eff = cast.getEffect();
                eff.setSource(caster);
                // Play spell's associated sound effect, if it has one
                SoundManager.playSound(cast.getSound());
                eff.resetEffect();
                eff.modifyEffectForPower(power);
                final AbstractCreature[] targets = SpellCaster
                        .resolveTarget(cast, caster, teamID, aiEnabled, battle);
                for (final AbstractCreature target : targets) {
                    if (target.isEffectActive(eff)) {
                        target.extendEffect(eff, eff.getInitialRounds());
                    } else {
                        eff.restoreEffect();
                        target.applyEffect(eff);
                    }
                }
                return true;
            } else {
                // Not enough MP
                return false;
            }
        } else {
            return false;
        }
    }

    private static AbstractCreature resolveTarget(final Spell cast,
            final int teamID) {
        final BattleTarget target = cast.getTarget();
        switch (target) {
        case ONE_ALLY:
        case ALL_ALLIES:
        case SELF:
            if (teamID == AbstractCreature.TEAM_PARTY) {
                return PartyManager.getParty().getLeader();
            } else {
                return FantastleX.getApplication().getBattle().getEnemy();
            }
        case ONE_ENEMY:
        case ALL_ENEMIES:
            if (teamID == AbstractCreature.TEAM_PARTY) {
                return FantastleX.getApplication().getBattle().getEnemy();
            } else {
                return PartyManager.getParty().getLeader();
            }
        default:
            return null;
        }
    }

    private static AbstractCreature[] resolveTarget(final Spell cast,
            final AbstractCreature caster, final int teamID,
            final boolean aiEnabled, final MapBattleDefinitions battle) {
        final BattleTarget target = cast.getTarget();
        final boolean hasAI = caster.hasAI();
        final boolean useAI = hasAI && aiEnabled;
        switch (target) {
        case SELF:
            // Self
            return new AbstractCreature[] { battle.getSelfTarget() };
        case ONE_ALLY:
            // One Ally
            if (useAI) {
                return new AbstractCreature[] {
                        battle.pickOneFriendOfTeamRandomly(teamID) };
            } else {
                SoundManager.playSound(SoundConstants.SOUND_ON_WHO);
                return new AbstractCreature[] {
                        battle.pickOneFriendOfTeam(teamID) };
            }
        case ONE_ENEMY:
            // One Enemy
            if (useAI) {
                return new AbstractCreature[] {
                        battle.pickOneEnemyOfTeamRandomly(teamID) };
            } else {
                SoundManager.playSound(SoundConstants.SOUND_ON_WHO);
                return new AbstractCreature[] {
                        battle.pickOneEnemyOfTeam(teamID) };
            }
        case ALL_ALLIES:
            // All Allies
            return battle.getAllFriendsOfTeam(teamID);
        case ALL_ENEMIES:
            // All Enemies
            return battle.getAllEnemiesOfTeam(teamID);
        default:
            return null;
        }
    }
}
