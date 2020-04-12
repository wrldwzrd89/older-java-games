/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.compatibility.objects;

import com.puttysoftware.mazer5d.Mazer5D;
import com.puttysoftware.mazer5d.assets.SoundGroup;
import com.puttysoftware.mazer5d.assets.SoundIndex;
import com.puttysoftware.mazer5d.compatibility.abc.GenericWand;
import com.puttysoftware.mazer5d.compatibility.abc.MazeObject;
import com.puttysoftware.mazer5d.gui.Application;
import com.puttysoftware.mazer5d.loaders.SoundPlayer;

public class TeleportWand extends GenericWand {
    public TeleportWand() {
        super();
    }

    @Override
    public String getName() {
        return "Teleport Wand";
    }

    @Override
    public String getPluralName() {
        return "Teleport Wands";
    }

    @Override
    public void useHelper(final int x, final int y, final int z) {
        this.useAction(null, x, y, z);
        SoundPlayer.playSound(SoundIndex.TELEPORT,
                SoundGroup.GAME);
    }

    @Override
    public void useAction(final MazeObject mo, final int x, final int y,
            final int z) {
        final Application app = Mazer5D.getApplication();
        app.getGameManager().updatePositionAbsolute(x, y, z);
    }

    @Override
    public String getDescription() {
        return "Teleport Wands will teleport you to the target square when used. You cannot teleport to areas you cannot see.";
    }
}
