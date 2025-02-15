/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import javax.crypto.interfaces.PBEKey;
import org.bouncycastle.jcajce.PKCS12Key;
import org.bouncycastle.util.Arrays;

public class PKCS12KeyWithParameters
extends PKCS12Key
implements PBEKey {
    private final byte[] salt;
    private final int iterationCount;

    public PKCS12KeyWithParameters(char[] cArray, byte[] byArray, int n) {
        super(cArray);
        this.salt = Arrays.clone(byArray);
        this.iterationCount = n;
    }

    public PKCS12KeyWithParameters(char[] cArray, boolean bl, byte[] byArray, int n) {
        super(cArray, bl);
        this.salt = Arrays.clone(byArray);
        this.iterationCount = n;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public int getIterationCount() {
        return this.iterationCount;
    }
}

