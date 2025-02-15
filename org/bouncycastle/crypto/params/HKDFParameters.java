/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.util.Arrays;

public class HKDFParameters
implements DerivationParameters {
    private final byte[] ikm;
    private final boolean skipExpand;
    private final byte[] salt;
    private final byte[] info;

    private HKDFParameters(byte[] byArray, boolean bl, byte[] byArray2, byte[] byArray3) {
        if (byArray == null) {
            throw new IllegalArgumentException("IKM (input keying material) should not be null");
        }
        this.ikm = Arrays.clone(byArray);
        this.skipExpand = bl;
        this.salt = (byte[])(byArray2 == null || byArray2.length == 0 ? null : Arrays.clone(byArray2));
        this.info = byArray3 == null ? new byte[0] : Arrays.clone(byArray3);
    }

    public HKDFParameters(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        this(byArray, false, byArray2, byArray3);
    }

    public static HKDFParameters skipExtractParameters(byte[] byArray, byte[] byArray2) {
        return new HKDFParameters(byArray, true, null, byArray2);
    }

    public static HKDFParameters defaultParameters(byte[] byArray) {
        return new HKDFParameters(byArray, false, null, null);
    }

    public byte[] getIKM() {
        return Arrays.clone(this.ikm);
    }

    public boolean skipExtract() {
        return this.skipExpand;
    }

    public byte[] getSalt() {
        return Arrays.clone(this.salt);
    }

    public byte[] getInfo() {
        return Arrays.clone(this.info);
    }
}

