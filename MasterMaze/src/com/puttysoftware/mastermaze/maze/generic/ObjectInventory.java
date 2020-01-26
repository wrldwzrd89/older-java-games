/*  MasterMaze: A Maze-Solving Game
Copyright (C) 2008-2012 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mastermaze.maze.generic;

import java.io.IOException;

import com.puttysoftware.mastermaze.MasterMaze;
import com.puttysoftware.mastermaze.maze.objects.Bow;
import com.puttysoftware.mastermaze.maze.objects.NormalAmulet;
import com.puttysoftware.mastermaze.maze.objects.RegularBoots;
import com.puttysoftware.xio.XDataReader;
import com.puttysoftware.xio.XDataWriter;

public final class ObjectInventory implements Cloneable {
    // Properties
    private final String[] nameList;
    private final int[][] typeList;
    private final String[] bowNameList;
    private final int[] contents;
    private final int[] uses;
    private final int[] bows;
    private final int[] bowUses;
    private GenericBoots boots;
    private GenericAmulet amulet;
    private static final GenericBoots DEFAULT_BOOTS = new RegularBoots();
    private static final GenericAmulet DEFAULT_AMULET = new NormalAmulet();

    // Constructors
    public ObjectInventory() {
        final MazeObjectList list = MasterMaze.getApplication().getObjects();
        this.nameList = list.getAllInventoryableNamesMinusSpecial();
        final MazeObject[] invobj = list
                .getAllInventoryableObjectsMinusSpecial();
        this.bowNameList = list.getAllBowNames();
        this.contents = new int[this.nameList.length];
        this.uses = new int[this.nameList.length];
        this.bows = new int[this.bowNameList.length];
        this.bowUses = new int[this.bowNameList.length];
        this.boots = ObjectInventory.DEFAULT_BOOTS;
        this.amulet = ObjectInventory.DEFAULT_AMULET;
        final int bowIndex = this.bowIndexOf(new Bow());
        this.bowUses[bowIndex] = -1;
        this.typeList = new int[this.nameList.length][];
        for (int x = 0; x < this.nameList.length; x++) {
            this.typeList[x] = invobj[x].getAllTypes();
        }
    }

    // Accessors
    public int getItemCount(final MazeObject mo) {
        if (ObjectInventory.isBoots(mo)) {
            return this.getBootsCount();
        } else if (ObjectInventory.isBow(mo)) {
            return this.getBowCount(mo);
        } else if (ObjectInventory.isAmulet(mo)) {
            return 1;
        } else {
            return this.getOtherCount(mo);
        }
    }

    public int getUses(final MazeObject mo) {
        if (ObjectInventory.isBoots(mo)) {
            return 0;
        } else if (ObjectInventory.isAmulet(mo)) {
            return 0;
        } else {
            if (ObjectInventory.isBow(mo)) {
                return this.getBowUses(mo);
            } else {
                return this.getOtherUses(mo);
            }
        }
    }

    private void setUses(final MazeObject mo, final int newUses) {
        if (!ObjectInventory.isBoots(mo) && !ObjectInventory.isAmulet(mo)) {
            if (!ObjectInventory.isBow(mo)) {
                this.setOtherUses(mo, newUses);
            } else {
                this.setBowUses(mo, newUses);
            }
        }
    }

    public void use(final MazeObject mo, final int x, final int y,
            final int z) {
        int tempUses = this.getUses(mo);
        if (mo.isUsable() && tempUses > 0) {
            tempUses--;
            this.setUses(mo, tempUses);
            mo.useHelper(x, y, z);
            if (tempUses == 0) {
                this.removeItem(mo);
            }
        }
    }

    public void useBow(final GenericBow bow) {
        int tempUses = this.getUses(bow);
        if (tempUses != -1) {
            if (tempUses > 0) {
                tempUses--;
                this.setUses(bow, tempUses);
                if (tempUses == 0) {
                    this.removeItem(bow);
                }
            }
        }
    }

    public boolean isItemThere(final MazeObject mo) {
        if (ObjectInventory.isBoots(mo)) {
            return this.areBootsThere(mo);
        } else if (ObjectInventory.isBow(mo)) {
            return this.isBowThere(mo);
        } else if (ObjectInventory.isAmulet(mo)) {
            return this.isAmuletThere(mo);
        } else {
            return this.isOtherThere(mo);
        }
    }

    public boolean isItemCategoryThere(final int cat) {
        final MazeObjectList list = MasterMaze.getApplication().getObjects();
        final MazeObject[] objects = list
                .getAllInventoryableObjectsMinusSpecial();
        for (int x = 0; x < objects.length; x++) {
            if (objects[x].isOfType(cat) && this.contents[x] > 0) {
                return true;
            }
        }
        return false;
    }

    // Transformers
    public void fireStepActions() {
        if (!this.boots.getName()
                .equals(ObjectInventory.DEFAULT_BOOTS.getName())) {
            this.boots.stepAction();
        }
        if (!this.amulet.getName()
                .equals(ObjectInventory.DEFAULT_AMULET.getName())) {
            this.amulet.stepAction();
        }
    }

    public void addItem(final MazeObject mo) {
        if (ObjectInventory.isBoots(mo)) {
            this.addBoots((GenericBoots) mo);
        } else if (ObjectInventory.isBow(mo)) {
            this.addBow(mo);
        } else if (ObjectInventory.isAmulet(mo)) {
            this.addAmulet((GenericAmulet) mo);
        } else {
            this.addOther(mo);
        }
    }

    public void removeItem(final MazeObject mo) {
        if (ObjectInventory.isBoots(mo)) {
            this.removeBoots();
        } else if (ObjectInventory.isBow(mo)) {
            this.removeBow(mo);
        } else if (ObjectInventory.isAmulet(mo)) {
            this.removeAmulet();
        } else {
            this.removeOther(mo);
        }
    }

    public void removeAllBoots() {
        this.removeBoots();
    }

    public String[] generateInventoryStringArray() {
        final String[] result = new String[this.contents.length
                + this.bows.length + 2];
        StringBuilder sb;
        for (int x = 0; x < this.contents.length; x++) {
            sb = new StringBuilder();
            sb.append("Slot ");
            sb.append(x + 1);
            sb.append(": ");
            sb.append(this.nameList[x]);
            sb.append(" (Qty: ");
            sb.append(this.contents[x]);
            sb.append(", Uses: ");
            sb.append(this.uses[x]);
            sb.append(")");
            result[x] = sb.toString();
        }
        for (int x = 0; x < this.bows.length; x++) {
            sb = new StringBuilder();
            sb.append("Slot ");
            sb.append(this.contents.length + x + 1);
            sb.append(": ");
            sb.append(this.bowNameList[x]);
            sb.append(" (Qty: ");
            sb.append(this.bows[x]);
            sb.append(", Uses: ");
            final int u = this.bowUses[x];
            String ux;
            if (u == -1) {
                ux = "Infinite";
            } else {
                ux = Integer.toString(u);
            }
            sb.append(ux);
            sb.append(")");
            result[x + this.contents.length] = sb.toString();
        }
        sb = new StringBuilder();
        sb.append("Slot ");
        sb.append(this.contents.length + this.bows.length + 1);
        sb.append(": ");
        sb.append(this.boots.getName());
        sb.append(" (Qty: 1, Uses: 0)");
        result[this.contents.length + this.bows.length] = sb.toString();
        sb = new StringBuilder();
        sb.append("Slot ");
        sb.append(this.contents.length + this.bows.length + 2);
        sb.append(": ");
        sb.append(this.amulet.getName());
        sb.append(" (Qty: 1, Uses: 0)");
        result[this.contents.length + this.bows.length + 1] = sb.toString();
        return result;
    }

    public String[] generateUseStringArray() {
        final MazeObjectList list = MasterMaze.getApplication().getObjects();
        final String[] names = list.getAllUsableNamesMinusSpecial();
        final int len = names.length;
        StringBuilder sb;
        final String[] result = new String[len];
        for (int x = 0; x < len; x++) {
            final int index = this.indexByName(names[x]);
            sb = new StringBuilder();
            sb.append(names[x]);
            sb.append(" (Qty: ");
            sb.append(this.contents[index]);
            sb.append(", Uses: ");
            sb.append(this.uses[index]);
            sb.append(")");
            result[x] = sb.toString();
        }
        return result;
    }

    public String[] generateBowStringArray() {
        final MazeObjectList list = MasterMaze.getApplication().getObjects();
        final String[] names = list.getAllBowNames();
        final int len = names.length;
        StringBuilder sb;
        final String[] result = new String[len];
        for (int x = 0; x < len; x++) {
            final int index = this.bowIndexByName(names[x]);
            sb = new StringBuilder();
            sb.append(names[x]);
            sb.append(" (Qty: ");
            sb.append(this.bows[index]);
            sb.append(", Uses: ");
            String u;
            if (this.bowUses[index] == -1) {
                u = "Infinite";
            } else {
                u = Integer.toString(this.bowUses[index]);
            }
            sb.append(u);
            sb.append(")");
            result[x] = sb.toString();
        }
        return result;
    }

    // Helper methods
    private int getBootsCount() {
        if (!this.boots.equals(ObjectInventory.DEFAULT_BOOTS)) {
            return 1;
        } else {
            return 0;
        }
    }

    private int getBowCount(final MazeObject mo) {
        final int loc = this.bowIndexOf(mo);
        return this.bows[loc];
    }

    private int getOtherCount(final MazeObject mo) {
        final int loc = this.indexOf(mo);
        return this.contents[loc];
    }

    private int getBowUses(final MazeObject mo) {
        final int loc = this.bowIndexOf(mo);
        return this.bowUses[loc];
    }

    private int getOtherUses(final MazeObject mo) {
        final int loc = this.indexOf(mo);
        return this.uses[loc];
    }

    private void setBowUses(final MazeObject mo, final int newUses) {
        final int loc = this.bowIndexOf(mo);
        this.bowUses[loc] = newUses;
    }

    private void setOtherUses(final MazeObject mo, final int newUses) {
        final int loc = this.indexOf(mo);
        this.uses[loc] = newUses;
    }

    private boolean isBowThere(final MazeObject mo) {
        final int loc = this.bowIndexOf(mo);
        if (loc != -1) {
            return this.bows[loc] != 0;
        } else {
            return false;
        }
    }

    private boolean isOtherThere(final MazeObject mo) {
        final int loc = this.indexOf(mo);
        if (loc != -1) {
            return this.contents[loc] != 0;
        } else {
            return false;
        }
    }

    private boolean isAmuletThere(final MazeObject mo) {
        return this.amulet.getName().equals(mo.getName());
    }

    private boolean areBootsThere(final MazeObject mo) {
        return this.boots.getName().equals(mo.getName());
    }

    private void addAmulet(final GenericAmulet mo) {
        this.amulet = mo;
    }

    private void addBoots(final GenericBoots mo) {
        this.boots = mo;
    }

    private void addBow(final MazeObject mo) {
        final int loc = this.bowIndexOf(mo);
        this.bows[loc]++;
        this.bowUses[loc] = mo.getUses();
    }

    private void addOther(final MazeObject mo) {
        final int loc = this.indexOf(mo);
        this.contents[loc]++;
        this.uses[loc] = mo.getUses();
    }

    private void removeAmulet() {
        this.amulet = ObjectInventory.DEFAULT_AMULET;
    }

    private void removeBoots() {
        this.boots = ObjectInventory.DEFAULT_BOOTS;
    }

    private void removeBow(final MazeObject mo) {
        final int loc = this.bowIndexOf(mo);
        if (this.bows[loc] != 0) {
            this.bows[loc]--;
            if (this.bows[loc] > 0) {
                this.bowUses[loc] = mo.getUses();
            } else {
                this.bowUses[loc] = 0;
            }
        }
    }

    private void removeOther(final MazeObject mo) {
        final int loc = this.indexOf(mo);
        if (this.contents[loc] != 0) {
            this.contents[loc]--;
            if (this.contents[loc] > 0) {
                this.uses[loc] = mo.getUses();
            } else {
                this.uses[loc] = 0;
            }
        }
    }

    private static boolean isBoots(final MazeObject mo) {
        return mo.isOfType(TypeConstants.TYPE_BOOTS);
    }

    private static boolean isBow(final MazeObject mo) {
        return mo.isOfType(TypeConstants.TYPE_BOW);
    }

    private static boolean isAmulet(final MazeObject mo) {
        return mo.isOfType(TypeConstants.TYPE_AMULET);
    }

    private int bowIndexOf(final MazeObject mo) {
        int x;
        for (x = 0; x < this.bows.length; x++) {
            if (mo.getName().equals(this.bowNameList[x])) {
                return x;
            }
        }
        return -1;
    }

    private int indexOf(final MazeObject mo) {
        int x;
        for (x = 0; x < this.contents.length; x++) {
            if (mo.getName().equals(this.nameList[x])) {
                return x;
            }
        }
        return -1;
    }

    private int bowIndexByName(final String name) {
        int x;
        for (x = 0; x < this.bows.length; x++) {
            if (name.equals(this.bowNameList[x])) {
                return x;
            }
        }
        return -1;
    }

    private int indexByName(final String name) {
        int x;
        for (x = 0; x < this.contents.length; x++) {
            if (name.equals(this.nameList[x])) {
                return x;
            }
        }
        return -1;
    }

    @Override
    public ObjectInventory clone() {
        final ObjectInventory clone = new ObjectInventory();
        for (int x = 0; x < this.contents.length; x++) {
            clone.contents[x] = this.contents[x];
        }
        for (int x = 0; x < this.contents.length; x++) {
            clone.uses[x] = this.uses[x];
        }
        for (int x = 0; x < this.bows.length; x++) {
            clone.bows[x] = this.bows[x];
        }
        for (int x = 0; x < this.bows.length; x++) {
            clone.bowUses[x] = this.bowUses[x];
        }
        clone.boots = this.boots;
        clone.amulet = this.amulet;
        return clone;
    }

    public static ObjectInventory readInventory(final XDataReader reader,
            final int formatVersion) throws IOException {
        final MazeObjectList objects = MasterMaze.getApplication().getObjects();
        final ObjectInventory i = new ObjectInventory();
        i.boots = (GenericBoots) objects.readMazeObject(reader, formatVersion);
        if (i.boots == null) {
            i.boots = ObjectInventory.DEFAULT_BOOTS;
        }
        i.amulet = (GenericAmulet) objects.readMazeObject(reader,
                formatVersion);
        if (i.amulet == null) {
            i.amulet = ObjectInventory.DEFAULT_AMULET;
        }
        for (int x = 0; x < i.contents.length; x++) {
            i.contents[x] = reader.readInt();
        }
        for (int x = 0; x < i.contents.length; x++) {
            i.uses[x] = reader.readInt();
        }
        for (int x = 0; x < i.bows.length; x++) {
            i.bows[x] = reader.readInt();
        }
        for (int x = 0; x < i.bows.length; x++) {
            i.bowUses[x] = reader.readInt();
        }
        return i;
    }

    public void writeInventory(final XDataWriter writer) throws IOException {
        this.boots.writeMazeObject(writer);
        this.amulet.writeMazeObject(writer);
        for (final int content : this.contents) {
            writer.writeInt(content);
        }
        for (int x = 0; x < this.contents.length; x++) {
            writer.writeInt(this.uses[x]);
        }
        for (final int bow : this.bows) {
            writer.writeInt(bow);
        }
        for (int x = 0; x < this.bows.length; x++) {
            writer.writeInt(this.bowUses[x]);
        }
    }

    public void removeAllItemsOfType(final int type) {
        for (int x = 0; x < this.typeList.length; x++) {
            for (int y = 0; y < this.typeList[x].length; y++) {
                if (this.typeList[x][y] == type) {
                    this.contents[x] = 0;
                }
            }
        }
    }
}
