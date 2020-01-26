/*  DungeonDiver4: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver4.game;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.puttysoftware.dungeondiver4.DungeonDiver4;
import com.puttysoftware.dungeondiver4.creatures.party.PartyManager;
import com.puttysoftware.dungeondiver4.creatures.party.PartyMember;
import com.puttysoftware.dungeondiver4.dungeon.Dungeon;
import com.puttysoftware.dungeondiver4.resourcemanagers.StatImageConstants;
import com.puttysoftware.dungeondiver4.resourcemanagers.StatImageManager;
import com.puttysoftware.images.BufferedImageIcon;

class StatGUI {
    // Fields
    private Container statsPane;
    private JLabel hpLabel;
    private JLabel mpLabel;
    private JLabel goldLabel;
    private JLabel attackLabel;
    private JLabel defenseLabel;
    private JLabel xpLabel;
    private JLabel poisonLabel;

    // Constructors
    StatGUI() {
        this.setUpGUI();
    }

    // Methods
    Container getStatsPane() {
        return this.statsPane;
    }

    void updateStats() {
        PartyMember pc = PartyManager.getParty().getLeader();
        Dungeon m = DungeonDiver4.getApplication().getDungeonManager()
                .getDungeon();
        this.hpLabel.setText(pc.getHPString());
        this.mpLabel.setText(pc.getMPString());
        this.goldLabel.setText(Integer.toString(pc.getGold()));
        this.attackLabel.setText(Integer.toString(pc.getAttack()));
        this.defenseLabel.setText(Integer.toString(pc.getDefense()));
        this.xpLabel.setText(pc.getXPString());
        this.poisonLabel.setText(m.getPoisonString());
    }

    private void setUpGUI() {
        this.statsPane = new Container();
        this.statsPane.setLayout(new GridLayout(8, 1));
        this.hpLabel = new JLabel("", null, SwingConstants.LEFT);
        this.mpLabel = new JLabel("", null, SwingConstants.LEFT);
        this.goldLabel = new JLabel("", null, SwingConstants.LEFT);
        this.attackLabel = new JLabel("", null, SwingConstants.LEFT);
        this.defenseLabel = new JLabel("", null, SwingConstants.LEFT);
        this.xpLabel = new JLabel("", null, SwingConstants.LEFT);
        this.poisonLabel = new JLabel("", null, SwingConstants.LEFT);
        this.statsPane.add(this.hpLabel);
        this.statsPane.add(this.mpLabel);
        this.statsPane.add(this.goldLabel);
        this.statsPane.add(this.attackLabel);
        this.statsPane.add(this.defenseLabel);
        this.statsPane.add(this.xpLabel);
        this.statsPane.add(this.poisonLabel);
    }

    void updateImages() {
        BufferedImageIcon hpImage = StatImageManager
                .getImage(StatImageConstants.STAT_IMAGE_HEALTH);
        this.hpLabel.setIcon(hpImage);
        BufferedImageIcon mpImage = StatImageManager
                .getImage(StatImageConstants.STAT_IMAGE_MAGIC);
        this.mpLabel.setIcon(mpImage);
        BufferedImageIcon goldImage = StatImageManager
                .getImage(StatImageConstants.STAT_IMAGE_GOLD);
        this.goldLabel.setIcon(goldImage);
        BufferedImageIcon attackImage = StatImageManager
                .getImage(StatImageConstants.STAT_IMAGE_ATTACK);
        this.attackLabel.setIcon(attackImage);
        BufferedImageIcon defenseImage = StatImageManager
                .getImage(StatImageConstants.STAT_IMAGE_DEFENSE);
        this.defenseLabel.setIcon(defenseImage);
        BufferedImageIcon xpImage = StatImageManager
                .getImage(StatImageConstants.STAT_IMAGE_XP);
        this.xpLabel.setIcon(xpImage);
        BufferedImageIcon poisonImage = StatImageManager
                .getImage(StatImageConstants.STAT_IMAGE_POISON);
        this.poisonLabel.setIcon(poisonImage);
    }
}