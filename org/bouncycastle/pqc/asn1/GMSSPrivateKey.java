/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.asn1;

import java.math.BigInteger;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.pqc.crypto.gmss.GMSSLeaf;
import org.bouncycastle.pqc.crypto.gmss.GMSSParameters;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootCalc;
import org.bouncycastle.pqc.crypto.gmss.GMSSRootSig;
import org.bouncycastle.pqc.crypto.gmss.Treehash;

public class GMSSPrivateKey
extends ASN1Object {
    private ASN1Primitive primitive;

    private GMSSPrivateKey(ASN1Sequence aSN1Sequence) {
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(0);
        int[] nArray = new int[aSN1Sequence2.size()];
        for (int i = 0; i < aSN1Sequence2.size(); ++i) {
            nArray[i] = GMSSPrivateKey.checkBigIntegerInIntRange(aSN1Sequence2.getObjectAt(i));
        }
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence)aSN1Sequence.getObjectAt(1);
        byte[][] byArrayArray = new byte[aSN1Sequence3.size()][];
        for (int i = 0; i < byArrayArray.length; ++i) {
            byArrayArray[i] = ((DEROctetString)aSN1Sequence3.getObjectAt(i)).getOctets();
        }
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence)aSN1Sequence.getObjectAt(2);
        byte[][] byArrayArray2 = new byte[aSN1Sequence4.size()][];
        for (int i = 0; i < byArrayArray2.length; ++i) {
            byArrayArray2[i] = ((DEROctetString)aSN1Sequence4.getObjectAt(i)).getOctets();
        }
        ASN1Sequence aSN1Sequence5 = (ASN1Sequence)aSN1Sequence.getObjectAt(3);
        byte[][][] byArrayArray3 = new byte[aSN1Sequence5.size()][][];
        for (int i = 0; i < byArrayArray3.length; ++i) {
            ASN1Sequence aSN1Sequence6 = (ASN1Sequence)aSN1Sequence5.getObjectAt(i);
            byArrayArray3[i] = new byte[aSN1Sequence6.size()][];
            for (int j = 0; j < byArrayArray3[i].length; ++j) {
                byArrayArray3[i][j] = ((DEROctetString)aSN1Sequence6.getObjectAt(j)).getOctets();
            }
        }
        ASN1Sequence aSN1Sequence7 = (ASN1Sequence)aSN1Sequence.getObjectAt(4);
        byte[][][] byArrayArray4 = new byte[aSN1Sequence7.size()][][];
        for (int i = 0; i < byArrayArray4.length; ++i) {
            ASN1Sequence aSN1Sequence8 = (ASN1Sequence)aSN1Sequence7.getObjectAt(i);
            byArrayArray4[i] = new byte[aSN1Sequence8.size()][];
            for (int j = 0; j < byArrayArray4[i].length; ++j) {
                byArrayArray4[i][j] = ((DEROctetString)aSN1Sequence8.getObjectAt(j)).getOctets();
            }
        }
        ASN1Sequence aSN1Sequence9 = (ASN1Sequence)aSN1Sequence.getObjectAt(5);
        Treehash[][] treehashArrayArray = new Treehash[aSN1Sequence9.size()][];
    }

    public GMSSPrivateKey(int[] nArray, byte[][] byArray, byte[][] byArray2, byte[][][] byArray3, byte[][][] byArray4, Treehash[][] treehashArray, Treehash[][] treehashArray2, Vector[] vectorArray, Vector[] vectorArray2, Vector[][] vectorArray3, Vector[][] vectorArray4, byte[][][] byArray5, GMSSLeaf[] gMSSLeafArray, GMSSLeaf[] gMSSLeafArray2, GMSSLeaf[] gMSSLeafArray3, int[] nArray2, byte[][] byArray6, GMSSRootCalc[] gMSSRootCalcArray, byte[][] byArray7, GMSSRootSig[] gMSSRootSigArray, GMSSParameters gMSSParameters, AlgorithmIdentifier algorithmIdentifier) {
        AlgorithmIdentifier[] algorithmIdentifierArray = new AlgorithmIdentifier[]{algorithmIdentifier};
        this.primitive = this.encode(nArray, byArray, byArray2, byArray3, byArray4, byArray5, treehashArray, treehashArray2, vectorArray, vectorArray2, vectorArray3, vectorArray4, gMSSLeafArray, gMSSLeafArray2, gMSSLeafArray3, nArray2, byArray6, gMSSRootCalcArray, byArray7, gMSSRootSigArray, gMSSParameters, algorithmIdentifierArray);
    }

    private ASN1Primitive encode(int[] nArray, byte[][] byArray, byte[][] byArray2, byte[][][] byArray3, byte[][][] byArray4, byte[][][] byArray5, Treehash[][] treehashArray, Treehash[][] treehashArray2, Vector[] vectorArray, Vector[] vectorArray2, Vector[][] vectorArray3, Vector[][] vectorArray4, GMSSLeaf[] gMSSLeafArray, GMSSLeaf[] gMSSLeafArray2, GMSSLeaf[] gMSSLeafArray3, int[] nArray2, byte[][] byArray6, GMSSRootCalc[] gMSSRootCalcArray, byte[][] byArray7, GMSSRootSig[] gMSSRootSigArray, GMSSParameters gMSSParameters, AlgorithmIdentifier[] algorithmIdentifierArray) {
        int n;
        Object object;
        Object object2;
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int i = 0; i < nArray.length; ++i) {
            aSN1EncodableVector2.add(new ASN1Integer(nArray[i]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        for (int i = 0; i < byArray.length; ++i) {
            aSN1EncodableVector3.add(new DEROctetString(byArray[i]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        for (int i = 0; i < byArray2.length; ++i) {
            aSN1EncodableVector4.add(new DEROctetString(byArray2[i]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
        ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
        for (int i = 0; i < byArray3.length; ++i) {
            for (int j = 0; j < byArray3[i].length; ++j) {
                aSN1EncodableVector5.add(new DEROctetString(byArray3[i][j]));
            }
            aSN1EncodableVector6.add(new DERSequence(aSN1EncodableVector5));
            aSN1EncodableVector5 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector6));
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector8 = new ASN1EncodableVector();
        for (int i = 0; i < byArray4.length; ++i) {
            for (int j = 0; j < byArray4[i].length; ++j) {
                aSN1EncodableVector7.add(new DEROctetString(byArray4[i][j]));
            }
            aSN1EncodableVector8.add(new DERSequence(aSN1EncodableVector7));
            aSN1EncodableVector7 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector8));
        ASN1EncodableVector aSN1EncodableVector9 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector12 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector13 = new ASN1EncodableVector();
        for (n6 = 0; n6 < treehashArray.length; ++n6) {
            for (n5 = 0; n5 < treehashArray[n6].length; ++n5) {
                aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArray[0]));
                n4 = treehashArray[n6][n5].getStatInt()[1];
                aSN1EncodableVector12.add(new DEROctetString(treehashArray[n6][n5].getStatByte()[0]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArray[n6][n5].getStatByte()[1]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArray[n6][n5].getStatByte()[2]));
                for (n3 = 0; n3 < n4; ++n3) {
                    aSN1EncodableVector12.add(new DEROctetString(treehashArray[n6][n5].getStatByte()[3 + n3]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
                aSN1EncodableVector12 = new ASN1EncodableVector();
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray[n6][n5].getStatInt()[0]));
                aSN1EncodableVector13.add(new ASN1Integer(n4));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray[n6][n5].getStatInt()[2]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray[n6][n5].getStatInt()[3]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray[n6][n5].getStatInt()[4]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray[n6][n5].getStatInt()[5]));
                for (n3 = 0; n3 < n4; ++n3) {
                    aSN1EncodableVector13.add(new ASN1Integer(treehashArray[n6][n5].getStatInt()[6 + n3]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
                aSN1EncodableVector13 = new ASN1EncodableVector();
                aSN1EncodableVector10.add(new DERSequence(aSN1EncodableVector11));
                aSN1EncodableVector11 = new ASN1EncodableVector();
            }
            aSN1EncodableVector9.add(new DERSequence(aSN1EncodableVector10));
            aSN1EncodableVector10 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector9));
        aSN1EncodableVector9 = new ASN1EncodableVector();
        aSN1EncodableVector10 = new ASN1EncodableVector();
        aSN1EncodableVector11 = new ASN1EncodableVector();
        aSN1EncodableVector12 = new ASN1EncodableVector();
        aSN1EncodableVector13 = new ASN1EncodableVector();
        for (n6 = 0; n6 < treehashArray2.length; ++n6) {
            for (n5 = 0; n5 < treehashArray2[n6].length; ++n5) {
                aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArray[0]));
                n4 = treehashArray2[n6][n5].getStatInt()[1];
                aSN1EncodableVector12.add(new DEROctetString(treehashArray2[n6][n5].getStatByte()[0]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArray2[n6][n5].getStatByte()[1]));
                aSN1EncodableVector12.add(new DEROctetString(treehashArray2[n6][n5].getStatByte()[2]));
                for (n3 = 0; n3 < n4; ++n3) {
                    aSN1EncodableVector12.add(new DEROctetString(treehashArray2[n6][n5].getStatByte()[3 + n3]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
                aSN1EncodableVector12 = new ASN1EncodableVector();
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray2[n6][n5].getStatInt()[0]));
                aSN1EncodableVector13.add(new ASN1Integer(n4));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray2[n6][n5].getStatInt()[2]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray2[n6][n5].getStatInt()[3]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray2[n6][n5].getStatInt()[4]));
                aSN1EncodableVector13.add(new ASN1Integer(treehashArray2[n6][n5].getStatInt()[5]));
                for (n3 = 0; n3 < n4; ++n3) {
                    aSN1EncodableVector13.add(new ASN1Integer(treehashArray2[n6][n5].getStatInt()[6 + n3]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
                aSN1EncodableVector13 = new ASN1EncodableVector();
                aSN1EncodableVector10.add(new DERSequence(aSN1EncodableVector11));
                aSN1EncodableVector11 = new ASN1EncodableVector();
            }
            aSN1EncodableVector9.add(new DERSequence(new DERSequence(aSN1EncodableVector10)));
            aSN1EncodableVector10 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector9));
        ASN1EncodableVector aSN1EncodableVector14 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector15 = new ASN1EncodableVector();
        for (n4 = 0; n4 < byArray5.length; ++n4) {
            for (n3 = 0; n3 < byArray5[n4].length; ++n3) {
                aSN1EncodableVector14.add(new DEROctetString(byArray5[n4][n3]));
            }
            aSN1EncodableVector15.add(new DERSequence(aSN1EncodableVector14));
            aSN1EncodableVector14 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector15));
        ASN1EncodableVector aSN1EncodableVector16 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector17 = new ASN1EncodableVector();
        for (int i = 0; i < vectorArray.length; ++i) {
            for (int j = 0; j < vectorArray[i].size(); ++j) {
                aSN1EncodableVector16.add(new DEROctetString((byte[])vectorArray[i].elementAt(j)));
            }
            aSN1EncodableVector17.add(new DERSequence(aSN1EncodableVector16));
            aSN1EncodableVector16 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector17));
        ASN1EncodableVector aSN1EncodableVector18 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector19 = new ASN1EncodableVector();
        for (int i = 0; i < vectorArray2.length; ++i) {
            for (int j = 0; j < vectorArray2[i].size(); ++j) {
                aSN1EncodableVector18.add(new DEROctetString((byte[])vectorArray2[i].elementAt(j)));
            }
            aSN1EncodableVector19.add(new DERSequence(aSN1EncodableVector18));
            aSN1EncodableVector18 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector19));
        ASN1EncodableVector aSN1EncodableVector20 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector21 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector22 = new ASN1EncodableVector();
        for (int i = 0; i < vectorArray3.length; ++i) {
            for (int j = 0; j < vectorArray3[i].length; ++j) {
                for (int k = 0; k < vectorArray3[i][j].size(); ++k) {
                    aSN1EncodableVector20.add(new DEROctetString((byte[])vectorArray3[i][j].elementAt(k)));
                }
                aSN1EncodableVector21.add(new DERSequence(aSN1EncodableVector20));
                aSN1EncodableVector20 = new ASN1EncodableVector();
            }
            aSN1EncodableVector22.add(new DERSequence(aSN1EncodableVector21));
            aSN1EncodableVector21 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector22));
        ASN1EncodableVector aSN1EncodableVector23 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector24 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector25 = new ASN1EncodableVector();
        for (int i = 0; i < vectorArray4.length; ++i) {
            for (n2 = 0; n2 < vectorArray4[i].length; ++n2) {
                for (int j = 0; j < vectorArray4[i][n2].size(); ++j) {
                    aSN1EncodableVector23.add(new DEROctetString((byte[])vectorArray4[i][n2].elementAt(j)));
                }
                aSN1EncodableVector24.add(new DERSequence(aSN1EncodableVector23));
                aSN1EncodableVector23 = new ASN1EncodableVector();
            }
            aSN1EncodableVector25.add(new DERSequence(aSN1EncodableVector24));
            aSN1EncodableVector24 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector25));
        ASN1EncodableVector aSN1EncodableVector26 = new ASN1EncodableVector();
        aSN1EncodableVector11 = new ASN1EncodableVector();
        aSN1EncodableVector12 = new ASN1EncodableVector();
        aSN1EncodableVector13 = new ASN1EncodableVector();
        for (n2 = 0; n2 < gMSSLeafArray.length; ++n2) {
            aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArray[0]));
            byte[][] byArray8 = gMSSLeafArray[n2].getStatByte();
            aSN1EncodableVector12.add(new DEROctetString(byArray8[0]));
            aSN1EncodableVector12.add(new DEROctetString(byArray8[1]));
            aSN1EncodableVector12.add(new DEROctetString(byArray8[2]));
            aSN1EncodableVector12.add(new DEROctetString(byArray8[3]));
            aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
            aSN1EncodableVector12 = new ASN1EncodableVector();
            object2 = gMSSLeafArray[n2].getStatInt();
            aSN1EncodableVector13.add(new ASN1Integer((long)object2[0]));
            aSN1EncodableVector13.add(new ASN1Integer((long)object2[1]));
            aSN1EncodableVector13.add(new ASN1Integer((long)object2[2]));
            aSN1EncodableVector13.add(new ASN1Integer((long)object2[3]));
            aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
            aSN1EncodableVector13 = new ASN1EncodableVector();
            aSN1EncodableVector26.add(new DERSequence(aSN1EncodableVector11));
            aSN1EncodableVector11 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector26));
        ASN1EncodableVector aSN1EncodableVector27 = new ASN1EncodableVector();
        aSN1EncodableVector11 = new ASN1EncodableVector();
        aSN1EncodableVector12 = new ASN1EncodableVector();
        aSN1EncodableVector13 = new ASN1EncodableVector();
        for (int i = 0; i < gMSSLeafArray2.length; ++i) {
            aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArray[0]));
            object2 = gMSSLeafArray2[i].getStatByte();
            aSN1EncodableVector12.add(new DEROctetString(object2[0]));
            aSN1EncodableVector12.add(new DEROctetString(object2[1]));
            aSN1EncodableVector12.add(new DEROctetString(object2[2]));
            aSN1EncodableVector12.add(new DEROctetString(object2[3]));
            aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
            aSN1EncodableVector12 = new ASN1EncodableVector();
            object = gMSSLeafArray2[i].getStatInt();
            aSN1EncodableVector13.add(new ASN1Integer((long)object[0]));
            aSN1EncodableVector13.add(new ASN1Integer((long)object[1]));
            aSN1EncodableVector13.add(new ASN1Integer((long)object[2]));
            aSN1EncodableVector13.add(new ASN1Integer((long)object[3]));
            aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
            aSN1EncodableVector13 = new ASN1EncodableVector();
            aSN1EncodableVector27.add(new DERSequence(aSN1EncodableVector11));
            aSN1EncodableVector11 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector27));
        ASN1EncodableVector aSN1EncodableVector28 = new ASN1EncodableVector();
        aSN1EncodableVector11 = new ASN1EncodableVector();
        aSN1EncodableVector12 = new ASN1EncodableVector();
        aSN1EncodableVector13 = new ASN1EncodableVector();
        for (int i = 0; i < gMSSLeafArray3.length; ++i) {
            aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArray[0]));
            object = gMSSLeafArray3[i].getStatByte();
            aSN1EncodableVector12.add(new DEROctetString(object[0]));
            aSN1EncodableVector12.add(new DEROctetString(object[1]));
            aSN1EncodableVector12.add(new DEROctetString(object[2]));
            aSN1EncodableVector12.add(new DEROctetString(object[3]));
            aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
            aSN1EncodableVector12 = new ASN1EncodableVector();
            int[] nArray3 = gMSSLeafArray3[i].getStatInt();
            aSN1EncodableVector13.add(new ASN1Integer(nArray3[0]));
            aSN1EncodableVector13.add(new ASN1Integer(nArray3[1]));
            aSN1EncodableVector13.add(new ASN1Integer(nArray3[2]));
            aSN1EncodableVector13.add(new ASN1Integer(nArray3[3]));
            aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
            aSN1EncodableVector13 = new ASN1EncodableVector();
            aSN1EncodableVector28.add(new DERSequence(aSN1EncodableVector11));
            aSN1EncodableVector11 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector28));
        ASN1EncodableVector aSN1EncodableVector29 = new ASN1EncodableVector();
        for (int i = 0; i < nArray2.length; ++i) {
            aSN1EncodableVector29.add(new ASN1Integer(nArray2[i]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector29));
        ASN1EncodableVector aSN1EncodableVector30 = new ASN1EncodableVector();
        for (int i = 0; i < byArray6.length; ++i) {
            aSN1EncodableVector30.add(new DEROctetString(byArray6[i]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector30));
        ASN1EncodableVector aSN1EncodableVector31 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector32 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector33 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector34 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector35 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector36 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector37 = new ASN1EncodableVector();
        for (int i = 0; i < gMSSRootCalcArray.length; ++i) {
            int n7;
            int n8;
            aSN1EncodableVector32.add(new DERSequence(algorithmIdentifierArray[0]));
            aSN1EncodableVector33 = new ASN1EncodableVector();
            n = gMSSRootCalcArray[i].getStatInt()[0];
            int n9 = gMSSRootCalcArray[i].getStatInt()[7];
            aSN1EncodableVector34.add(new DEROctetString(gMSSRootCalcArray[i].getStatByte()[0]));
            for (n8 = 0; n8 < n; ++n8) {
                aSN1EncodableVector34.add(new DEROctetString(gMSSRootCalcArray[i].getStatByte()[1 + n8]));
            }
            for (n8 = 0; n8 < n9; ++n8) {
                aSN1EncodableVector34.add(new DEROctetString(gMSSRootCalcArray[i].getStatByte()[1 + n + n8]));
            }
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector34));
            aSN1EncodableVector34 = new ASN1EncodableVector();
            aSN1EncodableVector35.add(new ASN1Integer(n));
            aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[1]));
            aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[2]));
            aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[3]));
            aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[4]));
            aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[5]));
            aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[6]));
            aSN1EncodableVector35.add(new ASN1Integer(n9));
            for (n8 = 0; n8 < n; ++n8) {
                aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[8 + n8]));
            }
            for (n8 = 0; n8 < n9; ++n8) {
                aSN1EncodableVector35.add(new ASN1Integer(gMSSRootCalcArray[i].getStatInt()[8 + n + n8]));
            }
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector35));
            aSN1EncodableVector35 = new ASN1EncodableVector();
            aSN1EncodableVector11 = new ASN1EncodableVector();
            aSN1EncodableVector12 = new ASN1EncodableVector();
            aSN1EncodableVector13 = new ASN1EncodableVector();
            if (gMSSRootCalcArray[i].getTreehash() != null) {
                for (n8 = 0; n8 < gMSSRootCalcArray[i].getTreehash().length; ++n8) {
                    aSN1EncodableVector11.add(new DERSequence(algorithmIdentifierArray[0]));
                    n9 = gMSSRootCalcArray[i].getTreehash()[n8].getStatInt()[1];
                    aSN1EncodableVector12.add(new DEROctetString(gMSSRootCalcArray[i].getTreehash()[n8].getStatByte()[0]));
                    aSN1EncodableVector12.add(new DEROctetString(gMSSRootCalcArray[i].getTreehash()[n8].getStatByte()[1]));
                    aSN1EncodableVector12.add(new DEROctetString(gMSSRootCalcArray[i].getTreehash()[n8].getStatByte()[2]));
                    for (n7 = 0; n7 < n9; ++n7) {
                        aSN1EncodableVector12.add(new DEROctetString(gMSSRootCalcArray[i].getTreehash()[n8].getStatByte()[3 + n7]));
                    }
                    aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector12));
                    aSN1EncodableVector12 = new ASN1EncodableVector();
                    aSN1EncodableVector13.add(new ASN1Integer(gMSSRootCalcArray[i].getTreehash()[n8].getStatInt()[0]));
                    aSN1EncodableVector13.add(new ASN1Integer(n9));
                    aSN1EncodableVector13.add(new ASN1Integer(gMSSRootCalcArray[i].getTreehash()[n8].getStatInt()[2]));
                    aSN1EncodableVector13.add(new ASN1Integer(gMSSRootCalcArray[i].getTreehash()[n8].getStatInt()[3]));
                    aSN1EncodableVector13.add(new ASN1Integer(gMSSRootCalcArray[i].getTreehash()[n8].getStatInt()[4]));
                    aSN1EncodableVector13.add(new ASN1Integer(gMSSRootCalcArray[i].getTreehash()[n8].getStatInt()[5]));
                    for (n7 = 0; n7 < n9; ++n7) {
                        aSN1EncodableVector13.add(new ASN1Integer(gMSSRootCalcArray[i].getTreehash()[n8].getStatInt()[6 + n7]));
                    }
                    aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector13));
                    aSN1EncodableVector13 = new ASN1EncodableVector();
                    aSN1EncodableVector36.add(new DERSequence(aSN1EncodableVector11));
                    aSN1EncodableVector11 = new ASN1EncodableVector();
                }
            }
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector36));
            aSN1EncodableVector36 = new ASN1EncodableVector();
            aSN1EncodableVector20 = new ASN1EncodableVector();
            if (gMSSRootCalcArray[i].getRetain() != null) {
                for (n8 = 0; n8 < gMSSRootCalcArray[i].getRetain().length; ++n8) {
                    for (n7 = 0; n7 < gMSSRootCalcArray[i].getRetain()[n8].size(); ++n7) {
                        aSN1EncodableVector20.add(new DEROctetString((byte[])gMSSRootCalcArray[i].getRetain()[n8].elementAt(n7)));
                    }
                    aSN1EncodableVector37.add(new DERSequence(aSN1EncodableVector20));
                    aSN1EncodableVector20 = new ASN1EncodableVector();
                }
            }
            aSN1EncodableVector32.add(new DERSequence(aSN1EncodableVector37));
            aSN1EncodableVector37 = new ASN1EncodableVector();
            aSN1EncodableVector31.add(new DERSequence(aSN1EncodableVector32));
            aSN1EncodableVector32 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector31));
        ASN1EncodableVector aSN1EncodableVector38 = new ASN1EncodableVector();
        for (n = 0; n < byArray7.length; ++n) {
            aSN1EncodableVector38.add(new DEROctetString(byArray7[n]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector38));
        ASN1EncodableVector aSN1EncodableVector39 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector40 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector41 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector42 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector43 = new ASN1EncodableVector();
        for (int i = 0; i < gMSSRootSigArray.length; ++i) {
            aSN1EncodableVector40.add(new DERSequence(algorithmIdentifierArray[0]));
            aSN1EncodableVector41 = new ASN1EncodableVector();
            aSN1EncodableVector42.add(new DEROctetString(gMSSRootSigArray[i].getStatByte()[0]));
            aSN1EncodableVector42.add(new DEROctetString(gMSSRootSigArray[i].getStatByte()[1]));
            aSN1EncodableVector42.add(new DEROctetString(gMSSRootSigArray[i].getStatByte()[2]));
            aSN1EncodableVector42.add(new DEROctetString(gMSSRootSigArray[i].getStatByte()[3]));
            aSN1EncodableVector42.add(new DEROctetString(gMSSRootSigArray[i].getStatByte()[4]));
            aSN1EncodableVector40.add(new DERSequence(aSN1EncodableVector42));
            aSN1EncodableVector42 = new ASN1EncodableVector();
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[0]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[1]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[2]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[3]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[4]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[5]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[6]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[7]));
            aSN1EncodableVector43.add(new ASN1Integer(gMSSRootSigArray[i].getStatInt()[8]));
            aSN1EncodableVector40.add(new DERSequence(aSN1EncodableVector43));
            aSN1EncodableVector43 = new ASN1EncodableVector();
            aSN1EncodableVector39.add(new DERSequence(aSN1EncodableVector40));
            aSN1EncodableVector40 = new ASN1EncodableVector();
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector39));
        ASN1EncodableVector aSN1EncodableVector44 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector45 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector46 = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector47 = new ASN1EncodableVector();
        for (int i = 0; i < gMSSParameters.getHeightOfTrees().length; ++i) {
            aSN1EncodableVector45.add(new ASN1Integer(gMSSParameters.getHeightOfTrees()[i]));
            aSN1EncodableVector46.add(new ASN1Integer(gMSSParameters.getWinternitzParameter()[i]));
            aSN1EncodableVector47.add(new ASN1Integer(gMSSParameters.getK()[i]));
        }
        aSN1EncodableVector44.add(new ASN1Integer(gMSSParameters.getNumOfLayers()));
        aSN1EncodableVector44.add(new DERSequence(aSN1EncodableVector45));
        aSN1EncodableVector44.add(new DERSequence(aSN1EncodableVector46));
        aSN1EncodableVector44.add(new DERSequence(aSN1EncodableVector47));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector44));
        ASN1EncodableVector aSN1EncodableVector48 = new ASN1EncodableVector();
        for (int i = 0; i < algorithmIdentifierArray.length; ++i) {
            aSN1EncodableVector48.add(algorithmIdentifierArray[i]);
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector48));
        return new DERSequence(aSN1EncodableVector);
    }

    private static int checkBigIntegerInIntRange(ASN1Encodable aSN1Encodable) {
        BigInteger bigInteger = ((ASN1Integer)aSN1Encodable).getValue();
        if (bigInteger.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 || bigInteger.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            throw new IllegalArgumentException("BigInteger not in Range: " + bigInteger.toString());
        }
        return bigInteger.intValue();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.primitive;
    }
}

