/*  Mazer5D: A Maze-Solving Game
Copyright (C) 2008-2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazer5d.objects;

import com.puttysoftware.mazer5d.generic.GenericPort;

public class GPort extends GenericPort {
    // Constructors
    public GPort() {
        super(new GPlug(), 'G');
    }
}