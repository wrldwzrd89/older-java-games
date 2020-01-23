/*  DungeonDiver3: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver3.support.creatures;

import java.io.IOException;

import com.puttysoftware.dungeondiver3.support.creatures.castes.Caste;
import com.puttysoftware.dungeondiver3.support.creatures.castes.CasteManager;
import com.puttysoftware.dungeondiver3.support.creatures.faiths.Faith;
import com.puttysoftware.dungeondiver3.support.creatures.genders.Gender;
import com.puttysoftware.dungeondiver3.support.creatures.monsters.Element;
import com.puttysoftware.dungeondiver3.support.creatures.personalities.Personality;
import com.puttysoftware.dungeondiver3.support.creatures.personalities.PersonalityConstants;
import com.puttysoftware.dungeondiver3.support.creatures.races.Race;
import com.puttysoftware.dungeondiver3.support.creatures.races.RaceConstants;
import com.puttysoftware.dungeondiver3.support.items.ItemInventory;
import com.puttysoftware.dungeondiver3.support.resourcemanagers.ImageManager;
import com.puttysoftware.dungeondiver3.support.spells.SpellBook;
import com.puttysoftware.images.BufferedImageIcon;
import com.puttysoftware.page.Page;
import com.puttysoftware.xio.XDataReader;
import com.puttysoftware.xio.XDataWriter;

public class PartyMember extends Creature {
    // Fields
    private final Race race;
    private final Caste caste;
    private final Faith faith;
    private final Personality personality;
    private final Gender gender;
    private final Element element;
    private final String name;
    private static final int START_GOLD = 0;
    private static final double BASE_COEFF = 10.0;

    // Constructors
    PartyMember(Race r, Caste c, Faith f, Personality p, Gender g, String n) {
        super(true);
        this.name = n;
        this.race = r;
        this.caste = c;
        this.faith = f;
        this.personality = p;
        this.gender = g;
        this.element = new Element(f);
        this.setLevel(1);
        this.setStrength(StatConstants.GAIN_STRENGTH
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_STRENGTH_PER_LEVEL));
        this.setBlock(StatConstants.GAIN_BLOCK
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_BLOCK_PER_LEVEL));
        this.setVitality(StatConstants.GAIN_VITALITY
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_VITALITY_PER_LEVEL));
        this.setIntelligence(StatConstants.GAIN_INTELLIGENCE
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_INTELLIGENCE_PER_LEVEL));
        this.setAgility(StatConstants.GAIN_AGILITY
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_AGILITY_PER_LEVEL));
        this.setLuck(StatConstants.GAIN_LUCK
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_LUCK_PER_LEVEL));
        this.setAttacksPerRound(1);
        this.setSpellsPerRound(1);
        this.healAndRegenerateFully();
        this.setGold(PartyMember.START_GOLD);
        this.setExperience(0L);
        Page nextLevelEquation = new Page(3, 1, 0, true);
        double value = BASE_COEFF
                * this.personality
                        .getAttribute(PersonalityConstants.PERSONALITY_ATTRIBUTE_LEVEL_UP_SPEED);
        nextLevelEquation.setCoefficient(1, value);
        nextLevelEquation.setCoefficient(2, value);
        nextLevelEquation.setCoefficient(3, value);
        this.setToNextLevel(nextLevelEquation);
        this.setSpellBook(CasteManager.getSpellBookByID(this.caste.getCasteID()));
    }

    // Methods
    @Override
    public BufferedImageIcon getInitialImage() {
        return ImageManager.getPlayerImage(this);
    }

    public String getXPString() {
        return this.getExperience() + "/" + this.getToNextLevelValue();
    }

    // Transformers
    @Override
    protected void levelUpHook() {
        this.offsetStrength(StatConstants.GAIN_STRENGTH
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_STRENGTH_PER_LEVEL));
        this.offsetBlock(StatConstants.GAIN_BLOCK
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_BLOCK_PER_LEVEL));
        this.offsetVitality(StatConstants.GAIN_VITALITY
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_VITALITY_PER_LEVEL));
        this.offsetIntelligence(StatConstants.GAIN_INTELLIGENCE
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_INTELLIGENCE_PER_LEVEL));
        this.offsetAgility(StatConstants.GAIN_AGILITY
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_AGILITY_PER_LEVEL));
        this.offsetLuck(StatConstants.GAIN_LUCK
                + this.race
                        .getAttribute(RaceConstants.RACE_ATTRIBUTE_LUCK_PER_LEVEL));
        this.healAndRegenerateFully();
    }

    private void loadPartyMember(final int newLevel, final int chp,
            final int cmp, final int newGold, final int newLoad,
            final long newExperience, final int bookID, final boolean[] known,
            final long[] pres) {
        this.setLevel(newLevel);
        this.setCurrentHP(chp);
        this.setCurrentMP(cmp);
        this.setGold(newGold);
        this.setLoad(newLoad);
        this.setExperience(newExperience);
        SpellBook book = CasteManager.getSpellBookByID(bookID);
        for (int x = 0; x < known.length; x++) {
            if (known[x]) {
                book.learnSpellByID(x);
            }
        }
        for (int x = 0; x < pres.length; x++) {
            this.setPrestigeValue(x, pres[x]);
        }
        this.setSpellBook(book);
    }

    @Override
    public int getActionsPerRound() {
        return (int) (super.getActionsPerRound() * this.getPersonality()
                .getAttribute(
                        PersonalityConstants.PERSONALITY_ATTRIBUTE_MOVE_MOD));
    }

    @Override
    public int getCapacity() {
        return Math
                .max(StatConstants.MIN_CAPACITY,
                        (int) (super.getCapacity() * this
                                .getPersonality()
                                .getAttribute(
                                        PersonalityConstants.PERSONALITY_ATTRIBUTE_CAPACITY_MOD)));
    }

    @Override
    public void offsetGold(int value) {
        int fixedValue = value;
        if (value > 0) {
            fixedValue = (int) (fixedValue * this
                    .getPersonality()
                    .getAttribute(
                            PersonalityConstants.PERSONALITY_ATTRIBUTE_WEALTH_MOD));
        }
        super.offsetGold(fixedValue);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Race getRace() {
        return this.race;
    }

    public Caste getCaste() {
        return this.caste;
    }

    @Override
    public Faith getFaith() {
        return this.faith;
    }

    private Personality getPersonality() {
        return this.personality;
    }

    private Gender getGender() {
        return this.gender;
    }

    public Element getElement() {
        return this.element;
    }

    public static PartyMember read(XDataReader worldFile) throws IOException {
        int strength = worldFile.readInt();
        int block = worldFile.readInt();
        int agility = worldFile.readInt();
        int vitality = worldFile.readInt();
        int intelligence = worldFile.readInt();
        int luck = worldFile.readInt();
        int lvl = worldFile.readInt();
        int cHP = worldFile.readInt();
        int cMP = worldFile.readInt();
        int gld = worldFile.readInt();
        int apr = worldFile.readInt();
        int spr = worldFile.readInt();
        int load = worldFile.readInt();
        long exp = worldFile.readLong();
        int r = worldFile.readInt();
        int c = worldFile.readInt();
        int f = worldFile.readInt();
        int p = worldFile.readInt();
        int g = worldFile.readInt();
        int max = worldFile.readInt();
        boolean[] known = new boolean[max];
        for (int x = 0; x < max; x++) {
            known[x] = worldFile.readBoolean();
        }
        long[] prestige = new long[PrestigeConstants.MAX_PRESTIGE];
        for (int x = 0; x < PrestigeConstants.MAX_PRESTIGE; x++) {
            prestige[x] = worldFile.readLong();
        }
        String n = worldFile.readString();
        PartyMember pm = PartyManager.getNewPCInstance(r, c, f, p, g, n);
        pm.setStrength(strength);
        pm.setBlock(block);
        pm.setAgility(agility);
        pm.setVitality(vitality);
        pm.setIntelligence(intelligence);
        pm.setLuck(luck);
        pm.setAttacksPerRound(apr);
        pm.setSpellsPerRound(spr);
        pm.setItems(ItemInventory.readItemInventory(worldFile));
        pm.loadPartyMember(lvl, cHP, cMP, gld, load, exp, c, known, prestige);
        return pm;
    }

    static PartyMember readV1(XDataReader worldFile) throws IOException {
        int strength = worldFile.readInt();
        int block = worldFile.readInt();
        int agility = worldFile.readInt();
        int vitality = worldFile.readInt();
        int intelligence = worldFile.readInt();
        int luck = worldFile.readInt();
        int lvl = worldFile.readInt();
        int cHP = worldFile.readInt();
        int cMP = worldFile.readInt();
        int gld = worldFile.readInt();
        int apr = worldFile.readInt();
        int spr = worldFile.readInt();
        int load = worldFile.readInt();
        long exp = worldFile.readLong();
        int r = worldFile.readInt();
        int c = worldFile.readInt();
        int f = worldFile.readInt();
        int p = worldFile.readInt();
        int g = worldFile.readInt();
        int max = worldFile.readInt();
        boolean[] known = new boolean[max];
        for (int x = 0; x < max; x++) {
            known[x] = worldFile.readBoolean();
        }
        long[] prestige = new long[PrestigeConstants.MAX_PRESTIGE];
        for (int x = 0; x < PrestigeConstants.MAX_PRESTIGE; x++) {
            prestige[x] = worldFile.readLong();
        }
        String n = worldFile.readString();
        PartyMember pm = PartyManager.getNewPCInstance(r, c, f, p, g, n);
        pm.setStrength(strength);
        pm.setBlock(block);
        pm.setAgility(agility);
        pm.setVitality(vitality);
        pm.setIntelligence(intelligence);
        pm.setLuck(luck);
        pm.setAttacksPerRound(apr);
        pm.setSpellsPerRound(spr);
        pm.setItems(ItemInventory.readItemInventoryV1(worldFile));
        pm.loadPartyMember(lvl, cHP, cMP, gld, load, exp, c, known, prestige);
        return pm;
    }

    public void write(XDataWriter worldFile) throws IOException {
        worldFile.writeInt(this.getStrength());
        worldFile.writeInt(this.getBlock());
        worldFile.writeInt(this.getAgility());
        worldFile.writeInt(this.getVitality());
        worldFile.writeInt(this.getIntelligence());
        worldFile.writeInt(this.getLuck());
        worldFile.writeInt(this.getLevel());
        worldFile.writeInt(this.getCurrentHP());
        worldFile.writeInt(this.getCurrentMP());
        worldFile.writeInt(this.getGold());
        worldFile.writeInt(this.getAttacksPerRound());
        worldFile.writeInt(this.getSpellsPerRound());
        worldFile.writeInt(this.getLoad());
        worldFile.writeLong(this.getExperience());
        worldFile.writeInt(this.getRace().getRaceID());
        worldFile.writeInt(this.getCaste().getCasteID());
        worldFile.writeInt(this.getFaith().getFaithID());
        worldFile.writeInt(this.getPersonality().getPersonalityID());
        worldFile.writeInt(this.getGender().getGenderID());
        int max = this.getSpellBook().getSpellCount();
        worldFile.writeInt(max);
        for (int x = 0; x < max; x++) {
            worldFile.writeBoolean(this.getSpellBook().isSpellKnown(x));
        }
        for (int x = 0; x < PrestigeConstants.MAX_PRESTIGE; x++) {
            worldFile.writeLong(this.getPrestigeValue(x));
        }
        worldFile.writeString(this.getName());
        this.getItems().writeItemInventory(worldFile);
    }
}
