/*  WidgetWarren: A Maze-Solving Game
Copyright (C) 2008-2014 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.widgetwarren.generic;

import java.io.IOException;

import com.puttysoftware.widgetwarren.WidgetWarren;
import com.puttysoftware.widgetwarren.game.ObjectInventory;
import com.puttysoftware.widgetwarren.maze.MazeConstants;
import com.puttysoftware.widgetwarren.objects.Empty;
import com.puttysoftware.widgetwarren.resourcemanagers.SoundConstants;
import com.puttysoftware.widgetwarren.resourcemanagers.SoundManager;
import com.puttysoftware.xio.XDataReader;
import com.puttysoftware.xio.XDataWriter;

public abstract class GenericCharacter extends MazeObject {
    // Fields
    public static final int FULL_HEAL_PERCENTAGE = 100;
    private static final int SHOT_SELF_NORMAL_DAMAGE = 5;
    private static final int SHOT_SELF_SPECIAL_DAMAGE = 10;

    // Constructors
    protected GenericCharacter() {
        super(false);
        this.setSavedObject(new Empty());
    }

    // Methods
    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        // Do nothing
    }

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
        return MazeConstants.LAYER_OBJECT;
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_CHARACTER);
    }

    @Override
    public boolean arrowHitAction(final int locX, final int locY,
            final int locZ, final int dirX, final int dirY, final int arrowType,
            final ObjectInventory inv) {
        // Shot self
        WidgetWarren.getApplication().showMessage("Ouch, you shot yourself!");
        if (arrowType == ArrowTypeConstants.ARROW_TYPE_PLAIN) {
            WidgetWarren.getApplication().getMazeManager().getMaze()
                    .doDamage(GenericCharacter.SHOT_SELF_NORMAL_DAMAGE);
        } else {
            WidgetWarren.getApplication().getMazeManager().getMaze()
                    .doDamage(GenericCharacter.SHOT_SELF_SPECIAL_DAMAGE);
        }
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_LAVA);
        return false;
    }

    @Override
    public int getCustomFormat() {
        if (WidgetWarren.getApplication().getMazeManager()
                .isMazeXML2Compatible()) {
            return 0;
        } else {
            return MazeObject.CUSTOM_FORMAT_MANUAL_OVERRIDE;
        }
    }

    @Override
    public int getCustomProperty(final int propID) {
        return MazeObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }

    @Override
    protected void writeMazeObjectHookXML(final XDataWriter writer)
            throws IOException {
        this.getSavedObject().writeMazeObjectXML(writer);
    }

    @Override
    protected MazeObject readMazeObjectHookXML(final XDataReader reader,
            final int formatVersion) throws IOException {
        this.setSavedObject(WidgetWarren.getApplication().getObjects()
                .readMazeObjectXML(reader, formatVersion));
        return this;
    }
}
