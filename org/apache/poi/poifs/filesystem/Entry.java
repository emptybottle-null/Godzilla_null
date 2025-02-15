/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.poifs.filesystem;

import org.apache.poi.poifs.filesystem.DirectoryEntry;

public interface Entry {
    public String getName();

    public boolean isDirectoryEntry();

    public boolean isDocumentEntry();

    public DirectoryEntry getParent();

    public boolean delete();

    public boolean renameTo(String var1);
}

