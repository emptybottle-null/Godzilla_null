/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.gost;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.GOST3411Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.GOST3410Signer;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.asymmetric.util.GOST3410Util;
import org.bouncycastle.jce.interfaces.ECKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.interfaces.GOST3410Key;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SignatureSpi
extends java.security.SignatureSpi
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers {
    private Digest digest = new GOST3411Digest();
    private DSA signer = new GOST3410Signer();
    private SecureRandom random;

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (publicKey instanceof ECPublicKey) {
            asymmetricKeyParameter = ECUtil.generatePublicKeyParameter(publicKey);
        } else if (publicKey instanceof GOST3410Key) {
            asymmetricKeyParameter = GOST3410Util.generatePublicKeyParameter(publicKey);
        } else {
            try {
                byte[] byArray = publicKey.getEncoded();
                publicKey = BouncyCastleProvider.getPublicKey(SubjectPublicKeyInfo.getInstance(byArray));
                if (!(publicKey instanceof ECPublicKey)) {
                    throw new InvalidKeyException("can't recognise key type in DSA based signer");
                }
                asymmetricKeyParameter = ECUtil.generatePublicKeyParameter(publicKey);
            } catch (Exception exception) {
                throw new InvalidKeyException("can't recognise key type in DSA based signer");
            }
        }
        this.digest.reset();
        this.signer.init(false, asymmetricKeyParameter);
    }

    protected void engineInitSign(PrivateKey privateKey, SecureRandom secureRandom) throws InvalidKeyException {
        this.random = secureRandom;
        this.engineInitSign(privateKey);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter = privateKey instanceof ECKey ? ECUtil.generatePrivateKeyParameter(privateKey) : GOST3410Util.generatePrivateKeyParameter(privateKey);
        this.digest.reset();
        if (this.random != null) {
            this.signer.init(true, new ParametersWithRandom(asymmetricKeyParameter, this.random));
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
            byte[] byArray2 = new byte[64];
            BigInteger[] bigIntegerArray = this.signer.generateSignature(byArray);
            byte[] byArray3 = bigIntegerArray[0].toByteArray();
            byte[] byArray4 = bigIntegerArray[1].toByteArray();
            if (byArray4[0] != 0) {
                System.arraycopy(byArray4, 0, byArray2, 32 - byArray4.length, byArray4.length);
            } else {
                System.arraycopy(byArray4, 1, byArray2, 32 - (byArray4.length - 1), byArray4.length - 1);
            }
            if (byArray3[0] != 0) {
                System.arraycopy(byArray3, 0, byArray2, 64 - byArray3.length, byArray3.length);
            } else {
                System.arraycopy(byArray3, 1, byArray2, 64 - (byArray3.length - 1), byArray3.length - 1);
            }
            return byArray2;
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        BigInteger[] bigIntegerArray;
        byte[] byArray2 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray2, 0);
        try {
            byte[] byArray3 = new byte[32];
            byte[] byArray4 = new byte[32];
            System.arraycopy(byArray, 0, byArray4, 0, 32);
            System.arraycopy(byArray, 32, byArray3, 0, 32);
            bigIntegerArray = new BigInteger[]{new BigInteger(1, byArray3), new BigInteger(1, byArray4)};
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

