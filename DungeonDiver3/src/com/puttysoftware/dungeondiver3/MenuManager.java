/*  DungeonDiver3: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.dungeondiver3.battle.BattleLogic;
import com.puttysoftware.dungeondiver3.game.InventoryViewer;
import com.puttysoftware.dungeondiver3.game.NoteManager;
import com.puttysoftware.dungeondiver3.game.PrestigeViewer;
import com.puttysoftware.dungeondiver3.game.StatisticsViewer;
import com.puttysoftware.dungeondiver3.namer.generic.GenericEditor;
import com.puttysoftware.dungeondiver3.prefs.PreferencesManager;
import com.puttysoftware.dungeondiver3.scenario.ScenarioManager;
import com.puttysoftware.dungeondiver3.support.BoardPrinter;
import com.puttysoftware.dungeondiver3.support.Support;
import com.puttysoftware.dungeondiver3.support.creatures.PartyManager;
import com.puttysoftware.dungeondiver3.support.creatures.PartyMember;
import com.puttysoftware.dungeondiver3.support.creatures.characterfiles.CharacterRegistration;

public class MenuManager {
    // Fields
    private JMenuBar mainMenuBar;
    private JMenuItem fileOpenSavedGame, fileClose, fileSave, fileSaveAs,
            filePrint, filePreferences, fileExit;
    private JMenuItem gamePlay, gameEquipment, gameInventory, gamePickLeader,
            gameRegisterCharacter, gameUnregisterCharacter, gameRemoveCharacter,
            gameEditNote, gameViewPrestige, gameViewScore, gameViewStats;
    private JMenuItem battleItem, battleSpell, battleSteal, battleDrain,
            battleEndTurn;
    private JMenuItem helpAbout, helpObjectHelp, helpWhatsNew;
    private KeyStroke fileOpenSavedGameAccel, fileCloseAccel, fileSaveAccel,
            fileSaveAsAccel, filePrintAccel, filePreferencesAccel;
    private KeyStroke gamePlayMapAccel, gameEditNoteAccel;
    private KeyStroke battleItemAccel, battleSpellAccel, battleStealAccel,
            battleDrainAccel, battleEndTurnAccel;
    private final EventHandler handler;

    // Constructors
    public MenuManager() {
        this.handler = new EventHandler();
        this.createAccelerators();
        this.createMenus();
        this.setInitialMenuState();
    }

    // Methods
    public JMenuBar getMainMenuBar() {
        return this.mainMenuBar;
    }

    public void setEditorMenus() {
        this.fileOpenSavedGame.setEnabled(false);
        this.fileClose.setEnabled(true);
        this.filePreferences.setEnabled(true);
        this.fileSave.setEnabled(false);
        this.fileSaveAs.setEnabled(false);
        this.fileExit.setEnabled(true);
        this.disableBattleMenus();
        this.disableGameMenus();
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            ge.disableEditorCommands();
        }
    }

    public void setBattleMenus() {
        this.fileOpenSavedGame.setEnabled(false);
        this.fileClose.setEnabled(false);
        this.fileSave.setEnabled(false);
        this.fileSaveAs.setEnabled(false);
        this.filePreferences.setEnabled(true);
        this.fileExit.setEnabled(true);
        this.disableGameMenus();
        this.enableBattleMenus();
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            ge.disableEditorCommands();
        }
    }

    public void setGameMenus() {
        this.fileOpenSavedGame.setEnabled(false);
        this.filePreferences.setEnabled(true);
        this.fileExit.setEnabled(true);
        this.enableGameMenus();
        this.disableBattleMenus();
        this.checkFlags();
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            ge.disableEditorCommands();
        }
    }

    public void setPrefMenus() {
        this.fileOpenSavedGame.setEnabled(false);
        this.fileClose.setEnabled(false);
        this.fileSave.setEnabled(false);
        this.fileSaveAs.setEnabled(false);
        this.filePreferences.setEnabled(false);
        this.fileExit.setEnabled(true);
        this.disableGameMenus();
        this.disableBattleMenus();
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            ge.disableEditorCommands();
        }
    }

    void setHelpMenus() {
        this.fileOpenSavedGame.setEnabled(false);
        this.fileClose.setEnabled(false);
        this.fileSave.setEnabled(false);
        this.fileSaveAs.setEnabled(false);
        this.filePreferences.setEnabled(false);
        this.fileExit.setEnabled(true);
        this.disableGameMenus();
        this.disableBattleMenus();
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            ge.disableEditorCommands();
        }
    }

    void setMainMenus() {
        this.fileOpenSavedGame.setEnabled(true);
        this.filePreferences.setEnabled(true);
        this.fileExit.setEnabled(true);
        this.disableGameMenus();
        this.disableBattleMenus();
        this.checkFlags();
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            ge.enableEditorCommands();
        }
    }

    public void enableBattleMenus() {
        this.battleSpell.setEnabled(true);
        this.battleItem.setEnabled(true);
        this.battleSteal.setEnabled(true);
        this.battleDrain.setEnabled(true);
        this.battleEndTurn.setEnabled(true);
    }

    public void disableBattleMenus() {
        this.battleSpell.setEnabled(false);
        this.battleItem.setEnabled(false);
        this.battleSteal.setEnabled(false);
        this.battleDrain.setEnabled(false);
        this.battleEndTurn.setEnabled(false);
    }

    private void enableGameMenus() {
        this.gameEquipment.setEnabled(true);
        this.gameInventory.setEnabled(true);
        this.gamePickLeader.setEnabled(true);
        this.gameEditNote.setEnabled(true);
        this.gameViewPrestige.setEnabled(true);
        this.gameViewScore.setEnabled(true);
        this.gameViewStats.setEnabled(true);
    }

    private void disableGameMenus() {
        this.gameEquipment.setEnabled(false);
        this.gameInventory.setEnabled(false);
        this.gamePickLeader.setEnabled(false);
        this.gameEditNote.setEnabled(false);
        this.gameViewPrestige.setEnabled(false);
        this.gameViewScore.setEnabled(false);
        this.gameViewStats.setEnabled(false);
    }

    public void checkFlags() {
        final Application app = DungeonDiver3.getApplication();
        if (app.getScenarioManager().getDirty()) {
            this.setMenusDirtyOn();
        } else {
            this.setMenusDirtyOff();
        }
        if (app.getMode() == Application.STATUS_GUI) {
            this.fileClose.setEnabled(false);
            this.fileSaveAs.setEnabled(false);
            this.gamePlay.setEnabled(true);
            this.gameRegisterCharacter.setEnabled(true);
            this.gameUnregisterCharacter.setEnabled(true);
            this.gameRemoveCharacter.setEnabled(true);
        } else {
            this.fileClose.setEnabled(true);
            this.fileSaveAs.setEnabled(true);
            this.gamePlay.setEnabled(false);
            this.gameRegisterCharacter.setEnabled(false);
            this.gameUnregisterCharacter.setEnabled(false);
            this.gameRemoveCharacter.setEnabled(false);
        }
    }

    private void setMenusDirtyOn() {
        this.fileSave.setEnabled(true);
    }

    private void setMenusDirtyOff() {
        this.fileSave.setEnabled(false);
    }

    private void createAccelerators() {
        int modKey;
        if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
            modKey = InputEvent.META_DOWN_MASK;
        } else {
            modKey = InputEvent.CTRL_DOWN_MASK;
        }
        this.fileOpenSavedGameAccel = KeyStroke.getKeyStroke(KeyEvent.VK_O,
                modKey);
        this.fileCloseAccel = KeyStroke.getKeyStroke(KeyEvent.VK_W, modKey);
        this.fileSaveAccel = KeyStroke.getKeyStroke(KeyEvent.VK_S, modKey);
        this.fileSaveAsAccel = KeyStroke.getKeyStroke(KeyEvent.VK_S,
                modKey | InputEvent.SHIFT_DOWN_MASK);
        this.filePrintAccel = KeyStroke.getKeyStroke(KeyEvent.VK_P, modKey);
        this.filePreferencesAccel = KeyStroke.getKeyStroke(KeyEvent.VK_COMMA,
                modKey);
        this.gamePlayMapAccel = KeyStroke.getKeyStroke(KeyEvent.VK_P,
                modKey | InputEvent.SHIFT_DOWN_MASK);
        this.gameEditNoteAccel = KeyStroke.getKeyStroke(KeyEvent.VK_N,
                modKey | InputEvent.SHIFT_DOWN_MASK);
        this.battleItemAccel = KeyStroke.getKeyStroke(KeyEvent.VK_M, modKey);
        this.battleSpellAccel = KeyStroke.getKeyStroke(KeyEvent.VK_L, modKey);
        this.battleStealAccel = KeyStroke.getKeyStroke(KeyEvent.VK_T, modKey);
        this.battleDrainAccel = KeyStroke.getKeyStroke(KeyEvent.VK_D, modKey);
        this.battleEndTurnAccel = KeyStroke.getKeyStroke(KeyEvent.VK_E, modKey);
    }

    private void createMenus() {
        this.mainMenuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");
        final JMenu gameMenu = new JMenu("Game");
        final JMenu battleMenu = new JMenu("Battle");
        final JMenu editorMenu = new JMenu("Editor");
        final JMenu helpMenu = new JMenu("Help");
        this.fileOpenSavedGame = new JMenuItem("Open Saved Game...");
        this.fileOpenSavedGame.setAccelerator(this.fileOpenSavedGameAccel);
        this.fileClose = new JMenuItem("Close");
        this.fileClose.setAccelerator(this.fileCloseAccel);
        this.fileSave = new JMenuItem("Save");
        this.fileSave.setAccelerator(this.fileSaveAccel);
        this.fileSaveAs = new JMenuItem("Save As...");
        this.fileSaveAs.setAccelerator(this.fileSaveAsAccel);
        this.filePrint = new JMenuItem("Print...");
        this.filePrint.setAccelerator(this.filePrintAccel);
        this.filePreferences = new JMenuItem("Preferences...");
        this.filePreferences.setAccelerator(this.filePreferencesAccel);
        this.fileExit = new JMenuItem("Exit");
        this.gamePlay = new JMenuItem("Play");
        this.gamePlay.setAccelerator(this.gamePlayMapAccel);
        this.gameRegisterCharacter = new JMenuItem("Register Character...");
        this.gameUnregisterCharacter = new JMenuItem("Unregister Character...");
        this.gameRemoveCharacter = new JMenuItem("Remove Character...");
        this.gameEquipment = new JMenuItem("Show Equipment...");
        this.gameInventory = new JMenuItem("Show Inventory...");
        this.gamePickLeader = new JMenuItem("Pick Leader...");
        this.gameEditNote = new JMenuItem("Edit Note...");
        this.gameEditNote.setAccelerator(this.gameEditNoteAccel);
        this.gameViewPrestige = new JMenuItem("View Prestige...");
        this.gameViewScore = new JMenuItem("View Current Score...");
        this.gameViewStats = new JMenuItem("View Statistics...");
        this.battleSpell = new JMenuItem("Cast Spell");
        this.battleSpell.setAccelerator(this.battleSpellAccel);
        this.battleItem = new JMenuItem("Use an Item");
        this.battleItem.setAccelerator(this.battleItemAccel);
        this.battleSteal = new JMenuItem("Steal Stuff");
        this.battleSteal.setAccelerator(this.battleStealAccel);
        this.battleDrain = new JMenuItem("Drain MP");
        this.battleDrain.setAccelerator(this.battleDrainAccel);
        this.battleEndTurn = new JMenuItem("End Turn");
        this.battleEndTurn.setAccelerator(this.battleEndTurnAccel);
        this.helpAbout = new JMenuItem("About DungeonDiver3...");
        this.helpObjectHelp = new JMenuItem("DungeonDiver3 Help");
        this.helpWhatsNew = new JMenuItem("What's New in DungeonDiver3");
        this.fileOpenSavedGame.addActionListener(this.handler);
        this.fileClose.addActionListener(this.handler);
        this.fileSave.addActionListener(this.handler);
        this.fileSaveAs.addActionListener(this.handler);
        this.filePrint.addActionListener(this.handler);
        this.filePreferences.addActionListener(this.handler);
        this.fileExit.addActionListener(this.handler);
        this.gamePlay.addActionListener(this.handler);
        this.gameRegisterCharacter.addActionListener(this.handler);
        this.gameUnregisterCharacter.addActionListener(this.handler);
        this.gameRemoveCharacter.addActionListener(this.handler);
        this.gameEquipment.addActionListener(this.handler);
        this.gameInventory.addActionListener(this.handler);
        this.gamePickLeader.addActionListener(this.handler);
        this.gameEditNote.addActionListener(this.handler);
        this.gameViewPrestige.addActionListener(this.handler);
        this.gameViewScore.addActionListener(this.handler);
        this.gameViewStats.addActionListener(this.handler);
        this.battleSpell.addActionListener(this.handler);
        this.battleItem.addActionListener(this.handler);
        this.battleSteal.addActionListener(this.handler);
        this.battleDrain.addActionListener(this.handler);
        this.battleEndTurn.addActionListener(this.handler);
        this.helpAbout.addActionListener(this.handler);
        this.helpObjectHelp.addActionListener(this.handler);
        this.helpWhatsNew.addActionListener(this.handler);
        fileMenu.add(this.fileOpenSavedGame);
        fileMenu.add(this.fileClose);
        fileMenu.add(this.fileSave);
        fileMenu.add(this.fileSaveAs);
        fileMenu.add(this.filePrint);
        if (!System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
            fileMenu.add(this.filePreferences);
            fileMenu.add(this.fileExit);
        }
        gameMenu.add(this.gamePlay);
        gameMenu.add(this.gameEquipment);
        gameMenu.add(this.gameInventory);
        gameMenu.add(this.gamePickLeader);
        gameMenu.add(this.gameRegisterCharacter);
        gameMenu.add(this.gameUnregisterCharacter);
        gameMenu.add(this.gameRemoveCharacter);
        gameMenu.add(this.gameEditNote);
        gameMenu.add(this.gameViewPrestige);
        gameMenu.add(this.gameViewScore);
        gameMenu.add(this.gameViewStats);
        battleMenu.add(this.battleSpell);
        battleMenu.add(this.battleItem);
        battleMenu.add(this.battleSteal);
        battleMenu.add(this.battleDrain);
        battleMenu.add(this.battleEndTurn);
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            editorMenu.add(ge.createEditorCommandsMenu());
        }
        if (!System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
            helpMenu.add(this.helpAbout);
        }
        helpMenu.add(this.helpObjectHelp);
        helpMenu.add(this.helpWhatsNew);
        this.mainMenuBar.add(fileMenu);
        this.mainMenuBar.add(gameMenu);
        this.mainMenuBar.add(battleMenu);
        this.mainMenuBar.add(editorMenu);
        this.mainMenuBar.add(helpMenu);
    }

    private void setInitialMenuState() {
        this.fileOpenSavedGame.setEnabled(true);
        this.fileClose.setEnabled(false);
        this.fileSave.setEnabled(false);
        this.fileSaveAs.setEnabled(false);
        this.filePrint.setEnabled(true);
        this.filePreferences.setEnabled(true);
        this.fileExit.setEnabled(true);
        this.gamePlay.setEnabled(true);
        this.gameRegisterCharacter.setEnabled(true);
        this.gameUnregisterCharacter.setEnabled(true);
        this.gameRemoveCharacter.setEnabled(true);
        this.disableGameMenus();
        this.disableBattleMenus();
        this.helpAbout.setEnabled(true);
        this.helpObjectHelp.setEnabled(true);
        this.helpWhatsNew.setEnabled(true);
        final ArrayList<GenericEditor> allEditors = DungeonDiver3
                .getApplication().getAllEditors();
        for (final GenericEditor ge : allEditors) {
            ge.enableEditorCommands();
        }
    }

    private class EventHandler implements ActionListener {
        String suffix;

        public EventHandler() {
            if (Support.inDebugMode()) {
                this.suffix = " (DEBUG)";
            } else {
                this.suffix = "";
            }
        }

        // Handle menus
        @Override
        public void actionPerformed(final ActionEvent e) {
            try {
                final Application app = DungeonDiver3.getApplication();
                final BattleLogic ba = app.getBattle();
                final String cmd = e.getActionCommand();
                if (cmd.equals("Open Saved Game...")) {
                    app.getScenarioManager().loadSavedGame();
                } else if (cmd.equals("Close")) {
                    // Close the window
                    boolean saved = true;
                    int status = 0;
                    if (app.getScenarioManager().getDirty()) {
                        status = ScenarioManager.showSaveDialog();
                        if (status == JOptionPane.YES_OPTION) {
                            saved = app.getScenarioManager().saveGame();
                        } else if (status == JOptionPane.CANCEL_OPTION) {
                            saved = false;
                        } else {
                            app.getScenarioManager().setDirty(false);
                        }
                    }
                    if (saved) {
                        if (app.getMode() == Application.STATUS_GAME) {
                            app.getGameManager().exitGame();
                        } else if (app.getMode() == Application.STATUS_EDITOR) {
                            app.getCurrentEditor().handleCloseWindow();
                        }
                    }
                } else if (cmd.equals("Save")) {
                    app.getScenarioManager().saveGame();
                } else if (cmd.equals("Save As...")) {
                    app.getScenarioManager().saveGameAs();
                } else if (cmd.equals("Print...")) {
                    BoardPrinter.printBoard(app.getOutputFrame());
                } else if (cmd.equals("Exit")) {
                    // Exit program
                    if (app.getGUIManager().quitHandler()) {
                        System.exit(0);
                    }
                } else if (cmd.equals("Preferences...")) {
                    // Show preferences dialog
                    PreferencesManager.showPrefs();
                } else if (cmd.equals("Play")) {
                    // Play the current scenario
                    final boolean proceed = app.getGameManager().newGame();
                    if (proceed) {
                        app.getGameManager().playMap();
                    }
                } else if (cmd.equals("Register Character...")) {
                    // Register Character
                    CharacterRegistration.registerCharacter();
                } else if (cmd.equals("Unregister Character...")) {
                    // Unregister Character
                    CharacterRegistration.unregisterCharacter();
                } else if (cmd.equals("Remove Character...")) {
                    // Confirm
                    final int confirm = CommonDialogs.showConfirmDialog(
                            "WARNING: This will DELETE the character from disk,\n"
                                    + "and CANNOT be undone! Proceed anyway?",
                            "Remove Character" + this.suffix);
                    if (confirm == CommonDialogs.YES_OPTION) {
                        // Remove Character
                        CharacterRegistration.removeCharacter();
                    }
                } else if (cmd.equals("Show Equipment...")) {
                    InventoryViewer.showEquipmentDialog();
                } else if (cmd.equals("Show Inventory...")) {
                    InventoryViewer.showInventoryDialog();
                } else if (cmd.equals("Pick Leader...")) {
                    final PartyMember pm = PartyManager.getParty()
                            .pickOnePartyMember();
                    if (pm != null) {
                        PartyManager.getParty().setLeader(pm.getName());
                    }
                    app.getGameManager().updateStats();
                } else if (cmd.equals("Edit Note...")) {
                    // Edit Note
                    NoteManager.editNote();
                } else if (cmd.equals("View Prestige...")) {
                    // View Prestige
                    PrestigeViewer.viewPrestige();
                } else if (cmd.equals("View Current Score...")) {
                    // View Score
                    app.getGameManager().showCurrentScore();
                } else if (cmd.equals("View Statistics...")) {
                    // View Statistics
                    StatisticsViewer.viewStatistics();
                } else if (cmd.equals("Cast Spell")) {
                    ba.castSpell();
                } else if (cmd.equals("Use an Item")) {
                    ba.useItem();
                } else if (cmd.equals("Steal Stuff")) {
                    ba.steal();
                } else if (cmd.equals("Drain MP")) {
                    ba.drain();
                } else if (cmd.equals("End Turn")) {
                    ba.endTurn();
                } else if (cmd.equals("About DungeonDiver3...")) {
                    app.getAboutDialog().showAboutDialog();
                } else if (cmd.equals("DungeonDiver3 Help")) {
                    app.getHelpManager().showHelp();
                } else if (cmd.equals("What's New in DungeonDiver3")) {
                    app.getHistoryManager().showHelp();
                }
                MenuManager.this.checkFlags();
            } catch (final Exception ex) {
                DungeonDiver3.getErrorLogger().logError(ex);
            }
        }
    }
}
