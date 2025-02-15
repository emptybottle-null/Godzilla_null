/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider;
import org.bouncycastle.pqc.crypto.gmss.GMSSKeyGenerationParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSPrivateKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSPublicKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.bouncycastle.pqc.crypto.gmss.Treehash;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;

public class GMSSKeyPairGenerator
implements AsymmetricCipherKeyPairGenerator {
    private GMSSRandom gmssRandom;
    private Digest messDigestTree;
    private byte[][] currentSeeds;
    private byte[][] nextNextSeeds;
    private byte[][] currentRootSigs;
    private GMSSDigestProvider digestProvider;
    private int mdLength;
    private int numLayer;
    private boolean initialized = false;
    private GMSSParameters gmssPS;
    private int[] heightOfTrees;
    private int[] otsIndex;
    private int[] K;
    private GMSSKeyGenerationParameters gmssParams;
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.3";

    public GMSSKeyPairGenerator(GMSSDigestProvider gMSSDigestProvider) {
        this.digestProvider = gMSSDigestProvider;
        this.messDigestTree = gMSSDigestProvider.get();
        this.mdLength = this.messDigestTree.getDigestSize();
        this.gmssRandom = new GMSSRandom(this.messDigestTree);
    }

    private AsymmetricCipherKeyPair genKeyPair() {
        int n;
        Object object;
        int n2;
        if (!this.initialized) {
            this.initializeDefault();
        }
        byte[][][] byArrayArray = new byte[this.numLayer][][];
        byte[][][] byArrayArray2 = new byte[this.numLayer - 1][][];
        Treehash[][] treehashArray = new Treehash[this.numLayer][];
        Treehash[][] treehashArray2 = new Treehash[this.numLayer - 1][];
        Vector[] vectorArray = new Vector[this.numLayer];
        Vector[] vectorArray2 = new Vector[this.numLayer - 1];
        Vector[][] vectorArray3 = new Vector[this.numLayer][];
        Vector[][] vectorArray4 = new Vector[this.numLayer - 1][];
        for (int i = 0; i < this.numLayer; ++i) {
            byArrayArray[i] = new byte[this.heightOfTrees[i]][this.mdLength];
            treehashArray[i] = new Treehash[this.heightOfTrees[i] - this.K[i]];
            if (i > 0) {
                byArrayArray2[i - 1] = new byte[this.heightOfTrees[i]][this.mdLength];
                treehashArray2[i - 1] = new Treehash[this.heightOfTrees[i] - this.K[i]];
            }
            vectorArray[i] = new Vector();
            if (i <= 0) continue;
            vectorArray2[i - 1] = new Vector();
        }
        byte[][] byArray = new byte[this.numLayer][this.mdLength];
        byte[][] byArray2 = new byte[this.numLayer - 1][this.mdLength];
        byte[][] byArray3 = new byte[this.numLayer][this.mdLength];
        for (n2 = 0; n2 < this.numLayer; ++n2) {
            System.arraycopy(this.currentSeeds[n2], 0, byArray3[n2], 0, this.mdLength);
        }
        this.currentRootSigs = new byte[this.numLayer - 1][this.mdLength];
        for (n2 = this.numLayer - 1; n2 >= 0; --n2) {
            object = new GMSSRootCalc(this.heightOfTrees[n2], this.K[n2], this.digestProvider);
            try {
                object = n2 == this.numLayer - 1 ? this.generateCurrentAuthpathAndRoot(null, vectorArray[n2], byArray3[n2], n2) : this.generateCurrentAuthpathAndRoot(byArray[n2 + 1], vectorArray[n2], byArray3[n2], n2);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            for (n = 0; n < this.heightOfTrees[n2]; ++n) {
                System.arraycopy(((GMSSRootCalc)object).getAuthPath()[n], 0, byArrayArray[n2][n], 0, this.mdLength);
            }
            vectorArray3[n2] = ((GMSSRootCalc)object).getRetain();
            treehashArray[n2] = ((GMSSRootCalc)object).getTreehash();
            System.arraycopy(((GMSSRootCalc)object).getRoot(), 0, byArray[n2], 0, this.mdLength);
        }
        for (n2 = this.numLayer - 2; n2 >= 0; --n2) {
            object = this.generateNextAuthpathAndRoot(vectorArray2[n2], byArray3[n2 + 1], n2 + 1);
            for (n = 0; n < this.heightOfTrees[n2 + 1]; ++n) {
                System.arraycopy(((GMSSRootCalc)object).getAuthPath()[n], 0, byArrayArray2[n2][n], 0, this.mdLength);
            }
            vectorArray4[n2] = ((GMSSRootCalc)object).getRetain();
            treehashArray2[n2] = ((GMSSRootCalc)object).getTreehash();
            System.arraycopy(((GMSSRootCalc)object).getRoot(), 0, byArray2[n2], 0, this.mdLength);
            System.arraycopy(byArray3[n2 + 1], 0, this.nextNextSeeds[n2], 0, this.mdLength);
        }
        GMSSPublicKeyParameters gMSSPublicKeyParameters = new GMSSPublicKeyParameters(byArray[0], this.gmssPS);
        object = new GMSSPrivateKeyParameters(this.currentSeeds, this.nextNextSeeds, byArrayArray, byArrayArray2, treehashArray, treehashArray2, vectorArray, vectorArray2, vectorArray3, vectorArray4, byArray2, this.currentRootSigs, this.gmssPS, this.digestProvider);
        return new AsymmetricCipherKeyPair(gMSSPublicKeyParameters, (AsymmetricKeyParameter)object);
    }

    private GMSSRootCalc generateCurrentAuthpathAndRoot(byte[] byArray, Vector vector, byte[] byArray2, int n) {
        WinternitzOTSignature winternitzOTSignature;
        byte[] byArray3 = new byte[this.mdLength];
        byte[] byArray4 = new byte[this.mdLength];
        byArray4 = this.gmssRandom.nextSeed(byArray2);
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[n], this.K[n], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        if (n == this.numLayer - 1) {
            winternitzOTSignature = new WinternitzOTSignature(byArray4, this.digestProvider.get(), this.otsIndex[n]);
            byArray3 = winternitzOTSignature.getPublicKey();
        } else {
            winternitzOTSignature = new WinternitzOTSignature(byArray4, this.digestProvider.get(), this.otsIndex[n]);
            this.currentRootSigs[n] = winternitzOTSignature.getSignature(byArray);
            WinternitzOTSVerify winternitzOTSVerify = new WinternitzOTSVerify(this.digestProvider.get(), this.otsIndex[n]);
            byArray3 = winternitzOTSVerify.Verify(byArray, this.currentRootSigs[n]);
        }
        gMSSRootCalc.update(byArray3);
        int n2 = 3;
        int n3 = 0;
        for (int i = 1; i < 1 << this.heightOfTrees[n]; ++i) {
            if (i == n2 && n3 < this.heightOfTrees[n] - this.K[n]) {
                gMSSRootCalc.initializeTreehashSeed(byArray2, n3);
                n2 *= 2;
                ++n3;
            }
            byArray4 = this.gmssRandom.nextSeed(byArray2);
            winternitzOTSignature = new WinternitzOTSignature(byArray4, this.digestProvider.get(), this.otsIndex[n]);
            gMSSRootCalc.update(winternitzOTSignature.getPublicKey());
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    private GMSSRootCalc generateNextAuthpathAndRoot(Vector vector, byte[] byArray, int n) {
        byte[] byArray2 = new byte[this.numLayer];
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.heightOfTrees[n], this.K[n], this.digestProvider);
        gMSSRootCalc.initialize(vector);
        int n2 = 3;
        int n3 = 0;
        for (int i = 0; i < 1 << this.heightOfTrees[n]; ++i) {
            if (i == n2 && n3 < this.heightOfTrees[n] - this.K[n]) {
                gMSSRootCalc.initializeTreehashSeed(byArray, n3);
                n2 *= 2;
                ++n3;
            }
            byArray2 = this.gmssRandom.nextSeed(byArray);
            WinternitzOTSignature winternitzOTSignature = new WinternitzOTSignature(byArray2, this.digestProvider.get(), this.otsIndex[n]);
            gMSSRootCalc.update(winternitzOTSignature.getPublicKey());
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("N\ufffdchster Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    public void initialize(int n, SecureRandom secureRandom) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters;
        if (n <= 10) {
            int[] nArray = new int[]{10};
            int[] nArray2 = new int[]{3};
            int[] nArray3 = new int[]{2};
            gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(nArray.length, nArray, nArray2, nArray3));
        } else if (n <= 20) {
            int[] nArray = new int[]{10, 10};
            int[] nArray4 = new int[]{5, 4};
            int[] nArray5 = new int[]{2, 2};
            gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(nArray.length, nArray, nArray4, nArray5));
        } else {
            int[] nArray = new int[]{10, 10, 10, 10};
            int[] nArray6 = new int[]{9, 9, 9, 3};
            int[] nArray7 = new int[]{2, 2, 2, 2};
            gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(nArray.length, nArray, nArray6, nArray7));
        }
        this.initialize(gMSSKeyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.gmssParams = (GMSSKeyGenerationParameters)keyGenerationParameters;
        this.gmssPS = new GMSSParameters(this.gmssParams.getParameters().getNumOfLayers(), this.gmssParams.getParameters().getHeightOfTrees(), this.gmssParams.getParameters().getWinternitzParameter(), this.gmssParams.getParameters().getK());
        this.numLayer = this.gmssPS.getNumOfLayers();
        this.heightOfTrees = this.gmssPS.getHeightOfTrees();
        this.otsIndex = this.gmssPS.getWinternitzParameter();
        this.K = this.gmssPS.getK();
        this.currentSeeds = new byte[this.numLayer][this.mdLength];
        this.nextNextSeeds = new byte[this.numLayer - 1][this.mdLength];
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < this.numLayer; ++i) {
            secureRandom.nextBytes(this.currentSeeds[i]);
            this.gmssRandom.nextSeed(this.currentSeeds[i]);
        }
        this.initialized = true;
    }

    private void initializeDefault() {
        int[] nArray = new int[]{10, 10, 10, 10};
        int[] nArray2 = new int[]{3, 3, 3, 3};
        int[] nArray3 = new int[]{2, 2, 2, 2};
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(new SecureRandom(), new GMSSParameters(nArray.length, nArray, nArray2, nArray3));
        this.initialize(gMSSKeyGenerationParameters);
    }

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.initialize(keyGenerationParameters);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return this.genKeyPair();
    }
}

