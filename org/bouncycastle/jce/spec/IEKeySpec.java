/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.spec;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import org.bouncycastle.jce.interfaces.IESKey;

public class IEKeySpec
implements KeySpec,
IESKey {
    private PublicKey pubKey;
    private PrivateKey privKey;

    public IEKeySpec(PrivateKey privateKey, PublicKey publicKey) {
        this.privKey = privateKey;
        this.pubKey = publicKey;
    }

    public PublicKey getPublic() {
        return this.pubKey;
    }

    public PrivateKey getPrivate() {
        return this.privKey;
    }

    public String getAlgorithm() {
        return "IES";
    }

    public String getFormat() {
        return null;
    }

    public byte[] getEncoded() {
        return null;
    }
}

