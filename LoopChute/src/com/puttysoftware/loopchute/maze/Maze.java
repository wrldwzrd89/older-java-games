/*  loopchute: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.loopchute.maze;

import java.io.File;
import java.io.IOException;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.fileutils.FileUtilities;
import com.puttysoftware.loopchute.LoopChute;
import com.puttysoftware.loopchute.generic.MazeObject;
import com.puttysoftware.loopchute.objects.Empty;
import com.puttysoftware.loopchute.prefs.PreferencesManager;
import com.puttysoftware.randomrange.RandomLongRange;
import com.puttysoftware.xio.XDataReader;
import com.puttysoftware.xio.XDataWriter;

public class Maze implements MazeConstants {
    // Properties
    private LayeredTower mazeData;
    private LayeredTower clipboard;
    private int startW;
    private int locW;
    private int saveW;
    private int levelCount;
    private int activeLevel;
    private String mazeTitle;
    private String mazeStartMessage;
    private String mazeEndMessage;
    private String basePath;
    private PrefixIO xmlPrefixHandler;
    private SuffixIO xmlSuffixHandler;
    private final int[] savedStart;
    private static final int MIN_LEVELS = 1;
    private static final int MAX_LEVELS = Integer.MAX_VALUE;

    // Constructors
    public Maze() {
        this.mazeData = null;
        this.clipboard = null;
        this.levelCount = 0;
        this.startW = 0;
        this.locW = 0;
        this.saveW = 0;
        this.activeLevel = 0;
        this.xmlPrefixHandler = null;
        this.xmlSuffixHandler = null;
        this.mazeTitle = "Untitled Maze";
        this.mazeStartMessage = "Let's Solve The Maze!";
        this.mazeEndMessage = "Maze Solved!";
        this.savedStart = new int[4];
        final long random = RandomLongRange.generateRaw();
        final String randomID = Long.toHexString(random);
        this.basePath = System.getProperty("java.io.tmpdir") + File.separator
                + "loopchute" + File.separator + randomID + ".maze";
        final File base = new File(this.basePath);
        final boolean success = base.mkdirs();
        if (!success) {
            CommonDialogs.showErrorDialog(
                    "Maze temporary folder creation failed!", "loopchute");
        }
    }

    // Static methods
    public static String getMazeTempFolder() {
        return System.getProperty("java.io.tmpdir") + File.separator
                + "loopchute";
    }

    public static int getMinLevels() {
        return Maze.MIN_LEVELS;
    }

    public static int getMaxLevels() {
        return Maze.MAX_LEVELS;
    }

    public static int getMinFloors() {
        return LayeredTower.getMinFloors();
    }

    public static int getMaxFloors() {
        return LayeredTower.getMaxFloors();
    }

    public static int getMinColumns() {
        return LayeredTower.getMinColumns();
    }

    public static int getMaxColumns() {
        return LayeredTower.getMaxColumns();
    }

    public static int getMinRows() {
        return LayeredTower.getMinRows();
    }

    public static int getMaxRows() {
        return LayeredTower.getMaxRows();
    }

    // Methods
    public String getBasePath() {
        return this.basePath;
    }

    public void setPrefixHandler(final PrefixIO xph) {
        this.xmlPrefixHandler = xph;
    }

    public void setSuffixHandler(final SuffixIO xsh) {
        this.xmlSuffixHandler = xsh;
    }

    public String getMazeTitle() {
        return this.mazeTitle;
    }

    public void setMazeTitle(final String title) {
        if (title == null) {
            throw new NullPointerException("Title cannot be null!");
        }
        this.mazeTitle = title;
    }

    public String getMazeStartMessage() {
        return this.mazeStartMessage;
    }

    public void setMazeStartMessage(final String msg) {
        if (msg == null) {
            throw new NullPointerException("Message cannot be null!");
        }
        this.mazeStartMessage = msg;
    }

    public String getMazeEndMessage() {
        return this.mazeEndMessage;
    }

    public void setMazeEndMessage(final String msg) {
        if (msg == null) {
            throw new NullPointerException("Message cannot be null!");
        }
        this.mazeEndMessage = msg;
    }

    public String getLevelTitle() {
        return this.mazeData.getLevelTitle();
    }

    public void setLevelTitle(final String title) {
        this.mazeData.setLevelTitle(title);
    }

    public String getLevelStartMessage() {
        return this.mazeData.getLevelStartMessage();
    }

    public void setLevelStartMessage(final String msg) {
        this.mazeData.setLevelStartMessage(msg);
    }

    public String getLevelEndMessage() {
        return this.mazeData.getLevelEndMessage();
    }

    public void setLevelEndMessage(final String msg) {
        this.mazeData.setLevelEndMessage(msg);
    }

    public int getExploreRadius() {
        return this.mazeData.getExploreRadius();
    }

    public void setExploreRadius(final int newER) {
        this.mazeData.setExploreRadius(newER);
    }

    public int getVisionMode() {
        return this.mazeData.getVisionMode();
    }

    public void setVisionMode(final int newVM) {
        this.mazeData.setVisionMode(newVM);
    }

    public void resetVisibleSquares() {
        this.mazeData.resetVisibleSquares();
    }

    public void updateVisibleSquares(final int xp, final int yp, final int zp) {
        this.mazeData.updateVisibleSquares(xp, yp, zp);
    }

    public int getAutoFinishThreshold() {
        return this.mazeData.getAutoFinishThreshold();
    }

    public int getAlternateAutoFinishThreshold() {
        return this.mazeData.getAlternateAutoFinishThreshold();
    }

    public int getNextLevel() {
        return this.mazeData.getNextLevel();
    }

    public boolean useOffset() {
        return this.mazeData.useOffset();
    }

    public void setUseOffset(final boolean uo) {
        this.mazeData.setUseOffset(uo);
    }

    public void setNextLevel(final int nl) {
        this.mazeData.setNextLevel(nl);
    }

    public void setNextLevelOffset(final int nlo) {
        this.mazeData.setNextLevelOffset(nlo);
    }

    public int getAlternateNextLevel() {
        return this.mazeData.getAlternateNextLevel();
    }

    public boolean useAlternateOffset() {
        return this.mazeData.useAlternateOffset();
    }

    public void setUseAlternateOffset(final boolean uao) {
        this.mazeData.setUseAlternateOffset(uao);
    }

    public void setAlternateNextLevel(final int anl) {
        this.mazeData.setAlternateNextLevel(anl);
    }

    public void setAlternateNextLevelOffset(final int anlo) {
        this.mazeData.setAlternateNextLevelOffset(anlo);
    }

    public int getActiveLevelNumber() {
        return this.activeLevel;
    }

    public void switchLevel(final int level) {
        this.switchLevelInternal(level);
    }

    public void switchLevelOffset(final int level) {
        this.switchLevelInternal(this.activeLevel + level);
    }

    private void switchLevelInternal(final int level) {
        if (this.activeLevel != level) {
            if (this.mazeData != null) {
                try {
                    // Save old level
                    final XDataWriter writer = this.getLevelWriter();
                    this.writeMazeLevel(writer);
                    writer.close();
                } catch (final IOException io) {
                    // Ignore
                }
            }
            this.activeLevel = level;
            try {
                // Load new level
                final XDataReader reader = this.getLevelReader();
                this.readMazeLevel(reader);
                reader.close();
            } catch (final IOException io) {
                // Ignore
            }
        }
    }

    public boolean doesLevelExist(final int level) {
        return level < this.levelCount && level >= 0;
    }

    public boolean doesLevelExistOffset(final int level) {
        return this.activeLevel + level < this.levelCount
                && this.activeLevel + level >= 0;
    }

    public void cutLevel() {
        if (this.levelCount > 1) {
            this.clipboard = this.mazeData;
            this.removeLevel();
        }
    }

    public void copyLevel() {
        this.clipboard = this.mazeData.clone();
    }

    public void pasteLevel() {
        if (this.clipboard != null) {
            this.mazeData = this.clipboard.clone();
            LoopChute.getApplication().getMazeManager().setDirty(true);
        }
    }

    public boolean isPasteBlocked() {
        return this.clipboard == null;
    }

    public boolean isCutBlocked() {
        return this.levelCount <= 1;
    }

    public boolean insertLevelFromClipboard() {
        if (this.levelCount < Maze.MAX_LEVELS) {
            this.mazeData = this.clipboard.clone();
            this.levelCount++;
            return true;
        } else {
            return false;
        }
    }

    public boolean addLevel(final int rows, final int cols, final int floors) {
        if (this.levelCount < Maze.MAX_LEVELS) {
            if (this.mazeData != null) {
                try {
                    // Save old level
                    final XDataWriter writer = this.getLevelWriter();
                    this.writeMazeLevel(writer);
                    writer.close();
                } catch (final IOException io) {
                    // Ignore
                }
            }
            this.mazeData = new LayeredTower(rows, cols, floors);
            this.levelCount++;
            this.activeLevel = this.levelCount - 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeLevel() {
        if (this.levelCount > 1) {
            if (this.activeLevel >= 1 && this.activeLevel <= this.levelCount) {
                this.mazeData = null;
                // Delete file corresponding to current level
                final boolean delSuccess = this.getLevelFile(this.activeLevel)
                        .delete();
                if (!delSuccess) {
                    return false;
                }
                // Shift all higher-numbered levels down
                for (int x = this.activeLevel; x < this.levelCount - 1; x++) {
                    final File sourceLocation = this.getLevelFile(x + 1);
                    final File targetLocation = this.getLevelFile(x);
                    try {
                        FileUtilities.moveFile(sourceLocation,
                                targetLocation);
                    } catch (final IOException io) {
                        // Ignore
                    }
                }
                // Load level 1
                this.switchLevel(0);
                this.levelCount--;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void tickTimers(final int floor) {
        this.mazeData.tickTimers(floor);
    }

    public MazeObject getCell(final int row, final int col, final int floor,
            final int extra) {
        return this.mazeData.getCell(row, col, floor, extra);
    }

    public int getStartRow() {
        return this.mazeData.getStartRow();
    }

    public int getStartColumn() {
        return this.mazeData.getStartColumn();
    }

    public int getPlayerLocationX() {
        return this.mazeData.getPlayerRow();
    }

    public int getPlayerLocationY() {
        return this.mazeData.getPlayerColumn();
    }

    public int getPlayerLocationZ() {
        return this.mazeData.getPlayerFloor();
    }

    public int getPlayerLocationW() {
        return this.locW;
    }

    public int getStartLevel() {
        return this.startW;
    }

    public int getRows() {
        return this.mazeData.getRows();
    }

    public int getColumns() {
        return this.mazeData.getColumns();
    }

    public int getFloors() {
        return this.mazeData.getFloors();
    }

    public int getLevels() {
        return this.levelCount;
    }

    public boolean doesPlayerExist() {
        return this.mazeData.doesPlayerExist();
    }

    public void findStart() {
        this.mazeData.findStart();
    }

    public void findAllObjectPairsAndSwap(final MazeObject o1,
            final MazeObject o2) {
        this.mazeData.findAllObjectPairsAndSwap(o1, o2);
    }

    public void resize(final int x, final int y, final int z) {
        this.mazeData.resize(x, y, z);
    }

    public boolean isSquareVisible(final int x1, final int y1, final int x2,
            final int y2) {
        return this.mazeData.isSquareVisible(x1, y1, x2, y2);
    }

    public void setCell(final MazeObject mo, final int row, final int col,
            final int floor, final int extra) {
        this.mazeData.setCell(mo, row, col, floor, extra);
    }

    public void setStartRow(final int newStartRow) {
        this.mazeData.setStartRow(newStartRow);
    }

    public void setStartColumn(final int newStartColumn) {
        this.mazeData.setStartColumn(newStartColumn);
    }

    public void setStartFloor(final int newStartFloor) {
        this.mazeData.setStartFloor(newStartFloor);
    }

    public void setStartLevel(final int newStartLevel) {
        this.startW = newStartLevel;
    }

    public void savePlayerLocation() {
        this.saveW = this.locW;
        this.mazeData.savePlayerLocation();
    }

    public void restorePlayerLocation() {
        this.locW = this.saveW;
        this.mazeData.restorePlayerLocation();
    }

    public void setPlayerToStart() {
        this.mazeData.setPlayerToStart();
    }

    public void setPlayerLocationX(final int newPlayerRow) {
        this.mazeData.setPlayerRow(newPlayerRow);
    }

    public void setPlayerLocationY(final int newPlayerColumn) {
        this.mazeData.setPlayerColumn(newPlayerColumn);
    }

    public void setPlayerLocationZ(final int newPlayerFloor) {
        this.mazeData.setPlayerFloor(newPlayerFloor);
    }

    public void setPlayerLocationW(final int newPlayerLevel) {
        this.locW = newPlayerLevel;
    }

    public void offsetPlayerLocationX(final int newPlayerRow) {
        this.mazeData.offsetPlayerRow(newPlayerRow);
    }

    public void offsetPlayerLocationY(final int newPlayerColumn) {
        this.mazeData.offsetPlayerColumn(newPlayerColumn);
    }

    public void updateThresholds() {
        this.mazeData.updateThresholds();
    }

    public void fill() {
        final MazeObject bottom = PreferencesManager.getEditorDefaultFill();
        final MazeObject top = new Empty();
        this.mazeData.fill(bottom, top);
    }

    public void fillFloor(final int floor) {
        final MazeObject bottom = PreferencesManager.getEditorDefaultFill();
        final MazeObject top = new Empty();
        this.mazeData.fillFloor(bottom, top, floor);
    }

    public void fillLevel(final MazeObject bottom, final MazeObject top) {
        this.mazeData.fill(bottom, top);
    }

    public void fillLevelRandomly() {
        this.mazeData.fillRandomly(this, this.activeLevel);
    }

    public void fillFloorRandomly(final int z) {
        this.mazeData.fillFloorRandomly(this, z, this.activeLevel);
    }

    public void fillLevelRandomlyCustom() {
        this.mazeData.fillRandomlyCustom(this, this.activeLevel);
    }

    public void fillFloorRandomlyCustom(final int z) {
        this.mazeData.fillFloorRandomlyCustom(this, z, this.activeLevel);
    }

    public void fillLayer(final int layer) {
        final MazeObject fillWith = PreferencesManager
                .getEditorDefaultFill(layer);
        this.mazeData.fillLayer(fillWith, layer);
    }

    public void fillFloorAndLayer(final int floor, final int layer) {
        final MazeObject fillWith = PreferencesManager
                .getEditorDefaultFill(layer);
        this.mazeData.fillFloorAndLayer(fillWith, floor, layer);
    }

    public void fillLevelAndLayerRandomly(final int layer) {
        this.mazeData.fillLayerRandomly(this, this.activeLevel, layer);
    }

    public void fillFloorAndLayerRandomly(final int z, final int layer) {
        this.mazeData.fillFloorAndLayerRandomly(this, z, this.activeLevel,
                layer);
    }

    public void fillLevelAndLayerRandomlyCustom(final int layer) {
        this.mazeData.fillLayerRandomlyCustom(this, this.activeLevel, layer);
    }

    public void fillFloorAndLayerRandomlyCustom(final int z, final int layer) {
        this.mazeData.fillFloorAndLayerRandomlyCustom(this, z, this.activeLevel,
                layer);
    }

    public void save() {
        this.mazeData.save();
    }

    public void restore() {
        this.mazeData.restore();
    }

    public void saveStart() {
        this.savedStart[0] = this.startW;
        this.savedStart[1] = this.mazeData.getStartRow();
        this.savedStart[2] = this.mazeData.getStartColumn();
        this.savedStart[3] = this.mazeData.getStartFloor();
    }

    public void restoreStart() {
        this.startW = this.savedStart[0];
        this.mazeData.setStartRow(this.savedStart[1]);
        this.mazeData.setStartColumn(this.savedStart[2]);
        this.mazeData.setStartFloor(this.savedStart[3]);
    }

    public void hotGround(final int x, final int y, final int z) {
        this.mazeData.hotGround(x, y, z);
    }

    public void enableHorizontalWraparound() {
        this.mazeData.enableHorizontalWraparound();
    }

    public void disableHorizontalWraparound() {
        this.mazeData.disableHorizontalWraparound();
    }

    public void enableVerticalWraparound() {
        this.mazeData.enableVerticalWraparound();
    }

    public void disableVerticalWraparound() {
        this.mazeData.disableVerticalWraparound();
    }

    public void enable3rdDimensionWraparound() {
        this.mazeData.enable3rdDimensionWraparound();
    }

    public void disable3rdDimensionWraparound() {
        this.mazeData.disable3rdDimensionWraparound();
    }

    public boolean isHorizontalWraparoundEnabled() {
        return this.mazeData.isHorizontalWraparoundEnabled();
    }

    public boolean isVerticalWraparoundEnabled() {
        return this.mazeData.isVerticalWraparoundEnabled();
    }

    public boolean is3rdDimensionWraparoundEnabled() {
        return this.mazeData.is3rdDimensionWraparoundEnabled();
    }

    public Maze readMaze() throws IOException {
        final Maze m = new Maze();
        // Attach handlers
        m.setPrefixHandler(this.xmlPrefixHandler);
        m.setSuffixHandler(this.xmlSuffixHandler);
        // Make base paths the same
        m.basePath = this.basePath;
        // Create metafile reader
        final XDataReader metaReader = new XDataReader(
                m.basePath + File.separator + "metafile.xml", "maze");
        // Read metafile
        final int version = m.readMazeMetafile(metaReader);
        metaReader.close();
        // Create data reader
        final XDataReader dataReader = m.getLevelReader();
        // Read data
        m.readMazeLevel(dataReader, version);
        // Close reader
        dataReader.close();
        return m;
    }

    private XDataReader getLevelReader() throws IOException {
        return new XDataReader(this.basePath + File.separator + "level"
                + this.activeLevel + ".xml", "level");
    }

    private int readMazeMetafile(final XDataReader reader) throws IOException {
        int ver = FormatConstants.MAZE_FORMAT_1;
        if (this.xmlPrefixHandler != null) {
            ver = this.xmlPrefixHandler.readPrefix(reader);
        }
        final int levels = reader.readInt();
        this.levelCount = levels;
        this.startW = reader.readInt();
        this.mazeTitle = reader.readString();
        this.mazeStartMessage = reader.readString();
        this.mazeEndMessage = reader.readString();
        if (this.xmlSuffixHandler != null) {
            this.xmlSuffixHandler.readSuffix(reader, ver);
        }
        return ver;
    }

    private void readMazeLevel(final XDataReader reader) throws IOException {
        this.readMazeLevel(reader, FormatConstants.MAZE_FORMAT_LATEST);
    }

    private void readMazeLevel(final XDataReader reader,
            final int formatVersion) throws IOException {
        if (formatVersion == FormatConstants.MAZE_FORMAT_1
                || formatVersion == FormatConstants.MAZE_FORMAT_2
                || formatVersion == FormatConstants.MAZE_FORMAT_3) {
            this.mazeData = LayeredTower.readLayeredTower(reader,
                    formatVersion);
            this.mazeData.readSavedTowerState(reader, formatVersion);
        } else {
            throw new IOException("Unknown maze format version!");
        }
    }

    private File getLevelFile(final int level) {
        return new File(this.basePath + File.separator + level + ".level");
    }

    public void writeMaze() throws IOException {
        // Create metafile writer
        final XDataWriter metaWriter = new XDataWriter(
                this.basePath + File.separator + "metafile.xml", "maze");
        // Write metafile
        this.writeMazeMetafile(metaWriter);
        // Close writer
        metaWriter.close();
        // Create data writer
        final XDataWriter dataWriter = this.getLevelWriter();
        // Write data
        this.writeMazeLevel(dataWriter);
        // Close writer
        dataWriter.close();
    }

    private XDataWriter getLevelWriter() throws IOException {
        return new XDataWriter(this.basePath + File.separator + "level"
                + this.activeLevel + ".xml", "level");
    }

    private void writeMazeMetafile(final XDataWriter writer)
            throws IOException {
        if (this.xmlPrefixHandler != null) {
            this.xmlPrefixHandler.writePrefix(writer);
        }
        writer.writeInt(this.levelCount);
        writer.writeInt(this.startW);
        writer.writeString(this.mazeTitle);
        writer.writeString(this.mazeStartMessage);
        writer.writeString(this.mazeEndMessage);
        if (this.xmlSuffixHandler != null) {
            this.xmlSuffixHandler.writeSuffix(writer);
        }
    }

    private void writeMazeLevel(final XDataWriter writer) throws IOException {
        // Write the level
        this.mazeData.writeLayeredTower(writer);
        this.mazeData.writeSavedTowerState(writer);
    }
}
