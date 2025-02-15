/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import org.bouncycastle.crypto.CharToByteConverter;
import org.bouncycastle.jcajce.PBKDFKey;

public class PBKDF1Key
implements PBKDFKey {
    private final char[] password;
    private final CharToByteConverter converter;

    public PBKDF1Key(char[] cArray, CharToByteConverter charToByteConverter) {
        this.password = new char[cArray.length];
        this.converter = charToByteConverter;
        System.arraycopy(cArray, 0, this.password, 0, cArray.length);
    }

    public char[] getPassword() {
        return this.password;
    }

    public String getAlgorithm() {
        return "PBKDF1";
    }

    public String getFormat() {
        return this.converter.getType();
    }

    public byte[] getEncoded() {
        return this.converter.convert(this.password);
    }
}

