/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

import java.util.Date;

public interface ArchiveEntry {
    public static final long SIZE_UNKNOWN = -1L;

    public String getName();

    public long getSize();

    public boolean isDirectory();

    public Date getLastModifiedDate();
}

