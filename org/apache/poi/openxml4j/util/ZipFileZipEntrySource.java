/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.openxml4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.poi.openxml4j.util.ZipEntrySource;

public class ZipFileZipEntrySource
implements ZipEntrySource {
    private ZipFile zipArchive;

    public ZipFileZipEntrySource(ZipFile zipFile) {
        this.zipArchive = zipFile;
    }

    @Override
    public void close() throws IOException {
        if (this.zipArchive != null) {
            this.zipArchive.close();
        }
        this.zipArchive = null;
    }

    @Override
    public boolean isClosed() {
        return this.zipArchive == null;
    }

    @Override
    public Enumeration<? extends ZipArchiveEntry> getEntries() {
        if (this.zipArchive == null) {
            throw new IllegalStateException("Zip File is closed");
        }
        return this.zipArchive.getEntries();
    }

    @Override
    public InputStream getInputStream(ZipArchiveEntry entry) throws IOException {
        if (this.zipArchive == null) {
            throw new IllegalStateException("Zip File is closed");
        }
        return this.zipArchive.getInputStream(entry);
    }

    @Override
    public ZipArchiveEntry getEntry(String path) {
        String normalizedPath = path.replace('\\', '/');
        ZipArchiveEntry entry = this.zipArchive.getEntry(normalizedPath);
        if (entry != null) {
            return entry;
        }
        Enumeration<ZipArchiveEntry> zipArchiveEntryEnumeration = this.zipArchive.getEntries();
        while (zipArchiveEntryEnumeration.hasMoreElements()) {
            ZipArchiveEntry ze = zipArchiveEntryEnumeration.nextElement();
            if (!normalizedPath.equalsIgnoreCase(ze.getName().replace('\\', '/'))) continue;
            return ze;
        }
        return null;
    }
}

