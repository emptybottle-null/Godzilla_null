/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.GMSSDigestProvider;
import org.bouncycastle.pqc.crypto.gmss.GMSSUtils;
import org.bouncycastle.pqc.crypto.gmss.Treehash;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.encoders.Hex;

public class GMSSRootCalc {
    private int heightOfTree;
    private int mdLength;
    private Treehash[] treehash;
    private Vector[] retain;
    private byte[] root;
    private byte[][] AuthPath;
    private int K;
    private Vector tailStack;
    private Vector heightOfNodes;
    private Digest messDigestTree;
    private GMSSDigestProvider digestProvider;
    private int[] index;
    private boolean isInitialized;
    private boolean isFinished;
    private int indexForNextSeed;
    private int heightOfNextSeed;

    public GMSSRootCalc(Digest digest, byte[][] byArray, int[] nArray, Treehash[] treehashArray, Vector[] vectorArray) {
        int n;
        this.messDigestTree = this.digestProvider.get();
        this.digestProvider = this.digestProvider;
        this.heightOfTree = nArray[0];
        this.mdLength = nArray[1];
        this.K = nArray[2];
        this.indexForNextSeed = nArray[3];
        this.heightOfNextSeed = nArray[4];
        this.isFinished = nArray[5] == 1;
        this.isInitialized = nArray[6] == 1;
        int n2 = nArray[7];
        this.index = new int[this.heightOfTree];
        for (n = 0; n < this.heightOfTree; ++n) {
            this.index[n] = nArray[8 + n];
        }
        this.heightOfNodes = new Vector();
        for (n = 0; n < n2; ++n) {
            this.heightOfNodes.addElement(Integers.valueOf(nArray[8 + this.heightOfTree + n]));
        }
        this.root = byArray[0];
        this.AuthPath = new byte[this.heightOfTree][this.mdLength];
        for (n = 0; n < this.heightOfTree; ++n) {
            this.AuthPath[n] = byArray[1 + n];
        }
        this.tailStack = new Vector();
        for (n = 0; n < n2; ++n) {
            this.tailStack.addElement(byArray[1 + this.heightOfTree + n]);
        }
        this.treehash = GMSSUtils.clone(treehashArray);
        this.retain = GMSSUtils.clone(vectorArray);
    }

    public GMSSRootCalc(int n, int n2, GMSSDigestProvider gMSSDigestProvider) {
        this.heightOfTree = n;
        this.digestProvider = gMSSDigestProvider;
        this.messDigestTree = gMSSDigestProvider.get();
        this.mdLength = this.messDigestTree.getDigestSize();
        this.K = n2;
        this.index = new int[n];
        this.AuthPath = new byte[n][this.mdLength];
        this.root = new byte[this.mdLength];
        this.retain = new Vector[this.K - 1];
        for (int i = 0; i < n2 - 1; ++i) {
            this.retain[i] = new Vector();
        }
    }

    public void initialize(Vector vector) {
        int n;
        this.treehash = new Treehash[this.heightOfTree - this.K];
        for (n = 0; n < this.heightOfTree - this.K; ++n) {
            this.treehash[n] = new Treehash(vector, n, this.digestProvider.get());
        }
        this.index = new int[this.heightOfTree];
        this.AuthPath = new byte[this.heightOfTree][this.mdLength];
        this.root = new byte[this.mdLength];
        this.tailStack = new Vector();
        this.heightOfNodes = new Vector();
        this.isInitialized = true;
        this.isFinished = false;
        for (n = 0; n < this.heightOfTree; ++n) {
            this.index[n] = -1;
        }
        this.retain = new Vector[this.K - 1];
        for (n = 0; n < this.K - 1; ++n) {
            this.retain[n] = new Vector();
        }
        this.indexForNextSeed = 3;
        this.heightOfNextSeed = 0;
    }

    public void update(byte[] byArray, byte[] byArray2) {
        if (this.heightOfNextSeed < this.heightOfTree - this.K && this.indexForNextSeed - 2 == this.index[0]) {
            this.initializeTreehashSeed(byArray, this.heightOfNextSeed);
            ++this.heightOfNextSeed;
            this.indexForNextSeed *= 2;
        }
        this.update(byArray2);
    }

    public void update(byte[] byArray) {
        if (this.isFinished) {
            System.out.print("Too much updates for Tree!!");
            return;
        }
        if (!this.isInitialized) {
            System.err.println("GMSSRootCalc not initialized!");
            return;
        }
        this.index[0] = this.index[0] + 1;
        if (this.index[0] == 1) {
            System.arraycopy(byArray, 0, this.AuthPath[0], 0, this.mdLength);
        } else if (this.index[0] == 3 && this.heightOfTree > this.K) {
            this.treehash[0].setFirstNode(byArray);
        }
        if ((this.index[0] - 3) % 2 == 0 && this.index[0] >= 3 && this.heightOfTree == this.K) {
            this.retain[0].insertElementAt(byArray, 0);
        }
        if (this.index[0] == 0) {
            this.tailStack.addElement(byArray);
            this.heightOfNodes.addElement(Integers.valueOf(0));
        } else {
            byte[] byArray2 = new byte[this.mdLength];
            byte[] byArray3 = new byte[this.mdLength << 1];
            System.arraycopy(byArray, 0, byArray2, 0, this.mdLength);
            int n = 0;
            while (this.tailStack.size() > 0 && n == (Integer)this.heightOfNodes.lastElement()) {
                System.arraycopy(this.tailStack.lastElement(), 0, byArray3, 0, this.mdLength);
                this.tailStack.removeElementAt(this.tailStack.size() - 1);
                this.heightOfNodes.removeElementAt(this.heightOfNodes.size() - 1);
                System.arraycopy(byArray2, 0, byArray3, this.mdLength, this.mdLength);
                this.messDigestTree.update(byArray3, 0, byArray3.length);
                byArray2 = new byte[this.messDigestTree.getDigestSize()];
                this.messDigestTree.doFinal(byArray2, 0);
                if (++n >= this.heightOfTree) continue;
                int n2 = n;
                this.index[n2] = this.index[n2] + 1;
                if (this.index[n] == 1) {
                    System.arraycopy(byArray2, 0, this.AuthPath[n], 0, this.mdLength);
                }
                if (n >= this.heightOfTree - this.K) {
                    if (n == 0) {
                        System.out.println("M\ufffd\ufffd\ufffdP");
                    }
                    if ((this.index[n] - 3) % 2 != 0 || this.index[n] < 3) continue;
                    this.retain[n - (this.heightOfTree - this.K)].insertElementAt(byArray2, 0);
                    continue;
                }
                if (this.index[n] != 3) continue;
                this.treehash[n].setFirstNode(byArray2);
            }
            this.tailStack.addElement(byArray2);
            this.heightOfNodes.addElement(Integers.valueOf(n));
            if (n == this.heightOfTree) {
                this.isFinished = true;
                this.isInitialized = false;
                this.root = (byte[])this.tailStack.lastElement();
            }
        }
    }

    public void initializeTreehashSeed(byte[] byArray, int n) {
        this.treehash[n].initializeSeed(byArray);
    }

    public boolean wasInitialized() {
        return this.isInitialized;
    }

    public boolean wasFinished() {
        return this.isFinished;
    }

    public byte[][] getAuthPath() {
        return GMSSUtils.clone(this.AuthPath);
    }

    public Treehash[] getTreehash() {
        return GMSSUtils.clone(this.treehash);
    }

    public Vector[] getRetain() {
        return GMSSUtils.clone(this.retain);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.root);
    }

    public Vector getStack() {
        Vector vector = new Vector();
        Enumeration enumeration = this.tailStack.elements();
        while (enumeration.hasMoreElements()) {
            vector.addElement(enumeration.nextElement());
        }
        return vector;
    }

    public byte[][] getStatByte() {
        int n;
        int n2 = this.tailStack == null ? 0 : this.tailStack.size();
        byte[][] byArray = new byte[1 + this.heightOfTree + n2][64];
        byArray[0] = this.root;
        for (n = 0; n < this.heightOfTree; ++n) {
            byArray[1 + n] = this.AuthPath[n];
        }
        for (n = 0; n < n2; ++n) {
            byArray[1 + this.heightOfTree + n] = (byte[])this.tailStack.elementAt(n);
        }
        return byArray;
    }

    public int[] getStatInt() {
        int n;
        int n2 = this.tailStack == null ? 0 : this.tailStack.size();
        int[] nArray = new int[8 + this.heightOfTree + n2];
        nArray[0] = this.heightOfTree;
        nArray[1] = this.mdLength;
        nArray[2] = this.K;
        nArray[3] = this.indexForNextSeed;
        nArray[4] = this.heightOfNextSeed;
        nArray[5] = this.isFinished ? 1 : 0;
        nArray[6] = this.isInitialized ? 1 : 0;
        nArray[7] = n2;
        for (n = 0; n < this.heightOfTree; ++n) {
            nArray[8 + n] = this.index[n];
        }
        for (n = 0; n < n2; ++n) {
            nArray[8 + this.heightOfTree + n] = (Integer)this.heightOfNodes.elementAt(n);
        }
        return nArray;
    }

    public String toString() {
        int n;
        String string = "";
        int n2 = this.tailStack == null ? 0 : this.tailStack.size();
        for (n = 0; n < 8 + this.heightOfTree + n2; ++n) {
            string = string + this.getStatInt()[n] + " ";
        }
        for (n = 0; n < 1 + this.heightOfTree + n2; ++n) {
            string = string + new String(Hex.encode(this.getStatByte()[n])) + " ";
        }
        string = string + "  " + this.digestProvider.get().getDigestSize();
        return string;
    }
}

