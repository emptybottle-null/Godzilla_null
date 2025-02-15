/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.unit;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.util.unit.DataUnit;

public final class DataSize
implements Comparable<DataSize>,
Serializable {
    private static final Pattern PATTERN = Pattern.compile("^([+\\-]?\\d+)([a-zA-Z]{0,2})$");
    private static final long BYTES_PER_KB = 1024L;
    private static final long BYTES_PER_MB = 0x100000L;
    private static final long BYTES_PER_GB = 0x40000000L;
    private static final long BYTES_PER_TB = 0x10000000000L;
    private final long bytes;

    private DataSize(long bytes) {
        this.bytes = bytes;
    }

    public static DataSize ofBytes(long bytes) {
        return new DataSize(bytes);
    }

    public static DataSize ofKilobytes(long kilobytes) {
        return new DataSize(Math.multiplyExact(kilobytes, 1024L));
    }

    public static DataSize ofMegabytes(long megabytes) {
        return new DataSize(Math.multiplyExact(megabytes, 0x100000L));
    }

    public static DataSize ofGigabytes(long gigabytes) {
        return new DataSize(Math.multiplyExact(gigabytes, 0x40000000L));
    }

    public static DataSize ofTerabytes(long terabytes) {
        return new DataSize(Math.multiplyExact(terabytes, 0x10000000000L));
    }

    public static DataSize of(long amount, DataUnit unit) {
        Assert.notNull((Object)unit, "Unit must not be null");
        return new DataSize(Math.multiplyExact(amount, unit.size().toBytes()));
    }

    public static DataSize parse(CharSequence text) {
        return DataSize.parse(text, null);
    }

    public static DataSize parse(CharSequence text, @Nullable DataUnit defaultUnit) {
        Assert.notNull((Object)text, "Text must not be null");
        try {
            Matcher matcher = PATTERN.matcher(text);
            Assert.state(matcher.matches(), "Does not match data size pattern");
            DataUnit unit = DataSize.determineDataUnit(matcher.group(2), defaultUnit);
            long amount = Long.parseLong(matcher.group(1));
            return DataSize.of(amount, unit);
        } catch (Exception ex) {
            throw new IllegalArgumentException("'" + text + "' is not a valid data size", ex);
        }
    }

    private static DataUnit determineDataUnit(String suffix, @Nullable DataUnit defaultUnit) {
        DataUnit defaultUnitToUse = defaultUnit != null ? defaultUnit : DataUnit.BYTES;
        return StringUtils.hasLength(suffix) ? DataUnit.fromSuffix(suffix) : defaultUnitToUse;
    }

    public boolean isNegative() {
        return this.bytes < 0L;
    }

    public long toBytes() {
        return this.bytes;
    }

    public long toKilobytes() {
        return this.bytes / 1024L;
    }

    public long toMegabytes() {
        return this.bytes / 0x100000L;
    }

    public long toGigabytes() {
        return this.bytes / 0x40000000L;
    }

    public long toTerabytes() {
        return this.bytes / 0x10000000000L;
    }

    @Override
    public int compareTo(DataSize other) {
        return Long.compare(this.bytes, other.bytes);
    }

    public String toString() {
        return String.format("%dB", this.bytes);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        DataSize otherSize = (DataSize)other;
        return this.bytes == otherSize.bytes;
    }

    public int hashCode() {
        return Long.hashCode(this.bytes);
    }
}

