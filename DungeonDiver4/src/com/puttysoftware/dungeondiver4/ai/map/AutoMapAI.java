/*  DungeonDiver4: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver4.ai.map;

import java.awt.Point;

public class AutoMapAI extends AbstractMapAIRoutine {
    // Constructor
    public AutoMapAI() {
        super();
    }

    @Override
    public int getNextAction(MapAIContext ac) {
        Point there = ac.isEnemyNearby();
        if (there != null) {
            // Something hostile is nearby, so attack it
            this.moveX = there.x;
            this.moveY = there.y;
            return AbstractMapAIRoutine.ACTION_MOVE;
        } else {
            return AbstractMapAIRoutine.ACTION_END_TURN;
        }
    }
}
