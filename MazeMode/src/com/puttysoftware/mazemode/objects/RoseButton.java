/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.mazemode.objects;

import com.puttysoftware.mazemode.generic.GenericButton;

public class RoseButton extends GenericButton {
    public RoseButton() {
        super(new RoseWallOff(), new RoseWallOn());
    }

    @Override
    public String getName() {
        return "Rose Button";
    }

    @Override
    public String getPluralName() {
        return "Rose Buttons";
    }

    @Override
    public String getDescription() {
        return "Rose Buttons will cause all Rose Walls Off to become On, and all Rose Walls On to become Off.";
    }
}
