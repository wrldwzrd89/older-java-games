/*  MazeRunnerII: An RPG
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazerunner2.scenario;

public class ScenarioManager {
    // Fields
    private NamesFileManager nfMgr;

    // Constructors
    public ScenarioManager() {
        this.nfMgr = new NamesFileManager();
    }

    // Methods
    public final NamesFileManager getNamesFileManager() {
        return this.nfMgr;
    }
}