/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider;
import org.bouncycastle.pqc.crypto.gmss.GMSSKeyParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSLeaf;
import org.bouncycastle.pqc.crypto.gmss.GMSSParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootSig;
import org.bouncycastle.pqc.crypto.gmss.Treehash;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
import org.bouncycastle.util.Arrays;

public class GMSSPrivateKeyParameters
extends GMSSKeyParameters {
    private int[] index;
    private byte[][] currentSeeds;
    private byte[][] nextNextSeeds;
    private byte[][][] currentAuthPaths;
    private byte[][][] nextAuthPaths;
    private Treehash[][] currentTreehash;
    private Treehash[][] nextTreehash;
    private Vector[] currentStack;
    private Vector[] nextStack;
    private Vector[][] currentRetain;
    private Vector[][] nextRetain;
    private byte[][][] keep;
    private GMSSLeaf[] nextNextLeaf;
    private GMSSLeaf[] upperLeaf;
    private GMSSLeaf[] upperTreehashLeaf;
    private int[] minTreehash;
    private GMSSParameters gmssPS;
    private byte[][] nextRoot;
    private GMSSRootCalc[] nextNextRoot;
    private byte[][] currentRootSig;
    private GMSSRootSig[] nextRootSig;
    private GMSSDigestProvider digestProvider;
    private boolean used = false;
    private int[] heightOfTrees;
    private int[] otsIndex;
    private int[] K;
    private int numLayer;
    private Digest messDigestTrees;
    private int mdLength;
    private GMSSRandom gmssRandom;
    private int[] numLeafs;

    public GMSSPrivateKeyParameters(byte[][] byArray, byte[][] byArray2, byte[][][] byArray3, byte[][][] byArray4, Treehash[][] treehashArray, Treehash[][] treehashArray2, Vector[] vectorArray, Vector[] vectorArray2, Vector[][] vectorArray3, Vector[][] vectorArray4, byte[][] byArray5, byte[][] byArray6, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        this(null, byArray, byArray2, byArray3, byArray4, null, treehashArray, treehashArray2, vectorArray, vectorArray2, vectorArray3, vectorArray4, null, null, null, null, byArray5, null, byArray6, null, gMSSParameters, gMSSDigestProvider);
    }

    public GMSSPrivateKeyParameters(int[] nArray, byte[][] byArray, byte[][] byArray2, byte[][][] byArray3, byte[][][] byArray4, byte[][][] byArray5, Treehash[][] treehashArray, Treehash[][] treehashArray2, Vector[] vectorArray, Vector[] vectorArray2, Vector[][] vectorArray3, Vector[][] vectorArray4, GMSSLeaf[] gMSSLeafArray, GMSSLeaf[] gMSSLeafArray2, GMSSLeaf[] gMSSLeafArray3, int[] nArray2, byte[][] byArray6, GMSSRootCalc[] gMSSRootCalcArray, byte[][] byArray7, GMSSRootSig[] gMSSRootSigArray, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        super(true, gMSSParameters);
        int n;
        this.messDigestTrees = gMSSDigestProvider.get();
        this.mdLength = this.messDigestTrees.getDigestSize();
        this.gmssPS = gMSSParameters;
        this.otsIndex = gMSSParameters.getWinternitzParameter();
        this.K = gMSSParameters.getK();
        this.heightOfTrees = gMSSParameters.getHeightOfTrees();
        this.numLayer = this.gmssPS.getNumOfLayers();
        if (nArray == null) {
            this.index = new int[this.numLayer];
            for (n = 0; n < this.numLayer; ++n) {
                this.index[n] = 0;
            }
        } else {
            this.index = nArray;
        }
        this.currentSeeds = byArray;
        this.nextNextSeeds = byArray2;
        this.currentAuthPaths = byArray3;
        this.nextAuthPaths = byArray4;
        if (byArray5 == null) {
            this.keep = new byte[this.numLayer][][];
            for (n = 0; n < this.numLayer; ++n) {
                this.keep[n] = new byte[(int)Math.floor(this.heightOfTrees[n] / 2)][this.mdLength];
            }
        } else {
            this.keep = byArray5;
        }
        if (vectorArray == null) {
            this.currentStack = new Vector[this.numLayer];
            for (n = 0; n < this.numLayer; ++n) {
                this.currentStack[n] = new Vector();
            }
        } else {
            this.currentStack = vectorArray;
        }
        if (vectorArray2 == null) {
            this.nextStack = new Vector[this.numLayer - 1];
            for (n = 0; n < this.numLayer - 1; ++n) {
                this.nextStack[n] = new Vector();
            }
        } else {
            this.nextStack = vectorArray2;
        }
        this.currentTreehash = treehashArray;
        this.nextTreehash = treehashArray2;
        this.currentRetain = vectorArray3;
        this.nextRetain = vectorArray4;
        this.nextRoot = byArray6;
        this.digestProvider = gMSSDigestProvider;
        if (gMSSRootCalcArray == null) {
            this.nextNextRoot = new GMSSRootCalc[this.numLayer - 1];
            for (n = 0; n < this.numLayer - 1; ++n) {
                this.nextNextRoot[n] = new GMSSRootCalc(this.heightOfTrees[n + 1], this.K[n + 1], this.digestProvider);
            }
        } else {
            this.nextNextRoot = gMSSRootCalcArray;
        }
        this.currentRootSig = byArray7;
        this.numLeafs = new int[this.numLayer];
        for (n = 0; n < this.numLayer; ++n) {
            this.numLeafs[n] = 1 << this.heightOfTrees[n];
        }
        this.gmssRandom = new GMSSRandom(this.messDigestTrees);
        if (this.numLayer > 1) {
            if (gMSSLeafArray == null) {
                this.nextNextLeaf = new GMSSLeaf[this.numLayer - 2];
                for (n = 0; n < this.numLayer - 2; ++n) {
                    this.nextNextLeaf[n] = new GMSSLeaf(gMSSDigestProvider.get(), this.otsIndex[n + 1], this.numLeafs[n + 2], this.nextNextSeeds[n]);
                }
            } else {
                this.nextNextLeaf = gMSSLeafArray;
            }
        } else {
            this.nextNextLeaf = new GMSSLeaf[0];
        }
        if (gMSSLeafArray2 == null) {
            this.upperLeaf = new GMSSLeaf[this.numLayer - 1];
            for (n = 0; n < this.numLayer - 1; ++n) {
                this.upperLeaf[n] = new GMSSLeaf(gMSSDigestProvider.get(), this.otsIndex[n], this.numLeafs[n + 1], this.currentSeeds[n]);
            }
        } else {
            this.upperLeaf = gMSSLeafArray2;
        }
        if (gMSSLeafArray3 == null) {
            this.upperTreehashLeaf = new GMSSLeaf[this.numLayer - 1];
            for (n = 0; n < this.numLayer - 1; ++n) {
                this.upperTreehashLeaf[n] = new GMSSLeaf(gMSSDigestProvider.get(), this.otsIndex[n], this.numLeafs[n + 1]);
            }
        } else {
            this.upperTreehashLeaf = gMSSLeafArray3;
        }
        if (nArray2 == null) {
            this.minTreehash = new int[this.numLayer - 1];
            for (n = 0; n < this.numLayer - 1; ++n) {
                this.minTreehash[n] = -1;
            }
        } else {
            this.minTreehash = nArray2;
        }
        byte[] byArray8 = new byte[this.mdLength];
        byte[] byArray9 = new byte[this.mdLength];
        if (gMSSRootSigArray == null) {
            this.nextRootSig = new GMSSRootSig[this.numLayer - 1];
            for (int i = 0; i < this.numLayer - 1; ++i) {
                System.arraycopy(byArray[i], 0, byArray8, 0, this.mdLength);
                this.gmssRandom.nextSeed(byArray8);
                byArray9 = this.gmssRandom.nextSeed(byArray8);
                this.nextRootSig[i] = new GMSSRootSig(gMSSDigestProvider.get(), this.otsIndex[i], this.heightOfTrees[i + 1]);
                this.nextRootSig[i].initSign(byArray9, byArray6[i]);
            }
        } else {
            this.nextRootSig = gMSSRootSigArray;
        }
    }

    private GMSSPrivateKeyParameters(GMSSPrivateKeyParameters gMSSPrivateKeyParameters) {
        super(true, gMSSPrivateKeyParameters.getParameters());
        this.index = Arrays.clone(gMSSPrivateKeyParameters.index);
        this.currentSeeds = Arrays.clone(gMSSPrivateKeyParameters.currentSeeds);
        this.nextNextSeeds = Arrays.clone(gMSSPrivateKeyParameters.nextNextSeeds);
        this.currentAuthPaths = Arrays.clone(gMSSPrivateKeyParameters.currentAuthPaths);
        this.nextAuthPaths = Arrays.clone(gMSSPrivateKeyParameters.nextAuthPaths);
        this.currentTreehash = gMSSPrivateKeyParameters.currentTreehash;
        this.nextTreehash = gMSSPrivateKeyParameters.nextTreehash;
        this.currentStack = gMSSPrivateKeyParameters.currentStack;
        this.nextStack = gMSSPrivateKeyParameters.nextStack;
        this.currentRetain = gMSSPrivateKeyParameters.currentRetain;
        this.nextRetain = gMSSPrivateKeyParameters.nextRetain;
        this.keep = Arrays.clone(gMSSPrivateKeyParameters.keep);
        this.nextNextLeaf = gMSSPrivateKeyParameters.nextNextLeaf;
        this.upperLeaf = gMSSPrivateKeyParameters.upperLeaf;
        this.upperTreehashLeaf = gMSSPrivateKeyParameters.upperTreehashLeaf;
        this.minTreehash = gMSSPrivateKeyParameters.minTreehash;
        this.gmssPS = gMSSPrivateKeyParameters.gmssPS;
        this.nextRoot = Arrays.clone(gMSSPrivateKeyParameters.nextRoot);
        this.nextNextRoot = gMSSPrivateKeyParameters.nextNextRoot;
        this.currentRootSig = gMSSPrivateKeyParameters.currentRootSig;
        this.nextRootSig = gMSSPrivateKeyParameters.nextRootSig;
        this.digestProvider = gMSSPrivateKeyParameters.digestProvider;
        this.heightOfTrees = gMSSPrivateKeyParameters.heightOfTrees;
        this.otsIndex = gMSSPrivateKeyParameters.otsIndex;
        this.K = gMSSPrivateKeyParameters.K;
        this.numLayer = gMSSPrivateKeyParameters.numLayer;
        this.messDigestTrees = gMSSPrivateKeyParameters.messDigestTrees;
        this.mdLength = gMSSPrivateKeyParameters.mdLength;
        this.gmssRandom = gMSSPrivateKeyParameters.gmssRandom;
        this.numLeafs = gMSSPrivateKeyParameters.numLeafs;
    }

    public boolean isUsed() {
        return this.used;
    }

    public void markUsed() {
        this.used = true;
    }

    public GMSSPrivateKeyParameters nextKey() {
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = new GMSSPrivateKeyParameters(this);
        gMSSPrivateKeyParameters.nextKey(this.gmssPS.getNumOfLayers() - 1);
        return gMSSPrivateKeyParameters;
    }

    private void nextKey(int n) {
        if (n == this.numLayer - 1) {
            int n2 = n;
            this.index[n2] = this.index[n2] + 1;
        }
        if (this.index[n] == this.numLeafs[n]) {
            if (this.numLayer != 1) {
                this.nextTree(n);
                this.index[n] = 0;
            }
        } else {
            this.updateKey(n);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void nextTree(int n) {
        if (n > 0) {
            int n2 = n - 1;
            this.index[n2] = this.index[n2] + 1;
            boolean bl = true;
            int n3 = n;
            do {
                if (this.index[--n3] >= this.numLeafs[n3]) continue;
                bl = false;
            } while (bl && n3 > 0);
            if (!bl) {
                int n4;
                this.gmssRandom.nextSeed(this.currentSeeds[n]);
                this.nextRootSig[n - 1].updateSign();
                if (n > 1) {
                    this.nextNextLeaf[n - 1 - 1] = this.nextNextLeaf[n - 1 - 1].nextLeaf();
                }
                this.upperLeaf[n - 1] = this.upperLeaf[n - 1].nextLeaf();
                if (this.minTreehash[n - 1] >= 0) {
                    this.upperTreehashLeaf[n - 1] = this.upperTreehashLeaf[n - 1].nextLeaf();
                    byte[] byArray = this.upperTreehashLeaf[n - 1].getLeaf();
                    try {
                        this.currentTreehash[n - 1][this.minTreehash[n - 1]].update(this.gmssRandom, byArray);
                        if (!this.currentTreehash[n - 1][this.minTreehash[n - 1]].wasFinished()) {
                            // empty if block
                        }
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                }
                this.updateNextNextAuthRoot(n);
                this.currentRootSig[n - 1] = this.nextRootSig[n - 1].getSig();
                for (n4 = 0; n4 < this.heightOfTrees[n] - this.K[n]; ++n4) {
                    this.currentTreehash[n][n4] = this.nextTreehash[n - 1][n4];
                    this.nextTreehash[n - 1][n4] = this.nextNextRoot[n - 1].getTreehash()[n4];
                }
                for (n4 = 0; n4 < this.heightOfTrees[n]; ++n4) {
                    System.arraycopy(this.nextAuthPaths[n - 1][n4], 0, this.currentAuthPaths[n][n4], 0, this.mdLength);
                    System.arraycopy(this.nextNextRoot[n - 1].getAuthPath()[n4], 0, this.nextAuthPaths[n - 1][n4], 0, this.mdLength);
                }
                for (n4 = 0; n4 < this.K[n] - 1; ++n4) {
                    this.currentRetain[n][n4] = this.nextRetain[n - 1][n4];
                    this.nextRetain[n - 1][n4] = this.nextNextRoot[n - 1].getRetain()[n4];
                }
                this.currentStack[n] = this.nextStack[n - 1];
                this.nextStack[n - 1] = this.nextNextRoot[n - 1].getStack();
                this.nextRoot[n - 1] = this.nextNextRoot[n - 1].getRoot();
                byte[] byArray = new byte[this.mdLength];
                byte[] byArray2 = new byte[this.mdLength];
                System.arraycopy(this.currentSeeds[n - 1], 0, byArray2, 0, this.mdLength);
                byArray = this.gmssRandom.nextSeed(byArray2);
                byArray = this.gmssRandom.nextSeed(byArray2);
                byArray = this.gmssRandom.nextSeed(byArray2);
                this.nextRootSig[n - 1].initSign(byArray, this.nextRoot[n - 1]);
                this.nextKey(n - 1);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void updateKey(int n) {
        this.computeAuthPaths(n);
        if (n > 0) {
            if (n > 1) {
                this.nextNextLeaf[n - 1 - 1] = this.nextNextLeaf[n - 1 - 1].nextLeaf();
            }
            this.upperLeaf[n - 1] = this.upperLeaf[n - 1].nextLeaf();
            int n2 = (int)Math.floor((double)(this.getNumLeafs(n) * 2) / (double)(this.heightOfTrees[n - 1] - this.K[n - 1]));
            if (this.index[n] % n2 == 1) {
                byte[] byArray;
                if (this.index[n] > 1 && this.minTreehash[n - 1] >= 0) {
                    byArray = this.upperTreehashLeaf[n - 1].getLeaf();
                    try {
                        this.currentTreehash[n - 1][this.minTreehash[n - 1]].update(this.gmssRandom, byArray);
                        if (!this.currentTreehash[n - 1][this.minTreehash[n - 1]].wasFinished()) {
                            // empty if block
                        }
                    } catch (Exception exception) {
                        System.out.println(exception);
                    }
                }
                this.minTreehash[n - 1] = this.getMinTreehashIndex(n - 1);
                if (this.minTreehash[n - 1] >= 0) {
                    byArray = this.currentTreehash[n - 1][this.minTreehash[n - 1]].getSeedActive();
                    this.upperTreehashLeaf[n - 1] = new GMSSLeaf(this.digestProvider.get(), this.otsIndex[n - 1], n2, byArray);
                    this.upperTreehashLeaf[n - 1] = this.upperTreehashLeaf[n - 1].nextLeaf();
                }
            } else if (this.minTreehash[n - 1] >= 0) {
                this.upperTreehashLeaf[n - 1] = this.upperTreehashLeaf[n - 1].nextLeaf();
            }
            this.nextRootSig[n - 1].updateSign();
            if (this.index[n] == 1) {
                this.nextNextRoot[n - 1].initialize(new Vector());
            }
            this.updateNextNextAuthRoot(n);
        }
    }

    private int getMinTreehashIndex(int n) {
        int n2 = -1;
        for (int i = 0; i < this.heightOfTrees[n] - this.K[n]; ++i) {
            if (!this.currentTreehash[n][i].wasInitialized() || this.currentTreehash[n][i].wasFinished()) continue;
            if (n2 == -1) {
                n2 = i;
                continue;
            }
            if (this.currentTreehash[n][i].getLowestNodeHeight() >= this.currentTreehash[n][n2].getLowestNodeHeight()) continue;
            n2 = i;
        }
        return n2;
    }

    private void computeAuthPaths(int n) {
        int n2;
        Object object;
        int n3;
        int n4 = this.index[n];
        int n5 = this.heightOfTrees[n];
        int n6 = this.K[n];
        for (n3 = 0; n3 < n5 - n6; ++n3) {
            this.currentTreehash[n][n3].updateNextSeed(this.gmssRandom);
        }
        n3 = this.heightOfPhi(n4);
        byte[] byArray = new byte[this.mdLength];
        byArray = this.gmssRandom.nextSeed(this.currentSeeds[n]);
        int n7 = n4 >>> n3 + 1 & 1;
        byte[] byArray2 = new byte[this.mdLength];
        if (n3 < n5 - 1 && n7 == 0) {
            System.arraycopy(this.currentAuthPaths[n][n3], 0, byArray2, 0, this.mdLength);
        }
        byte[] byArray3 = new byte[this.mdLength];
        if (n3 == 0) {
            if (n == this.numLayer - 1) {
                object = new WinternitzOTSignature(byArray, this.digestProvider.get(), this.otsIndex[n]);
                byArray3 = ((WinternitzOTSignature)object).getPublicKey();
            } else {
                object = new byte[this.mdLength];
                System.arraycopy(this.currentSeeds[n], 0, object, 0, this.mdLength);
                this.gmssRandom.nextSeed((byte[])object);
                byArray3 = this.upperLeaf[n].getLeaf();
                this.upperLeaf[n].initLeafCalc((byte[])object);
            }
            System.arraycopy(byArray3, 0, this.currentAuthPaths[n][0], 0, this.mdLength);
        } else {
            object = new byte[this.mdLength << 1];
            System.arraycopy(this.currentAuthPaths[n][n3 - 1], 0, object, 0, this.mdLength);
            System.arraycopy(this.keep[n][(int)Math.floor((n3 - 1) / 2)], 0, object, this.mdLength, this.mdLength);
            this.messDigestTrees.update((byte[])object, 0, ((Object)object).length);
            this.currentAuthPaths[n][n3] = new byte[this.messDigestTrees.getDigestSize()];
            this.messDigestTrees.doFinal(this.currentAuthPaths[n][n3], 0);
            for (n2 = 0; n2 < n3; ++n2) {
                int n8;
                if (n2 < n5 - n6) {
                    if (this.currentTreehash[n][n2].wasFinished()) {
                        System.arraycopy(this.currentTreehash[n][n2].getFirstNode(), 0, this.currentAuthPaths[n][n2], 0, this.mdLength);
                        this.currentTreehash[n][n2].destroy();
                    } else {
                        System.err.println("Treehash (" + n + "," + n2 + ") not finished when needed in AuthPathComputation");
                    }
                }
                if (n2 < n5 - 1 && n2 >= n5 - n6 && this.currentRetain[n][n2 - (n5 - n6)].size() > 0) {
                    System.arraycopy(this.currentRetain[n][n2 - (n5 - n6)].lastElement(), 0, this.currentAuthPaths[n][n2], 0, this.mdLength);
                    this.currentRetain[n][n2 - (n5 - n6)].removeElementAt(this.currentRetain[n][n2 - (n5 - n6)].size() - 1);
                }
                if (n2 >= n5 - n6 || (n8 = n4 + 3 * (1 << n2)) >= this.numLeafs[n]) continue;
                this.currentTreehash[n][n2].initialize();
            }
        }
        if (n3 < n5 - 1 && n7 == 0) {
            System.arraycopy(byArray2, 0, this.keep[n][(int)Math.floor(n3 / 2)], 0, this.mdLength);
        }
        if (n == this.numLayer - 1) {
            for (int i = 1; i <= (n5 - n6) / 2; ++i) {
                n2 = this.getMinTreehashIndex(n);
                if (n2 < 0) continue;
                try {
                    byte[] byArray4 = new byte[this.mdLength];
                    System.arraycopy(this.currentTreehash[n][n2].getSeedActive(), 0, byArray4, 0, this.mdLength);
                    byte[] byArray5 = this.gmssRandom.nextSeed(byArray4);
                    WinternitzOTSignature winternitzOTSignature = new WinternitzOTSignature(byArray5, this.digestProvider.get(), this.otsIndex[n]);
                    byte[] byArray6 = winternitzOTSignature.getPublicKey();
                    this.currentTreehash[n][n2].update(this.gmssRandom, byArray6);
                    continue;
                } catch (Exception exception) {
                    System.out.println(exception);
                }
            }
        } else {
            this.minTreehash[n] = this.getMinTreehashIndex(n);
        }
    }

    private int heightOfPhi(int n) {
        if (n == 0) {
            return -1;
        }
        int n2 = 0;
        int n3 = 1;
        while (n % n3 == 0) {
            n3 *= 2;
            ++n2;
        }
        return n2 - 1;
    }

    private void updateNextNextAuthRoot(int n) {
        byte[] byArray = new byte[this.mdLength];
        byArray = this.gmssRandom.nextSeed(this.nextNextSeeds[n - 1]);
        if (n == this.numLayer - 1) {
            WinternitzOTSignature winternitzOTSignature = new WinternitzOTSignature(byArray, this.digestProvider.get(), this.otsIndex[n]);
            this.nextNextRoot[n - 1].update(this.nextNextSeeds[n - 1], winternitzOTSignature.getPublicKey());
        } else {
            this.nextNextRoot[n - 1].update(this.nextNextSeeds[n - 1], this.nextNextLeaf[n - 1].getLeaf());
            this.nextNextLeaf[n - 1].initLeafCalc(this.nextNextSeeds[n - 1]);
        }
    }

    public int[] getIndex() {
        return this.index;
    }

    public int getIndex(int n) {
        return this.index[n];
    }

    public byte[][] getCurrentSeeds() {
        return Arrays.clone(this.currentSeeds);
    }

    public byte[][][] getCurrentAuthPaths() {
        return Arrays.clone(this.currentAuthPaths);
    }

    public byte[] getSubtreeRootSig(int n) {
        return this.currentRootSig[n];
    }

    public GMSSDigestProvider getName() {
        return this.digestProvider;
    }

    public int getNumLeafs(int n) {
        return this.numLeafs[n];
    }
}

