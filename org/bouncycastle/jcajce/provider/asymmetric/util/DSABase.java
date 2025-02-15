/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.jcajce.provider.asymmetric.util.DSAEncoder;

public abstract class DSABase
extends SignatureSpi
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers {
    protected Digest digest;
    protected DSA signer;
    protected DSAEncoder encoder;

    protected DSABase(Digest digest, DSA dSA, DSAEncoder dSAEncoder) {
        this.digest = digest;
        this.signer = dSA;
        this.encoder = dSAEncoder;
    }

    protected void engineUpdate(byte by) throws SignatureException {
        this.digest.update(by);
    }

    protected void engineUpdate(byte[] byArray, int n, int n2) throws SignatureException {
        this.digest.update(byArray, n, n2);
    }

    protected byte[] engineSign() throws SignatureException {
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray, 0);
        try {
            BigInteger[] bigIntegerArray = this.signer.generateSignature(byArray);
            return this.encoder.encode(bigIntegerArray[0], bigIntegerArray[1]);
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        BigInteger[] bigIntegerArray;
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray2, 0);
        try {
            bigIntegerArray = this.encoder.decode(byArray);
        } catch (Exception exception) {
            throw new SignatureException("error decoding signature bytes.");
        }
        return this.signer.verifySignature(byArray2, bigIntegerArray[0], bigIntegerArray[1]);
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected void engineSetParameter(String string, Object object) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String string) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }
}

