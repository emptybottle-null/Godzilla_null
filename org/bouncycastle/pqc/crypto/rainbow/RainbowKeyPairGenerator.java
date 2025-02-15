/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.rainbow.Layer;
import org.bouncycastle.pqc.crypto.rainbow.RainbowKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.RainbowPublicKeyParameters;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;

public class RainbowKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private boolean initialized = false;
    private SecureRandom sr;
    private RainbowKeyGenerationParameters rainbowParams;
    private short[][] A1;
    private short[][] A1inv;
    private short[] b1;
    private short[][] A2;
    private short[][] A2inv;
    private short[] b2;
    private int numOfLayers;
    private Layer[] layers;
    private int[] vi;
    private short[][] pub_quadratic;
    private short[][] pub_singular;
    private short[] pub_scalar;

    public AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            this.initializeDefault();
        }
        this.keygen();
        RainbowPrivateKeyParameters rainbowPrivateKeyParameters = new RainbowPrivateKeyParameters(this.A1inv, this.b1, this.A2inv, this.b2, this.vi, this.layers);
        RainbowPublicKeyParameters rainbowPublicKeyParameters = new RainbowPublicKeyParameters(this.vi[this.vi.length - 1] - this.vi[0], this.pub_quadratic, this.pub_singular, this.pub_scalar);
        return new AsymmetricCipherKeyPair(rainbowPublicKeyParameters, rainbowPrivateKeyParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.rainbowParams = (RainbowKeyGenerationParameters)keyGenerationParameters;
        this.sr = this.rainbowParams.getRandom();
        this.vi = this.rainbowParams.getParameters().getVi();
        this.numOfLayers = this.rainbowParams.getParameters().getNumOfLayers();
        this.initialized = true;
    }

    private void initializeDefault() {
        RainbowKeyGenerationParameters rainbowKeyGenerationParameters = new RainbowKeyGenerationParameters(new SecureRandom(), new RainbowParameters());
        this.initialize(rainbowKeyGenerationParameters);
    }

    private void keygen() {
        this.generateL1();
        this.generateL2();
        this.generateF();
        this.computePublicKey();
    }

    private void generateL1() {
        int n;
        int n2 = this.vi[this.vi.length - 1] - this.vi[0];
        this.A1 = new short[n2][n2];
        this.A1inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A1inv == null) {
            for (n = 0; n < n2; ++n) {
                for (int i = 0; i < n2; ++i) {
                    this.A1[n][i] = (short)(this.sr.nextInt() & 0xFF);
                }
            }
            this.A1inv = computeInField.inverse(this.A1);
        }
        this.b1 = new short[n2];
        for (n = 0; n < n2; ++n) {
            this.b1[n] = (short)(this.sr.nextInt() & 0xFF);
        }
    }

    private void generateL2() {
        int n;
        int n2 = this.vi[this.vi.length - 1];
        this.A2 = new short[n2][n2];
        this.A2inv = null;
        ComputeInField computeInField = new ComputeInField();
        while (this.A2inv == null) {
            for (n = 0; n < n2; ++n) {
                for (int i = 0; i < n2; ++i) {
                    this.A2[n][i] = (short)(this.sr.nextInt() & 0xFF);
                }
            }
            this.A2inv = computeInField.inverse(this.A2);
        }
        this.b2 = new short[n2];
        for (n = 0; n < n2; ++n) {
            this.b2[n] = (short)(this.sr.nextInt() & 0xFF);
        }
    }

    private void generateF() {
        this.layers = new Layer[this.numOfLayers];
        for (int i = 0; i < this.numOfLayers; ++i) {
            this.layers[i] = new Layer(this.vi[i], this.vi[i + 1], this.sr);
        }
    }

    private void computePublicKey() {
        int n;
        ComputeInField computeInField = new ComputeInField();
        int n2 = this.vi[this.vi.length - 1] - this.vi[0];
        int n3 = this.vi[this.vi.length - 1];
        short[][][] sArray = new short[n2][n3][n3];
        this.pub_singular = new short[n2][n3];
        this.pub_scalar = new short[n2];
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        short[] sArray2 = new short[n3];
        short s = 0;
        for (int i = 0; i < this.layers.length; ++i) {
            short[][][] sArray3 = this.layers[i].getCoeffAlpha();
            short[][][] sArray4 = this.layers[i].getCoeffBeta();
            short[][] sArray5 = this.layers[i].getCoeffGamma();
            short[] sArray6 = this.layers[i].getCoeffEta();
            n4 = sArray3[0].length;
            n5 = sArray4[0].length;
            for (int j = 0; j < n4; ++j) {
                int n7;
                for (n7 = 0; n7 < n4; ++n7) {
                    for (n = 0; n < n5; ++n) {
                        sArray2 = computeInField.multVect(sArray3[j][n7][n], this.A2[n7 + n5]);
                        sArray[n6 + j] = computeInField.addSquareMatrix(sArray[n6 + j], computeInField.multVects(sArray2, this.A2[n]));
                        sArray2 = computeInField.multVect(this.b2[n], sArray2);
                        this.pub_singular[n6 + j] = computeInField.addVect(sArray2, this.pub_singular[n6 + j]);
                        sArray2 = computeInField.multVect(sArray3[j][n7][n], this.A2[n]);
                        sArray2 = computeInField.multVect(this.b2[n7 + n5], sArray2);
                        this.pub_singular[n6 + j] = computeInField.addVect(sArray2, this.pub_singular[n6 + j]);
                        s = GF2Field.multElem(sArray3[j][n7][n], this.b2[n7 + n5]);
                        this.pub_scalar[n6 + j] = GF2Field.addElem(this.pub_scalar[n6 + j], GF2Field.multElem(s, this.b2[n]));
                    }
                }
                for (n7 = 0; n7 < n5; ++n7) {
                    for (n = 0; n < n5; ++n) {
                        sArray2 = computeInField.multVect(sArray4[j][n7][n], this.A2[n7]);
                        sArray[n6 + j] = computeInField.addSquareMatrix(sArray[n6 + j], computeInField.multVects(sArray2, this.A2[n]));
                        sArray2 = computeInField.multVect(this.b2[n], sArray2);
                        this.pub_singular[n6 + j] = computeInField.addVect(sArray2, this.pub_singular[n6 + j]);
                        sArray2 = computeInField.multVect(sArray4[j][n7][n], this.A2[n]);
                        sArray2 = computeInField.multVect(this.b2[n7], sArray2);
                        this.pub_singular[n6 + j] = computeInField.addVect(sArray2, this.pub_singular[n6 + j]);
                        s = GF2Field.multElem(sArray4[j][n7][n], this.b2[n7]);
                        this.pub_scalar[n6 + j] = GF2Field.addElem(this.pub_scalar[n6 + j], GF2Field.multElem(s, this.b2[n]));
                    }
                }
                for (n7 = 0; n7 < n5 + n4; ++n7) {
                    sArray2 = computeInField.multVect(sArray5[j][n7], this.A2[n7]);
                    this.pub_singular[n6 + j] = computeInField.addVect(sArray2, this.pub_singular[n6 + j]);
                    this.pub_scalar[n6 + j] = GF2Field.addElem(this.pub_scalar[n6 + j], GF2Field.multElem(sArray5[j][n7], this.b2[n7]));
                }
                this.pub_scalar[n6 + j] = GF2Field.addElem(this.pub_scalar[n6 + j], sArray6[j]);
            }
            n6 += n4;
        }
        short[][][] sArray7 = new short[n2][n3][n3];
        short[][] sArray8 = new short[n2][n3];
        short[] sArray9 = new short[n2];
        for (n = 0; n < n2; ++n) {
            for (int i = 0; i < this.A1.length; ++i) {
                sArray7[n] = computeInField.addSquareMatrix(sArray7[n], computeInField.multMatrix(this.A1[n][i], sArray[i]));
                sArray8[n] = computeInField.addVect(sArray8[n], computeInField.multVect(this.A1[n][i], this.pub_singular[i]));
                sArray9[n] = GF2Field.addElem(sArray9[n], GF2Field.multElem(this.A1[n][i], this.pub_scalar[i]));
            }
            sArray9[n] = GF2Field.addElem(sArray9[n], this.b1[n]);
        }
        sArray = sArray7;
        this.pub_singular = sArray8;
        this.pub_scalar = sArray9;
        this.compactPublicKey(sArray);
    }

    private void compactPublicKey(short[][][] sArray) {
        int n = sArray.length;
        int n2 = sArray[0].length;
        int n3 = n2 * (n2 + 1) / 2;
        this.pub_quadratic = new short[n][n3];
        int n4 = 0;
        for (int i = 0; i < n; ++i) {
            n4 = 0;
            for (int j = 0; j < n2; ++j) {
                for (int k = j; k < n2; ++k) {
                    this.pub_quadratic[i][n4] = k == j ? sArray[i][j][k] : GF2Field.addElem(sArray[i][j][k], sArray[i][k][j]);
                    ++n4;
                }
            }
        }
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.initialize(keyGenerationParameters);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return this.genKeyPair();
    }
}

