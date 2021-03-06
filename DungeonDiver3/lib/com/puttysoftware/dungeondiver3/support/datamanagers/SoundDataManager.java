/*  DungeonDiver3: An RPG
Copyright (C) 2011-2012 Eric Ahnell


Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver3.support.datamanagers;

import java.util.ArrayList;

import com.puttysoftware.fileutils.ResourceStreamReader;

public class SoundDataManager {
    public static String[] getSoundData() {
        try {
            // Fetch data
            final ResourceStreamReader rsr = new ResourceStreamReader(
                    SoundDataManager.class.getResourceAsStream(
                            "/com/puttysoftware/dungeondiver3/support/resources/data/sound/catalog.txt"));
            final ArrayList<String> data = new ArrayList<>();
            String raw = "0";
            while (raw != null) {
                raw = rsr.readString();
                data.add(raw);
            }
            rsr.close();
            final Object[] arr = data.toArray();
            final String[] tempres = new String[arr.length];
            int count = 0;
            for (int x = 0; x < arr.length; x++) {
                if (arr[x] != null) {
                    tempres[x] = arr[x].toString();
                    count++;
                }
            }
            final String[] res = new String[count];
            count = 0;
            for (final String tempre : tempres) {
                if (tempre != null) {
                    res[count] = tempre;
                    count++;
                }
            }
            return res;
        } catch (final Exception e) {
            return null;
        }
    }
}
