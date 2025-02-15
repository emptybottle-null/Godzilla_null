/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.rainbow.Layer;
import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowSigner
implements MessageSigner {
    private static final int MAXITS = 65536;
    private SecureRandom random;
    int signableDocumentLength;
    private short[] x;
    private ComputeInField cf = new ComputeInField();
    RainbowKeyParameters key;

    public void init(boolean bl, CipherParameters cipherParameters) {
        if (bl) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom)cipherParameters;
                this.random = parametersWithRandom.getRandom();
                this.key = (RainbowPrivateKeyParameters)parametersWithRandom.getParameters();
            } else {
                this.random = new SecureRandom();
                this.key = (RainbowPrivateKeyParameters)cipherParameters;
            }
        } else {
            this.key = (RainbowPublicKeyParameters)cipherParameters;
        }
        this.signableDocumentLength = this.key.getDocLength();
    }

    private short[] initSign(Layer[] layerArray, short[] sArray) {
        short[] sArray2 = new short[sArray.length];
        sArray2 = this.cf.addVect(((RainbowPrivateKeyParameters)this.key).getB1(), sArray);
        short[] sArray3 = this.cf.multiplyMatrix(((RainbowPrivateKeyParameters)this.key).getInvA1(), sArray2);
        for (int i = 0; i < layerArray[0].getVi(); ++i) {
            this.x[i] = (short)this.random.nextInt();
            this.x[i] = (short)(this.x[i] & 0xFF);
        }
        return sArray3;
    }

    public byte[] generateSignature(byte[] byArray) {
        boolean bl;
        Layer[] layerArray = ((RainbowPrivateKeyParameters)this.key).getLayers();
        int n = layerArray.length;
        this.x = new short[((RainbowPrivateKeyParameters)this.key).getInvA2().length];
        byte[] byArray2 = new byte[layerArray[n - 1].getViNext()];
        short[] sArray = this.makeMessageRepresentative(byArray);
        int n2 = 0;
        do {
            bl = true;
            int n3 = 0;
            try {
                int n4;
                short[] sArray2 = this.initSign(layerArray, sArray);
                for (n4 = 0; n4 < n; ++n4) {
                    int n5;
                    short[] sArray3 = new short[layerArray[n4].getOi()];
                    short[] sArray4 = new short[layerArray[n4].getOi()];
                    for (n5 = 0; n5 < layerArray[n4].getOi(); ++n5) {
                        sArray3[n5] = sArray2[n3];
                        ++n3;
                    }
                    sArray4 = this.cf.solveEquation(layerArray[n4].plugInVinegars(this.x), sArray3);
                    if (sArray4 == null) {
                        throw new Exception("LES is not solveable!");
                    }
                    for (n5 = 0; n5 < sArray4.length; ++n5) {
                        this.x[layerArray[n4].getVi() + n5] = sArray4[n5];
                    }
                }
                short[] sArray5 = this.cf.addVect(((RainbowPrivateKeyParameters)this.key).getB2(), this.x);
                short[] sArray6 = this.cf.multiplyMatrix(((RainbowPrivateKeyParameters)this.key).getInvA2(), sArray5);
                for (n4 = 0; n4 < byArray2.length; ++n4) {
                    byArray2[n4] = (byte)sArray6[n4];
                }
            } catch (Exception exception) {
                bl = false;
            }
        } while (!bl && ++n2 < 65536);
        if (n2 == 65536) {
            throw new IllegalStateException("unable to generate signature - LES not solvable");
        }
        return byArray2;
    }

    public boolean verifySignature(byte[] byArray, byte[] byArray2) {
        short[] sArray = new short[byArray2.length];
        for (int i = 0; i < byArray2.length; ++i) {
            short s = byArray2[i];
            sArray[i] = s = (short)(s & 0xFF);
        }
        short[] sArray2 = this.makeMessageRepresentative(byArray);
        short[] sArray3 = this.verifySignatureIntern(sArray);
        boolean bl = true;
        if (sArray2.length != sArray3.length) {
            return false;
        }
        for (int i = 0; i < sArray2.length; ++i) {
            bl = bl && sArray2[i] == sArray3[i];
        }
        return bl;
    }

    private short[] verifySignatureIntern(short[] sArray) {
        short[][] sArray2 = ((RainbowPublicKeyParameters)this.key).getCoeffQuadratic();
        short[][] sArray3 = ((RainbowPublicKeyParameters)this.key).getCoeffSingular();
        short[] sArray4 = ((RainbowPublicKeyParameters)this.key).getCoeffScalar();
        short[] sArray5 = new short[sArray2.length];
        int n = sArray3[0].length;
        int n2 = 0;
        short s = 0;
        for (int i = 0; i < sArray2.length; ++i) {
            n2 = 0;
            for (int j = 0; j < n; ++j) {
                for (int k = j; k < n; ++k) {
                    s = GF2Field.multElem(sArray2[i][n2], GF2Field.multElem(sArray[j], sArray[k]));
                    sArray5[i] = GF2Field.addElem(sArray5[i], s);
                    ++n2;
                }
                s = GF2Field.multElem(sArray3[i][j], sArray[j]);
                sArray5[i] = GF2Field.addElem(sArray5[i], s);
            }
            sArray5[i] = GF2Field.addElem(sArray5[i], sArray4[i]);
        }
        return sArray5;
    }

    private short[] makeMessageRepresentative(byte[] byArray) {
        short[] sArray = new short[this.signableDocumentLength];
        int n = 0;
        int n2 = 0;
        while (n2 < byArray.length) {
            sArray[n2] = byArray[n];
            int n3 = n2++;
            sArray[n3] = (short)(sArray[n3] & 0xFF);
            ++n;
            if (n2 < sArray.length) continue;
        }
        return sArray;
    }
}

