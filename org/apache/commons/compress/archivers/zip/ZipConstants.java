/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

final class ZipConstants {
    static final int BYTE_MASK = 255;
    static final int SHORT = 2;
    static final int WORD = 4;
    static final int DWORD = 8;
    static final int INITIAL_VERSION = 10;
    static final int DEFLATE_MIN_VERSION = 20;
    static final int DATA_DESCRIPTOR_MIN_VERSION = 20;
    static final int ZIP64_MIN_VERSION = 45;
    static final int ZIP64_MAGIC_SHORT = 65535;
    static final long ZIP64_MAGIC = 0xFFFFFFFFL;

    private ZipConstants() {
    }
}

