/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.objects;

import com.puttysoftware.widgetwarren.Application;
import com.puttysoftware.widgetwarren.WidgetWarren;
import com.puttysoftware.widgetwarren.generic.GenericWall;
import com.puttysoftware.widgetwarren.maze.MazeConstants;

public class FadingWall extends GenericWall {
    // Fields
    private static final int SCAN_LIMIT = 3;

    // Constructors
    public FadingWall() {
        super();
        this.activateTimer(1);
    }

    @Override
    public void timerExpiredAction(final int dirX, final int dirY) {
        // Disappear if the player is close to us
        boolean scanResult = false;
        final Application app = WidgetWarren.getApplication();
        final int pz = app.getGameManager().getPlayerManager()
                .getPlayerLocationZ();
        final int pl = MazeConstants.LAYER_OBJECT;
        final String targetName = new Player().getName();
        scanResult = app.getMazeManager().getMaze().radialScan(dirX, dirY, pz,
                pl, FadingWall.SCAN_LIMIT, targetName);
        if (scanResult) {
            app.getGameManager().morph(new Empty(), dirX, dirY, pz);
        }
        this.activateTimer(1);
    }

    @Override
    public String getName() {
        return "Fading Wall";
    }

    @Override
    public String getGameName() {
        return "Wall";
    }

    @Override
    public String getPluralName() {
        return "Fading Walls";
    }

    @Override
    public String getDescription() {
        return "Fading Walls disappear when you get close to them.";
    }
}
