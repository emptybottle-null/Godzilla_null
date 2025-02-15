/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.mceliece;

import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import org.bouncycastle.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import org.bouncycastle.pqc.jcajce.provider.mceliece.BCMcElieceCCA2PrivateKey;
import org.bouncycastle.pqc.jcajce.provider.mceliece.BCMcElieceCCA2PublicKey;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.math.linearalgebra.GF2Vector;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.GoppaCode;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.pqc.math.linearalgebra.Vector;

public final class McElieceCCA2Primitives {
    private McElieceCCA2Primitives() {
    }

    public static GF2Vector encryptionPrimitive(BCMcElieceCCA2PublicKey bCMcElieceCCA2PublicKey, GF2Vector gF2Vector, GF2Vector gF2Vector2) {
        GF2Matrix gF2Matrix = bCMcElieceCCA2PublicKey.getG();
        Vector vector = gF2Matrix.leftMultiplyLeftCompactForm(gF2Vector);
        return (GF2Vector)vector.add(gF2Vector2);
    }

    public static GF2Vector encryptionPrimitive(McElieceCCA2PublicKeyParameters mcElieceCCA2PublicKeyParameters, GF2Vector gF2Vector, GF2Vector gF2Vector2) {
        GF2Matrix gF2Matrix = mcElieceCCA2PublicKeyParameters.getG();
        Vector vector = gF2Matrix.leftMultiplyLeftCompactForm(gF2Vector);
        return (GF2Vector)vector.add(gF2Vector2);
    }

    public static GF2Vector[] decryptionPrimitive(BCMcElieceCCA2PrivateKey bCMcElieceCCA2PrivateKey, GF2Vector gF2Vector) {
        int n = bCMcElieceCCA2PrivateKey.getK();
        Permutation permutation = bCMcElieceCCA2PrivateKey.getP();
        GF2mField gF2mField = bCMcElieceCCA2PrivateKey.getField();
        PolynomialGF2mSmallM polynomialGF2mSmallM = bCMcElieceCCA2PrivateKey.getGoppaPoly();
        GF2Matrix gF2Matrix = bCMcElieceCCA2PrivateKey.getH();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArray = bCMcElieceCCA2PrivateKey.getQInv();
        Permutation permutation2 = permutation.computeInverse();
        GF2Vector gF2Vector2 = (GF2Vector)gF2Vector.multiply(permutation2);
        GF2Vector gF2Vector3 = (GF2Vector)gF2Matrix.rightMultiply(gF2Vector2);
        GF2Vector gF2Vector4 = GoppaCode.syndromeDecode(gF2Vector3, gF2mField, polynomialGF2mSmallM, polynomialGF2mSmallMArray);
        GF2Vector gF2Vector5 = (GF2Vector)gF2Vector2.add(gF2Vector4);
        gF2Vector5 = (GF2Vector)gF2Vector5.multiply(permutation);
        gF2Vector4 = (GF2Vector)gF2Vector4.multiply(permutation);
        GF2Vector gF2Vector6 = gF2Vector5.extractRightVector(n);
        return new GF2Vector[]{gF2Vector6, gF2Vector4};
    }

    public static GF2Vector[] decryptionPrimitive(McElieceCCA2PrivateKeyParameters mcElieceCCA2PrivateKeyParameters, GF2Vector gF2Vector) {
        int n = mcElieceCCA2PrivateKeyParameters.getK();
        Permutation permutation = mcElieceCCA2PrivateKeyParameters.getP();
        GF2mField gF2mField = mcElieceCCA2PrivateKeyParameters.getField();
        PolynomialGF2mSmallM polynomialGF2mSmallM = mcElieceCCA2PrivateKeyParameters.getGoppaPoly();
        GF2Matrix gF2Matrix = mcElieceCCA2PrivateKeyParameters.getH();
        PolynomialGF2mSmallM[] polynomialGF2mSmallMArray = mcElieceCCA2PrivateKeyParameters.getQInv();
        Permutation permutation2 = permutation.computeInverse();
        GF2Vector gF2Vector2 = (GF2Vector)gF2Vector.multiply(permutation2);
        GF2Vector gF2Vector3 = (GF2Vector)gF2Matrix.rightMultiply(gF2Vector2);
        GF2Vector gF2Vector4 = GoppaCode.syndromeDecode(gF2Vector3, gF2mField, polynomialGF2mSmallM, polynomialGF2mSmallMArray);
        GF2Vector gF2Vector5 = (GF2Vector)gF2Vector2.add(gF2Vector4);
        gF2Vector5 = (GF2Vector)gF2Vector5.multiply(permutation);
        gF2Vector4 = (GF2Vector)gF2Vector4.multiply(permutation);
        GF2Vector gF2Vector6 = gF2Vector5.extractRightVector(n);
        return new GF2Vector[]{gF2Vector6, gF2Vector4};
    }
}

