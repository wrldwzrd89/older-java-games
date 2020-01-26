/*  DungeonDiver4: A Dungeon-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver4.resourcemanagers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.BufferUnderflowException;

import com.puttysoftware.dungeondiver4.DungeonDiver4;
import com.puttysoftware.dungeondiver4.dungeon.Dungeon;
import com.puttysoftware.micromod.MicroMod;
import com.puttysoftware.xio.DirectoryUtilities;

public class MusicManager {
    private static final String DEFAULT_LOAD_PATH = "/com/puttysoftware/dungeondiver4/resources/music/";
    private static String LOAD_PATH = MusicManager.DEFAULT_LOAD_PATH;
    private static Class<?> LOAD_CLASS = MusicManager.class;
    private static MicroMod CURRENT_MUSIC;

    private static MicroMod getMusic(final String filename) {
        try {
            final File modFile = new File(Dungeon.getDungeonTempFolder()
                    + File.separator + "MusicTemp" + File.separator
                    + filename.toLowerCase() + ".mod");
            if (!modFile.exists()) {
                File modParent = modFile.getParentFile();
                if (!modParent.exists()) {
                    boolean result = modParent.mkdirs();
                    if (!result) {
                        throw new IOException();
                    }
                }
                try (final InputStream is = MusicManager.LOAD_CLASS
                        .getResourceAsStream(MusicManager.LOAD_PATH
                                + filename.toLowerCase() + ".mod")) {
                    DirectoryUtilities.copyRAMFile(is, modFile);
                }
            }
            MicroMod mm = new MicroMod();
            mm.loadModule(modFile);
            return mm;
        } catch (final NullPointerException np) {
            return null;
        } catch (final IOException io) {
            return null;
        }
    }

    public static void playMusic(String musicName) {
        MusicManager.CURRENT_MUSIC = MusicManager.getMusic(musicName);
        if (MusicManager.CURRENT_MUSIC != null) {
            // Play the music
            MusicManager.CURRENT_MUSIC.playModule();
        }
    }

    public static void stopMusic() {
        if (MusicManager.CURRENT_MUSIC != null) {
            // Stop the music
            try {
                MusicManager.CURRENT_MUSIC.stopModule();
            } catch (BufferUnderflowException bue) {
                // Ignore
            } catch (NullPointerException np) {
                // Ignore
            } catch (Throwable t) {
                DungeonDiver4.getErrorLogger().logError(t);
            }
        }
    }

    public static boolean isMusicPlaying() {
        if (MusicManager.CURRENT_MUSIC != null) {
            if (MusicManager.CURRENT_MUSIC.isPlayThreadAlive()) {
                return true;
            }
        }
        return false;
    }
}