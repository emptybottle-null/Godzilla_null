/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.jcajce.provider.rainbow;

import java.io.IOException;
import java.security.PrivateKey;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.asn1.PQCObjectIdentifiers;
import org.bouncycastle.pqc.asn1.RainbowPrivateKey;
import org.bouncycastle.pqc.crypto.rainbow.Layer;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;
import org.bouncycastle.pqc.jcajce.spec.RainbowPrivateKeySpec;
import org.bouncycastle.util.Arrays;

public class BCRainbowPrivateKey
implements PrivateKey {
    private static final long serialVersionUID = 1L;
    private short[][] A1inv;
    private short[] b1;
    private short[][] A2inv;
    private short[] b2;
    private Layer[] layers;
    private int[] vi;

    public BCRainbowPrivateKey(short[][] sArray, short[] sArray2, short[][] sArray3, short[] sArray4, int[] nArray, Layer[] layerArray) {
        this.A1inv = sArray;
        this.b1 = sArray2;
        this.A2inv = sArray3;
        this.b2 = sArray4;
        this.vi = nArray;
        this.layers = layerArray;
    }

    public BCRainbowPrivateKey(RainbowPrivateKeySpec rainbowPrivateKeySpec) {
        this(rainbowPrivateKeySpec.getInvA1(), rainbowPrivateKeySpec.getB1(), rainbowPrivateKeySpec.getInvA2(), rainbowPrivateKeySpec.getB2(), rainbowPrivateKeySpec.getVi(), rainbowPrivateKeySpec.getLayers());
    }

    public BCRainbowPrivateKey(RainbowPrivateKeyParameters rainbowPrivateKeyParameters) {
        this(rainbowPrivateKeyParameters.getInvA1(), rainbowPrivateKeyParameters.getB1(), rainbowPrivateKeyParameters.getInvA2(), rainbowPrivateKeyParameters.getB2(), rainbowPrivateKeyParameters.getVi(), rainbowPrivateKeyParameters.getLayers());
    }

    public short[][] getInvA1() {
        return this.A1inv;
    }

    public short[] getB1() {
        return this.b1;
    }

    public short[] getB2() {
        return this.b2;
    }

    public short[][] getInvA2() {
        return this.A2inv;
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return this.vi;
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof BCRainbowPrivateKey)) {
            return false;
        }
        BCRainbowPrivateKey bCRainbowPrivateKey = (BCRainbowPrivateKey)object;
        boolean bl = true;
        bl = bl && RainbowUtil.equals(this.A1inv, bCRainbowPrivateKey.getInvA1());
        bl = bl && RainbowUtil.equals(this.A2inv, bCRainbowPrivateKey.getInvA2());
        bl = bl && RainbowUtil.equals(this.b1, bCRainbowPrivateKey.getB1());
        bl = bl && RainbowUtil.equals(this.b2, bCRainbowPrivateKey.getB2());
        boolean bl2 = bl = bl && java.util.Arrays.equals(this.vi, bCRainbowPrivateKey.getVi());
        if (this.layers.length != bCRainbowPrivateKey.getLayers().length) {
            return false;
        }
        for (int i = this.layers.length - 1; i >= 0; --i) {
            bl &= this.layers[i].equals(bCRainbowPrivateKey.getLayers()[i]);
        }
        return bl;
    }

    public int hashCode() {
        int n = this.layers.length;
        n = n * 37 + Arrays.hashCode(this.A1inv);
        n = n * 37 + Arrays.hashCode(this.b1);
        n = n * 37 + Arrays.hashCode(this.A2inv);
        n = n * 37 + Arrays.hashCode(this.b2);
        n = n * 37 + Arrays.hashCode(this.vi);
        for (int i = this.layers.length - 1; i >= 0; --i) {
            n = n * 37 + this.layers[i].hashCode();
        }
        return n;
    }

    public final String getAlgorithm() {
        return "Rainbow";
    }

    public byte[] getEncoded() {
        PrivateKeyInfo privateKeyInfo;
        Object object;
        RainbowPrivateKey rainbowPrivateKey = new RainbowPrivateKey(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
        try {
            object = new AlgorithmIdentifier(PQCObjectIdentifiers.rainbow, DERNull.INSTANCE);
            privateKeyInfo = new PrivateKeyInfo((AlgorithmIdentifier)object, rainbowPrivateKey);
        } catch (IOException iOException) {
            return null;
        }
        try {
            object = privateKeyInfo.getEncoded();
            return object;
        } catch (IOException iOException) {
            return null;
        }
    }

    public String getFormat() {
        return "PKCS#8";
    }
}

