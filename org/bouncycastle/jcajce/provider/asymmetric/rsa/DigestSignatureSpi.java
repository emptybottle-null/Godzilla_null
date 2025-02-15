/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jcajce.provider.asymmetric.rsa;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.RIPEMD256Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.bouncycastle.util.Arrays;

public class DigestSignatureSpi
extends SignatureSpi {
    private Digest digest;
    private AsymmetricBlockCipher cipher;
    private AlgorithmIdentifier algId;

    protected DigestSignatureSpi(Digest digest, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.digest = digest;
        this.cipher = asymmetricBlockCipher;
        this.algId = null;
    }

    protected DigestSignatureSpi(ASN1ObjectIdentifier aSN1ObjectIdentifier, Digest digest, AsymmetricBlockCipher asymmetricBlockCipher) {
        this.digest = digest;
        this.cipher = asymmetricBlockCipher;
        this.algId = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
    }

    protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        if (!(publicKey instanceof RSAPublicKey)) {
            throw new InvalidKeyException("Supplied key (" + this.getType(publicKey) + ") is not a RSAPublicKey instance");
        }
        RSAKeyParameters rSAKeyParameters = RSAUtil.generatePublicKeyParameter((RSAPublicKey)publicKey);
        this.digest.reset();
        this.cipher.init(false, rSAKeyParameters);
    }

    protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        if (!(privateKey instanceof RSAPrivateKey)) {
            throw new InvalidKeyException("Supplied key (" + this.getType(privateKey) + ") is not a RSAPrivateKey instance");
        }
        RSAKeyParameters rSAKeyParameters = RSAUtil.generatePrivateKeyParameter((RSAPrivateKey)privateKey);
        this.digest.reset();
        this.cipher.init(true, rSAKeyParameters);
    }

    private String getType(Object object) {
        if (object == null) {
            return null;
        }
        return object.getClass().getName();
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
            byte[] byArray2 = this.derEncode(byArray);
            return this.cipher.processBlock(byArray2, 0, byArray2.length);
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new SignatureException("key too small for signature type");
        } catch (Exception exception) {
            throw new SignatureException(exception.toString());
        }
    }

    protected boolean engineVerify(byte[] byArray) throws SignatureException {
        byte[] byArray2;
        byte[] byArray3;
        byte[] byArray4 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray4, 0);
        try {
            byArray3 = this.cipher.processBlock(byArray, 0, byArray.length);
            byArray2 = this.derEncode(byArray4);
        } catch (Exception exception) {
            return false;
        }
        if (byArray3.length == byArray2.length) {
            return Arrays.constantTimeAreEqual(byArray3, byArray2);
        }
        if (byArray3.length == byArray2.length - 2) {
            int n;
            byArray2[1] = (byte)(byArray2[1] - 2);
            byArray2[3] = (byte)(byArray2[3] - 2);
            int n2 = 4 + byArray2[3];
            int n3 = n2 + 2;
            int n4 = 0;
            for (n = 0; n < byArray2.length - n3; ++n) {
                n4 |= byArray3[n2 + n] ^ byArray2[n3 + n];
            }
            for (n = 0; n < n2; ++n) {
                n4 |= byArray3[n] ^ byArray2[n];
            }
            return n4 == 0;
        }
        Arrays.constantTimeAreEqual(byArray2, byArray2);
        return false;
    }

    protected void engineSetParameter(AlgorithmParameterSpec algorithmParameterSpec) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected void engineSetParameter(String string, Object object) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    protected Object engineGetParameter(String string) {
        return null;
    }

    protected AlgorithmParameters engineGetParameters() {
        return null;
    }

    private byte[] derEncode(byte[] byArray) throws IOException {
        if (this.algId == null) {
            return byArray;
        }
        DigestInfo digestInfo = new DigestInfo(this.algId, byArray);
        return digestInfo.getEncoded("DER");
    }

    public static class MD2
    extends DigestSignatureSpi {
        public MD2() {
            super(PKCSObjectIdentifiers.md2, new MD2Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD4
    extends DigestSignatureSpi {
        public MD4() {
            super(PKCSObjectIdentifiers.md4, new MD4Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class MD5
    extends DigestSignatureSpi {
        public MD5() {
            super(PKCSObjectIdentifiers.md5, DigestFactory.createMD5(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD128
    extends DigestSignatureSpi {
        public RIPEMD128() {
            super(TeleTrusTObjectIdentifiers.ripemd128, new RIPEMD128Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD160
    extends DigestSignatureSpi {
        public RIPEMD160() {
            super(TeleTrusTObjectIdentifiers.ripemd160, new RIPEMD160Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class RIPEMD256
    extends DigestSignatureSpi {
        public RIPEMD256() {
            super(TeleTrusTObjectIdentifiers.ripemd256, new RIPEMD256Digest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA1
    extends DigestSignatureSpi {
        public SHA1() {
            super(OIWObjectIdentifiers.idSHA1, DigestFactory.createSHA1(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA224
    extends DigestSignatureSpi {
        public SHA224() {
            super(NISTObjectIdentifiers.id_sha224, DigestFactory.createSHA224(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA256
    extends DigestSignatureSpi {
        public SHA256() {
            super(NISTObjectIdentifiers.id_sha256, DigestFactory.createSHA256(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA384
    extends DigestSignatureSpi {
        public SHA384() {
            super(NISTObjectIdentifiers.id_sha384, DigestFactory.createSHA384(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA3_224
    extends DigestSignatureSpi {
        public SHA3_224() {
            super(NISTObjectIdentifiers.id_sha3_224, DigestFactory.createSHA3_224(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA3_256
    extends DigestSignatureSpi {
        public SHA3_256() {
            super(NISTObjectIdentifiers.id_sha3_256, DigestFactory.createSHA3_256(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA3_384
    extends DigestSignatureSpi {
        public SHA3_384() {
            super(NISTObjectIdentifiers.id_sha3_384, DigestFactory.createSHA3_384(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA3_512
    extends DigestSignatureSpi {
        public SHA3_512() {
            super(NISTObjectIdentifiers.id_sha3_512, DigestFactory.createSHA3_512(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA512
    extends DigestSignatureSpi {
        public SHA512() {
            super(NISTObjectIdentifiers.id_sha512, DigestFactory.createSHA512(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA512_224
    extends DigestSignatureSpi {
        public SHA512_224() {
            super(NISTObjectIdentifiers.id_sha512_224, DigestFactory.createSHA512_224(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class SHA512_256
    extends DigestSignatureSpi {
        public SHA512_256() {
            super(NISTObjectIdentifiers.id_sha512_256, DigestFactory.createSHA512_256(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }

    public static class noneRSA
    extends DigestSignatureSpi {
        public noneRSA() {
            super(new NullDigest(), new PKCS1Encoding(new RSABlindedEngine()));
        }
    }
}

