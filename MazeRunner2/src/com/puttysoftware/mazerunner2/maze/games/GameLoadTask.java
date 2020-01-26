/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazerunner2.maze.games;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.mazerunner2.Application;
import com.puttysoftware.mazerunner2.MazeRunnerII;
import com.puttysoftware.mazerunner2.maze.Maze;
import com.puttysoftware.mazerunner2.maze.legacy.LegacyPrefixHandler;
import com.puttysoftware.mazerunner2.resourcemanagers.LogoManager;
import com.puttysoftware.xio.ZipUtilities;

public class GameLoadTask extends Thread {
    // Fields
    private String filename;
    private JFrame loadFrame;

    // Constructors
    public GameLoadTask(String file) {
        this.filename = file;
        this.setName("Locked File Loader");
        this.loadFrame = new JFrame("Loading...");
        this.loadFrame.setIconImage(LogoManager.getIconLogo());
        JProgressBar loadBar = new JProgressBar();
        loadBar.setIndeterminate(true);
        this.loadFrame.getContentPane().add(loadBar);
        this.loadFrame.setResizable(false);
        this.loadFrame
                .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.loadFrame.pack();
    }

    // Methods
    @Override
    public void run() {
        this.loadFrame.setVisible(true);
        Application app = MazeRunnerII.getApplication();
        int startW;
        String sg;
        app.getGameManager().setSavedGameFlag(false);
        sg = "Maze";
        try {
            File mazeFile = new File(this.filename);
            File tempLock = new File(Maze.getMazeTempFolder() + "lock.tmp");
            Maze gameMaze = new Maze();
            // Unlock the file
            GameFileManager.load(mazeFile, tempLock);
            ZipUtilities.unzipDirectory(tempLock,
                    new File(gameMaze.getBasePath()));
            boolean success = tempLock.delete();
            if (!success) {
                throw new IOException("Failed to delete temporary file!");
            }
            // Set prefix handler
            gameMaze.setLegacyPrefixHandler(new LegacyPrefixHandler());
            // Set suffix handler
            gameMaze.setLegacySuffixHandler(null);
            gameMaze = gameMaze.readLegacyMaze();
            if (gameMaze == null) {
                throw new IOException("Unknown object encountered.");
            }
            app.getMazeManager().setMaze(gameMaze);
            startW = gameMaze.getStartLevel();
            gameMaze.switchLevel(startW);
            final boolean playerExists = gameMaze.doesPlayerExist();
            if (playerExists) {
                app.getMazeManager().getMaze().setPlayerToStart();
                app.getGameManager().resetViewingWindow();
            }
            gameMaze.save();
            // Final cleanup
            app.getMenuManager().setGameFlag();
            app.getEditor().mazeChanged();
            app.getGameManager().stateChanged();
            CommonDialogs.showDialog(sg + " file loaded.");
            app.getMazeManager().handleDeferredSuccess(true);
        } catch (final FileNotFoundException fnfe) {
            CommonDialogs
                    .showDialog("Loading the "
                            + sg.toLowerCase()
                            + " file failed, probably due to illegal characters in the file name.");
            app.getMazeManager().handleDeferredSuccess(false);
        } catch (final IOException ie) {
            CommonDialogs.showDialog("Loading the " + sg.toLowerCase()
                    + " file failed, due to some other type of I/O error.");
            app.getMazeManager().handleDeferredSuccess(false);
        } catch (final Exception ex) {
            MazeRunnerII.getErrorLogger().logError(ex);
        } finally {
            this.loadFrame.setVisible(false);
        }
    }
}