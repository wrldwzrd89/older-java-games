/*  TallerTower: An RPG
Copyright (C) 2011-2012 Eric Ahnell


Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.tallertower.datamanagers;

import java.io.IOException;

import com.puttysoftware.tallertower.TallerTower;
import com.puttysoftware.tallertower.creatures.personalities.PersonalityConstants;
import com.puttysoftware.tallertower.maze.Extension;
import com.puttysoftware.xio.ResourceStreamReader;

public class PersonalityDataManager {
    public static double[] getPersonalityData(final int p) {
        final String name = PersonalityConstants.getPersonalityName(p)
                .toLowerCase();
        try (final ResourceStreamReader rsr = new ResourceStreamReader(
                PersonalityDataManager.class.getResourceAsStream(
                        "/com/puttysoftware/tallertower/resources/data/personality/"
                                + name + Extension
                                        .getInternalDataExtensionWithPeriod()))) {
            // Fetch data
            final int[] rawData = new int[PersonalityConstants.PERSONALITY_ATTRIBUTES_COUNT];
            for (int x = 0; x < rawData.length; x++) {
                rawData[x] = rsr.readInt();
            }
            // Parse raw data
            final double[] finalData = new double[rawData.length];
            for (int x = 0; x < rawData.length; x++) {
                if (x == PersonalityConstants.PERSONALITY_ATTRIBUTE_LEVEL_UP_SPEED) {
                    finalData[x] = PersonalityConstants
                            .getLookupTableEntry(-rawData[x]);
                } else {
                    finalData[x] = PersonalityConstants
                            .getLookupTableEntry(rawData[x]);
                }
            }
            return finalData;
        } catch (final IOException e) {
            TallerTower.getErrorLogger().logError(e);
            return null;
        }
    }
}
