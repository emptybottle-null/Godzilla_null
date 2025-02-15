/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageEncryptor;
import org.bouncycastle.pqc.crypto.mceliece.McElieceKeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.GoppaCode;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.pqc.math.linearalgebra.Vector;

public class McElieceCipher
implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    private SecureRandom sr;
    private int n;
    private int k;
    private int t;
    public int maxPlainTextSize;
    public int cipherTextSize;
    private McElieceKeyParameters key;
    private boolean forEncryption;

    public void init(boolean bl, CipherParameters cipherParameters) {
        this.forEncryption = bl;
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.sr = parametersWithRandom.getRandom();
                this.key = (McEliecePublicKeyParameters)parametersWithRandom.getParameters();
                this.initCipherEncrypt((McEliecePublicKeyParameters)this.key);
            } else {
                this.sr = new SecureRandom();
                this.key = (McEliecePublicKeyParameters)cipherParameters;
                this.initCipherEncrypt((McEliecePublicKeyParameters)this.key);
            }
        } else {
            this.key = (McEliecePrivateKeyParameters)cipherParameters;
            this.initCipherDecrypt((McEliecePrivateKeyParameters)this.key);
        }
    }

    public int getKeySize(McElieceKeyParameters mcElieceKeyParameters) {
        if (mcElieceKeyParameters instanceof McEliecePublicKeyParameters) {
            return ((McEliecePublicKeyParameters)mcElieceKeyParameters).getN();
        }
        if (mcElieceKeyParameters instanceof McEliecePrivateKeyParameters) {
            return ((McEliecePrivateKeyParameters)mcElieceKeyParameters).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    private void initCipherEncrypt(McEliecePublicKeyParameters mcEliecePublicKeyParameters) {
        this.sr = this.sr != null ? this.sr : new SecureRandom();
        this.n = mcEliecePublicKeyParameters.getN();
        this.k = mcEliecePublicKeyParameters.getK();
        this.t = mcEliecePublicKeyParameters.getT();
        this.cipherTextSize = this.n >> 3;
        this.maxPlainTextSize = this.k >> 3;
    }

    private void initCipherDecrypt(McEliecePrivateKeyParameters mcEliecePrivateKeyParameters) {
        this.n = mcEliecePrivateKeyParameters.getN();
        this.k = mcEliecePrivateKeyParameters.getK();
        this.maxPlainTextSize = this.k >> 3;
        this.cipherTextSize = this.n >> 3;
    }

    public byte[] messageEncrypt(byte[] byArray) {
        if (!this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        GF2Vector gF2Vector = this.computeMessageRepresentative(byArray);
        GF2Vector gF2Vector2 = new GF2Vector(this.n, this.t, this.sr);
        GF2Matrix gF2Matrix = ((McEliecePublicKeyParameters)this.key).getG();
        Vector vector = gF2Matrix.leftMultiply(gF2Vector);
        GF2Vector gF2Vector3 = (GF2Vector)vector.add(gF2Vector2);
        return gF2Vector3.getEncoded();
    }

    private GF2Vector computeMessageRepresentative(byte[] byArray) {
        byte[] byArray2 = new byte[this.maxPlainTextSize + ((this.k & 7) != 0 ? 1 : 0)];
        System.arraycopy(byArray, 0, byArray2, 0, byArray.length);
        byArray2[byArray.length] = 1;
        return GF2Vector.OS2VP(this.k, byArray2);
    }

    public byte[] messageDecrypt(byte[] byArray) throws InvalidCipherTextException {
        if (this.forEncryption) {
            throw new IllegalStateException("cipher initialised for decryption");
        }
        GF2Vector gF2Vector = GF2Vector.OS2VP(this.n, byArray);
        McEliecePrivateKeyParameters mcEliecePrivateKeyParameters = (McEliecePrivateKeyParameters)this.key;
        GF2mField gF2mField = mcEliecePrivateKeyParameters.getField();
        PolynomialGF2mSmallM polynomialGF2mSmallM = mcEliecePrivateKeyParameters.getGoppaPoly();
        GF2Matrix gF2Matrix = mcEliecePrivateKeyParameters.getSInv();
        Permutation permutation = mcEliecePrivateKeyParameters.getP1();
        Permutation permutation2 = mcEliecePrivateKeyParameters.getP2();
        GF2Matrix gF2Matrix2 = mcEliecePrivateKeyParameters.getH();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArray = mcEliecePrivateKeyParameters.getQInv();
        Permutation permutation3 = permutation.rightMultiply(permutation2);
        Permutation permutation4 = permutation3.computeInverse();
        GF2Vector gF2Vector2 = (GF2Vector)gF2Vector.multiply(permutation4);
        GF2Vector gF2Vector3 = (GF2Vector)gF2Matrix2.rightMultiply(gF2Vector2);
        GF2Vector gF2Vector4 = GoppaCode.syndromeDecode(gF2Vector3, gF2mField, polynomialGF2mSmallM, polynomialGF2mSmallMArray);
        GF2Vector gF2Vector5 = (GF2Vector)gF2Vector2.add(gF2Vector4);
        gF2Vector5 = (GF2Vector)gF2Vector5.multiply(permutation);
        gF2Vector4 = (GF2Vector)gF2Vector4.multiply(permutation3);
        GF2Vector gF2Vector6 = gF2Vector5.extractRightVector(this.k);
        GF2Vector gF2Vector7 = (GF2Vector)gF2Matrix.leftMultiply(gF2Vector6);
        return this.computeMessage(gF2Vector7);
    }

    private byte[] computeMessage(GF2Vector gF2Vector) throws InvalidCipherTextException {
        int n;
        byte[] byArray = gF2Vector.getEncoded();
        for (n = byArray.length - 1; n >= 0 && byArray[n] == 0; --n) {
        }
        if (n < 0 || byArray[n] != 1) {
            throw new InvalidCipherTextException("Bad Padding: invalid ciphertext");
        }
        byte[] byArray2 = new byte[n];
        System.arraycopy(byArray, 0, byArray2, 0, n);
        return byArray2;
    }
}

