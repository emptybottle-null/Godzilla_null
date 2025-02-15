/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.jcajce.provider.symmetric.util.BCPBEKey;
import org.bouncycastle.jcajce.provider.symmetric.util.BaseSecretKeyFactory;
import org.bouncycastle.jcajce.provider.symmetric.util.PBE;

public class PBESecretKeyFactory
extends BaseSecretKeyFactory
implements PBE {
    private boolean forCipher;
    private int scheme;
    private int digest;
    private int keySize;
    private int ivSize;

    public PBESecretKeyFactory(String string, ASN1ObjectIdentifier aSN1ObjectIdentifier, boolean bl, int n, int n2, int n3, int n4) {
        super(string, aSN1ObjectIdentifier);
        this.forCipher = bl;
        this.scheme = n;
        this.digest = n2;
        this.keySize = n3;
        this.ivSize = n4;
    }

    protected SecretKey engineGenerateSecret(KeySpec keySpec) throws InvalidKeySpecException {
        if (keySpec instanceof PBEKeySpec) {
            PBEKeySpec pBEKeySpec = (PBEKeySpec)keySpec;
            if (pBEKeySpec.getSalt() == null) {
                return new BCPBEKey(this.algName, this.algOid, this.scheme, this.digest, this.keySize, this.ivSize, pBEKeySpec, null);
            }
            CipherParameters cipherParameters = this.forCipher ? PBE.Util.makePBEParameters(pBEKeySpec, this.scheme, this.digest, this.keySize, this.ivSize) : PBE.Util.makePBEMacParameters(pBEKeySpec, this.scheme, this.digest, this.keySize);
            return new BCPBEKey(this.algName, this.algOid, this.scheme, this.digest, this.keySize, this.ivSize, pBEKeySpec, cipherParameters);
        }
        throw new InvalidKeySpecException("Invalid KeySpec");
    }
}

