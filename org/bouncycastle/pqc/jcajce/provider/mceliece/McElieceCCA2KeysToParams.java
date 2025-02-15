/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.mceliece;

import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.jcajce.provider.mceliece.BCMcElieceCCA2PrivateKey;
import org.bouncycastle.pqc.jcajce.provider.mceliece.BCMcElieceCCA2PublicKey;

public class McElieceCCA2KeysToParams {
    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey instanceof BCMcElieceCCA2PublicKey) {
            BCMcElieceCCA2PublicKey bCMcElieceCCA2PublicKey = (BCMcElieceCCA2PublicKey)publicKey;
            return bCMcElieceCCA2PublicKey.getKeyParams();
        }
        throw new InvalidKeyException("can't identify McElieceCCA2 public key: " + publicKey.getClass().getName());
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey privateKey) throws InvalidKeyException {
        if (privateKey instanceof BCMcElieceCCA2PrivateKey) {
            BCMcElieceCCA2PrivateKey bCMcElieceCCA2PrivateKey = (BCMcElieceCCA2PrivateKey)privateKey;
            return bCMcElieceCCA2PrivateKey.getKeyParams();
        }
        throw new InvalidKeyException("can't identify McElieceCCA2 private key.");
    }
}

