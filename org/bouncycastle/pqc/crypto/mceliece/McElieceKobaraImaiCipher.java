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
import org.bouncycastle.pqc.math.linearalgebra.IntegerFunctions;

public class McElieceKobaraImaiCipher
implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.3";
    private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
    public static final byte[] PUBLIC_CONSTANT = "a predetermined public constant".getBytes();
    private Digest messDigest;
    private SecureRandom sr;
    McElieceCCA2KeyParameters key;
    private int n;
    private int k;
    private int t;
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

    public int getKeySize(McElieceCCA2KeyParameters mcElieceCCA2KeyParameters) {
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters)mcElieceCCA2KeyParameters).getN();
        }
        if (mcElieceCCA2KeyParameters instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters)mcElieceCCA2KeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters) {
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
        if (!this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int n = this.messDigest.getDigestSize();
        int n2 = this.k >> 3;
        int n3 = IntegerFunctions.binomial(this.n, this.t).bitLength() - 1 >> 3;
        int n4 = n2 + n3 - n - PUBLIC_CONSTANT.length;
        if (byArray.length > n4) {
            n4 = byArray.length;
        }
        int n5 = n4 + PUBLIC_CONSTANT.length;
        int n6 = n5 + n - n2 - n3;
        byte[] byArray2 = new byte[n5];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        System.arraycopy(PUBLIC_CONSTANT, 0, byArray2, n4, PUBLIC_CONSTANT.length);
        byte[] byArray3 = new byte[n];
        this.sr.nextBytes(byArray3);
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(byArray3);
        byte[] byArray4 = new byte[n5];
        digestRandomGenerator.nextBytes(byArray4);
        for (int i = n5 - 1; i >= 0; --i) {
            int n7 = i;
            byArray4[n7] = (byte)(byArray4[n7] ^ byArray2[i]);
        }
        byte[] byArray5 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(byArray4, 0, byArray4.length);
        this.messDigest.doFinal(byArray5, 0);
        for (int i = n - 1; i >= 0; --i) {
            int n8 = i;
            byArray5[n8] = (byte)(byArray5[n8] ^ byArray3[i]);
        }
        byte[] byArray6 = ByteUtils.concatenate(byArray5, byArray4);
        byte[] byArray7 = new byte[]{};
        if (n6 > 0) {
            byArray7 = new byte[n6];
            System.arraycopy(byArray6, 0, byArray7, 0, n6);
        }
        byte[] byArray8 = new byte[n3];
        System.arraycopy(byArray6, n6, byArray8, 0, n3);
        byte[] byArray9 = new byte[n2];
        System.arraycopy(byArray6, n6 + n3, byArray9, 0, n2);
        GF2Vector gF2Vector = GF2Vector.OS2VP(this.k, byArray9);
        GF2Vector gF2Vector2 = Conversions.encode(this.n, this.t, byArray8);
        byte[] byArray10 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters)this.key, gF2Vector, gF2Vector2).getEncoded();
        if (n6 > 0) {
            return ByteUtils.concatenate(byArray7, byArray10);
        }
        return byArray10;
    }

    public byte[] messageDecrypt(byte[] byArray) throws InvalidCipherTextException {
        byte[] byArray2;
        byte[] byArray3;
        Object object;
        if (this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        int n = this.n >> 3;
        if (byArray.length < n) {
            throw new InvalidCipherTextException("Bad Padding: Ciphertext too short.");
        }
        int n2 = this.messDigest.getDigestSize();
        int n3 = this.k >> 3;
        int n4 = byArray.length - n;
        if (n4 > 0) {
            object = ByteUtils.split(byArray, n4);
            byArray3 = object[0];
            byArray2 = object[1];
        } else {
            byArray3 = new byte[]{};
            byArray2 = byArray;
        }
        object = GF2Vector.OS2VP(this.n, byArray2);
        GF2Vector[] gF2VectorArray = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters)this.key, (GF2Vector)object);
        byte[] byArray4 = gF2VectorArray[0].getEncoded();
        GF2Vector gF2Vector = gF2VectorArray[1];
        if (byArray4.length > n3) {
            byArray4 = ByteUtils.subArray(byArray4, 0, n3);
        }
        byte[] byArray5 = Conversions.decode(this.n, this.t, gF2Vector);
        byte[] byArray6 = ByteUtils.concatenate(byArray3, byArray5);
        byArray6 = ByteUtils.concatenate(byArray6, byArray4);
        int n5 = byArray6.length - n2;
        byte[][] byArray7 = ByteUtils.split(byArray6, n2);
        byte[] byArray8 = byArray7[0];
        byte[] byArray9 = byArray7[1];
        byte[] byArray10 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(byArray9, 0, byArray9.length);
        this.messDigest.doFinal(byArray10, 0);
        for (int i = n2 - 1; i >= 0; --i) {
            int n6 = i;
            byArray10[n6] = (byte)(byArray10[n6] ^ byArray8[i]);
        }
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(byArray10);
        byte[] byArray11 = new byte[n5];
        digestRandomGenerator.nextBytes(byArray11);
        for (int i = n5 - 1; i >= 0; --i) {
            int n7 = i;
            byArray11[n7] = (byte)(byArray11[n7] ^ byArray9[i]);
        }
        if (byArray11.length < n5) {
            throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
        }
        byte[][] byArray12 = ByteUtils.split(byArray11, n5 - PUBLIC_CONSTANT.length);
        byte[] byArray13 = byArray12[0];
        byte[] byArray14 = byArray12[1];
        if (!ByteUtils.equals(byArray14, PUBLIC_CONSTANT)) {
            throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
        }
        return byArray13;
    }
}

