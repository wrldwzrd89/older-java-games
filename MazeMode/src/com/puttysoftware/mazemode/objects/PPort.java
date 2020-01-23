/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.generic.GenericPort;

public class PPort extends GenericPort {
    // Constructors
    public PPort() {
        super(new PPlug(), 'P');
    }
}