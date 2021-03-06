/*  RolePlayer: A Map-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package net.worldwizard.support.map.objects;

import net.worldwizard.support.creatures.PartyManager;
import net.worldwizard.support.map.Map;
import net.worldwizard.support.map.generic.GameSounds;
import net.worldwizard.support.map.generic.GenericTeleport;
import net.worldwizard.support.map.generic.MapObject;
import net.worldwizard.support.map.generic.TemplateTransform;
import net.worldwizard.support.scripts.game.GameActionCode;
import net.worldwizard.support.scripts.game.GameScript;
import net.worldwizard.support.scripts.game.GameScriptEntry;
import net.worldwizard.support.scripts.game.GameScriptEntryArgument;

public class StairsInto extends GenericTeleport {
    // Fields
    private final GameScript postMoveScript;

    // Constructors
    public StairsInto() {
        super();
        this.setTemplateTransform(new TemplateTransform(1.0, 1.0, 1.0, ""));
        // Create post-move script
        final GameScript scpt = new GameScript();
        final GameScriptEntry entry1 = new GameScriptEntry();
        entry1.setActionCode(GameActionCode.RELATIVE_LEVEL_CHANGE);
        entry1.addActionArg(new GameScriptEntryArgument(1));
        entry1.finalizeActionArgs();
        scpt.addAction(entry1);
        final GameScriptEntry entry2 = new GameScriptEntry();
        entry2.setActionCode(GameActionCode.SOUND);
        entry2.addActionArg(
                new GameScriptEntryArgument(GameSounds.STAIRS));
        entry2.finalizeActionArgs();
        scpt.addAction(entry2);
        scpt.finalizeActions();
        this.postMoveScript = scpt;
    }

    @Override
    public String getName() {
        return "Stairs Into";
    }

    @Override
    public String getPluralName() {
        return "Sets of Stairs Into";
    }

    @Override
    public GameScript getPostMoveScript(final boolean ie, final int dirX,
            final int dirY, final int dirZ, final Map map) {
        PartyManager.getParty().increaseDungeonLevel();
        return this.postMoveScript;
    }

    @Override
    public String getDescription() {
        return "Stairs Into lead deeper into the dungeon.";
    }

    @Override
    public int getCustomFormat() {
        return 0;
    }

    @Override
    public int getCustomProperty(final int propID) {
        return MapObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }
}
