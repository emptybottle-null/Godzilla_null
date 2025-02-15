/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.cpio;

public interface CpioConstants {
    public static final String MAGIC_NEW = "070701";
    public static final String MAGIC_NEW_CRC = "070702";
    public static final String MAGIC_OLD_ASCII = "070707";
    public static final int MAGIC_OLD_BINARY = 29127;
    public static final short FORMAT_NEW = 1;
    public static final short FORMAT_NEW_CRC = 2;
    public static final short FORMAT_OLD_ASCII = 4;
    public static final short FORMAT_OLD_BINARY = 8;
    public static final short FORMAT_NEW_MASK = 3;
    public static final short FORMAT_OLD_MASK = 12;
    public static final int S_IFMT = 61440;
    public static final int C_ISSOCK = 49152;
    public static final int C_ISLNK = 40960;
    public static final int C_ISNWK = 36864;
    public static final int C_ISREG = 32768;
    public static final int C_ISBLK = 24576;
    public static final int C_ISDIR = 16384;
    public static final int C_ISCHR = 8192;
    public static final int C_ISFIFO = 4096;
    public static final int C_ISUID = 2048;
    public static final int C_ISGID = 1024;
    public static final int C_ISVTX = 512;
    public static final int C_IRUSR = 256;
    public static final int C_IWUSR = 128;
    public static final int C_IXUSR = 64;
    public static final int C_IRGRP = 32;
    public static final int C_IWGRP = 16;
    public static final int C_IXGRP = 8;
    public static final int C_IROTH = 4;
    public static final int C_IWOTH = 2;
    public static final int C_IXOTH = 1;
    public static final String CPIO_TRAILER = "TRAILER!!!";
    public static final int BLOCK_SIZE = 512;
}

