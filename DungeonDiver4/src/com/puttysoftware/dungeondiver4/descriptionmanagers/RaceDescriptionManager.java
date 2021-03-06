/*  DungeonDiver4: An RPG
Copyright (C) 2011-2012 Eric Ahnell


Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver4.descriptionmanagers;

import com.puttysoftware.dungeondiver4.creatures.races.RaceConstants;
import com.puttysoftware.dungeondiver4.dungeon.Extension;
import com.puttysoftware.fileutils.ResourceStreamReader;

public class RaceDescriptionManager {
    public static String getRaceDescription(final int r) {
        final String name = RaceConstants.getRaceName(r).toLowerCase();
        try (final ResourceStreamReader rsr = new ResourceStreamReader(
                RaceDescriptionManager.class.getResourceAsStream(
                        "/com/puttysoftware/dungeondiver4/resources/descriptions/race/"
                                + name + Extension
                                        .getInternalDataExtensionWithPeriod()))) {
            // Fetch description
            final String desc = rsr.readString();
            return desc;
        } catch (final Exception e) {
            return null;
        }
    }
}
