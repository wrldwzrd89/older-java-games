/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.Application;
import com.puttysoftware.mazemode.MazeMode;
import com.puttysoftware.mazemode.generic.GenericWall;
import com.puttysoftware.mazemode.generic.MazeObject;
import com.puttysoftware.mazemode.generic.TypeConstants;
import com.puttysoftware.mazemode.maze.MazeConstants;
import com.puttysoftware.mazemode.resourcemanagers.SoundConstants;
import com.puttysoftware.mazemode.resourcemanagers.SoundManager;

public class BreakableWallHorizontal extends GenericWall {
    // Constructors
    public BreakableWallHorizontal() {
        super(true, true);
    }

    @Override
    public void chainReactionAction(final int x, final int y, final int z) {
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_CRACK);
        BreakableWallHorizontal.doChainReact(x, y, z);
    }

    private static void doChainReact(final int x, final int y, final int z) {
        final Application app = MazeMode.getApplication();
        BreakableWallHorizontal curr = null;
        try {
            curr = (BreakableWallHorizontal) app.getMazeManager()
                    .getMazeObject(x, y, z, MazeConstants.LAYER_OBJECT);
        } catch (final ClassCastException cce) {
            // We're not a breakable wall horizontal, so abort
            return;
        }
        String mo4Name, mo6Name, invalidName, currName;
        invalidName = new EmptyVoid().getName();
        currName = curr.getName();
        final MazeObject mo4 = app.getMazeManager().getMazeObject(x - 1, y, z,
                MazeConstants.LAYER_OBJECT);
        try {
            mo4Name = mo4.getName();
        } catch (final NullPointerException np) {
            mo4Name = invalidName;
        }
        final MazeObject mo6 = app.getMazeManager().getMazeObject(x + 1, y, z,
                MazeConstants.LAYER_OBJECT);
        try {
            mo6Name = mo6.getName();
        } catch (final NullPointerException np) {
            mo6Name = invalidName;
        }
        app.getGameManager().morph(new Empty(), x, y, z);
        if (mo4Name.equals(currName)) {
            BreakableWallHorizontal.doChainReact(x - 1, y, z);
        }
        if (mo6Name.equals(currName)) {
            BreakableWallHorizontal.doChainReact(x + 1, y, z);
        }
    }

    @Override
    public String getName() {
        return "Breakable Wall Horizontal";
    }

    @Override
    public String getGameName() {
        return "Wall";
    }

    @Override
    public String getPluralName() {
        return "Breakable Walls Horizontal";
    }

    @Override
    public String getDescription() {
        return "Breakable Walls Horizontal disintegrate when touched, causing other Breakable Walls Horizontal nearby to also disintegrate.";
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_BREAKABLE_WALL);
        this.type.set(TypeConstants.TYPE_WALL);
    }
}
