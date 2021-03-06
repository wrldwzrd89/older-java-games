/*  TallerTower: An RPG
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.tallertower.maze;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import com.puttysoftware.randomrange.RandomRange;
import com.puttysoftware.tallertower.Application;
import com.puttysoftware.tallertower.TallerTower;
import com.puttysoftware.tallertower.creatures.party.PartyManager;
import com.puttysoftware.tallertower.maze.abc.AbstractMazeObject;
import com.puttysoftware.tallertower.maze.utilities.ImageColorConstants;
import com.puttysoftware.tallertower.resourcemanagers.LogoManager;

public class GenerateTask extends Thread {
    // Fields
    private final JFrame generateFrame;
    private final boolean scratch;

    // Constructors
    public GenerateTask(final boolean startFromScratch) {
        this.scratch = startFromScratch;
        this.setName("Level Generator");
        this.generateFrame = new JFrame("Generating...");
        this.generateFrame.setIconImage(LogoManager.getIconLogo());
        final JProgressBar loadBar = new JProgressBar();
        loadBar.setIndeterminate(true);
        this.generateFrame.getContentPane().add(loadBar);
        this.generateFrame.setResizable(false);
        this.generateFrame
                .setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.generateFrame.pack();
    }

    // Methods
    @Override
    public void run() {
        try {
            this.generateFrame.setVisible(true);
            final Application app = TallerTower.getApplication();
            Maze gameMaze = app.getMazeManager().getMaze();
            if (!this.scratch) {
                app.getGameManager().disableEvents();
            } else {
                gameMaze = new Maze();
                app.getMazeManager().setMaze(gameMaze);
            }
            gameMaze.addLevel(Maze.getMaxRows(), Maze.getMaxColumns(),
                    Maze.getMaxFloors());
            gameMaze.fillLevelRandomly();
            final RandomRange rR = new RandomRange(0, Maze.getMaxRows() - 1);
            final RandomRange rC = new RandomRange(0, Maze.getMaxColumns() - 1);
            final RandomRange rF = new RandomRange(0, Maze.getMaxFloors() - 1);
            if (this.scratch) {
                int startR, startC, startF;
                do {
                    startR = rR.generate();
                    startC = rC.generate();
                    startF = rF.generate();
                } while (gameMaze.getCell(startR, startC, startF,
                        MazeConstants.LAYER_OBJECT).isSolid());
                gameMaze.setStartRow(startR);
                gameMaze.setStartColumn(startC);
                gameMaze.setStartFloor(startF);
                app.getMazeManager().setLoaded(true);
                final boolean playerExists = gameMaze.doesPlayerExist();
                if (playerExists) {
                    gameMaze.setPlayerToStart();
                    app.getGameManager().resetViewingWindow();
                }
            } else {
                int startR, startC, startF;
                do {
                    startR = rR.generate();
                    startC = rC.generate();
                    startF = rF.generate();
                } while (gameMaze.getCell(startR, startC, startF,
                        MazeConstants.LAYER_OBJECT).isSolid());
                gameMaze.setPlayerLocationX(startR);
                gameMaze.setPlayerLocationY(startC);
                gameMaze.setPlayerLocationZ(startF);
                PartyManager.getParty().offsetTowerLevel(1);
            }
            gameMaze.save();
            // Final cleanup
            AbstractMazeObject.setTemplateColor(ImageColorConstants
                    .getColorForLevel(PartyManager.getParty().getTowerLevel()));
            if (this.scratch) {
                app.getGameManager().stateChanged();
                app.getGameManager().playMaze();
            } else {
                app.getGameManager().resetViewingWindow();
                app.getGameManager().enableEvents();
                app.getGameManager().redrawMaze();
            }
        } catch (final Throwable t) {
            TallerTower.getErrorLogger().logError(t);
        } finally {
            this.generateFrame.setVisible(false);
        }
    }
}
