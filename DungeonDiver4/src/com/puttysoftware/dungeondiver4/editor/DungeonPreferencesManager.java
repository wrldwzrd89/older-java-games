/*  DungeonDiver4: A Dungeon-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver4.editor;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.puttysoftware.dungeondiver4.DungeonDiver4;
import com.puttysoftware.dungeondiver4.dungeon.Dungeon;

public class DungeonPreferencesManager {
    // Fields
    private JFrame prefFrame;
    private JComboBox<String> startLevelChoices;
    private JTextField dungeonTitle;
    private JTextArea dungeonStartMessage;
    private JTextArea dungeonEndMessage;

    // Constructors
    public DungeonPreferencesManager() {
        this.setUpGUI();
    }

    // Methods
    public void showPrefs() {
        this.loadPrefs();
        DungeonDiver4.getApplication().getEditor().disableOutput();
        this.prefFrame.setVisible(true);
    }

    public void hidePrefs() {
        this.prefFrame.setVisible(false);
        DungeonDiver4.getApplication().getEditor().enableOutput();
        DungeonDiver4.getApplication().getDungeonManager().setDirty(true);
        DungeonDiver4.getApplication().getEditor().redrawEditor();
    }

    void setPrefs() {
        Dungeon m = DungeonDiver4.getApplication().getDungeonManager()
                .getDungeon();
        m.setStartLevel(this.startLevelChoices.getSelectedIndex());
        m.setDungeonTitle(this.dungeonTitle.getText());
        m.setDungeonStartMessage(this.dungeonStartMessage.getText());
        m.setDungeonEndMessage(this.dungeonEndMessage.getText());
    }

    private void loadPrefs() {
        Dungeon m = DungeonDiver4.getApplication().getDungeonManager()
                .getDungeon();
        String[] startLevelChoiceArray = new String[m.getLevels()];
        for (int x = 0; x < m.getLevels(); x++) {
            startLevelChoiceArray[x] = Integer.toString(x + 1);
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(
                startLevelChoiceArray);
        this.startLevelChoices.setModel(model);
        this.startLevelChoices.setSelectedIndex(m.getStartLevel());
        this.dungeonTitle.setText(m.getDungeonTitle());
        this.dungeonStartMessage.setText(m.getDungeonStartMessage());
        this.dungeonEndMessage.setText(m.getDungeonEndMessage());
    }

    private void setUpGUI() {
        EventHandler handler = new EventHandler();
        this.prefFrame = new JFrame("Dungeon Preferences");
        final Image iconlogo = DungeonDiver4.getApplication().getIconLogo();
        this.prefFrame.setIconImage(iconlogo);
        Container mainPrefPane = new Container();
        Container contentPane = new Container();
        Container buttonPane = new Container();
        JButton prefsOK = new JButton("OK");
        prefsOK.setDefaultCapable(true);
        this.prefFrame.getRootPane().setDefaultButton(prefsOK);
        JButton prefsCancel = new JButton("Cancel");
        prefsCancel.setDefaultCapable(false);
        this.startLevelChoices = new JComboBox<>();
        this.dungeonTitle = new JTextField("");
        this.dungeonStartMessage = new JTextArea("");
        this.dungeonEndMessage = new JTextArea("");
        this.prefFrame.setContentPane(mainPrefPane);
        this.prefFrame
                .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.prefFrame.addWindowListener(handler);
        mainPrefPane.setLayout(new BorderLayout());
        this.prefFrame.setResizable(false);
        contentPane.setLayout(new GridLayout(8, 1));
        contentPane.add(new JLabel("Starting Level"));
        contentPane.add(this.startLevelChoices);
        contentPane.add(new JLabel("Dungeon Title"));
        contentPane.add(this.dungeonTitle);
        contentPane.add(new JLabel("Dungeon Start Message"));
        contentPane.add(this.dungeonStartMessage);
        contentPane.add(new JLabel("Dungeon End Message"));
        contentPane.add(this.dungeonEndMessage);
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
                DungeonPreferencesManager mpm = DungeonPreferencesManager.this;
                final String cmd = e.getActionCommand();
                if (cmd.equals("OK")) {
                    mpm.setPrefs();
                    mpm.hidePrefs();
                } else if (cmd.equals("Cancel")) {
                    mpm.hidePrefs();
                }
            } catch (Exception ex) {
                DungeonDiver4.getErrorLogger().logError(ex);
            }
        }

        // handle window
        @Override
        public void windowOpened(WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowClosing(WindowEvent e) {
            DungeonPreferencesManager pm = DungeonPreferencesManager.this;
            pm.hidePrefs();
        }

        @Override
        public void windowClosed(WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowIconified(WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowActivated(WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            // Do nothing
        }
    }
}
