/*  DungeonDiver3: An RPG
Copyright (C) 2011-2012 Eric Ahnell


Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver3.battle;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.puttysoftware.dungeondiver3.support.map.objects.BattleCharacter;
import com.puttysoftware.dungeondiver3.support.resourcemanagers.ImageManager;
import com.puttysoftware.images.BufferedImageIcon;

class BattleStats {
    // Fields
    private Container statsPane;
    private JLabel nameLabel;
    private JLabel teamLabel;
    private JLabel hpLabel;
    private JLabel mpLabel;
    private JLabel attLabel;
    private JLabel defLabel;
    private JLabel apLabel;
    private JLabel attLeftLabel;
    private JLabel splLabel;

    // Constructors
    public BattleStats() {
        this.setUpGUI();
        this.updateIcons();
    }

    // Methods
    public Container getStatsPane() {
        return this.statsPane;
    }

    public void updateStats(BattleCharacter bc) {
        this.nameLabel.setText(bc.getName());
        this.teamLabel.setText(bc.getTeamString());
        this.hpLabel.setText(bc.getTemplate().getHPString());
        this.mpLabel.setText(bc.getTemplate().getMPString());
        this.attLabel.setText(bc.getTemplate().getAttackString());
        this.defLabel.setText(bc.getTemplate().getDefenseString());
        this.apLabel.setText(bc.getAPString());
        this.attLeftLabel.setText(bc.getAttackString());
        this.splLabel.setText(bc.getSpellString());
    }

    private void setUpGUI() {
        this.statsPane = new Container();
        this.statsPane.setLayout(new GridLayout(9, 1));
        this.nameLabel = new JLabel("", null, SwingConstants.LEFT);
        this.teamLabel = new JLabel("", null, SwingConstants.LEFT);
        this.hpLabel = new JLabel("", null, SwingConstants.LEFT);
        this.mpLabel = new JLabel("", null, SwingConstants.LEFT);
        this.attLabel = new JLabel("", null, SwingConstants.LEFT);
        this.defLabel = new JLabel("", null, SwingConstants.LEFT);
        this.apLabel = new JLabel("", null, SwingConstants.LEFT);
        this.attLeftLabel = new JLabel("", null, SwingConstants.LEFT);
        this.splLabel = new JLabel("", null, SwingConstants.LEFT);
        this.statsPane.add(this.nameLabel);
        this.statsPane.add(this.teamLabel);
        this.statsPane.add(this.hpLabel);
        this.statsPane.add(this.mpLabel);
        this.statsPane.add(this.attLabel);
        this.statsPane.add(this.defLabel);
        this.statsPane.add(this.apLabel);
        this.statsPane.add(this.attLeftLabel);
        this.statsPane.add(this.splLabel);
    }

    private void updateIcons() {
        BufferedImageIcon nameImage = ImageManager.getStatImage("name");
        this.nameLabel.setIcon(nameImage);
        BufferedImageIcon teamImage = ImageManager.getStatImage("team");
        this.teamLabel.setIcon(teamImage);
        BufferedImageIcon hpImage = ImageManager.getStatImage("health");
        this.hpLabel.setIcon(hpImage);
        BufferedImageIcon mpImage = ImageManager.getStatImage("magic");
        this.mpLabel.setIcon(mpImage);
        BufferedImageIcon attImage = ImageManager.getStatImage("attack");
        this.attLabel.setIcon(attImage);
        BufferedImageIcon defImage = ImageManager.getStatImage("defense");
        this.defLabel.setIcon(defImage);
        BufferedImageIcon apImage = ImageManager.getStatImage("actions");
        this.apLabel.setIcon(apImage);
        this.attLeftLabel.setIcon(attImage);
        BufferedImageIcon spImage = ImageManager.getStatImage("spells");
        this.splLabel.setIcon(spImage);
    }
}