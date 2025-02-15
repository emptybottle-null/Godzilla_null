/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.constant;

public interface SectionFlag {
    public static final int IMAGE_SCN_RESERVED_1 = 0;
    public static final int IMAGE_SCN_RESERVED_2 = 1;
    public static final int IMAGE_SCN_RESERVED_3 = 2;
    public static final int IMAGE_SCN_RESERVED_4 = 4;
    public static final int IMAGE_SCN_TYPE_NO_PAD = 8;
    public static final int IMAGE_SCN_RESERVED_5 = 16;
    public static final int IMAGE_SCN_CNT_CODE = 32;
    public static final int IMAGE_SCN_CNT_INITIALIZED_DATA = 64;
    public static final int IMAGE_SCN_CNT_UNINITIALIZED_DATA = 128;
    public static final int IMAGE_SCN_LNK_OTHER = 256;
    public static final int IMAGE_SCN_LNK_INFO = 512;
    public static final int IMAGE_SCN_RESERVED_6 = 1024;
    public static final int IMAGE_SCN_LNK_REMOVE = 2048;
    public static final int IMAGE_SCN_LNK_COMDAT = 4096;
    public static final int IMAGE_SCN_GPREL = 32768;
    public static final int IMAGE_SCN_MEM_PURGEABLE = 131072;
    public static final int IMAGE_SCN_MEM_16BIT = 131072;
    public static final int IMAGE_SCN_MEM_LOCKED = 262144;
    public static final int IMAGE_SCN_MEM_PRELOAD = 524288;
    public static final int IMAGE_SCN_ALIGN_1BYTES = 0x100000;
    public static final int IMAGE_SCN_ALIGN_2BYTES = 0x200000;
    public static final int IMAGE_SCN_ALIGN_4BYTES = 0x300000;
    public static final int IMAGE_SCN_ALIGN_8BYTES = 0x400000;
    public static final int IMAGE_SCN_ALIGN_16BYTES = 0x500000;
    public static final int IMAGE_SCN_ALIGN_32BYTES = 0x600000;
    public static final int IMAGE_SCN_ALIGN_64BYTES = 0x700000;
    public static final int IMAGE_SCN_ALIGN_128BYTES = 0x800000;
    public static final int IMAGE_SCN_ALIGN_256BYTES = 0x900000;
    public static final int IMAGE_SCN_ALIGN_512BYTES = 0xA00000;
    public static final int IMAGE_SCN_ALIGN_1024BYTES = 0xB00000;
    public static final int IMAGE_SCN_ALIGN_2048BYTES = 0xC00000;
    public static final int IMAGE_SCN_ALIGN_4096BYTES = 0xD00000;
    public static final int IMAGE_SCN_ALIGN_8192BYTES = 0xE00000;
    public static final int IMAGE_SCN_LNK_NRELOC_OVFL = 0x1000000;
    public static final int IMAGE_SCN_MEM_DISCARDABLE = 0x2000000;
    public static final int IMAGE_SCN_MEM_NOT_CACHED = 0x4000000;
    public static final int IMAGE_SCN_MEM_NOT_PAGED = 0x8000000;
    public static final int IMAGE_SCN_MEM_SHARED = 0x10000000;
    public static final int IMAGE_SCN_MEM_EXECUTE = 0x20000000;
    public static final int IMAGE_SCN_MEM_READ = 0x40000000;
    public static final int IMAGE_SCN_MEM_WRITE = Integer.MIN_VALUE;
}

