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

public class McEliecePointchevalCipher
implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.2";
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

    protected int decryptOutputSize(int n) {
        return 0;
    }

    protected int encryptOutputSize(int n) {
        return 0;
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
        this.k = mcElieceCCA2PrivateKeyParameters.getK();
        this.t = mcElieceCCA2PrivateKeyParameters.getT();
    }

    public byte[] messageEncrypt(byte[] byArray) {
        int n;
        if (!this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int n2 = this.k >> 3;
        byte[] byArray2 = new byte[n2];
        this.sr.nextBytes(byArray2);
        GF2Vector gF2Vector = new GF2Vector(this.k, this.sr);
        byte[] byArray3 = gF2Vector.getEncoded();
        byte[] byArray4 = ByteUtils.concatenate(byArray, byArray2);
        this.messDigest.update(byArray4, 0, byArray4.length);
        byte[] byArray5 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(byArray5, 0);
        GF2Vector gF2Vector2 = Conversions.encode(this.n, this.t, byArray5);
        byte[] byArray6 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters)this.key, gF2Vector, gF2Vector2).getEncoded();
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(byArray3);
        byte[] byArray7 = new byte[byArray.length + n2];
        digestRandomGenerator.nextBytes(byArray7);
        for (n = 0; n < byArray.length; ++n) {
            int n3 = n;
            byArray7[n3] = (byte)(byArray7[n3] ^ byArray[n]);
        }
        for (n = 0; n < n2; ++n) {
            int n4 = byArray.length + n;
            byArray7[n4] = (byte)(byArray7[n4] ^ byArray2[n]);
        }
        return ByteUtils.concatenate(byArray6, byArray7);
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
        this.messDigest.update(byArray6, 0, byArray6.length);
        byte[] byArray7 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(byArray7, 0);
        gF2Vector = Conversions.encode(this.n, this.t, byArray7);
        if (!gF2Vector.equals(gF2Vector2)) {
            throw new InvalidCipherTextException("Bad Padding: Invalid ciphertext.");
        }
        int n4 = this.k >> 3;
        byte[][] byArray8 = ByteUtils.split(byArray6, n2 - n4);
        return byArray8[0];
    }
}

