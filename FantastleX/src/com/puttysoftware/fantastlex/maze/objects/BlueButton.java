/*  FantastleX: A Maze/RPG Hybrid Game
Copyleft 2013 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.fantastlex.maze.objects;

import com.puttysoftware.fantastlex.maze.abc.AbstractButton;
import com.puttysoftware.fantastlex.maze.utilities.ColorConstants;

public class BlueButton extends AbstractButton {
    public BlueButton() {
        super(new BlueWallOff(), new BlueWallOn(), ColorConstants.COLOR_BLUE);
    }

    @Override
    public String getName() {
        return "Blue Button";
    }

    @Override
    public String getPluralName() {
        return "Blue Buttons";
    }

    @Override
    public String getDescription() {
        return "Blue Buttons will cause all Blue Walls Off to become On, and all Blue Walls On to become Off.";
    }
}
