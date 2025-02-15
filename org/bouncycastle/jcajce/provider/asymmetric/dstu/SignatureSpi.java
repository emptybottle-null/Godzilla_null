/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.dstu;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.DSTU4145Signer;
import org.bouncycastle.jcajce.provider.asymmetric.dstu.BCDSTU4145PublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.interfaces.ECKey;

public class SignatureSpi
extends java.security.SignatureSpi
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers {
    private Digest digest;
    private DSA signer = new DSTU4145Signer();
    private static byte[] DEFAULT_SBOX = new byte[]{10, 9, 13, 6, 14, 11, 4, 5, 15, 1, 3, 12, 7, 0, 8, 2, 8, 0, 12, 4, 9, 6, 7, 11, 2, 3, 1, 15, 5, 14, 10, 13, 15, 6, 5, 8, 14, 11, 10, 4, 12, 0, 3, 7, 2, 9, 1, 13, 3, 8, 13, 9, 6, 11, 15, 0, 2, 5, 12, 10, 4, 14, 1, 7, 15, 8, 14, 9, 7, 2, 0, 13, 12, 6, 1, 5, 11, 4, 3, 10, 2, 8, 9, 7, 5, 15, 0, 11, 12, 1, 13, 14, 10, 3, 6, 4, 3, 8, 11, 5, 6, 4, 14, 10, 2, 12, 1, 7, 9, 15, 13, 0, 1, 2, 3, 14, 6, 13, 11, 8, 15, 10, 12, 5, 7, 9, 0, 4};

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter = publicKey instanceof BCDSTU4145PublicKey ? ((BCDSTU4145PublicKey)publicKey).engineGetKeyParameters() : ECUtil.generatePublicKeyParameter(publicKey);
        this.digest = new GOST3411Digest(this.expandSbox(((BCDSTU4145PublicKey)publicKey).getSbox()));
        this.signer.init(false, asymmetricKeyParameter);
    }

    byte[] expandSbox(byte[] byArray) {
        byte[] byArray2 = new byte[128];
        for (int i = 0; i < byArray.length; ++i) {
            byArray2[i * 2] = (byte)(byArray[i] >> 4 & 0xF);
            byArray2[i * 2 + 1] = (byte)(byArray[i] & 0xF);
        }
        return byArray2;
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter = null;
        if (privateKey instanceof ECKey) {
            asymmetricKeyParameter = ECUtil.generatePrivateKeyParameter(privateKey);
        }
        this.digest = new GOST3411Digest(DEFAULT_SBOX);
        if (this.appRandom != null) {
            this.signer.init(true, new ParametersWithRandom(asymmetricKeyParameter, this.appRandom));
        } else {
            this.signer.init(true, asymmetricKeyParameter);
        }
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
            byte[] byArray2 = bigIntegerArray[0].toByteArray();
            byte[] byArray3 = bigIntegerArray[1].toByteArray();
            byte[] byArray4 = new byte[byArray2.length > byArray3.length ? byArray2.length * 2 : byArray3.length * 2];
            System.arraycopy(byArray3, 0, byArray4, byArray4.length / 2 - byArray3.length, byArray3.length);
            System.arraycopy(byArray2, 0, byArray4, byArray4.length - byArray2.length, byArray2.length);
            return new DEROctetString(byArray4).getEncoded();
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        BigInteger[] bigIntegerArray;
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray2, 0);
        try {
            byte[] byArray3 = ((ASN1OctetString)ASN1OctetString.fromByteArray(byArray)).getOctets();
            byte[] byArray4 = new byte[byArray3.length / 2];
            byte[] byArray5 = new byte[byArray3.length / 2];
            System.arraycopy(byArray3, 0, byArray5, 0, byArray3.length / 2);
            System.arraycopy(byArray3, byArray3.length / 2, byArray4, 0, byArray3.length / 2);
            bigIntegerArray = new BigInteger[]{new BigInteger(1, byArray4), new BigInteger(1, byArray5)};
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

