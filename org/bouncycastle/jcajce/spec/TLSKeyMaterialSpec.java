/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.spec;

import java.security.spec.KeySpec;
import org.bouncycastle.util.Arrays;

public class TLSKeyMaterialSpec
implements KeySpec {
    public static final String MASTER_SECRET = "master secret";
    public static final String KEY_EXPANSION = "key expansion";
    private final byte[] secret;
    private final String label;
    private final int length;
    private final byte[] seed;

    public TLSKeyMaterialSpec(byte[] byArray, String string, int n, byte[] ... byArray2) {
        this.secret = Arrays.clone(byArray);
        this.label = string;
        this.length = n;
        this.seed = Arrays.concatenate(byArray2);
    }

    public String getLabel() {
        return this.label;
    }

    public int getLength() {
        return this.length;
    }

    public byte[] getSecret() {
        return Arrays.clone(this.secret);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }
}

