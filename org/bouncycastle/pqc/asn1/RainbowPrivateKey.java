/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.asn1;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.pqc.crypto.rainbow.Layer;
import org.bouncycastle.pqc.crypto.rainbow.util.RainbowUtil;

public class RainbowPrivateKey
extends ASN1Object {
    private ASN1Integer version;
    private ASN1ObjectIdentifier oid;
    private byte[][] invA1;
    private byte[] b1;
    private byte[][] invA2;
    private byte[] b2;
    private byte[] vi;
    private Layer[] layers;

    private RainbowPrivateKey(ASN1Sequence aSN1Sequence) {
        Object object;
        int n;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1Integer) {
            this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        } else {
            this.oid = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        }
        ASN1Sequence aSN1Sequence2 = (ASN1Sequence)aSN1Sequence.getObjectAt(1);
        this.invA1 = new byte[aSN1Sequence2.size()][];
        for (int i = 0; i < aSN1Sequence2.size(); ++i) {
            this.invA1[i] = ((ASN1OctetString)aSN1Sequence2.getObjectAt(i)).getOctets();
        }
        ASN1Sequence aSN1Sequence3 = (ASN1Sequence)aSN1Sequence.getObjectAt(2);
        this.b1 = ((ASN1OctetString)aSN1Sequence3.getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence4 = (ASN1Sequence)aSN1Sequence.getObjectAt(3);
        this.invA2 = new byte[aSN1Sequence4.size()][];
        for (int i = 0; i < aSN1Sequence4.size(); ++i) {
            this.invA2[i] = ((ASN1OctetString)aSN1Sequence4.getObjectAt(i)).getOctets();
        }
        ASN1Sequence aSN1Sequence5 = (ASN1Sequence)aSN1Sequence.getObjectAt(4);
        this.b2 = ((ASN1OctetString)aSN1Sequence5.getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence6 = (ASN1Sequence)aSN1Sequence.getObjectAt(5);
        this.vi = ((ASN1OctetString)aSN1Sequence6.getObjectAt(0)).getOctets();
        ASN1Sequence aSN1Sequence7 = (ASN1Sequence)aSN1Sequence.getObjectAt(6);
        byte[][][][] byArrayArray = new byte[aSN1Sequence7.size()][][][];
        byte[][][][] byArrayArray2 = new byte[aSN1Sequence7.size()][][][];
        byte[][][] byArrayArray3 = new byte[aSN1Sequence7.size()][][];
        byte[][] byArrayArray4 = new byte[aSN1Sequence7.size()][];
        for (n = 0; n < aSN1Sequence7.size(); ++n) {
            int n2;
            ASN1Sequence aSN1Sequence8;
            ASN1Sequence aSN1Sequence9 = (ASN1Sequence)aSN1Sequence7.getObjectAt(n);
            object = (ASN1Sequence)aSN1Sequence9.getObjectAt(0);
            byArrayArray[n] = new byte[((ASN1Sequence)object).size()][][];
            for (int i = 0; i < ((ASN1Sequence)object).size(); ++i) {
                aSN1Sequence8 = (ASN1Sequence)((ASN1Sequence)object).getObjectAt(i);
                byArrayArray[n][i] = new byte[aSN1Sequence8.size()][];
                for (n2 = 0; n2 < aSN1Sequence8.size(); ++n2) {
                    byArrayArray[n][i][n2] = ((ASN1OctetString)aSN1Sequence8.getObjectAt(n2)).getOctets();
                }
            }
            ASN1Sequence aSN1Sequence10 = (ASN1Sequence)aSN1Sequence9.getObjectAt(1);
            byArrayArray2[n] = new byte[aSN1Sequence10.size()][][];
            for (int i = 0; i < aSN1Sequence10.size(); ++i) {
                ASN1Sequence aSN1Sequence11 = (ASN1Sequence)aSN1Sequence10.getObjectAt(i);
                byArrayArray2[n][i] = new byte[aSN1Sequence11.size()][];
                for (int j = 0; j < aSN1Sequence11.size(); ++j) {
                    byArrayArray2[n][i][j] = ((ASN1OctetString)aSN1Sequence11.getObjectAt(j)).getOctets();
                }
            }
            aSN1Sequence8 = (ASN1Sequence)aSN1Sequence9.getObjectAt(2);
            byArrayArray3[n] = new byte[aSN1Sequence8.size()][];
            for (n2 = 0; n2 < aSN1Sequence8.size(); ++n2) {
                byArrayArray3[n][n2] = ((ASN1OctetString)aSN1Sequence8.getObjectAt(n2)).getOctets();
            }
            byArrayArray4[n] = ((ASN1OctetString)aSN1Sequence9.getObjectAt(3)).getOctets();
        }
        n = this.vi.length - 1;
        this.layers = new Layer[n];
        for (int i = 0; i < n; ++i) {
            this.layers[i] = object = new Layer(this.vi[i], this.vi[i + 1], RainbowUtil.convertArray(byArrayArray[i]), RainbowUtil.convertArray(byArrayArray2[i]), RainbowUtil.convertArray(byArrayArray3[i]), RainbowUtil.convertArray(byArrayArray4[i]));
        }
    }

    public RainbowPrivateKey(short[][] sArray, short[] sArray2, short[][] sArray3, short[] sArray4, int[] nArray, Layer[] layerArray) {
        this.version = new ASN1Integer(1L);
        this.invA1 = RainbowUtil.convertArray(sArray);
        this.b1 = RainbowUtil.convertArray(sArray2);
        this.invA2 = RainbowUtil.convertArray(sArray3);
        this.b2 = RainbowUtil.convertArray(sArray4);
        this.vi = RainbowUtil.convertIntArray(nArray);
        this.layers = layerArray;
    }

    public static RainbowPrivateKey getInstance(Object object) {
        if (object instanceof RainbowPrivateKey) {
            return (RainbowPrivateKey)object;
        }
        if (object != null) {
            return new RainbowPrivateKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public short[][] getInvA1() {
        return RainbowUtil.convertArray(this.invA1);
    }

    public short[] getB1() {
        return RainbowUtil.convertArray(this.b1);
    }

    public short[] getB2() {
        return RainbowUtil.convertArray(this.b2);
    }

    public short[][] getInvA2() {
        return RainbowUtil.convertArray(this.invA2);
    }

    public Layer[] getLayers() {
        return this.layers;
    }

    public int[] getVi() {
        return RainbowUtil.convertArraytoInt(this.vi);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.version != null) {
            aSN1EncodableVector.add(this.version);
        } else {
            aSN1EncodableVector.add(this.oid);
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int i = 0; i < this.invA1.length; ++i) {
            aSN1EncodableVector2.add(new DEROctetString(this.invA1[i]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        ASN1EncodableVector aSN1EncodableVector3 = new ASN1EncodableVector();
        aSN1EncodableVector3.add(new DEROctetString(this.b1));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector3));
        ASN1EncodableVector aSN1EncodableVector4 = new ASN1EncodableVector();
        for (int i = 0; i < this.invA2.length; ++i) {
            aSN1EncodableVector4.add(new DEROctetString(this.invA2[i]));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector4));
        ASN1EncodableVector aSN1EncodableVector5 = new ASN1EncodableVector();
        aSN1EncodableVector5.add(new DEROctetString(this.b2));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector5));
        ASN1EncodableVector aSN1EncodableVector6 = new ASN1EncodableVector();
        aSN1EncodableVector6.add(new DEROctetString(this.vi));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector6));
        ASN1EncodableVector aSN1EncodableVector7 = new ASN1EncodableVector();
        for (int i = 0; i < this.layers.length; ++i) {
            int n;
            ASN1EncodableVector aSN1EncodableVector8;
            int n2;
            ASN1EncodableVector aSN1EncodableVector9;
            ASN1EncodableVector aSN1EncodableVector10 = new ASN1EncodableVector();
            byte[][][] byArray = RainbowUtil.convertArray(this.layers[i].getCoeffAlpha());
            ASN1EncodableVector aSN1EncodableVector11 = new ASN1EncodableVector();
            for (int j = 0; j < byArray.length; ++j) {
                aSN1EncodableVector9 = new ASN1EncodableVector();
                for (n2 = 0; n2 < byArray[j].length; ++n2) {
                    aSN1EncodableVector9.add(new DEROctetString(byArray[j][n2]));
                }
                aSN1EncodableVector11.add(new DERSequence(aSN1EncodableVector9));
            }
            aSN1EncodableVector10.add(new DERSequence(aSN1EncodableVector11));
            byte[][][] byArray2 = RainbowUtil.convertArray(this.layers[i].getCoeffBeta());
            aSN1EncodableVector9 = new ASN1EncodableVector();
            for (n2 = 0; n2 < byArray2.length; ++n2) {
                aSN1EncodableVector8 = new ASN1EncodableVector();
                for (n = 0; n < byArray2[n2].length; ++n) {
                    aSN1EncodableVector8.add(new DEROctetString(byArray2[n2][n]));
                }
                aSN1EncodableVector9.add(new DERSequence(aSN1EncodableVector8));
            }
            aSN1EncodableVector10.add(new DERSequence(aSN1EncodableVector9));
            byte[][] byArray3 = RainbowUtil.convertArray(this.layers[i].getCoeffGamma());
            aSN1EncodableVector8 = new ASN1EncodableVector();
            for (n = 0; n < byArray3.length; ++n) {
                aSN1EncodableVector8.add(new DEROctetString(byArray3[n]));
            }
            aSN1EncodableVector10.add(new DERSequence(aSN1EncodableVector8));
            aSN1EncodableVector10.add(new DEROctetString(RainbowUtil.convertArray(this.layers[i].getCoeffEta())));
            aSN1EncodableVector7.add(new DERSequence(aSN1EncodableVector10));
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector7));
        return new DERSequence(aSN1EncodableVector);
    }
}

