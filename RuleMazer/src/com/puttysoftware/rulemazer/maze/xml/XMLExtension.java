/*  RuleMazer: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: rulemazer@puttysoftware.com
 */
package com.puttysoftware.rulemazer.maze.xml;

public class XMLExtension {
    // Constants
    private static final String XML_MAZE_EXTENSION = "5dxm";
    private static final String XML_SAVED_GAME_EXTENSION = "5dxg";
    private static final String XML_SCORES_EXTENSION = "5dxs";
    private static final String XML_RULE_SET_EXTENSION = "5dxt";

    // Methods
    public static String getXMLMazeExtension() {
        return XMLExtension.XML_MAZE_EXTENSION;
    }

    public static String getXMLMazeExtensionWithPeriod() {
        return "." + XMLExtension.XML_MAZE_EXTENSION;
    }

    public static String getXMLGameExtension() {
        return XMLExtension.XML_SAVED_GAME_EXTENSION;
    }

    public static String getXMLGameExtensionWithPeriod() {
        return "." + XMLExtension.XML_SAVED_GAME_EXTENSION;
    }

    public static String getXMLScoresExtension() {
        return XMLExtension.XML_SCORES_EXTENSION;
    }

    public static String getXMLScoresExtensionWithPeriod() {
        return "." + XMLExtension.XML_SCORES_EXTENSION;
    }

    public static String getXMLRuleSetExtension() {
        return XMLExtension.XML_RULE_SET_EXTENSION;
    }

    public static String getXMLRuleSetExtensionWithPeriod() {
        return "." + XMLExtension.XML_RULE_SET_EXTENSION;
    }
}
