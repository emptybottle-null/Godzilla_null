/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.symmetric.util;

import javax.crypto.interfaces.PBEKey;
import javax.crypto.spec.PBEKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

public class BCPBEKey
implements PBEKey {
    String algorithm;
    ASN1ObjectIdentifier oid;
    int type;
    int digest;
    int keySize;
    int ivSize;
    CipherParameters param;
    PBEKeySpec pbeKeySpec;
    boolean tryWrong = false;

    public BCPBEKey(String string, ASN1ObjectIdentifier aSN1ObjectIdentifier, int n, int n2, int n3, int n4, PBEKeySpec pBEKeySpec, CipherParameters cipherParameters) {
        this.algorithm = string;
        this.oid = aSN1ObjectIdentifier;
        this.type = n;
        this.digest = n2;
        this.keySize = n3;
        this.ivSize = n4;
        this.pbeKeySpec = pBEKeySpec;
        this.param = cipherParameters;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public String getFormat() {
        return "RAW";
    }

    public byte[] getEncoded() {
        if (this.param != null) {
            KeyParameter keyParameter = this.param instanceof ParametersWithIV ? (KeyParameter)((ParametersWithIV)this.param).getParameters() : (KeyParameter)this.param;
            return keyParameter.getKey();
        }
        if (this.type == 2) {
            return PBEParametersGenerator.PKCS12PasswordToBytes(this.pbeKeySpec.getPassword());
        }
        if (this.type == 5) {
            return PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(this.pbeKeySpec.getPassword());
        }
        return PBEParametersGenerator.PKCS5PasswordToBytes(this.pbeKeySpec.getPassword());
    }

    int getType() {
        return this.type;
    }

    int getDigest() {
        return this.digest;
    }

    int getKeySize() {
        return this.keySize;
    }

    public int getIvSize() {
        return this.ivSize;
    }

    public CipherParameters getParam() {
        return this.param;
    }

    public char[] getPassword() {
        return this.pbeKeySpec.getPassword();
    }

    public byte[] getSalt() {
        return this.pbeKeySpec.getSalt();
    }

    public int getIterationCount() {
        return this.pbeKeySpec.getIterationCount();
    }

    public ASN1ObjectIdentifier getOID() {
        return this.oid;
    }

    public void setTryWrongPKCS12Zero(boolean bl) {
        this.tryWrong = bl;
    }

    boolean shouldTryWrongPKCS12() {
        return this.tryWrong;
    }
}

