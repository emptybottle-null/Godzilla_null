/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.poi.openxml4j.util.ZipArchiveFakeEntry;
import org.apache.poi.openxml4j.util.ZipArchiveThresholdInputStream;
import org.apache.poi.openxml4j.util.ZipEntrySource;

public class ZipInputStreamZipEntrySource
implements ZipEntrySource {
    private final Map<String, ZipArchiveFakeEntry> zipEntries = new HashMap<String, ZipArchiveFakeEntry>();
    private InputStream streamToClose;

    public ZipInputStreamZipEntrySource(ZipArchiveThresholdInputStream inp) throws IOException {
        ZipArchiveEntry zipEntry;
        while ((zipEntry = inp.getNextEntry()) != null) {
            this.zipEntries.put(zipEntry.getName(), new ZipArchiveFakeEntry(zipEntry, inp));
        }
        this.streamToClose = inp;
    }

    @Override
    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.zipEntries.values());
    }

    @Override
    public InputStream getInputStream(ZipArchiveEntry zipEntry) {
        assert (zipEntry instanceof ZipArchiveFakeEntry);
        return ((ZipArchiveFakeEntry)zipEntry).getInputStream();
    }

    @Override
    public void close() throws IOException {
        this.zipEntries.clear();
        this.streamToClose.close();
    }

    @Override
    public boolean isClosed() {
        return this.zipEntries.isEmpty();
    }

    @Override
    public ZipArchiveEntry getEntry(String path) {
        String normalizedPath = path.replace('\\', '/');
        ZipArchiveEntry ze = this.zipEntries.get(normalizedPath);
        if (ze != null) {
            return ze;
        }
        for (Map.Entry<String, ZipArchiveFakeEntry> fze : this.zipEntries.entrySet()) {
            if (!normalizedPath.equalsIgnoreCase(fze.getKey())) continue;
            return fze.getValue();
        }
        return null;
    }
}

