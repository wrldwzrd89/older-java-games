/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.generic;

import java.io.IOException;

import com.puttysoftware.rulemazer.Application;
import com.puttysoftware.rulemazer.CommonDialogs;
import com.puttysoftware.rulemazer.Main;
import com.puttysoftware.rulemazer.game.ObjectInventory;
import com.puttysoftware.rulemazer.maze.effects.MazeEffectConstants;
import com.puttysoftware.rulemazer.objects.GhostAmulet;
import com.puttysoftware.rulemazer.objects.PasswallBoots;
import com.puttysoftware.rulemazer.objects.SignalCrystal;
import com.puttysoftware.rulemazer.resourcemanagers.SoundConstants;
import com.puttysoftware.rulemazer.resourcemanagers.SoundManager;
import com.puttysoftware.xmlio.XMLDataReader;
import com.puttysoftware.xmlio.XMLDataWriter;

public abstract class GenericProgrammableLock extends GenericSingleLock {
    private static final SignalCrystal SIGNAL = new SignalCrystal();

    protected GenericProgrammableLock() {
        super(GenericProgrammableLock.SIGNAL);
    }

    // Scriptability
    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_PROGRAMMABLE_LOCK);
        this.type.set(TypeConstants.TYPE_SINGLE_LOCK);
        this.type.set(TypeConstants.TYPE_LOCK);
    }

    @Override
    public void postMoveAction(final boolean ie, final int dirX, final int dirY,
            final ObjectInventory inv) {
        final Application app = Main.getApplication();
        if (!app.getGameManager()
                .isEffectActive(MazeEffectConstants.EFFECT_GHOSTLY)
                && !inv.isItemThere(new PasswallBoots())) {
            if (this.getKey() != GenericProgrammableLock.SIGNAL) {
                if (!this.getKey().isInfinite()) {
                    inv.removeItem(this.getKey());
                }
            }
            app.getGameManager().decay();
            // Play unlock sound, if it's enabled
            SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                    SoundConstants.SOUND_WALK);
            Main.getApplication().getGameManager()
                    .addToScore(GenericLock.SCORE_UNLOCK);
        } else {
            SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                    SoundConstants.SOUND_WALK);
        }
    }

    @Override
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        if (this.isConditionallyDirectionallySolid(ie, dirX, dirY, inv)) {
            if (this.getKey() == GenericProgrammableLock.SIGNAL) {
                Main.getApplication().showMessage("You need a Crystal");
            } else {
                Main.getApplication()
                        .showMessage("You need a " + this.getKey().getName());
            }
        }
        SoundManager.playSound(SoundConstants.SOUND_CATEGORY_SOLVING_MAZE,
                SoundConstants.SOUND_WALK_FAILED);
    }

    @Override
    public boolean isConditionallySolid(final ObjectInventory inv) {
        if (this.getKey() != GenericProgrammableLock.SIGNAL) {
            return !inv.isItemThere(this.getKey());
        } else {
            return !inv
                    .isItemCategoryThere(TypeConstants.TYPE_PROGRAMMABLE_KEY);
        }
    }

    @Override
    public boolean isConditionallyDirectionallySolid(final boolean ie,
            final int dirX, final int dirY, final ObjectInventory inv) {
        // Handle passwall boots and ghost amulet
        if (inv.isItemThere(new PasswallBoots())
                || inv.isItemThere(new GhostAmulet())) {
            return false;
        } else {
            if (this.getKey() != GenericProgrammableLock.SIGNAL) {
                return !inv.isItemThere(this.getKey());
            } else {
                return !inv.isItemCategoryThere(
                        TypeConstants.TYPE_PROGRAMMABLE_KEY);
            }
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
    public MazeObject editorPropertiesHook() {
        final MazeObjectList objects = Main.getApplication().getObjects();
        final String[] tempKeyNames = objects.getAllProgrammableKeyNames();
        final MazeObject[] tempKeys = objects.getAllProgrammableKeys();
        final String[] keyNames = new String[tempKeyNames.length + 1];
        final MazeObject[] keys = new MazeObject[tempKeys.length + 1];
        System.arraycopy(tempKeyNames, 0, keyNames, 0, tempKeyNames.length);
        System.arraycopy(tempKeys, 0, keys, 0, tempKeys.length);
        keyNames[tempKeyNames.length] = "Any Crystal";
        keys[tempKeys.length] = GenericProgrammableLock.SIGNAL;
        int oldIndex = -1;
        for (oldIndex = 0; oldIndex < keyNames.length; oldIndex++) {
            if (this.getKey().getName().equals(keyNames[oldIndex])) {
                break;
            }
        }
        oldIndex--;
        if (oldIndex == -1) {
            oldIndex = 0;
        }
        final String res = CommonDialogs.showInputDialog(
                "Set Key for " + this.getName(), "Editor", keyNames,
                keyNames[oldIndex]);
        if (res != null) {
            int index = -1;
            for (index = 0; index < keyNames.length; index++) {
                if (res.equals(keyNames[index])) {
                    break;
                }
            }
            if (index != -1) {
                this.setKey((GenericKey) keys[index]);
            }
        }
        return this;
    }

    @Override
    protected MazeObject readMazeObjectHookXML(final XMLDataReader reader,
            final int formatVersion) throws IOException {
        final MazeObject o = Main.getApplication().getObjects()
                .readMazeObjectXML(reader, formatVersion);
        if (o == null) {
            this.setKey(GenericProgrammableLock.SIGNAL);
        } else {
            this.setKey((GenericKey) o);
        }
        return this;
    }

    @Override
    protected void writeMazeObjectHookXML(final XMLDataWriter writer)
            throws IOException {
        this.getKey().writeMazeObjectXML(writer);
    }

    @Override
    public int getCustomFormat() {
        return MazeObject.CUSTOM_FORMAT_MANUAL_OVERRIDE;
    }
}
