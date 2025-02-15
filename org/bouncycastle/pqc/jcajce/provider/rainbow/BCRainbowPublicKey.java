/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.rainbow;

import java.security.PublicKey;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.RainbowPublicKey;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.bouncycastle.pqc.jcajce.provider.util.KeyUtil;
import org.bouncycastle.pqc.jcajce.spec.RainbowPublicKeySpec;
import org.bouncycastle.util.Arrays;

public class BCRainbowPublicKey
implements PublicKey {
    private static final long serialVersionUID = 1L;
    private short[][] coeffquadratic;
    private short[][] coeffsingular;
    private short[] coeffscalar;
    private int docLength;
    private RainbowParameters rainbowParams;

    public BCRainbowPublicKey(int n, short[][] sArray, short[][] sArray2, short[] sArray3) {
        this.docLength = n;
        this.coeffquadratic = sArray;
        this.coeffsingular = sArray2;
        this.coeffscalar = sArray3;
    }

    public BCRainbowPublicKey(RainbowPublicKeySpec rainbowPublicKeySpec) {
        this(rainbowPublicKeySpec.getDocLength(), rainbowPublicKeySpec.getCoeffQuadratic(), rainbowPublicKeySpec.getCoeffSingular(), rainbowPublicKeySpec.getCoeffScalar());
    }

    public BCRainbowPublicKey(RainbowPublicKeyParameters rainbowPublicKeyParameters) {
        this(rainbowPublicKeyParameters.getDocLength(), rainbowPublicKeyParameters.getCoeffQuadratic(), rainbowPublicKeyParameters.getCoeffSingular(), rainbowPublicKeyParameters.getCoeffScalar());
    }

    public int getDocLength() {
        return this.docLength;
    }

    public short[][] getCoeffQuadratic() {
        return this.coeffquadratic;
    }

    public short[][] getCoeffSingular() {
        short[][] sArrayArray = new short[this.coeffsingular.length][];
        for (int i = 0; i != this.coeffsingular.length; ++i) {
            sArrayArray[i] = Arrays.clone(this.coeffsingular[i]);
        }
        return sArrayArray;
    }

    public short[] getCoeffScalar() {
        return Arrays.clone(this.coeffscalar);
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof BCRainbowPublicKey)) {
            return false;
        }
        BCRainbowPublicKey bCRainbowPublicKey = (BCRainbowPublicKey)object;
        return this.docLength == bCRainbowPublicKey.getDocLength() && RainbowUtil.equals(this.coeffquadratic, bCRainbowPublicKey.getCoeffQuadratic()) && RainbowUtil.equals(this.coeffsingular, bCRainbowPublicKey.getCoeffSingular()) && RainbowUtil.equals(this.coeffscalar, bCRainbowPublicKey.getCoeffScalar());
    }

    public int hashCode() {
        int n = this.docLength;
        n = n * 37 + Arrays.hashCode(this.coeffquadratic);
        n = n * 37 + Arrays.hashCode(this.coeffsingular);
        n = n * 37 + Arrays.hashCode(this.coeffscalar);
        return n;
    }

    public final String getAlgorithm() {
        return "Rainbow";
    }

    public String getFormat() {
        return "X.509";
    }

    public byte[] getEncoded() {
        RainbowPublicKey rainbowPublicKey = new RainbowPublicKey(this.docLength, this.coeffquadratic, this.coeffsingular, this.coeffscalar);
        AlgorithmIdentifier algorithmIdentifier = new AlgorithmIdentifier(PQCObjectIdentifiers.rainbow, DERNull.INSTANCE);
        return KeyUtil.getEncodedSubjectPublicKeyInfo(algorithmIdentifier, rainbowPublicKey);
    }
}

