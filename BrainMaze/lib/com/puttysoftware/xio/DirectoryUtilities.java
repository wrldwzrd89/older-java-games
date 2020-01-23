package com.puttysoftware.xio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class DirectoryUtilities {
    private DirectoryUtilities() {
        // Do nothing
    }

    public static void copyDirectory(final File sourceLocation,
            final File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            final String[] children = sourceLocation.list();
            for (final String element : children) {
                DirectoryUtilities.copyDirectory(new File(sourceLocation,
                        element), new File(targetLocation, element));
            }
        } else {
            try (InputStream in = new FileInputStream(sourceLocation);
                    OutputStream out = new FileOutputStream(targetLocation)) {
                // Copy the bits from instream to outstream
                final byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } catch (final IOException ioe) {
                throw ioe;
            }
        }
    }

    public static void removeDirectory(final File location) throws IOException {
        boolean success;
        if (location.isDirectory()) {
            final String[] children = location.list();
            for (final String element : children) {
                DirectoryUtilities.removeDirectory(new File(location, element));
            }
            success = location.delete();
            if (!success) {
                throw new IOException("Directory deletion failed!");
            }
        } else {
            success = location.delete();
            if (!success) {
                throw new IOException("Directory deletion failed!");
            }
        }
    }

    public static void copyFile(final File sourceLocation,
            final File targetLocation) throws IOException {
        try (InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation)) {
            // Copy the bits from instream to outstream
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        } catch (final IOException ioe) {
            throw ioe;
        }
    }

    public static boolean moveFile(final File sourceLocation,
            final File targetLocation) throws IOException {
        try (InputStream in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation)) {
            // Copy the bits from instream to outstream
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            return sourceLocation.delete();
        } catch (final IOException ioe) {
            throw ioe;
        }
    }

    public static void copyRAMFile(final InputStream in,
            final File targetLocation) throws IOException {
        try (OutputStream out = new FileOutputStream(targetLocation)) {
            // Copy the bits from instream to outstream
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
        } catch (final IOException ioe) {
            throw ioe;
        }
    }
}
