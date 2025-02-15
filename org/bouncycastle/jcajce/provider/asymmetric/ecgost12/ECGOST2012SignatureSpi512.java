/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.ecgost12;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.DSA;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.GOST3411_2012_512Digest;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.ECGOST3410_2012Signer;
import org.bouncycastle.jcajce.provider.asymmetric.ecgost12.BCECGOST3410_2012PublicKey;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jce.interfaces.ECKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECGOST2012SignatureSpi512
extends SignatureSpi
implements PKCSObjectIdentifiers,
X509ObjectIdentifiers {
    private Digest digest = new GOST3411_2012_512Digest();
    private DSA signer = new ECGOST3410_2012Signer();
    private int size = 128;
    private int halfSize = 64;

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        AsymmetricKeyParameter asymmetricKeyParameter;
        if (publicKey instanceof ECPublicKey) {
            asymmetricKeyParameter = ECGOST2012SignatureSpi512.generatePublicKeyParameter(publicKey);
        } else {
            try {
                byte[] byArray = publicKey.getEncoded();
                publicKey = BouncyCastleProvider.getPublicKey(SubjectPublicKeyInfo.getInstance(byArray));
                asymmetricKeyParameter = ECUtil.generatePublicKeyParameter(publicKey);
            } catch (Exception exception) {
                throw new InvalidKeyException("cannot recognise key type in ECGOST-2012-512 signer");
            }
        }
        this.digest.reset();
        this.signer.init(false, asymmetricKeyParameter);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof ECKey)) {
            throw new InvalidKeyException("cannot recognise key type in ECGOST-2012-512 signer");
        }
        AsymmetricKeyParameter asymmetricKeyParameter = ECUtil.generatePrivateKeyParameter(privateKey);
        this.digest.reset();
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
            byte[] byArray2 = new byte[this.size];
            BigInteger[] bigIntegerArray = this.signer.generateSignature(byArray);
            byte[] byArray3 = bigIntegerArray[0].toByteArray();
            byte[] byArray4 = bigIntegerArray[1].toByteArray();
            if (byArray4[0] != 0) {
                System.arraycopy(byArray4, 0, byArray2, this.halfSize - byArray4.length, byArray4.length);
            } else {
                System.arraycopy(byArray4, 1, byArray2, this.halfSize - (byArray4.length - 1), byArray4.length - 1);
            }
            if (byArray3[0] != 0) {
                System.arraycopy(byArray3, 0, byArray2, this.size - byArray3.length, byArray3.length);
            } else {
                System.arraycopy(byArray3, 1, byArray2, this.size - (byArray3.length - 1), byArray3.length - 1);
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
            byte[] byArray3 = new byte[this.halfSize];
            byte[] byArray4 = new byte[this.halfSize];
            System.arraycopy(byArray, 0, byArray4, 0, this.halfSize);
            System.arraycopy(byArray, this.halfSize, byArray3, 0, this.halfSize);
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

    static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey publicKey) throws InvalidKeyException {
        return publicKey instanceof BCECGOST3410_2012PublicKey ? ((BCECGOST3410_2012PublicKey)publicKey).engineGetKeyParameters() : ECUtil.generatePublicKeyParameter(publicKey);
    }
}

