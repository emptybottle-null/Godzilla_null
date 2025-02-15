/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.snappy;

public enum FramedSnappyDialect {
    STANDARD(true, true),
    IWORK_ARCHIVE(false, false);

    private final boolean streamIdentifier;
    private final boolean checksumWithCompressedChunks;

    private FramedSnappyDialect(boolean hasStreamIdentifier, boolean usesChecksumWithCompressedChunks) {
        this.streamIdentifier = hasStreamIdentifier;
        this.checksumWithCompressedChunks = usesChecksumWithCompressedChunks;
    }

    boolean hasStreamIdentifier() {
        return this.streamIdentifier;
    }

    boolean usesChecksumWithCompressedChunks() {
        return this.checksumWithCompressedChunks;
    }
}

