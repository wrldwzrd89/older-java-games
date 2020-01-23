package com.puttysoftware.mazerunner2.maze;

import java.io.IOException;

import com.puttysoftware.xio.XDataReader;
import com.puttysoftware.xio.XDataWriter;

public class PrefixHandler implements PrefixIO {
    private static final byte FORMAT_VERSION = (byte) FormatConstants.MAZE_FORMAT_LATEST;

    @Override
    public int readPrefix(XDataReader reader) throws IOException {
        byte formatVer = PrefixHandler.readFormatVersion(reader);
        boolean res = PrefixHandler.checkFormatVersion(formatVer);
        if (!res) {
            throw new IOException("Unsupported maze format version.");
        }
        return formatVer;
    }

    @Override
    public void writePrefix(XDataWriter writer) throws IOException {
        PrefixHandler.writeFormatVersion(writer);
    }

    private static byte readFormatVersion(XDataReader reader)
            throws IOException {
        return reader.readByte();
    }

    private static boolean checkFormatVersion(byte version) {
        return (version <= PrefixHandler.FORMAT_VERSION);
    }

    private static void writeFormatVersion(XDataWriter writer)
            throws IOException {
        writer.writeByte(PrefixHandler.FORMAT_VERSION);
    }
}
