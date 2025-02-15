/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.crypto.gmss;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.pqc.crypto.gmss.GMSSLeaf;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootSig;
import org.bouncycastle.pqc.crypto.gmss.Treehash;
import org.bouncycastle.util.Arrays;

class GMSSUtils {
    GMSSUtils() {
    }

    static GMSSLeaf[] clone(GMSSLeaf[] gMSSLeafArray) {
        if (gMSSLeafArray == null) {
            return null;
        }
        GMSSLeaf[] gMSSLeafArray2 = new GMSSLeaf[gMSSLeafArray.length];
        System.arraycopy(gMSSLeafArray, 0, gMSSLeafArray2, 0, gMSSLeafArray.length);
        return gMSSLeafArray2;
    }

    static GMSSRootCalc[] clone(GMSSRootCalc[] gMSSRootCalcArray) {
        if (gMSSRootCalcArray == null) {
            return null;
        }
        GMSSRootCalc[] gMSSRootCalcArray2 = new GMSSRootCalc[gMSSRootCalcArray.length];
        System.arraycopy(gMSSRootCalcArray, 0, gMSSRootCalcArray2, 0, gMSSRootCalcArray.length);
        return gMSSRootCalcArray2;
    }

    static GMSSRootSig[] clone(GMSSRootSig[] gMSSRootSigArray) {
        if (gMSSRootSigArray == null) {
            return null;
        }
        GMSSRootSig[] gMSSRootSigArray2 = new GMSSRootSig[gMSSRootSigArray.length];
        System.arraycopy(gMSSRootSigArray, 0, gMSSRootSigArray2, 0, gMSSRootSigArray.length);
        return gMSSRootSigArray2;
    }

    static byte[][] clone(byte[][] byArray) {
        if (byArray == null) {
            return null;
        }
        byte[][] byArrayArray = new byte[byArray.length][];
        for (int i = 0; i != byArray.length; ++i) {
            byArrayArray[i] = Arrays.clone(byArray[i]);
        }
        return byArrayArray;
    }

    static byte[][][] clone(byte[][][] byArray) {
        if (byArray == null) {
            return null;
        }
        byte[][][] byArrayArray = new byte[byArray.length][][];
        for (int i = 0; i != byArray.length; ++i) {
            byArrayArray[i] = GMSSUtils.clone(byArray[i]);
        }
        return byArrayArray;
    }

    static Treehash[] clone(Treehash[] treehashArray) {
        if (treehashArray == null) {
            return null;
        }
        Treehash[] treehashArray2 = new Treehash[treehashArray.length];
        System.arraycopy(treehashArray, 0, treehashArray2, 0, treehashArray.length);
        return treehashArray2;
    }

    static Treehash[][] clone(Treehash[][] treehashArray) {
        if (treehashArray == null) {
            return null;
        }
        Treehash[][] treehashArray2 = new Treehash[treehashArray.length][];
        for (int i = 0; i != treehashArray.length; ++i) {
            treehashArray2[i] = GMSSUtils.clone(treehashArray[i]);
        }
        return treehashArray2;
    }

    static Vector[] clone(Vector[] vectorArray) {
        if (vectorArray == null) {
            return null;
        }
        Vector[] vectorArray2 = new Vector[vectorArray.length];
        for (int i = 0; i != vectorArray.length; ++i) {
            vectorArray2[i] = new Vector();
            Enumeration enumeration = vectorArray[i].elements();
            while (enumeration.hasMoreElements()) {
                vectorArray2[i].addElement(enumeration.nextElement());
            }
        }
        return vectorArray2;
    }

    static Vector[][] clone(Vector[][] vectorArray) {
        if (vectorArray == null) {
            return null;
        }
        Vector[][] vectorArray2 = new Vector[vectorArray.length][];
        for (int i = 0; i != vectorArray.length; ++i) {
            vectorArray2[i] = GMSSUtils.clone(vectorArray[i]);
        }
        return vectorArray2;
    }
}

