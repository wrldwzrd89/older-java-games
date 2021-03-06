/*  DynamicDungeon: An RPG
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: mazer5d@worldwizard.net
 */
package net.dynamicdungeon.dynamicdungeon.dungeon.abc;

import net.dynamicdungeon.dynamicdungeon.dungeon.Dungeon;
import net.dynamicdungeon.dynamicdungeon.dungeon.DungeonConstants;
import net.dynamicdungeon.dynamicdungeon.dungeon.utilities.TypeConstants;
import net.dynamicdungeon.randomrange.RandomRange;

public abstract class AbstractTrap extends AbstractDungeonObject {
    // Fields
    private final int base;

    // Constructors
    protected AbstractTrap(final int baseName) {
        super(false, false);
        this.base = baseName;
    }

    // Scriptability
    @Override
    public abstract void postMoveAction(final boolean ie, final int dirX,
            final int dirY);

    @Override
    public int getBaseID() {
        return this.base;
    }

    @Override
    public abstract String getName();

    @Override
    public int getLayer() {
        return DungeonConstants.LAYER_OBJECT;
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_TRAP);
    }

    @Override
    public int getCustomProperty(final int propID) {
        return AbstractDungeonObject.DEFAULT_CUSTOM_VALUE;
    }

    @Override
    public void setCustomProperty(final int propID, final int value) {
        // Do nothing
    }

    @Override
    public boolean shouldGenerateObject(final Dungeon maze, final int row,
            final int col, final int floor, final int level, final int layer) {
        // Generate all traps at 25% rate
        final RandomRange reject = new RandomRange(1, 100);
        return reject.generate() < 25;
    }
}