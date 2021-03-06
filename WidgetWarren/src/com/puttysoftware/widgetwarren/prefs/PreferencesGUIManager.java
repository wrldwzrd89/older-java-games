/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.prefs;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import com.puttysoftware.widgetwarren.Application;
import com.puttysoftware.widgetwarren.WidgetWarren;
import com.puttysoftware.widgetwarren.resourcemanagers.LogoManager;

class PreferencesGUIManager {
    // Fields
    private JFrame prefFrame;
    private JTabbedPane prefTabPane;
    private Container mainPrefPane, buttonPane, editorPane, miscPane, soundPane;
    private JButton prefsOK, prefsCancel;
    JCheckBox[] sounds;
    private JCheckBox music;
    private JCheckBox moveOneAtATime;
    private JComboBox<String> editorFillChoices;
    private String[] editorFillChoiceArray;
    private EventHandler handler;

    // Constructors
    public PreferencesGUIManager() {
        this.sounds = new JCheckBox[PreferencesManager.SOUNDS_LENGTH];
        this.setUpGUI();
        this.setDefaultPrefs();
    }

    // Methods
    private static int getGridLength() {
        return 4;
    }

    public JFrame getPrefFrame() {
        if (this.prefFrame != null && this.prefFrame.isVisible()) {
            return this.prefFrame;
        } else {
            return null;
        }
    }

    public void showPrefs() {
        final Application app = WidgetWarren.getApplication();
        app.setInPrefs(true);
        if (System.getProperty("os.name").startsWith("Mac OS X")) {
            this.prefFrame.setJMenuBar(app.getMenuManager().getMainMenuBar());
        }
        app.getMenuManager().setPrefMenus();
        this.prefFrame.setVisible(true);
        final int formerMode = app.getFormerMode();
        if (formerMode == Application.STATUS_GUI) {
            app.getGUIManager().hideGUITemporarily();
        } else if (formerMode == Application.STATUS_GAME) {
            app.getGameManager().hideOutput();
        } else if (formerMode == Application.STATUS_EDITOR) {
            app.getEditor().hideOutput();
        }
    }

    public void hidePrefs() {
        final Application app = WidgetWarren.getApplication();
        app.setInPrefs(false);
        this.prefFrame.setVisible(false);
        PreferencesManager.writePrefs();
        final int formerMode = app.getFormerMode();
        if (formerMode == Application.STATUS_GUI) {
            app.getGUIManager().showGUI();
        } else if (formerMode == Application.STATUS_GAME) {
            app.getGameManager().showOutput();
        } else if (formerMode == Application.STATUS_EDITOR) {
            app.getEditor().showOutput();
        }
    }

    private void loadPrefs() {
        this.editorFillChoices
                .setSelectedItem(PreferencesManager.getEditorDefaultFill());
        for (int x = 0; x < PreferencesManager.SOUNDS_LENGTH; x++) {
            this.sounds[x].setSelected(PreferencesManager.getSoundEnabled(x));
        }
        this.music.setSelected(PreferencesManager.getMusicEnabled());
        this.moveOneAtATime.setSelected(PreferencesManager.oneMove());
    }

    public void setPrefs() {
        PreferencesManager.setEditorDefaultFill(
                (String) this.editorFillChoices.getSelectedItem());
        for (int x = 0; x < PreferencesManager.SOUNDS_LENGTH; x++) {
            PreferencesManager.setSoundEnabled(x, this.sounds[x].isSelected());
        }
        PreferencesManager.setMusicEnabled(this.music.isSelected());
        PreferencesManager.setOneMove(this.moveOneAtATime.isSelected());
        this.hidePrefs();
    }

    public void setDefaultPrefs() {
        PreferencesManager.readPrefs();
        this.loadPrefs();
    }

    private void setUpGUI() {
        this.handler = new EventHandler();
        this.prefFrame = new JFrame("Preferences");
        this.prefTabPane = new JTabbedPane();
        this.mainPrefPane = new Container();
        this.editorPane = new Container();
        this.soundPane = new Container();
        this.miscPane = new Container();
        this.prefTabPane.setOpaque(true);
        this.buttonPane = new Container();
        this.prefsOK = new JButton("OK");
        this.prefsOK.setDefaultCapable(true);
        this.prefFrame.getRootPane().setDefaultButton(this.prefsOK);
        this.prefsCancel = new JButton("Cancel");
        this.prefsCancel.setDefaultCapable(false);
        this.editorFillChoiceArray = new String[] { "Grass", "Dirt", "Sand",
                "Snow", "Tile", "Tundra" };
        this.editorFillChoices = new JComboBox<>(this.editorFillChoiceArray);
        this.sounds[PreferencesManager.SOUNDS_ALL] = new JCheckBox(
                "Enable ALL sounds", true);
        this.sounds[PreferencesManager.SOUNDS_UI] = new JCheckBox(
                "Enable user interface sounds", true);
        this.sounds[PreferencesManager.SOUNDS_GAME] = new JCheckBox(
                "Enable game sounds", true);
        this.music = new JCheckBox("Enable music", true);
        this.moveOneAtATime = new JCheckBox("One Move at a Time", true);
        this.prefFrame.setContentPane(this.mainPrefPane);
        this.prefFrame
                .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.prefFrame.addWindowListener(this.handler);
        this.mainPrefPane.setLayout(new BorderLayout());
        this.prefFrame.setResizable(false);
        this.editorPane.setLayout(
                new GridLayout(PreferencesGUIManager.getGridLength(), 1));
        this.editorPane.add(new JLabel("Default fill for new mazes:"));
        this.editorPane.add(this.editorFillChoices);
        this.soundPane.setLayout(
                new GridLayout(PreferencesGUIManager.getGridLength(), 1));
        for (int x = 0; x < PreferencesManager.SOUNDS_LENGTH; x++) {
            this.soundPane.add(this.sounds[x]);
        }
        this.soundPane.add(this.music);
        this.miscPane.setLayout(
                new GridLayout(PreferencesGUIManager.getGridLength(), 1));
        this.miscPane.add(this.moveOneAtATime);
        this.buttonPane.setLayout(new FlowLayout());
        this.buttonPane.add(this.prefsOK);
        this.buttonPane.add(this.prefsCancel);
        this.prefTabPane.addTab("Editor", null, this.editorPane, "Editor");
        this.prefTabPane.addTab("Sounds", null, this.soundPane, "Sounds");
        this.prefTabPane.addTab("Misc.", null, this.miscPane, "Misc.");
        this.mainPrefPane.add(this.prefTabPane, BorderLayout.CENTER);
        this.mainPrefPane.add(this.buttonPane, BorderLayout.SOUTH);
        this.sounds[PreferencesManager.SOUNDS_ALL]
                .addItemListener(this.handler);
        this.prefsOK.addActionListener(this.handler);
        this.prefsCancel.addActionListener(this.handler);
        final Image iconlogo = LogoManager.getLogo();
        this.prefFrame.setIconImage(iconlogo);
        this.prefFrame.pack();
    }

    private class EventHandler
            implements ActionListener, ItemListener, WindowListener {
        public EventHandler() {
            // Do nothing
        }

        // Handle buttons
        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                final PreferencesGUIManager pm = PreferencesGUIManager.this;
                final String cmd = e.getActionCommand();
                if (cmd.equals("OK")) {
                    pm.setPrefs();
                } else if (cmd.equals("Cancel")) {
                    pm.hidePrefs();
                }
            } catch (final Exception ex) {
                WidgetWarren.getErrorLogger().logError(ex);
            }
        }

        @Override
        public void itemStateChanged(final ItemEvent e) {
            try {
                final PreferencesGUIManager pm = PreferencesGUIManager.this;
                final Object o = e.getItem();
                if (o.getClass().equals(JCheckBox.class)) {
                    final JCheckBox check = (JCheckBox) o;
                    if (check
                            .equals(pm.sounds[PreferencesManager.SOUNDS_ALL])) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            for (int x = 1; x < PreferencesManager.SOUNDS_LENGTH; x++) {
                                pm.sounds[x].setEnabled(true);
                            }
                        } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                            for (int x = 1; x < PreferencesManager.SOUNDS_LENGTH; x++) {
                                pm.sounds[x].setEnabled(false);
                            }
                        }
                    }
                }
            } catch (final Exception ex) {
                WidgetWarren.getErrorLogger().logError(ex);
            }
        }

        @Override
        public void windowOpened(final WindowEvent e) {
            // Do nothing
        }

        @Override
        public void windowClosing(final WindowEvent e) {
            final PreferencesGUIManager pm = PreferencesGUIManager.this;
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
