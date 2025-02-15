/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.util.Arrays;

public enum SevenZMethod {
    COPY(new byte[]{0}),
    LZMA(new byte[]{3, 1, 1}),
    LZMA2(new byte[]{33}),
    DEFLATE(new byte[]{4, 1, 8}),
    DEFLATE64(new byte[]{4, 1, 9}),
    BZIP2(new byte[]{4, 2, 2}),
    AES256SHA256(new byte[]{6, -15, 7, 1}),
    BCJ_X86_FILTER(new byte[]{3, 3, 1, 3}),
    BCJ_PPC_FILTER(new byte[]{3, 3, 2, 5}),
    BCJ_IA64_FILTER(new byte[]{3, 3, 4, 1}),
    BCJ_ARM_FILTER(new byte[]{3, 3, 5, 1}),
    BCJ_ARM_THUMB_FILTER(new byte[]{3, 3, 7, 1}),
    BCJ_SPARC_FILTER(new byte[]{3, 3, 8, 5}),
    DELTA_FILTER(new byte[]{3});

    private final byte[] id;

    private SevenZMethod(byte[] id) {
        this.id = id;
    }

    byte[] getId() {
        byte[] copy = new byte[this.id.length];
        System.arraycopy(this.id, 0, copy, 0, this.id.length);
        return copy;
    }

    static SevenZMethod byId(byte[] id) {
        for (SevenZMethod m : (SevenZMethod[])SevenZMethod.class.getEnumConstants()) {
            if (!Arrays.equals(m.id, id)) continue;
            return m;
        }
        return null;
    }
}

