/*  MazeRunnerII: A Names Editor
Copyright (C) 2011-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazerunner2.scenario;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.mazerunner2.MazeRunnerII;
import com.puttysoftware.mazerunner2.names.NamesConstants;
import com.puttysoftware.mazerunner2.names.NamesManager;

class NamesSaveTask extends Thread {
    // Fields
    private final String filename;
    private final String[] namesData;

    // Constructors
    NamesSaveTask(final String file, final String[] data) {
        this.filename = file;
        this.namesData = data;
        this.setName("Names File Writer");
    }

    @Override
    public void run() {
        final String sg = "Names";
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(this.filename))) {
            // Write version
            bw.write(Integer.toString(NamesConstants.NAMES_VERSION) + "\n");
            for (int x = 0; x < this.namesData.length; x++) {
                bw.write(this.namesData[x]);
                if (x < this.namesData.length - 1) {
                    bw.write("\n");
                }
            }
            NamesManager.invalidateNamesCache();
        } catch (final FileNotFoundException fnfe) {
            CommonDialogs.showDialog("Writing the " + sg.toLowerCase()
                    + " file failed, probably due to illegal characters in the file name.");
        } catch (final Exception ex) {
            MazeRunnerII.getErrorLogger().logError(ex);
        }
    }
}
