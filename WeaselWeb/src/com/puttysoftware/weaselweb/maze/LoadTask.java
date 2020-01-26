/*  WeaselWeb: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.weaselweb.maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.fileutils.ZipUtilities;
import com.puttysoftware.weaselweb.Application;
import com.puttysoftware.weaselweb.WeaselWeb;
import com.puttysoftware.weaselweb.resourcemanagers.LogoManager;

public class LoadTask extends Thread {
    // Fields
    private Maze gameMaze;
    private final String filename;
    private final boolean isSavedGame;
    private final JFrame loadFrame;
    private final JProgressBar loadBar;

    // Constructors
    public LoadTask(final String file, final boolean saved) {
        this.filename = file;
        this.isSavedGame = saved;
        this.setName(" File Loader");
        this.loadFrame = new JFrame("Loading...");
        this.loadFrame.setIconImage(LogoManager.getIconLogo());
        this.loadBar = new JProgressBar();
        this.loadBar.setIndeterminate(true);
        this.loadFrame.getContentPane().add(this.loadBar);
        this.loadFrame.setResizable(false);
        this.loadFrame
                .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.loadFrame.pack();
    }

    // Methods
    @Override
    public void run() {
        this.loadFrame.setVisible(true);
        final Application app = WeaselWeb.getApplication();
        int startW;
        String sg;
        if (this.isSavedGame) {
            app.getGameManager().setSavedGameFlag(true);
            sg = "Saved Game";
        } else {
            app.getGameManager().setSavedGameFlag(false);
            sg = "Maze";
        }
        try {
            final File mazeFile = new File(this.filename);
            try {
                this.gameMaze = new Maze();
                ZipUtilities.unzipDirectory(mazeFile,
                        new File(this.gameMaze.getBasePath()));
                // Set prefix handler
                this.gameMaze.setPrefixHandler(new PrefixHandler());
                // Set suffix handler
                if (this.isSavedGame) {
                    this.gameMaze.setSuffixHandler(new SuffixHandler());
                } else {
                    this.gameMaze.setSuffixHandler(null);
                }
                this.gameMaze = this.gameMaze.readMaze();
                if (this.gameMaze == null) {
                    throw new InvalidMazeException(
                            "Unknown object encountered.");
                }
                app.getMazeManager().setMaze(this.gameMaze);
                startW = this.gameMaze.getStartLevel();
                this.gameMaze.switchLevel(startW);
                final boolean playerExists = this.gameMaze.doesPlayerExist();
                if (playerExists) {
                    app.getGameManager().getPlayerManager().setPlayerLocation(
                            this.gameMaze.getStartColumn(),
                            this.gameMaze.getStartRow(),
                            this.gameMaze.getStartFloor(), startW);
                    app.getGameManager().resetViewingWindow();
                }
                if (!this.isSavedGame) {
                    this.gameMaze.save();
                }
                // Final cleanup
                final String lum = app.getMazeManager().getLastUsedMaze();
                final String lug = app.getMazeManager().getLastUsedGame();
                app.getMazeManager().clearLastUsedFilenames();
                if (this.isSavedGame) {
                    app.getMazeManager().setLastUsedGame(lug);
                } else {
                    app.getMazeManager().setLastUsedMaze(lum);
                }
                app.getMenuManager().clearLockedFlag();
                app.getEditor().mazeChanged();
                app.getGameManager().stateChanged();
                CommonDialogs.showDialog(sg + " file loaded.");
                app.getMazeManager().handleDeferredSuccess(true);
            } catch (final FileNotFoundException fnfe) {
                CommonDialogs.showDialog("Loading the  " + sg.toLowerCase()
                        + " file failed, probably due to illegal characters in the file name.");
                app.getMazeManager().handleDeferredSuccess(false);
            } catch (final IOException ie) {
                ie.printStackTrace();
                throw new InvalidMazeException(
                        "Error loading " + sg.toLowerCase() + " file.");
            }
        } catch (final InvalidMazeException ime) {
            CommonDialogs.showDialog(ime.getMessage());
            app.getMazeManager().handleDeferredSuccess(false);
        } catch (final Exception ex) {
            WeaselWeb.getErrorLogger().logError(ex);
        } finally {
            this.loadFrame.setVisible(false);
        }
    }
}
