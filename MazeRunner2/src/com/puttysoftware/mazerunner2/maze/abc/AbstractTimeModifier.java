/*  MazeRunnerII: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: mazer5d@worldwizard.net
 */
package com.puttysoftware.mazerunner2.maze.abc;

import com.puttysoftware.mazerunner2.maze.MazeConstants;
import com.puttysoftware.mazerunner2.maze.utilities.MazeObjectInventory;
import com.puttysoftware.mazerunner2.maze.utilities.TypeConstants;
import com.puttysoftware.mazerunner2.resourcemanagers.ObjectImageConstants;

public abstract class AbstractTimeModifier extends AbstractMazeObject {
    // Constructors
    protected AbstractTimeModifier(int attr, int attrColor) {
        super(false, false);
        this.setAttributeID(attr);
        this.setAttributeTemplateColor(attrColor);
    }

    @Override
    public abstract void postMoveAction(final boolean ie, final int dirX,
            final int dirY, final MazeObjectInventory inv);

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_HOURGLASS;
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_TIME_MODIFIER);
    }

    @Override
    public int getLayer() {
        return MazeConstants.LAYER_OBJECT;
    }

    @Override
    public int getCustomProperty(int propID) {
        return AbstractMazeObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(int propID, int value) {
        // Do nothing
    }
}