/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import org.apache.poi.util.TempFileCreationStrategy;

public class DefaultTempFileCreationStrategy
implements TempFileCreationStrategy {
    static final String POIFILES = "poifiles";
    public static final String KEEP_FILES = "poi.keep.tmp.files";
    private static final SecureRandom random = new SecureRandom();
    private File dir;

    public DefaultTempFileCreationStrategy() {
        this(null);
    }

    public DefaultTempFileCreationStrategy(File dir) {
        this.dir = dir;
    }

    private void createPOIFilesDirectory() throws IOException {
        if (this.dir == null) {
            String tmpDir = System.getProperty("java.io.tmpdir");
            if (tmpDir == null) {
                throw new IOException("Systems temporary directory not defined - set the -Djava.io.tmpdir jvm property!");
            }
            this.dir = new File(tmpDir, POIFILES);
        }
        this.createTempDirectory(this.dir);
    }

    private synchronized void createTempDirectory(File directory) throws IOException {
        boolean dirExists;
        boolean bl = dirExists = directory.exists() || directory.mkdirs();
        if (!dirExists) {
            throw new IOException("Could not create temporary directory '" + directory + "'");
        }
        if (!directory.isDirectory()) {
            throw new IOException("Could not create temporary directory. '" + directory + "' exists but is not a directory.");
        }
    }

    @Override
    public File createTempFile(String prefix, String suffix) throws IOException {
        this.createPOIFilesDirectory();
        File newFile = File.createTempFile(prefix, suffix, this.dir);
        if (System.getProperty(KEEP_FILES) == null) {
            newFile.deleteOnExit();
        }
        return newFile;
    }

    @Override
    public File createTempDirectory(String prefix) throws IOException {
        this.createPOIFilesDirectory();
        long n = random.nextLong();
        File newDirectory = new File(this.dir, prefix + Long.toString(n));
        this.createTempDirectory(newDirectory);
        if (System.getProperty(KEEP_FILES) == null) {
            newDirectory.deleteOnExit();
        }
        return newDirectory;
    }
}

