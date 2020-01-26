/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazerunner2.maze;

import java.io.File;
import java.io.FileNotFoundException;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.mazerunner2.Application;
import com.puttysoftware.mazerunner2.MazeRunnerII;
import com.puttysoftware.xio.ZipUtilities;

public class SaveTask extends Thread {
    // Fields
    private String filename;
    private boolean isSavedGame;
    private int savedLevel;

    // Constructors
    public SaveTask(String file, boolean saved) {
        this.filename = file;
        this.isSavedGame = saved;
        this.setName("File Writer");
    }

    @Override
    public void run() {
        Application app = MazeRunnerII.getApplication();
        boolean success = true;
        final String sg;
        if (this.isSavedGame) {
            sg = "Saved Game";
        } else {
            sg = "Maze";
        }
        // filename check
        boolean hasExtension = SaveTask.hasExtension(this.filename);
        if (!hasExtension) {
            if (this.isSavedGame) {
                this.filename += Extension.getSavedGameExtensionWithPeriod();
            } else {
                this.filename += Extension.getMazeExtensionWithPeriod();
            }
        }
        File mazeFile = new File(this.filename);
        try {
            // Set prefix handler
            app.getMazeManager().getMaze()
                    .setPrefixHandler(new PrefixHandler());
            // Set suffix handler
            if (this.isSavedGame) {
                app.getMazeManager().getMaze()
                        .setSuffixHandler(new SuffixHandler());
            } else {
                app.getMazeManager().getMaze().setSuffixHandler(null);
            }
            if (this.isSavedGame) {
                // Save start location
                app.getMazeManager().getMaze().saveStart();
                // Save active level
                this.savedLevel = app.getMazeManager().getMaze()
                        .getActiveLevelNumber();
                // Update start location
                int currW = app.getMazeManager().getMaze().getPlayerLocationW();
                app.getMazeManager().getMaze().setStartLevel(currW);
                app.getMazeManager().getMaze().switchLevel(currW);
            }
            app.getMazeManager().getMaze().writeMaze();
            if (this.isSavedGame) {
                // Restore active level
                app.getMazeManager().getMaze().switchLevel(this.savedLevel);
                // Restore start location
                app.getMazeManager().getMaze().restoreStart();
            }
            ZipUtilities.zipDirectory(new File(app.getMazeManager().getMaze()
                    .getBasePath()), mazeFile);
        } catch (final FileNotFoundException fnfe) {
            CommonDialogs
                    .showDialog("Writing the "
                            + sg.toLowerCase()
                            + " file failed, probably due to illegal characters in the file name.");
            success = false;
        } catch (final Exception ex) {
            MazeRunnerII.getErrorLogger().logError(ex);
        }
        MazeRunnerII.getApplication().showMessage(sg + " file saved.");
        app.getMazeManager().handleDeferredSuccess(success);
    }

    private static boolean hasExtension(final String s) {
        String ext = null;
        final int i = s.lastIndexOf('.');
        if ((i > 0) && (i < s.length() - 1)) {
            ext = s.substring(i + 1).toLowerCase();
        }
        if (ext == null) {
            return false;
        } else {
            return true;
        }
    }
}