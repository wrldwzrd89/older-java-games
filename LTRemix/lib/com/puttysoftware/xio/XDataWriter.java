package com.puttysoftware.xio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XDataWriter implements AutoCloseable {
    // Fields
    private BufferedWriter bw;
    private String docTag;
    private static final String END_OF_LINE = "\r\n";

    // Constructors
    public XDataWriter(String filename, String newDocTag) throws IOException {
        this.bw = new BufferedWriter(new FileWriter(filename));
        this.docTag = newDocTag;
        this.writeXHeader();
        this.writeOpeningDocTag();
    }

    // Methods
    @Override
    public void close() throws IOException {
        this.writeClosingDocTag();
        this.bw.close();
    }

    public void writeDouble(double d) throws IOException {
        this.bw.write("<" + XDataConstants.DOUBLE_TAG + ">"
                + Double.toString(d) + "</" + XDataConstants.DOUBLE_TAG + ">"
                + XDataWriter.END_OF_LINE);
    }

    public void writeInt(int i) throws IOException {
        this.bw.write("<" + XDataConstants.INT_TAG + ">" + Integer.toString(i)
                + "</" + XDataConstants.INT_TAG + ">" + XDataWriter.END_OF_LINE);
    }

    public void writeLong(long l) throws IOException {
        this.bw.write("<" + XDataConstants.LONG_TAG + ">" + Long.toString(l)
                + "</" + XDataConstants.LONG_TAG + ">"
                + XDataWriter.END_OF_LINE);
    }

    public void writeByte(byte b) throws IOException {
        this.bw.write("<" + XDataConstants.BYTE_TAG + ">" + Byte.toString(b)
                + "</" + XDataConstants.BYTE_TAG + ">"
                + XDataWriter.END_OF_LINE);
    }

    public void writeBoolean(boolean b) throws IOException {
        this.bw.write("<" + XDataConstants.BOOLEAN_TAG + ">"
                + Boolean.toString(b) + "</" + XDataConstants.BOOLEAN_TAG + ">"
                + XDataWriter.END_OF_LINE);
    }

    public void writeString(String s) throws IOException {
        this.bw.write("<" + XDataConstants.STRING_TAG + ">"
                + XDataWriter.replaceSpecialCharacters(s) + "</"
                + XDataConstants.STRING_TAG + ">" + XDataWriter.END_OF_LINE);
    }

    public void writeOpeningGroup(String groupName) throws IOException {
        this.bw.write("<" + XDataWriter.replaceSpecialCharacters(groupName)
                + ">" + XDataWriter.END_OF_LINE);
    }

    public void writeClosingGroup(String groupName) throws IOException {
        this.bw.write("</" + XDataWriter.replaceSpecialCharacters(groupName)
                + ">" + XDataWriter.END_OF_LINE);
    }

    private void writeXHeader() throws IOException {
        this.bw.write(XDataConstants.X_HEADER + XDataWriter.END_OF_LINE);
    }

    private void writeOpeningDocTag() throws IOException {
        this.bw.write("<" + this.docTag + ">" + XDataWriter.END_OF_LINE);
    }

    private void writeClosingDocTag() throws IOException {
        this.bw.write("</" + this.docTag + ">");
    }

    private static String replaceSpecialCharacters(String s) {
        String r = s;
        r = r.replace("&", "&amp;");
        r = r.replace("<", "&lt;");
        r = r.replace(">", "&gt;");
        r = r.replace("\"", "&quot;");
        r = r.replace("\'", "&apos;");
        r = r.replace("\r", "");
        return r.replace("\n", "&#xA;");
    }
}