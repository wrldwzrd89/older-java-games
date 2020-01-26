package com.puttysoftware.mazerunner2.game;

import com.puttysoftware.commondialogs.CommonDialogs;
import com.puttysoftware.mazerunner2.MazeRunnerII;
import com.puttysoftware.mazerunner2.creatures.party.PartyManager;
import com.puttysoftware.mazerunner2.creatures.party.PartyMember;
import com.puttysoftware.mazerunner2.maze.utilities.MazeObjectInventory;

public final class InventoryViewer {
    private InventoryViewer() {
        // Do nothing
    }

    public static void showEquipmentDialog() {
        String title;
        if (MazeRunnerII.inDebugMode()) {
            title = "Equipment (DEBUG)";
        } else {
            title = "Equipment";
        }
        PartyMember member = PartyManager.getParty().pickOnePartyMember();
        if (member != null) {
            String[] equipString = member.getItems()
                    .generateEquipmentStringArray();
            CommonDialogs.showInputDialog("Equipment", title, equipString,
                    equipString[0]);
        }
    }

    public static void showItemInventoryDialog() {
        String title;
        if (MazeRunnerII.inDebugMode()) {
            title = "Items (DEBUG)";
        } else {
            title = "Items";
        }
        PartyMember member = PartyManager.getParty().pickOnePartyMember();
        if (member != null) {
            String[] invString = member.getItems()
                    .generateInventoryStringArray();
            CommonDialogs.showInputDialog("Items", title, invString,
                    invString[0]);
        }
    }

    public static void showObjectInventoryDialog(MazeObjectInventory oi) {
        String[] invString = oi.generateInventoryStringArray();
        CommonDialogs.showInputDialog("Objects", "Objects", invString,
                invString[0]);
    }
}