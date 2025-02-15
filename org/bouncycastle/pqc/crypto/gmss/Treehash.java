/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.encoders.Hex;

public class Treehash {
    private int maxHeight;
    private Vector tailStack;
    private Vector heightOfNodes;
    private byte[] firstNode;
    private byte[] seedActive;
    private byte[] seedNext;
    private int tailLength;
    private int firstNodeHeight;
    private boolean isInitialized;
    private boolean isFinished;
    private boolean seedInitialized;
    private Digest messDigestTree;

    public Treehash(Digest digest, byte[][] byArray, int[] nArray) {
        int n;
        this.messDigestTree = digest;
        this.maxHeight = nArray[0];
        this.tailLength = nArray[1];
        this.firstNodeHeight = nArray[2];
        this.isFinished = nArray[3] == 1;
        this.isInitialized = nArray[4] == 1;
        this.seedInitialized = nArray[5] == 1;
        this.heightOfNodes = new Vector();
        for (n = 0; n < this.tailLength; ++n) {
            this.heightOfNodes.addElement(Integers.valueOf(nArray[6 + n]));
        }
        this.firstNode = byArray[0];
        this.seedActive = byArray[1];
        this.seedNext = byArray[2];
        this.tailStack = new Vector();
        for (n = 0; n < this.tailLength; ++n) {
            this.tailStack.addElement(byArray[3 + n]);
        }
    }

    public Treehash(Vector vector, int n, Digest digest) {
        this.tailStack = vector;
        this.maxHeight = n;
        this.firstNode = null;
        this.isInitialized = false;
        this.isFinished = false;
        this.seedInitialized = false;
        this.messDigestTree = digest;
        this.seedNext = new byte[this.messDigestTree.getDigestSize()];
        this.seedActive = new byte[this.messDigestTree.getDigestSize()];
    }

    public void initializeSeed(byte[] byArray) {
        System.arraycopy(byArray, 0, this.seedNext, 0, this.messDigestTree.getDigestSize());
        this.seedInitialized = true;
    }

    public void initialize() {
        if (!this.seedInitialized) {
            System.err.println("Seed " + this.maxHeight + " not initialized");
            return;
        }
        this.heightOfNodes = new Vector();
        this.tailLength = 0;
        this.firstNode = null;
        this.firstNodeHeight = -1;
        this.isInitialized = true;
        System.arraycopy(this.seedNext, 0, this.seedActive, 0, this.messDigestTree.getDigestSize());
    }

    public void update(GMSSRandom gMSSRandom, byte[] byArray) {
        if (this.isFinished) {
            System.err.println("No more update possible for treehash instance!");
            return;
        }
        if (!this.isInitialized) {
            System.err.println("Treehash instance not initialized before update");
            return;
        }
        byte[] byArray2 = new byte[this.messDigestTree.getDigestSize()];
        int n = -1;
        gMSSRandom.nextSeed(this.seedActive);
        if (this.firstNode == null) {
            this.firstNode = byArray;
            this.firstNodeHeight = 0;
        } else {
            byte[] byArray3;
            byArray2 = byArray;
            n = 0;
            while (this.tailLength > 0 && n == (Integer)this.heightOfNodes.lastElement()) {
                byArray3 = new byte[this.messDigestTree.getDigestSize() << 1];
                System.arraycopy(this.tailStack.lastElement(), 0, byArray3, 0, this.messDigestTree.getDigestSize());
                this.tailStack.removeElementAt(this.tailStack.size() - 1);
                this.heightOfNodes.removeElementAt(this.heightOfNodes.size() - 1);
                System.arraycopy(byArray2, 0, byArray3, this.messDigestTree.getDigestSize(), this.messDigestTree.getDigestSize());
                this.messDigestTree.update(byArray3, 0, byArray3.length);
                byArray2 = new byte[this.messDigestTree.getDigestSize()];
                this.messDigestTree.doFinal(byArray2, 0);
                ++n;
                --this.tailLength;
            }
            this.tailStack.addElement(byArray2);
            this.heightOfNodes.addElement(Integers.valueOf(n));
            ++this.tailLength;
            if ((Integer)this.heightOfNodes.lastElement() == this.firstNodeHeight) {
                byArray3 = new byte[this.messDigestTree.getDigestSize() << 1];
                System.arraycopy(this.firstNode, 0, byArray3, 0, this.messDigestTree.getDigestSize());
                System.arraycopy(this.tailStack.lastElement(), 0, byArray3, this.messDigestTree.getDigestSize(), this.messDigestTree.getDigestSize());
                this.tailStack.removeElementAt(this.tailStack.size() - 1);
                this.heightOfNodes.removeElementAt(this.heightOfNodes.size() - 1);
                this.messDigestTree.update(byArray3, 0, byArray3.length);
                this.firstNode = new byte[this.messDigestTree.getDigestSize()];
                this.messDigestTree.doFinal(this.firstNode, 0);
                ++this.firstNodeHeight;
                this.tailLength = 0;
            }
        }
        if (this.firstNodeHeight == this.maxHeight) {
            this.isFinished = true;
        }
    }

    public void destroy() {
        this.isInitialized = false;
        this.isFinished = false;
        this.firstNode = null;
        this.tailLength = 0;
        this.firstNodeHeight = -1;
    }

    public int getLowestNodeHeight() {
        if (this.firstNode == null) {
            return this.maxHeight;
        }
        if (this.tailLength == 0) {
            return this.firstNodeHeight;
        }
        return Math.min(this.firstNodeHeight, (Integer)this.heightOfNodes.lastElement());
    }

    public int getFirstNodeHeight() {
        if (this.firstNode == null) {
            return this.maxHeight;
        }
        return this.firstNodeHeight;
    }

    public boolean wasInitialized() {
        return this.isInitialized;
    }

    public boolean wasFinished() {
        return this.isFinished;
    }

    public byte[] getFirstNode() {
        return this.firstNode;
    }

    public byte[] getSeedActive() {
        return this.seedActive;
    }

    public void setFirstNode(byte[] byArray) {
        if (!this.isInitialized) {
            this.initialize();
        }
        this.firstNode = byArray;
        this.firstNodeHeight = this.maxHeight;
        this.isFinished = true;
    }

    public void updateNextSeed(GMSSRandom gMSSRandom) {
        gMSSRandom.nextSeed(this.seedNext);
    }

    public Vector getTailStack() {
        return this.tailStack;
    }

    public byte[][] getStatByte() {
        byte[][] byArray = new byte[3 + this.tailLength][this.messDigestTree.getDigestSize()];
        byArray[0] = this.firstNode;
        byArray[1] = this.seedActive;
        byArray[2] = this.seedNext;
        for (int i = 0; i < this.tailLength; ++i) {
            byArray[3 + i] = (byte[])this.tailStack.elementAt(i);
        }
        return byArray;
    }

    public int[] getStatInt() {
        int[] nArray = new int[6 + this.tailLength];
        nArray[0] = this.maxHeight;
        nArray[1] = this.tailLength;
        nArray[2] = this.firstNodeHeight;
        nArray[3] = this.isFinished ? 1 : 0;
        nArray[4] = this.isInitialized ? 1 : 0;
        nArray[5] = this.seedInitialized ? 1 : 0;
        for (int i = 0; i < this.tailLength; ++i) {
            nArray[6 + i] = (Integer)this.heightOfNodes.elementAt(i);
        }
        return nArray;
    }

    public String toString() {
        int n;
        String string = "Treehash    : ";
        for (n = 0; n < 6 + this.tailLength; ++n) {
            string = string + this.getStatInt()[n] + " ";
        }
        for (n = 0; n < 3 + this.tailLength; ++n) {
            string = this.getStatByte()[n] != null ? string + new String(Hex.encode(this.getStatByte()[n])) + " " : string + "null ";
        }
        string = string + "  " + this.messDigestTree.getDigestSize();
        return string;
    }
}

