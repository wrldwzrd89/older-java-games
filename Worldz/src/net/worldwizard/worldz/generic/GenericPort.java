/*  Worldz: A World-Exploring Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: worldz@worldwizard.net
 */
package net.worldwizard.worldz.generic;

import net.worldwizard.worldz.Messager;
import net.worldwizard.worldz.PreferencesManager;
import net.worldwizard.worldz.Worldz;
import net.worldwizard.worldz.game.ObjectInventory;

public abstract class GenericPort extends GenericInfiniteLock {
    // Fields
    private char letter;

    protected GenericPort(final GenericPlug mgk, final char newLetter) {
        super(mgk);
        this.letter = Character.toUpperCase(newLetter);
    }

    @Override
    public GenericPort clone() {
        final GenericPort copy = (GenericPort) super.clone();
        copy.letter = this.letter;
        return copy;
    }

    // Scriptability
    @Override
    public void moveFailedAction(final boolean ie, final int dirX,
            final int dirY, final ObjectInventory inv) {
        String fill = "";
        if (this.isLetterVowel()) {
            fill = "an";
        } else {
            fill = "a";
        }
        Messager.showMessage("You need " + fill + " " + this.letter + " plug");
        // Play move failed sound, if it's enabled
        if (Worldz.getApplication().getPrefsManager()
                .getSoundEnabled(PreferencesManager.SOUNDS_GAME)) {
            this.playMoveFailedSound();
        }
    }

    @Override
    public String getName() {
        return this.letter + " Port";
    }

    @Override
    public String getPluralName() {
        return this.letter + " Ports";
    }

    private boolean isLetterVowel() {
        if (this.letter == 'A' || this.letter == 'E' || this.letter == 'I'
                || this.letter == 'O' || this.letter == 'U') {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void setTypes() {
        this.type.set(TypeConstants.TYPE_LETTER_LOCK);
        this.type.set(TypeConstants.TYPE_INFINITE_LOCK);
        this.type.set(TypeConstants.TYPE_LOCK);
    }

    @Override
    public String getDescription() {
        String fill;
        if (this.isLetterVowel()) {
            fill = "an";
        } else {
            fill = "a";
        }
        return this.letter + " Ports require " + fill + " " + this.letter
                + " Plug to open.";
    }
}
