/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazerunner2.maze.objects;

import com.puttysoftware.mazerunner2.MazeRunnerII;
import com.puttysoftware.mazerunner2.editor.MazeEditorLogic;
import com.puttysoftware.mazerunner2.maze.abc.AbstractMazeObject;
import com.puttysoftware.mazerunner2.maze.abc.AbstractNWayTeleport;
import com.puttysoftware.mazerunner2.resourcemanagers.ObjectImageConstants;

public class NWayTeleport extends AbstractNWayTeleport {
    // Constructors
    public NWayTeleport() {
        super(1, ObjectImageConstants.OBJECT_IMAGE_N_WAY);
    }

    @Override
    public String getName() {
        return "N-Way Teleport";
    }

    @Override
    public String getPluralName() {
        return "N-Way Teleports";
    }

    @Override
    public String getDescription() {
        return "N-Way Teleports send you to one of N predetermined destinations when stepped on, depending on which destination is selected.";
    }

    @Override
    public final AbstractMazeObject editorPropertiesHook() {
        final MazeEditorLogic me = MazeRunnerII.getApplication().getEditor();
        me.setNWayDestCount(this.getDestinationCount());
        me.setNWayEdited(this);
        me.editTeleportDestination(MazeEditorLogic.TELEPORT_TYPE_N_WAY);
        return null;
    }
}