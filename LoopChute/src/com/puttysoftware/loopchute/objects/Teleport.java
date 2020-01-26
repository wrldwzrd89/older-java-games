/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.objects;

import com.puttysoftware.loopchute.LoopChute;
import com.puttysoftware.loopchute.editor.MazeEditor;
import com.puttysoftware.loopchute.generic.GenericTeleport;
import com.puttysoftware.loopchute.generic.MazeObject;

public class Teleport extends GenericTeleport {
    // Constructors
    public Teleport() {
        super(0, 0, 0, true, "teleport");
    }

    public Teleport(final int destinationRow, final int destinationColumn,
            final int destinationFloor) {
        super(destinationRow, destinationColumn, destinationFloor, true,
                "teleport");
    }

    @Override
    public String getName() {
        return "Teleport";
    }

    @Override
    public String getPluralName() {
        return "Teleports";
    }

    @Override
    public MazeObject editorPropertiesHook() {
        final MazeEditor me = LoopChute.getApplication().getEditor();
        return me.editTeleportDestination(MazeEditor.TELEPORT_TYPE_GENERIC);
    }

    @Override
    public String getDescription() {
        return "Teleports send you to a predetermined destination when stepped on.";
    }
}