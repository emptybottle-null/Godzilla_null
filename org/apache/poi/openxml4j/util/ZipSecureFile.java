/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;

public class ZipSecureFile
extends ZipFile {
    static double MIN_INFLATE_RATIO = 0.01;
    static long MAX_ENTRY_SIZE = 0xFFFFFFFFL;
    private static long MAX_TEXT_SIZE = 0xA00000L;
    private final String fileName;

    public static void setMinInflateRatio(double ratio) {
        MIN_INFLATE_RATIO = ratio;
    }

    public static double getMinInflateRatio() {
        return MIN_INFLATE_RATIO;
    }

    public static void setMaxEntrySize(long maxEntrySize) {
        if (maxEntrySize < 0L || maxEntrySize > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Max entry size is bounded [0-4GB], but had " + maxEntrySize);
        }
        MAX_ENTRY_SIZE = maxEntrySize;
    }

    public static long getMaxEntrySize() {
        return MAX_ENTRY_SIZE;
    }

    public static void setMaxTextSize(long maxTextSize) {
        if (maxTextSize < 0L || maxTextSize > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Max text size is bounded [0-4GB], but had " + maxTextSize);
        }
        MAX_TEXT_SIZE = maxTextSize;
    }

    public static long getMaxTextSize() {
        return MAX_TEXT_SIZE;
    }

    public ZipSecureFile(File file) throws IOException {
        super(file);
        this.fileName = file.getAbsolutePath();
    }

    public ZipSecureFile(String name) throws IOException {
        super(name);
        this.fileName = new File(name).getAbsolutePath();
    }

    @Override
    public ZipArchiveThresholdInputStream getInputStream(ZipArchiveEntry entry) throws IOException {
        ZipArchiveThresholdInputStream zatis = new ZipArchiveThresholdInputStream(super.getInputStream(entry));
        zatis.setEntry(entry);
        return zatis;
    }

    public String getName() {
        return this.fileName;
    }
}

