/*  TallerTower: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.tallertower.items.combat.predefined;

import com.puttysoftware.tallertower.battle.BattleTarget;
import com.puttysoftware.tallertower.creatures.StatConstants;
import com.puttysoftware.tallertower.effects.Effect;
import com.puttysoftware.tallertower.items.combat.CombatItem;
import com.puttysoftware.tallertower.resourcemanagers.SoundConstants;

public class Fireball extends CombatItem {
    public Fireball() {
        super("Fireball", 500, BattleTarget.ENEMY);
    }

    @Override
    protected void defineFields() {
        this.sound = SoundConstants.SOUND_BOLT;
        this.e = new Effect("Fireball", 1);
        this.e.setEffect(Effect.EFFECT_ADD, StatConstants.STAT_CURRENT_HP, -3);
        this.e.setScaleStat(StatConstants.STAT_LEVEL);
        this.e.setScaleFactor(1.5);
        this.e.setMessage(Effect.MESSAGE_INITIAL,
                "You throw a fireball at the enemy!");
        this.e.setMessage(Effect.MESSAGE_SUBSEQUENT,
                "The fireball sears the enemy badly, dealing LOTS of damage!");
    }
}
