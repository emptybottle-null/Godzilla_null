/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import org.bouncycastle.crypto.tls.TlsUtils;

public class UseSRTPData {
    protected int[] protectionProfiles;
    protected byte[] mki;

    public UseSRTPData(int[] nArray, byte[] byArray) {
        if (nArray == null || nArray.length < 1 || nArray.length >= 32768) {
            throw new IllegalArgumentException("'protectionProfiles' must have length from 1 to (2^15 - 1)");
        }
        if (byArray == null) {
            byArray = TlsUtils.EMPTY_BYTES;
        } else if (byArray.length > 255) {
            throw new IllegalArgumentException("'mki' cannot be longer than 255 bytes");
        }
        this.protectionProfiles = nArray;
        this.mki = byArray;
    }

    public int[] getProtectionProfiles() {
        return this.protectionProfiles;
    }

    public byte[] getMki() {
        return this.mki;
    }
}

