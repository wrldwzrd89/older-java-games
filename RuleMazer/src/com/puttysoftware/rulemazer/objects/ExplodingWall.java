/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.objects;

import com.puttysoftware.rulemazer.Application;
import com.puttysoftware.rulemazer.Main;
import com.puttysoftware.rulemazer.game.ObjectInventory;
import com.puttysoftware.rulemazer.generic.GenericWall;
import com.puttysoftware.rulemazer.generic.MazeObject;
import com.puttysoftware.rulemazer.maze.MazeConstants;
import com.puttysoftware.rulemazer.resourcemanagers.SoundConstants;
import com.puttysoftware.rulemazer.resourcemanagers.SoundManager;

public class ExplodingWall extends GenericWall {
    // Constructors
    public ExplodingWall() {
        super(true, true);
    }

    @Override
    public boolean preMoveAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        Main.getApplication().showMessage("BOOM!");
        return true;
    }

    @Override
    public void chainReactionAction(final int x, final int y, final int z) {
        // Explode this wall, and any exploding walls next to this wall as well
        final Application app = Main.getApplication();
        ExplodingWall curr = null;
        try {
            curr = (ExplodingWall) app.getMazeManager().getMazeObject(x, y, z,
                    MazeConstants.LAYER_OBJECT);
        } catch (final ClassCastException cce) {
            // We're not an exploding wall, so abort
            return;
        }
        String mo2Name, mo4Name, mo6Name, mo8Name, invalidName, currName;
        invalidName = new EmptyVoid().getName();
        currName = curr.getName();
        final MazeObject mo2 = app.getMazeManager().getMazeObject(x - 1, y, z,
                MazeConstants.LAYER_OBJECT);
        try {
            mo2Name = mo2.getName();
        } catch (final NullPointerException np) {
            mo2Name = invalidName;
        }
        final MazeObject mo4 = app.getMazeManager().getMazeObject(x, y - 1, z,
                MazeConstants.LAYER_OBJECT);
        try {
            mo4Name = mo4.getName();
        } catch (final NullPointerException np) {
            mo4Name = invalidName;
        }
        final MazeObject mo6 = app.getMazeManager().getMazeObject(x, y + 1, z,
                MazeConstants.LAYER_OBJECT);
        try {
            mo6Name = mo6.getName();
        } catch (final NullPointerException np) {
            mo6Name = invalidName;
        }
        final MazeObject mo8 = app.getMazeManager().getMazeObject(x + 1, y, z,
                MazeConstants.LAYER_OBJECT);
        try {
            mo8Name = mo8.getName();
        } catch (final NullPointerException np) {
            mo8Name = invalidName;
        }
        app.getGameManager().morph(new Empty(), x, y, z, "BOOM!");
        if (mo2Name.equals(currName)) {
            curr.chainReactionAction(x - 1, y, z);
        }
        if (mo4Name.equals(currName)) {
            curr.chainReactionAction(x, y - 1, z);
        }
        if (mo6Name.equals(currName)) {
            curr.chainReactionAction(x, y + 1, z);
        }
        if (mo8Name.equals(currName)) {
            curr.chainReactionAction(x + 1, y, z);
        }
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_EXPLODE);
    }

    @Override
    public String getName() {
        return "Exploding Wall";
    }

    @Override
    public String getGameName() {
        return "Wall";
    }

    @Override
    public String getPluralName() {
        return "Exploding Walls";
    }

    @Override
    public String getDescription() {
        return "Exploding Walls explode when touched, causing other Exploding Walls nearby to also explode.";
    }
}