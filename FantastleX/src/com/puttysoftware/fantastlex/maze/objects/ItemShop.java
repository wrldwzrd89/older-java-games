/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell


Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.items.ShopTypes;
import com.puttysoftware.fantastlex.maze.abc.AbstractShop;
import com.puttysoftware.fantastlex.resourcemanagers.ObjectImageConstants;

public class ItemShop extends AbstractShop {
    // Constructors
    public ItemShop() {
        super(ShopTypes.SHOP_TYPE_ITEMS);
    }

    @Override
    public int getBaseID() {
        return ObjectImageConstants.OBJECT_IMAGE_ITEM_SHOP;
    }

    @Override
    public String getName() {
        return "Item Shop";
    }

    @Override
    public String getPluralName() {
        return "Item Shops";
    }

    @Override
    public String getDescription() {
        return "Item Shops sell items used in battle.";
    }
}
