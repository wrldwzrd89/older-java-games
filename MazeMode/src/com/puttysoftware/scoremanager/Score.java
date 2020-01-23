/*  MazeMode: A Maze-Solving Game
Copyright (C) 2008-2010 Eric Ahnell

Any questions should be directed to the author via email at: products@puttysoftware.com
 */
package com.puttysoftware.scoremanager;

import java.io.IOException;

import com.puttysoftware.xio.XDataReader;
import com.puttysoftware.xio.XDataWriter;

public final class Score {
    // Fields
    private long score;
    private String name;

    // Constructors
    public Score() {
        this.score = 0L;
        this.name = "Nobody";
    }

    public Score(final long newScore, final String newName) {
        this.score = newScore;
        this.name = newName;
    }

    // Methods
    public long getScore() {
        return this.score;
    }

    public String getName() {
        return this.name;
    }

    public void setScore(final long newScore) {
        this.score = newScore;
    }

    public void setName(final String newName) {
        this.name = newName;
    }

    public static Score readScoreX(final XDataReader xdr) throws IOException {
        final Score s = new Score();
        s.name = xdr.readString();
        s.score = xdr.readLong();
        return s;
    }

    public void writeScoreX(final XDataWriter xdw) throws IOException {
        xdw.writeString(this.name);
        xdw.writeLong(this.score);
    }
}
