/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.unit;

import org.springframework.util.unit.DataSize;

public enum DataUnit {
    BYTES("B", DataSize.ofBytes(1L)),
    KILOBYTES("KB", DataSize.ofKilobytes(1L)),
    MEGABYTES("MB", DataSize.ofMegabytes(1L)),
    GIGABYTES("GB", DataSize.ofGigabytes(1L)),
    TERABYTES("TB", DataSize.ofTerabytes(1L));

    private final String suffix;
    private final DataSize size;

    private DataUnit(String suffix, DataSize size) {
        this.suffix = suffix;
        this.size = size;
    }

    DataSize size() {
        return this.size;
    }

    public static DataUnit fromSuffix(String suffix) {
        for (DataUnit candidate : DataUnit.values()) {
            if (!candidate.suffix.equals(suffix)) continue;
            return candidate;
        }
        throw new IllegalArgumentException("Unknown data unit suffix '" + suffix + "'");
    }
}

