/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

public class ScatterStatistics {
    private final long compressionElapsed;
    private final long mergingElapsed;

    ScatterStatistics(long compressionElapsed, long mergingElapsed) {
        this.compressionElapsed = compressionElapsed;
        this.mergingElapsed = mergingElapsed;
    }

    public long getCompressionElapsed() {
        return this.compressionElapsed;
    }

    public long getMergingElapsed() {
        return this.mergingElapsed;
    }

    public String toString() {
        return "compressionElapsed=" + this.compressionElapsed + "ms, mergingElapsed=" + this.mergingElapsed + "ms";
    }
}

