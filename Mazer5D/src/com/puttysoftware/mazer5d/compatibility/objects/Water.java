/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.compatibility.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.compatibility.abc.GenericField;
import com.puttysoftware.mazer5d.compatibility.abc.MazeObjectModel;
import com.puttysoftware.mazer5d.compatibility.maze.MazeConstants;
import com.puttysoftware.mazer5d.game.ObjectInventory;
import com.puttysoftware.mazer5d.gui.Application;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;

public class Water extends GenericField {
    // Constructors
    public Water() {
        super(new AquaBoots(), true);
    }

    // Scriptability
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        SoundPlayer.playSound(SoundIndex.WALK_ON_WATER, SoundGroup.GAME);
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        Mazer5D.getApplication().showMessage("You'll drown");
        SoundPlayer.playSound(SoundIndex.WATER, SoundGroup.GAME);
    }

    @Override
    public void pushIntoAction(final ObjectInventory inv,
            final MazeObjectModel pushed, final int x, final int y, final int z) {
        final Application app = Mazer5D.getApplication();
        if (pushed.isPushable()) {
            app.getGameManager().morph(new SunkenBlock(), x, y, z,
                    MazeConstants.LAYER_GROUND);
            app.getGameManager().morph(new Empty(), x, y, z,
                    MazeConstants.LAYER_OBJECT);
            SoundPlayer.playSound(SoundIndex.SINK_BLOCK, SoundGroup.GAME);
        }
    }

    @Override
    public String getName() {
        return "Water";
    }

    @Override
    public String getPluralName() {
        return "Squares of Water";
    }

    @Override
    public boolean overridesDefaultPostMove() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Water is too unstable to walk on without Aqua Boots.";
    }
}
