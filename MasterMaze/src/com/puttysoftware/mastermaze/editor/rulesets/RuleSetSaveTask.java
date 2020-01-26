/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mastermaze.editor.rulesets;

import java.io.FileNotFoundException;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.mastermaze.Application;
import com.puttysoftware.mastermaze.MasterMaze;
import com.puttysoftware.mastermaze.maze.Extension;
import com.puttysoftware.xio.XDataWriter;

public class RuleSetSaveTask extends Thread {
    // Fields
    private String filename;

    // Constructors
    public RuleSetSaveTask(final String file) {
        this.filename = file;
        this.setName("Rule Set File Writer");
    }

    @Override
    public void run() {
        final Application app = MasterMaze.getApplication();
        final String sg = "Rule Set";
        // filename check
        final boolean hasExtension = RuleSetSaveTask
                .hasExtension(this.filename);
        if (!hasExtension) {
            this.filename += Extension.getRuleSetExtensionWithPeriod();
        }
        try {
            final XDataWriter ruleSetFile = new XDataWriter(this.filename,
                    "ruleset");
            ruleSetFile.writeInt(RuleSetConstants.MAGIC_NUMBER_2);
            app.getObjects().writeRuleSet(ruleSetFile);
            ruleSetFile.close();
            CommonDialogs.showTitledDialog(sg + " file saved.",
                    "Rule Set Picker");
        } catch (final FileNotFoundException fnfe) {
            CommonDialogs.showDialog("Saving the " + sg.toLowerCase()
                    + " file failed, probably due to illegal characters in the file name.");
        } catch (final Exception ex) {
            MasterMaze.getErrorLogger().logError(ex);
        }
    }

    private static boolean hasExtension(final String s) {
        String ext = null;
        final int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        if (ext == null) {
            return false;
        } else {
            return true;
        }
    }
}
