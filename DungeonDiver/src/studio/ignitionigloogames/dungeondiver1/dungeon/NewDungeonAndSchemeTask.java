package studio.ignitionigloogames.dungeondiver1.dungeon;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import studio.ignitionigloogames.dungeondiver1.DungeonDiver;
import studio.ignitionigloogames.dungeondiver1.dungeon.objects.Darkness;
import studio.ignitionigloogames.dungeondiver1.dungeon.objects.DungeonObjectList;
import studio.ignitionigloogames.dungeondiver1.dungeon.objects.Player;
import studio.ignitionigloogames.dungeondiver1.dungeon.objects.Tile;
import studio.ignitionigloogames.dungeondiver1.utilities.NDimensionalLocation;
import studio.ignitionigloogames.dungeondiver1.utilities.NDimensionalMap;

public class NewDungeonAndSchemeTask implements Runnable {
    @Override
    public void run() {
        try {
            final DungeonGUI gui = DungeonDiver.getHoldingBag().getDungeonGUI();
            final DungeonMap oldDungeon = gui.getDungeon();
            int visionState;
            if (oldDungeon != null) {
                visionState = oldDungeon.getDrawDistance();
            } else {
                visionState = gui.getDefaultDrawDistance();
            }
            final DungeonObjectList objectList = gui.getObjectList();
            final Dimension viewingWindow = gui.getViewingWindow();
            final Darkness dark = (Darkness) DungeonObjectList
                    .getSpecificObject("Darkness");
            final int size = gui.computeDungeonSize();
            final DungeonMap dungeon = new DungeonMap(size, size, viewingWindow,
                    dark, dark);
            final JFrame generatorFrame = gui.getGenerator();
            gui.hideDungeon();
            final Tile tile = (Tile) DungeonObjectList
                    .getSpecificObject("Tile");
            final Player player = (Player) DungeonObjectList
                    .getSpecificObject("Player");
            generatorFrame.setVisible(true);
            dungeon.fillMapRandomly(objectList, tile);
            NDimensionalLocation playerLocation;
            boolean found = dungeon.findObject(player);
            if (found) {
                gui.setPlayerLocation(dungeon.getFindResult());
                playerLocation = gui.getPlayerLocation();
                final Point newViewingWindow = new Point(
                        playerLocation
                                .getLocation(NDimensionalMap.ROW_DIMENSION)
                                - DungeonGUI.VIEWING_WINDOW_SIZE / 2,
                        playerLocation
                                .getLocation(NDimensionalMap.COLUMN_DIMENSION)
                                - DungeonGUI.VIEWING_WINDOW_SIZE / 2);
                dungeon.alterViewingWindow(newViewingWindow);
            } else {
                while (!found) {
                    dungeon.fillMapRandomly(objectList, tile);
                    found = dungeon.findObject(player);
                    if (found) {
                        gui.setPlayerLocation(dungeon.getFindResult());
                        playerLocation = gui.getPlayerLocation();
                        final Point newViewingWindow = new Point(
                                playerLocation.getLocation(
                                        NDimensionalMap.ROW_DIMENSION)
                                        - DungeonGUI.VIEWING_WINDOW_SIZE / 2,
                                playerLocation.getLocation(
                                        NDimensionalMap.COLUMN_DIMENSION)
                                        - DungeonGUI.VIEWING_WINDOW_SIZE / 2);
                        dungeon.alterViewingWindow(newViewingWindow);
                    }
                }
            }
            gui.setSavedMapObject(tile);
            SchemeList.setActiveScheme();
            dungeon.updateAllAppearances();
            gui.getObjectList();
            DungeonObjectList.updateAllAppearances();
            gui.getSavedMapObject().updateAppearance();
            dungeon.setDrawDistance(visionState);
            gui.setDungeon(dungeon);
            generatorFrame.setVisible(false);
            gui.showDungeonImmediately();
            gui.redrawDungeonBypassHook();
        } catch (final Throwable t) {
            DungeonDiver.debug(t);
        }
    }
}
