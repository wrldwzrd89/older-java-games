/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: MasterMaze@worldwizard.net
 */
package com.puttysoftware.mastermaze.maze.objects;

import com.puttysoftware.mastermaze.maze.generic.GenericPort;
import com.puttysoftware.mastermaze.resourcemanagers.ObjectImageConstants;

public class CPort extends GenericPort {
    // Constructors
    public CPort() {
        super(new CPlug(), 'C');
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_C_PORT;
    }
}