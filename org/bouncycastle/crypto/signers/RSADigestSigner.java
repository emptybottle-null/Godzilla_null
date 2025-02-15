/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.signers;

import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;

public class RSADigestSigner
implements Signer {
    private final AsymmetricBlockCipher rsaEngine = new PKCS1Encoding(new RSABlindedEngine());
    private final AlgorithmIdentifier algId;
    private final Digest digest;
    private boolean forSigning;
    private static final Hashtable oidMap = new Hashtable();

    public RSADigestSigner(Digest digest) {
        this(digest, (ASN1ObjectIdentifier)oidMap.get(digest.getAlgorithmName()));
    }

    public RSADigestSigner(Digest digest, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.digest = digest;
        this.algId = new AlgorithmIdentifier(aSN1ObjectIdentifier, DERNull.INSTANCE);
    }

    public String getAlgorithmName() {
        return this.digest.getAlgorithmName() + "withRSA";
    }

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forSigning = bl;
        AsymmetricKeyParameter asymmetricKeyParameter = cipherParameters instanceof ParametersWithRandom ? (AsymmetricKeyParameter)((ParametersWithRandom)cipherParameters).getParameters() : (AsymmetricKeyParameter)cipherParameters;
        if (bl && !asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("signing requires private key");
        }
        if (!bl && asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("verification requires public key");
        }
        this.reset();
        this.rsaEngine.init(bl, cipherParameters);
    }

    public void update(byte by) {
        this.digest.update(by);
    }

    public void update(byte[] byArray, int n, int n2) {
        this.digest.update(byArray, n, n2);
    }

    public byte[] generateSignature() throws CryptoException, DataLengthException {
        if (!this.forSigning) {
            throw new IllegalStateException("RSADigestSigner not initialised for signature generation.");
        }
        byte[] byArray = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray, 0);
        try {
            byte[] byArray2 = this.derEncode(byArray);
            return this.rsaEngine.processBlock(byArray2, 0, byArray2.length);
        } catch (IOException iOException) {
            throw new CryptoException("unable to encode signature: " + iOException.getMessage(), iOException);
        }
    }

    public boolean verifySignature(byte[] byArray) {
        byte[] byArray2;
        byte[] byArray3;
        if (this.forSigning) {
            throw new IllegalStateException("RSADigestSigner not initialised for verification");
        }
        byte[] byArray4 = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(byArray4, 0);
        try {
            byArray3 = this.rsaEngine.processBlock(byArray, 0, byArray.length);
            byArray2 = this.derEncode(byArray4);
        } catch (Exception exception) {
            return false;
        }
        if (byArray3.length == byArray2.length) {
            return Arrays.constantTimeAreEqual(byArray3, byArray2);
        }
        if (byArray3.length == byArray2.length - 2) {
            int n;
            int n2 = byArray3.length - byArray4.length - 2;
            int n3 = byArray2.length - byArray4.length - 2;
            byArray2[1] = (byte)(byArray2[1] - 2);
            byArray2[3] = (byte)(byArray2[3] - 2);
            int n4 = 0;
            for (n = 0; n < byArray4.length; ++n) {
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

    public void reset() {
        this.digest.reset();
    }

    private byte[] derEncode(byte[] byArray) throws IOException {
        DigestInfo digestInfo = new DigestInfo(this.algId, byArray);
        return digestInfo.getEncoded("DER");
    }

    static {
        oidMap.put("RIPEMD128", TeleTrusTObjectIdentifiers.ripemd128);
        oidMap.put("RIPEMD160", TeleTrusTObjectIdentifiers.ripemd160);
        oidMap.put("RIPEMD256", TeleTrusTObjectIdentifiers.ripemd256);
        oidMap.put("SHA-1", X509ObjectIdentifiers.id_SHA1);
        oidMap.put("SHA-224", NISTObjectIdentifiers.id_sha224);
        oidMap.put("SHA-256", NISTObjectIdentifiers.id_sha256);
        oidMap.put("SHA-384", NISTObjectIdentifiers.id_sha384);
        oidMap.put("SHA-512", NISTObjectIdentifiers.id_sha512);
        oidMap.put("SHA-512/224", NISTObjectIdentifiers.id_sha512_224);
        oidMap.put("SHA-512/256", NISTObjectIdentifiers.id_sha512_256);
        oidMap.put("SHA3-224", NISTObjectIdentifiers.id_sha3_224);
        oidMap.put("SHA3-256", NISTObjectIdentifiers.id_sha3_256);
        oidMap.put("SHA3-384", NISTObjectIdentifiers.id_sha3_384);
        oidMap.put("SHA3-512", NISTObjectIdentifiers.id_sha3_512);
        oidMap.put("MD2", PKCSObjectIdentifiers.md2);
        oidMap.put("MD4", PKCSObjectIdentifiers.md4);
        oidMap.put("MD5", PKCSObjectIdentifiers.md5);
    }
}

