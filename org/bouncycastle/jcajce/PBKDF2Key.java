/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce;

import org.bouncycastle.crypto.CharToByteConverter;
import org.bouncycastle.jcajce.PBKDFKey;
import org.bouncycastle.util.Arrays;

public class PBKDF2Key
implements PBKDFKey {
    private final char[] password;
    private final CharToByteConverter converter;

    public PBKDF2Key(char[] cArray, CharToByteConverter charToByteConverter) {
        this.password = Arrays.clone(cArray);
        this.converter = charToByteConverter;
    }

    public char[] getPassword() {
        return this.password;
    }

    public String getAlgorithm() {
        return "PBKDF2";
    }

    public String getFormat() {
        return this.converter.getType();
    }

    public byte[] getEncoded() {
        return this.converter.convert(this.password);
    }
}

