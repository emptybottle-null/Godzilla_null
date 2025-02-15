/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.util.IOUtils;

class ZipArchiveFakeEntry
extends ZipArchiveEntry {
    private final byte[] data;

    ZipArchiveFakeEntry(ZipArchiveEntry entry, InputStream inp) throws IOException {
        super(entry.getName());
        long entrySize = entry.getSize();
        if (entrySize < -1L || entrySize >= Integer.MAX_VALUE) {
            throw new IOException("ZIP entry size is too large or invalid");
        }
        this.data = entrySize == -1L ? IOUtils.toByteArray(inp) : IOUtils.toByteArray(inp, (int)entrySize);
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.data);
    }
}

