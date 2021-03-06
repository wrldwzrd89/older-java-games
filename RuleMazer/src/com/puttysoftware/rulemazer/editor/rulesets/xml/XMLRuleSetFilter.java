/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.editor.rulesets.xml;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import com.puttysoftware.rulemazer.maze.xml.XMLExtension;

public class XMLRuleSetFilter extends FileFilter {
    @Override
    public boolean accept(final File f) {
        if (f.isDirectory()) {
            return true;
        }
        final String extension = XMLRuleSetFilter.getExtension(f);
        if (extension != null) {
            if (extension.equals(XMLExtension.getXMLRuleSetExtension())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "RuleMazer XML Rule Sets ("
                + XMLExtension.getXMLRuleSetExtensionWithPeriod() + ")";
    }

    private static String getExtension(final File f) {
        String ext = null;
        final String s = f.getName();
        final int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
