/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.util;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public interface ZipEntrySource
extends Closeable {
    public Enumeration<? extends ZipArchiveEntry> getEntries();

    public ZipArchiveEntry getEntry(String var1);

    public InputStream getInputStream(ZipArchiveEntry var1) throws IOException;

    @Override
    public void close() throws IOException;

    public boolean isClosed();
}

