/*  BrainMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.brainmaze.objects;

import com.puttysoftware.brainmaze.Application;
import com.puttysoftware.brainmaze.BrainMaze;
import com.puttysoftware.brainmaze.editor.MazeEditor;
import com.puttysoftware.brainmaze.game.ObjectInventory;
import com.puttysoftware.brainmaze.generic.ColorConstants;
import com.puttysoftware.brainmaze.generic.GenericTeleport;
import com.puttysoftware.brainmaze.generic.MazeObject;
import com.puttysoftware.brainmaze.resourcemanagers.SoundConstants;
import com.puttysoftware.brainmaze.resourcemanagers.SoundManager;

public class StairsUp extends GenericTeleport {
    // Constructors
    public StairsUp() {
        super(0, 0, 0, false, "");
        this.setTemplateColor(ColorConstants.COLOR_NONE);
        this.setAttributeTemplateColor(ColorConstants.COLOR_NONE);
    }

    // For derived classes only
    protected StairsUp(final boolean doesAcceptPushInto) {
        super(doesAcceptPushInto, "");
        this.setTemplateColor(ColorConstants.COLOR_NONE);
        this.setAttributeTemplateColor(ColorConstants.COLOR_NONE);
    }

    @Override
    public final String getBaseName() {
        return this.getName();
    }

    @Override
    public String getName() {
        return "Stairs Up";
    }

    @Override
    public String getPluralName() {
        return "Sets of Stairs Up";
    }

    @Override
    public int getDestinationRow() {
        final Application app = BrainMaze.getApplication();
        return app.getMazeManager().getMaze().getPlayerLocationX();
    }

    @Override
    public int getDestinationColumn() {
        final Application app = BrainMaze.getApplication();
        return app.getMazeManager().getMaze().getPlayerLocationY();
    }

    @Override
    public int getDestinationFloor() {
        final Application app = BrainMaze.getApplication();
        return app.getMazeManager().getMaze().getPlayerLocationZ() + 1;
    }

    @Override
    public int getDestinationLevel() {
        final Application app = BrainMaze.getApplication();
        return app.getMazeManager().getMaze().getPlayerLocationW();
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        final Application app = BrainMaze.getApplication();
        app.getGameManager().updatePositionAbsoluteNoEvents(
                this.getDestinationRow(), this.getDestinationColumn(),
                this.getDestinationFloor(), this.getDestinationLevel());
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_UP);
    }

    @Override
    public void editorPlaceHook() {
        final MazeEditor me = BrainMaze.getApplication().getEditor();
        me.pairStairs(MazeEditor.STAIRS_UP);
    }

    @Override
    public MazeObject editorPropertiesHook() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Stairs Up lead to the floor above.";
    }

    @Override
    public int getCustomFormat() {
        return 0;
    }

    @Override
    public int getCustomProperty(final int propID) {
        return MazeObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }
}
