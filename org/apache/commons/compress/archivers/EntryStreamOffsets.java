/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

public interface EntryStreamOffsets {
    public static final long OFFSET_UNKNOWN = -1L;

    public long getDataOffset();

    public boolean isStreamContiguous();
}

