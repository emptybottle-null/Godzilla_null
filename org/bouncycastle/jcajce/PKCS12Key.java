/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.jcajce.PBKDFKey;

public class PKCS12Key
implements PBKDFKey {
    private final char[] password;
    private final boolean useWrongZeroLengthConversion;

    public PKCS12Key(char[] cArray) {
        this(cArray, false);
    }

    public PKCS12Key(char[] cArray, boolean bl) {
        if (cArray == null) {
            cArray = new char[]{};
        }
        this.password = new char[cArray.length];
        this.useWrongZeroLengthConversion = bl;
        System.arraycopy(cArray, 0, this.password, 0, cArray.length);
    }

    public char[] getPassword() {
        return this.password;
    }

    public String getAlgorithm() {
        return "PKCS12";
    }

    public String getFormat() {
        return "PKCS12";
    }

    public byte[] getEncoded() {
        if (this.useWrongZeroLengthConversion && this.password.length == 0) {
            return new byte[2];
        }
        return PBEParametersGenerator.PKCS12PasswordToBytes(this.password);
    }
}

