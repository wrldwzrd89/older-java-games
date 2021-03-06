/*  DungeonDiverII: A Map-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package net.worldwizard.support.map.objects;

import net.worldwizard.support.map.generic.GameSounds;
import net.worldwizard.support.map.generic.GenericField;
import net.worldwizard.support.map.generic.TemplateTransform;
import net.worldwizard.support.scripts.game.GameActionCode;
import net.worldwizard.support.scripts.game.GameScript;
import net.worldwizard.support.scripts.game.GameScriptEntry;
import net.worldwizard.support.scripts.game.GameScriptEntryArgument;

public class Lava extends GenericField {
    // Constructors
    public Lava() {
        super(5);
        this.setTemplateTransform(new TemplateTransform(1.0, 0.5, 0.25, ""));
    }

    @Override
    protected GameScript playSoundHook() {
        final GameScript scpt = new GameScript();
        final GameScriptEntry entry0 = new GameScriptEntry();
        entry0.setActionCode(GameActionCode.SOUND);
        entry0.addActionArg(
                new GameScriptEntryArgument(GameSounds.SHORT_OW));
        entry0.finalizeActionArgs();
        scpt.addAction(entry0);
        final GameScriptEntry entry1 = new GameScriptEntry();
        entry1.setActionCode(GameActionCode.MESSAGE);
        entry1.addActionArg(
                new GameScriptEntryArgument("Ow, the lava burned you!"));
        entry1.finalizeActionArgs();
        scpt.addAction(entry1);
        scpt.finalizeActions();
        return scpt;
    }

    @Override
    public String getName() {
        return "Lava";
    }

    @Override
    public String getPluralName() {
        return "Squares of Lava";
    }

    @Override
    public boolean overridesDefaultPostMove() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Lava will burn you if you walk on it.";
    }

    @Override
    public String getGameImageNameHook() {
        return "textured";
    }
}
