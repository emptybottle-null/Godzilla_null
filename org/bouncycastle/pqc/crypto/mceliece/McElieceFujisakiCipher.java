/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.pqc.crypto.MessageEncryptor;
import org.bouncycastle.pqc.crypto.mceliece.Conversions;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2KeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2Primitives;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.Utils;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;

public class McElieceFujisakiCipher
implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.1";
    private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
    private Digest messDigest;
    private SecureRandom sr;
    private int n;
    private int k;
    private int t;
    McElieceCCA2KeyParameters key;
    private boolean forEncryption;

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forEncryption = bl;
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.sr = parametersWithRandom.getRandom();
                this.key = (McElieceCCA2PublicKeyParameters)parametersWithRandom.getParameters();
                this.initCipherEncrypt((McElieceCCA2PublicKeyParameters)this.key);
            } else {
                this.sr = new SecureRandom();
                this.key = (McElieceCCA2PublicKeyParameters)cipherParameters;
                this.initCipherEncrypt((McElieceCCA2PublicKeyParameters)this.key);
            }
        } else {
            this.key = (McElieceCCA2PrivateKeyParameters)cipherParameters;
            this.initCipherDecrypt((McElieceCCA2PrivateKeyParameters)this.key);
        }
    }

    public int getKeySize(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) throws IllegalArgumentException {
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters)mcElieceCCA2KeyParameters).getN();
        }
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters)mcElieceCCA2KeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
        this.sr = this.sr != null ? this.sr : new SecureRandom();
        this.messDigest = Utils.getDigest(mcElieceCCA2PublicKeyParameters.getDigest());
        this.n = mcElieceCCA2PublicKeyParameters.getN();
        this.k = mcElieceCCA2PublicKeyParameters.getK();
        this.t = mcElieceCCA2PublicKeyParameters.getT();
    }

    private void initCipherDecrypt(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters) {
        this.messDigest = Utils.getDigest(mcElieceCCA2PrivateKeyParameters.getDigest());
        this.n = mcElieceCCA2PrivateKeyParameters.getN();
        this.t = mcElieceCCA2PrivateKeyParameters.getT();
    }

    public byte[] messageEncrypt(byte[] byArray) {
        if (!this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        GF2Vector gF2Vector = new GF2Vector(this.k, this.sr);
        byte[] byArray2 = gF2Vector.getEncoded();
        byte[] byArray3 = ByteUtils.concatenate(byArray2, byArray);
        this.messDigest.update(byArray3, 0, byArray3.length);
        byte[] byArray4 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(byArray4, 0);
        GF2Vector gF2Vector2 = Conversions.encode(this.n, this.t, byArray4);
        byte[] byArray5 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters)this.key, gF2Vector, gF2Vector2).getEncoded();
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(byArray2);
        byte[] byArray6 = new byte[byArray.length];
        digestRandomGenerator.nextBytes(byArray6);
        for (int i = 0; i < byArray.length; ++i) {
            int n = i;
            byArray6[n] = (byte)(byArray6[n] ^ byArray[i]);
        }
        return ByteUtils.concatenate(byArray5, byArray6);
    }

    public byte[] messageDecrypt(byte[] byArray) throws InvalidCipherTextException {
        if (this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int n = this.n + 7 >> 3;
        int n2 = byArray.length - n;
        byte[][] byArray2 = ByteUtils.split(byArray, n);
        byte[] byArray3 = byArray2[0];
        byte[] byArray4 = byArray2[1];
        GF2Vector gF2Vector = GF2Vector.OS2VP(this.n, byArray3);
        GF2Vector[] gF2VectorArray = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters)this.key, gF2Vector);
        byte[] byArray5 = gF2VectorArray[0].getEncoded();
        GF2Vector gF2Vector2 = gF2VectorArray[1];
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(byArray5);
        byte[] byArray6 = new byte[n2];
        digestRandomGenerator.nextBytes(byArray6);
        for (int i = 0; i < n2; ++i) {
            int n3 = i;
            byArray6[n3] = (byte)(byArray6[n3] ^ byArray4[i]);
        }
        byte[] byArray7 = ByteUtils.concatenate(byArray5, byArray6);
        byte[] byArray8 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(byArray7, 0, byArray7.length);
        this.messDigest.doFinal(byArray8, 0);
        gF2Vector = Conversions.encode(this.n, this.t, byArray8);
        if (!gF2Vector.equals(gF2Vector2)) {
            throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
        }
        return byArray6;
    }
}

