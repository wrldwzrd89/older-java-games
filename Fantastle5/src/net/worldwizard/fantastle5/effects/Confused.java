/*  Fantastle: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

Any questions should be directed to the author via email at: fantastle@worldwizard.net
 */
package net.worldwizard.fantastle5.effects;

import net.worldwizard.fantastle5.generic.DirectionConstants;
import net.worldwizard.randomnumbers.RandomRange;

public class Confused extends Effect {
    // Fields
    private int state;
    private static final int CONFUSED_STATE_UDRL = 1;
    private static final int CONFUSED_STATE_ULDR = 2;
    private static final int CONFUSED_STATE_ULRD = 3;
    private static final int CONFUSED_STATE_URDL = 4;
    private static final int CONFUSED_STATE_URLD = 5;
    private static final int CONFUSED_STATE_DULR = 6;
    private static final int CONFUSED_STATE_DURL = 7;
    private static final int CONFUSED_STATE_DLUR = 8;
    private static final int CONFUSED_STATE_DLRU = 9;
    private static final int CONFUSED_STATE_DRUL = 10;
    private static final int CONFUSED_STATE_DRLU = 11;
    private static final int CONFUSED_STATE_LDUR = 12;
    private static final int CONFUSED_STATE_LDRU = 13;
    private static final int CONFUSED_STATE_LUDR = 14;
    private static final int CONFUSED_STATE_LURD = 15;
    private static final int CONFUSED_STATE_LRDU = 16;
    private static final int CONFUSED_STATE_LRUD = 17;
    private static final int CONFUSED_STATE_RDLU = 18;
    private static final int CONFUSED_STATE_RDUL = 19;
    private static final int CONFUSED_STATE_RLDU = 20;
    private static final int CONFUSED_STATE_RLUD = 21;
    private static final int CONFUSED_STATE_RUDL = 22;
    private static final int CONFUSED_STATE_RULD = 23;
    private static final int MIN_CONFUSED_STATE = 1;
    private static final int MAX_CONFUSED_STATE = 23;

    // Constructor
    public Confused(final int newRounds) {
        super("Confused", newRounds);
    }

    @Override
    public void customExtendLogic() {
        if (this.rounds == 0) {
            final RandomRange r = new RandomRange(Confused.MIN_CONFUSED_STATE,
                    Confused.MAX_CONFUSED_STATE);
            this.state = r.generate();
        }
    }

    @Override
    public int modifyMove1(final int arg) {
        switch (arg) {
        case DirectionConstants.DIRECTION_NORTH:
            switch (this.state) {
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_URLD:
                return DirectionConstants.DIRECTION_NORTH;
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_DRLU:
                return DirectionConstants.DIRECTION_SOUTH;
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_LUDR:
            case CONFUSED_STATE_LURD:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_LRUD:
                return DirectionConstants.DIRECTION_WEST;
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RDUL:
            case CONFUSED_STATE_RLDU:
            case CONFUSED_STATE_RLUD:
            case CONFUSED_STATE_RUDL:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_EAST;
            default:
                break;
            }
            break;
        case DirectionConstants.DIRECTION_SOUTH:
            switch (this.state) {
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_LUDR:
            case CONFUSED_STATE_LURD:
            case CONFUSED_STATE_RUDL:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_NORTH;
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RDUL:
                return DirectionConstants.DIRECTION_SOUTH;
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_RLDU:
            case CONFUSED_STATE_RLUD:
                return DirectionConstants.DIRECTION_WEST;
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_URLD:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_DRLU:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_LRUD:
                return DirectionConstants.DIRECTION_EAST;
            default:
                break;
            }
            break;
        case DirectionConstants.DIRECTION_WEST:
            switch (this.state) {
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LRUD:
            case CONFUSED_STATE_RDUL:
            case CONFUSED_STATE_RLUD:
                return DirectionConstants.DIRECTION_NORTH;
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_LUDR:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_RLDU:
            case CONFUSED_STATE_RUDL:
                return DirectionConstants.DIRECTION_SOUTH;
            case CONFUSED_STATE_URLD:
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DRLU:
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_WEST;
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_LURD:
                return DirectionConstants.DIRECTION_EAST;
            default:
                break;
            }
            break;
        case DirectionConstants.DIRECTION_EAST:
            switch (this.state) {
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_DRLU:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RLDU:
                return DirectionConstants.DIRECTION_NORTH;
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_URLD:
            case CONFUSED_STATE_LURD:
            case CONFUSED_STATE_LRUD:
            case CONFUSED_STATE_RLUD:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_SOUTH;
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_RDUL:
            case CONFUSED_STATE_RUDL:
                return DirectionConstants.DIRECTION_WEST;
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LUDR:
                return DirectionConstants.DIRECTION_EAST;
            default:
                break;
            }
            break;
        case DirectionConstants.DIRECTION_NORTHWEST:
            switch (this.state) {
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_URLD:
                return DirectionConstants.DIRECTION_NORTHWEST;
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_DRLU:
                return DirectionConstants.DIRECTION_SOUTHEAST;
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_LUDR:
            case CONFUSED_STATE_LURD:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_LRUD:
                return DirectionConstants.DIRECTION_SOUTHWEST;
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RDUL:
            case CONFUSED_STATE_RLDU:
            case CONFUSED_STATE_RLUD:
            case CONFUSED_STATE_RUDL:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_NORTHEAST;
            default:
                break;
            }
            break;
        case DirectionConstants.DIRECTION_NORTHEAST:
            switch (this.state) {
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_LUDR:
            case CONFUSED_STATE_LURD:
            case CONFUSED_STATE_RUDL:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_NORTHWEST;
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RDUL:
                return DirectionConstants.DIRECTION_SOUTHEAST;
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_RLDU:
            case CONFUSED_STATE_RLUD:
                return DirectionConstants.DIRECTION_SOUTHWEST;
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_URLD:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_DRLU:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_LRUD:
                return DirectionConstants.DIRECTION_NORTHEAST;
            default:
                break;
            }
            break;
        case DirectionConstants.DIRECTION_SOUTHWEST:
            switch (this.state) {
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LRUD:
            case CONFUSED_STATE_RDUL:
            case CONFUSED_STATE_RLUD:
                return DirectionConstants.DIRECTION_NORTHWEST;
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_LUDR:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_RLDU:
            case CONFUSED_STATE_RUDL:
                return DirectionConstants.DIRECTION_SOUTHEAST;
            case CONFUSED_STATE_URLD:
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DRLU:
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_SOUTHWEST;
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_LURD:
                return DirectionConstants.DIRECTION_NORTHEAST;
            default:
                break;
            }
            break;
        case DirectionConstants.DIRECTION_SOUTHEAST:
            switch (this.state) {
            case CONFUSED_STATE_DLRU:
            case CONFUSED_STATE_DRLU:
            case CONFUSED_STATE_LDRU:
            case CONFUSED_STATE_LRDU:
            case CONFUSED_STATE_RDLU:
            case CONFUSED_STATE_RLDU:
                return DirectionConstants.DIRECTION_NORTHWEST;
            case CONFUSED_STATE_ULRD:
            case CONFUSED_STATE_URLD:
            case CONFUSED_STATE_LURD:
            case CONFUSED_STATE_LRUD:
            case CONFUSED_STATE_RLUD:
            case CONFUSED_STATE_RULD:
                return DirectionConstants.DIRECTION_SOUTHEAST;
            case CONFUSED_STATE_UDRL:
            case CONFUSED_STATE_URDL:
            case CONFUSED_STATE_DURL:
            case CONFUSED_STATE_DRUL:
            case CONFUSED_STATE_RDUL:
            case CONFUSED_STATE_RUDL:
                return DirectionConstants.DIRECTION_SOUTHWEST;
            case CONFUSED_STATE_ULDR:
            case CONFUSED_STATE_DULR:
            case CONFUSED_STATE_DLUR:
            case CONFUSED_STATE_LDUR:
            case CONFUSED_STATE_LUDR:
                return DirectionConstants.DIRECTION_NORTHEAST;
            default:
                break;
            }
            break;
        default:
            break;
        }
        return 0;
    }
}