package net.worldwizard.xio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XDataReader {
    // Fields
    private final BufferedReader br;
    private final String docTag;

    // Constructors
    public XDataReader(final String filename, final String newDocTag)
            throws IOException {
        this.br = new BufferedReader(new FileReader(filename));
        this.docTag = newDocTag;
        this.readXHeader();
        this.readOpeningDocTag();
    }

    // Methods
    public void close() throws IOException {
        this.readClosingDocTag();
        this.br.close();
    }

    public double readDouble() throws IOException {
        final String line = this.br.readLine();
        if (line != null) {
            final String[] split = XDataReader.splitLine(line);
            XDataReader.validateOpeningTag(split[0], XDataConstants.DOUBLE_TAG);
            XDataReader.validateClosingTag(split[2], XDataConstants.DOUBLE_TAG);
            return Double.parseDouble(split[1]);
        } else {
            throw new IOException("End of file!");
        }
    }

    public int readInt() throws IOException {
        final String line = this.br.readLine();
        if (line != null) {
            final String[] split = XDataReader.splitLine(line);
            XDataReader.validateOpeningTag(split[0], XDataConstants.INT_TAG);
            XDataReader.validateClosingTag(split[2], XDataConstants.INT_TAG);
            return Integer.parseInt(split[1]);
        } else {
            throw new IOException("End of file!");
        }
    }

    public long readLong() throws IOException {
        final String line = this.br.readLine();
        if (line != null) {
            final String[] split = XDataReader.splitLine(line);
            XDataReader.validateOpeningTag(split[0], XDataConstants.LONG_TAG);
            XDataReader.validateClosingTag(split[2], XDataConstants.LONG_TAG);
            return Long.parseLong(split[1]);
        } else {
            throw new IOException("End of file!");
        }
    }

    public byte readByte() throws IOException {
        final String line = this.br.readLine();
        if (line != null) {
            final String[] split = XDataReader.splitLine(line);
            XDataReader.validateOpeningTag(split[0], XDataConstants.BYTE_TAG);
            XDataReader.validateClosingTag(split[2], XDataConstants.BYTE_TAG);
            return Byte.parseByte(split[1]);
        } else {
            throw new IOException("End of file!");
        }
    }

    public boolean readBoolean() throws IOException {
        final String line = this.br.readLine();
        if (line != null) {
            final String[] split = XDataReader.splitLine(line);
            XDataReader.validateOpeningTag(split[0],
                    XDataConstants.BOOLEAN_TAG);
            XDataReader.validateClosingTag(split[2],
                    XDataConstants.BOOLEAN_TAG);
            return Boolean.parseBoolean(split[1]);
        } else {
            throw new IOException("End of file!");
        }
    }

    public String readString() throws IOException {
        final String line = this.br.readLine();
        if (line != null) {
            final String[] split = XDataReader.splitLine(line);
            XDataReader.validateOpeningTag(split[0], XDataConstants.STRING_TAG);
            XDataReader.validateClosingTag(split[2], XDataConstants.STRING_TAG);
            return split[1];
        } else {
            throw new IOException("End of file!");
        }
    }

    private static void validateOpeningTag(final String tag,
            final String tagType) throws IOException {
        if (!tag.equals("<" + tagType + ">")) {
            throw new UnexpectedTagException("Expected opening tag of <"
                    + tagType + ">, found " + tag + "!");
        }
    }

    private static void validateClosingTag(final String tag,
            final String tagType) throws IOException {
        if (!tag.equals("</" + tagType + ">")) {
            throw new UnexpectedTagException("Expected closing tag of </"
                    + tagType + ">, found " + tag + "!");
        }
    }

    private static String[] splitLine(final String line) {
        final String[] split = new String[3];
        final int loc0 = line.indexOf(">") + 1;
        final int loc2 = line.indexOf("<", loc0);
        split[0] = line.substring(0, loc0);
        split[1] = line.substring(loc0, loc2);
        split[2] = line.substring(loc2);
        return split;
    }

    private void readXHeader() throws IOException {
        final String header = this.br.readLine();
        if (header == null) {
            throw new UnexpectedTagException("Corrupt or invalid header!");
        }
        if (!header.equals(XDataConstants.X_HEADER)) {
            throw new UnexpectedTagException("Corrupt or invalid header!");
        }
    }

    private void readOpeningDocTag() throws IOException {
        final String line = this.br.readLine();
        if (!line.equals("<" + this.docTag + ">")) {
            throw new UnexpectedTagException(
                    "Opening doc tag does not match: expected <" + this.docTag
                            + ">, found " + line + "!");
        }
    }

    private void readClosingDocTag() throws IOException {
        final String line = this.br.readLine();
        if (!line.equals("</" + this.docTag + ">")) {
            throw new UnexpectedTagException(
                    "Closing doc tag does not match: expected </" + this.docTag
                            + ">, found " + line + "!");
        }
    }
}
