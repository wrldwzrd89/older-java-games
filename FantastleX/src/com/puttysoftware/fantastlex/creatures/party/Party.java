/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.creatures.party;

import java.io.IOException;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.fantastlex.FantastleX;
import com.puttysoftware.fantastlex.battle.VictorySpoilsDescription;
import com.puttysoftware.fantastlex.creatures.AbstractCreature;
import com.puttysoftware.fantastlex.creatures.characterfiles.CharacterLoader;
import com.puttysoftware.fantastlex.maze.objects.BattleCharacter;
import com.puttysoftware.fantastlex.resourcemanagers.SoundConstants;
import com.puttysoftware.fantastlex.resourcemanagers.SoundManager;
import com.puttysoftware.xio.XDataReader;
import com.puttysoftware.xio.XDataWriter;

public class Party {
    // Fields
    private PartyMember[] members;
    private BattleCharacter[] battlers;
    private int leaderID;
    private int activePCs;

    // Constructors
    private Party() {
        this.members = null;
        this.battlers = null;
        this.leaderID = 0;
        this.activePCs = 0;
    }

    Party(final PartyMember[] newMembers) {
        this.members = newMembers;
        this.battlers = null;
        this.leaderID = 0;
        this.activePCs = 0;
    }

    Party(final int maxMembers) {
        this.members = new PartyMember[maxMembers];
        this.leaderID = 0;
        this.activePCs = 0;
    }

    // Methods
    private void generateBattleCharacters() {
        final BattleCharacter[] tempChars = new BattleCharacter[this.members.length];
        int nnc = 0;
        for (int x = 0; x < tempChars.length; x++) {
            if (this.members[x] != null) {
                tempChars[x] = new BattleCharacter(this.members[x]);
                nnc++;
            }
        }
        final BattleCharacter[] chars = new BattleCharacter[nnc];
        nnc = 0;
        for (final BattleCharacter tempChar : tempChars) {
            if (tempChar != null) {
                chars[nnc] = tempChar;
                nnc++;
            }
        }
        this.battlers = chars;
    }

    public BattleCharacter[] getBattleCharacters() {
        if (this.battlers == null) {
            this.generateBattleCharacters();
        }
        return this.battlers;
    }

    public void checkPartyLevelUp() {
        for (final BattleCharacter battler : this.battlers) {
            // Level Up Check
            if (battler.getTemplate().checkLevelUp()) {
                battler.getTemplate().levelUp();
                SoundManager.playSound(SoundConstants.SOUND_LEVEL_UP);
                CommonDialogs.showTitledDialog(
                        battler.getTemplate().getName() + " reached level "
                                + battler.getTemplate().getLevel() + "!",
                        "Level Up");
                FantastleX.getApplication().getGameManager().addToScore(
                        Math.max(1, (10 * battler.getTemplate().getLevel() - 1)
                                / this.activePCs));
            }
        }
    }

    public int getPartyMeanLevel() {
        int sum = 0;
        for (final BattleCharacter battler : this.battlers) {
            sum += battler.getTemplate().getLevel();
        }
        return sum / this.battlers.length;
    }

    public void stripPartyEffects() {
        for (final PartyMember member : this.members) {
            if (member != null) {
                // Strip All Effects
                member.stripAllEffects();
            }
        }
    }

    public void distributeVictorySpoils(final VictorySpoilsDescription vsd) {
        final int divMod = this.battlers.length;
        final int monLen = vsd.getMonsterCount();
        for (int x = 0; x < divMod; x++) {
            // Distribute Victory Spoils
            for (int y = 0; y < monLen; y++) {
                this.battlers[x].getTemplate();
                this.battlers[x].getTemplate().offsetExperience(
                        AbstractCreature.getAdjustedExperience(
                                vsd.getExpPerMonster(y)) / divMod);
            }
            this.battlers[x].getTemplate()
                    .offsetGold(vsd.getGoldWon() / divMod);
        }
    }

    public long getPartyMaxToNextLevel() {
        long largest = Integer.MIN_VALUE;
        for (final PartyMember member : this.members) {
            if (member != null) {
                if (member.getToNextLevelValue() > largest) {
                    largest = member.getToNextLevelValue();
                }
            }
        }
        return largest;
    }

    void reviveParty() {
        for (final PartyMember member : this.members) {
            if (member != null) {
                member.healAndRegenerateFully();
            }
        }
    }

    void writebackMembers() {
        for (final PartyMember member : this.members) {
            if (member != null) {
                // Writeback Party Member
                CharacterLoader.saveCharacter(member);
            }
        }
    }

    public PartyMember getLeader() {
        return this.members[this.leaderID];
    }

    public int getActivePCCount() {
        return this.activePCs;
    }

    public void setLeader(final String name) {
        final int pos = this.findMember(name, 0, this.members.length);
        if (pos != -1) {
            this.leaderID = pos;
        }
    }

    public boolean isAlive() {
        boolean result = false;
        for (final PartyMember member : this.members) {
            if (member != null) {
                result = result || member.isAlive();
            }
        }
        return result;
    }

    public void fireStepActions() {
        for (final PartyMember member : this.members) {
            if (member != null) {
                member.getItems().fireStepActions(member);
            }
        }
    }

    private String[] buildNameList() {
        final String[] tempNames = new String[this.members.length];
        int nnc = 0;
        for (int x = 0; x < tempNames.length; x++) {
            if (this.members[x] != null) {
                tempNames[x] = this.members[x].getName();
                nnc++;
            }
        }
        final String[] names = new String[nnc];
        nnc = 0;
        for (final String tempName : tempNames) {
            if (tempName != null) {
                names[nnc] = tempName;
                nnc++;
            }
        }
        return names;
    }

    PartyMember pickOnePartyMemberCreate() {
        final String[] pickNames = this.buildNameList();
        final String response = CommonDialogs.showInputDialog(
                "Pick 1 Party Member", "Create Party", pickNames, pickNames[0]);
        if (response != null) {
            final int loc = this.findMember(response, 0, this.members.length);
            if (loc != -1) {
                return this.members[loc];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public PartyMember pickOnePartyMember() {
        final String[] pickNames = this.buildNameList();
        return this.pickPartyMemberInternal(pickNames, 1, 1);
    }

    private PartyMember pickPartyMemberInternal(final String[] pickNames,
            final int current, final int number) {
        final String response = CommonDialogs
                .showInputDialog(
                        "Pick " + number + " Party Member(s) - " + current
                                + " of " + number,
                        "FantastleX", pickNames, pickNames[0]);
        if (response != null) {
            final int loc = this.findMember(response, 0, this.members.length);
            if (loc != -1) {
                return this.members[loc];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    boolean addPartyMember(final PartyMember member) {
        final int pos = this.activePCs;
        if (pos < this.members.length) {
            this.members[pos] = member;
            this.activePCs++;
            this.generateBattleCharacters();
            return true;
        }
        return false;
    }

    private int findMember(final String name, final int start,
            final int limit) {
        for (int x = start; x < limit; x++) {
            if (this.members[x] != null) {
                if (this.members[x].getName().equals(name)) {
                    return x;
                }
            }
        }
        return -1;
    }

    static Party read(final XDataReader worldFile) throws IOException {
        final int memCount = worldFile.readInt();
        final int lid = worldFile.readInt();
        final int apc = worldFile.readInt();
        final Party pty = new Party();
        pty.leaderID = lid;
        pty.activePCs = apc;
        pty.members = new PartyMember[memCount];
        for (int z = 0; z < memCount; z++) {
            final boolean present = worldFile.readBoolean();
            if (present) {
                pty.members[z] = PartyMember.read(worldFile);
            }
        }
        return pty;
    }

    void write(final XDataWriter worldFile) throws IOException {
        worldFile.writeInt(this.members.length);
        worldFile.writeInt(this.leaderID);
        worldFile.writeInt(this.activePCs);
        for (final PartyMember member : this.members) {
            if (member == null) {
                worldFile.writeBoolean(false);
            } else {
                worldFile.writeBoolean(true);
                member.write(worldFile);
            }
        }
    }
}
