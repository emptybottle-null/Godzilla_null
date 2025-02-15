/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

public interface UnixStat {
    public static final int PERM_MASK = 4095;
    public static final int FILE_TYPE_FLAG = 61440;
    public static final int LINK_FLAG = 40960;
    public static final int FILE_FLAG = 32768;
    public static final int DIR_FLAG = 16384;
    public static final int DEFAULT_LINK_PERM = 511;
    public static final int DEFAULT_DIR_PERM = 493;
    public static final int DEFAULT_FILE_PERM = 420;
}

