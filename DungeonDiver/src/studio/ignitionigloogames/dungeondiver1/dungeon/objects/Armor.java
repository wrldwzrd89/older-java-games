package studio.ignitionigloogames.dungeondiver1.dungeon.objects;

import studio.ignitionigloogames.dungeondiver1.DungeonDiver;

public class Armor extends GenericNSRSBObject {
    // Serialization
    private static final long serialVersionUID = -6346043340623L;

    // Constructors
    public Armor() {
        super(false, "Armor", 80);
    }

    @Override
    public void moveOntoHook() {
        DungeonDiver.getHoldingBag().getArmor().showShop();
    }
}
