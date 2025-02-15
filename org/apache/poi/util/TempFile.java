/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.util;

import java.io.File;
import java.io.IOException;
import org.apache.poi.util.DefaultTempFileCreationStrategy;
import org.apache.poi.util.TempFileCreationStrategy;

public final class TempFile {
    private static TempFileCreationStrategy strategy = new DefaultTempFileCreationStrategy();
    public static final String JAVA_IO_TMPDIR = "java.io.tmpdir";

    private TempFile() {
    }

    public static void setTempFileCreationStrategy(TempFileCreationStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("strategy == null");
        }
        TempFile.strategy = strategy;
    }

    public static File createTempFile(String prefix, String suffix) throws IOException {
        return strategy.createTempFile(prefix, suffix);
    }

    public static File createTempDirectory(String name) throws IOException {
        return strategy.createTempDirectory(name);
    }
}

