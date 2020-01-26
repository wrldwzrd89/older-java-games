/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.puttysoftware.loopchute.LoopChute;
import com.puttysoftware.loopchute.maze.Maze;
import com.puttysoftware.loopchute.maze.MazeConstants;

public class LevelPreferencesManager {
    // Fields
    private JFrame prefFrame;
    private JCheckBox horizontalWrap;
    private JCheckBox verticalWrap;
    private JCheckBox thirdDimensionalWrap;
    private JTextField levelTitle;
    private JTextArea levelStartMessage;
    private JTextArea levelEndMessage;
    private JCheckBox useOffset;
    private JTextField nextLevel;
    private JCheckBox useAlternateOffset;
    private JTextField alternateNextLevel;
    private JTextField exploreRadius;
    private JCheckBox vmExplore;
    private JCheckBox vmLOS;

    // Constructors
    public LevelPreferencesManager() {
        this.setUpGUI();
    }

    // Methods
    public void showPrefs() {
        this.loadPrefs();
        LoopChute.getApplication().getEditor().disableOutput();
        this.prefFrame.setVisible(true);
    }

    public void hidePrefs() {
        this.prefFrame.setVisible(false);
        LoopChute.getApplication().getEditor().enableOutput();
        LoopChute.getApplication().getMazeManager().setDirty(true);
        LoopChute.getApplication().getEditor().redrawEditor();
    }

    void setPrefs() {
        final Maze m = LoopChute.getApplication().getMazeManager().getMaze();
        if (this.horizontalWrap.isSelected()) {
            m.enableHorizontalWraparound();
        } else {
            m.disableHorizontalWraparound();
        }
        if (this.verticalWrap.isSelected()) {
            m.enableVerticalWraparound();
        } else {
            m.disableVerticalWraparound();
        }
        if (this.thirdDimensionalWrap.isSelected()) {
            m.enable3rdDimensionWraparound();
        } else {
            m.disable3rdDimensionWraparound();
        }
        m.setLevelTitle(this.levelTitle.getText());
        m.setLevelStartMessage(this.levelStartMessage.getText());
        m.setLevelEndMessage(this.levelEndMessage.getText());
        m.setUseOffset(this.useOffset.isSelected());
        if (this.useOffset.isSelected()) {
            m.setNextLevelOffset(Integer.parseInt(this.nextLevel.getText()));
        } else {
            m.setNextLevel(Integer.parseInt(this.nextLevel.getText()) - 1);
        }
        m.setUseAlternateOffset(this.useAlternateOffset.isSelected());
        if (this.useAlternateOffset.isSelected()) {
            m.setAlternateNextLevelOffset(Integer
                    .parseInt(this.alternateNextLevel.getText()));
        } else {
            m.setAlternateNextLevel(Integer.parseInt(this.alternateNextLevel
                    .getText()) - 1);
        }
        int newER = m.getExploreRadius();
        try {
            newER = Integer.parseInt(this.exploreRadius.getText());
            if (newER < 1 || newER > 6) {
                throw new NumberFormatException();
            }
        } catch (final NumberFormatException nfe) {
            newER = m.getExploreRadius();
        }
        m.setExploreRadius(newER);
        int newVM = MazeConstants.VISION_MODE_NONE;
        if (this.vmExplore.isSelected()) {
            newVM = newVM | MazeConstants.VISION_MODE_EXPLORE;
        }
        if (this.vmLOS.isSelected()) {
            newVM = newVM | MazeConstants.VISION_MODE_LOS;
        }
        m.setVisionMode(newVM);
    }

    private void loadPrefs() {
        final Maze m = LoopChute.getApplication().getMazeManager().getMaze();
        this.horizontalWrap.setSelected(m.isHorizontalWraparoundEnabled());
        this.verticalWrap.setSelected(m.isVerticalWraparoundEnabled());
        this.thirdDimensionalWrap.setSelected(m
                .is3rdDimensionWraparoundEnabled());
        this.levelTitle.setText(m.getLevelTitle());
        this.levelStartMessage.setText(m.getLevelStartMessage());
        this.levelEndMessage.setText(m.getLevelEndMessage());
        this.useOffset.setSelected(m.useOffset());
        if (m.useOffset()) {
            this.nextLevel.setText(Integer.toString(m.getNextLevel()));
        } else {
            this.nextLevel.setText(Integer.toString(m.getNextLevel() + 1));
        }
        this.useAlternateOffset.setSelected(m.useAlternateOffset());
        if (m.useAlternateOffset()) {
            this.alternateNextLevel.setText(Integer.toString(m
                    .getAlternateNextLevel()));
        } else {
            this.alternateNextLevel.setText(Integer.toString(m
                    .getAlternateNextLevel() + 1));
        }
        this.exploreRadius.setText(Integer.toString(m.getExploreRadius()));
        final int vm = m.getVisionMode();
        if ((vm | MazeConstants.VISION_MODE_EXPLORE) == vm) {
            this.vmExplore.setSelected(true);
        } else {
            this.vmExplore.setSelected(false);
        }
        if ((vm | MazeConstants.VISION_MODE_LOS) == vm) {
            this.vmLOS.setSelected(true);
        } else {
            this.vmLOS.setSelected(false);
        }
    }

    private void setUpGUI() {
        final EventHandler handler = new EventHandler();
        this.prefFrame = new JFrame("Level Preferences");
        final Image iconlogo = LoopChute.getApplication().getIconLogo();
        this.prefFrame.setIconImage(iconlogo);
        final Container mainPrefPane = new Container();
        final Container contentPane = new Container();
        final Container buttonPane = new Container();
        final JButton prefsOK = new JButton("OK");
        prefsOK.setDefaultCapable(true);
        this.prefFrame.getRootPane().setDefaultButton(prefsOK);
        final JButton prefsCancel = new JButton("Cancel");
        prefsCancel.setDefaultCapable(false);
        this.horizontalWrap = new JCheckBox("Enable horizontal wraparound",
                false);
        this.verticalWrap = new JCheckBox("Enable vertical wraparound", false);
        this.thirdDimensionalWrap = new JCheckBox(
                "Enable 3rd dimension wraparound", false);
        this.levelTitle = new JTextField("");
        this.levelStartMessage = new JTextArea("");
        this.levelEndMessage = new JTextArea("");
        this.useOffset = new JCheckBox("Next Level Is Relative");
        this.nextLevel = new JTextField("");
        this.useAlternateOffset = new JCheckBox(
                "Alternate Next Level Is Relative");
        this.alternateNextLevel = new JTextField("");
        this.exploreRadius = new JTextField("");
        this.vmExplore = new JCheckBox("Enable exploring vision mode");
        this.vmLOS = new JCheckBox("Enable line-of-sight vision mode");
        this.prefFrame.setContentPane(mainPrefPane);
        this.prefFrame
                .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.prefFrame.addWindowListener(handler);
        mainPrefPane.setLayout(new BorderLayout());
        this.prefFrame.setResizable(false);
        contentPane.setLayout(new GridLayout(19, 1));
        contentPane.add(this.horizontalWrap);
        contentPane.add(this.verticalWrap);
        contentPane.add(this.thirdDimensionalWrap);
        contentPane.add(new JLabel("Level Title"));
        contentPane.add(this.levelTitle);
        contentPane.add(new JLabel("Level Start Message"));
        contentPane.add(this.levelStartMessage);
        contentPane.add(new JLabel("Level End Message"));
        contentPane.add(this.levelEndMessage);
        contentPane.add(this.useOffset);
        contentPane.add(new JLabel("Next Level Number"));
        contentPane.add(this.nextLevel);
        contentPane.add(this.useAlternateOffset);
        contentPane.add(new JLabel("Alternate Next Level Number"));
        contentPane.add(this.alternateNextLevel);
        contentPane.add(new JLabel("Exploring Mode Vision Radius (1-6)"));
        contentPane.add(this.exploreRadius);
        contentPane.add(this.vmExplore);
        contentPane.add(this.vmLOS);
        buttonPane.setLayout(new FlowLayout());
        buttonPane.add(prefsOK);
        buttonPane.add(prefsCancel);
        mainPrefPane.add(contentPane, BorderLayout.CENTER);
        mainPrefPane.add(buttonPane, BorderLayout.SOUTH);
        prefsOK.addActionListener(handler);
        prefsCancel.addActionListener(handler);
        this.prefFrame.pack();
    }

    private class EventHandler implements ActionListener, WindowListener {
        EventHandler() {
            // Do nothing
        }

        // Handle buttons
        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                final LevelPreferencesManager lpm = LevelPreferencesManager.this;
                final String cmd = e.getActionCommand();
                if (cmd.equals("OK")) {
                    lpm.setPrefs();
                    lpm.hidePrefs();
                } else if (cmd.equals("Cancel")) {
                    lpm.hidePrefs();
                }
            } catch (final Exception ex) {
                LoopChute.getErrorLogger().logError(ex);
            }
        }

        // handle window
        @Override
        public void windowOpened(final WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowClosing(final WindowEvent e) {
            final LevelPreferencesManager pm = LevelPreferencesManager.this;
            pm.hidePrefs();
        }

        @Override
        public void windowClosed(final WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowIconified(final WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowDeiconified(final WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowActivated(final WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowDeactivated(final WindowEvent e) {
            // Do nothing
        }
    }
}