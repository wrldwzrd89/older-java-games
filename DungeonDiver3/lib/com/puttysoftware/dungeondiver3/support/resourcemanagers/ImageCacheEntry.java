/*  DungeonDiver3: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.dungeondiver3.support.resourcemanagers;

import com.puttysoftware.images.BufferedImageIcon;

class ImageCacheEntry {
    // Fields
    private final BufferedImageIcon entry;
    private final String name;

    // Constructor
    ImageCacheEntry(BufferedImageIcon image, String n) {
        this.entry = image;
        this.name = n;
    }

    // Methods
    BufferedImageIcon getEntry() {
        return this.entry;
    }

    String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        return prime + ((this.name == null) ? 0 : this.name.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ImageCacheEntry)) {
            return false;
        }
        ImageCacheEntry other = (ImageCacheEntry) obj;
        if (this.name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
