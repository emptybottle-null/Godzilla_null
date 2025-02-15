/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kichik.pecoff4j.constant;

public interface DLLCharacteristics {
    public static final int IMAGE_DLL_RESERVED_1 = 1;
    public static final int IMAGE_DLL_RESERVED_2 = 2;
    public static final int IMAGE_DLL_RESERVED_3 = 4;
    public static final int IMAGE_DLL_RESERVED_4 = 8;
    public static final int IMAGE_DLL_CHARACTERISTICS_DYNAMIC_BASE = 64;
    public static final int IMAGE_DLL_CHARACTERISTICS_FORCE_INTEGRITY = 128;
    public static final int IMAGE_DLL_CHARACTERISTICS_NX_COMPAT = 256;
    public static final int IMAGE_DLLCHARACTERISTICS_NO_ISOLATION = 512;
    public static final int IMAGE_DLLCHARACTERISTICS_NO_SEH = 1024;
    public static final int IMAGE_DLLCHARACTERISTICS_NO_BIND = 2048;
    public static final int IMAGE_DLL_RESERVED_5 = 4096;
    public static final int IMAGE_DLLCHARACTERISTICS_WDM_DRIVER = 8192;
    public static final int IMAGE_DLLCHARACTERISTICS_TERMINAL_SERVER_AWARE = 32768;
}

