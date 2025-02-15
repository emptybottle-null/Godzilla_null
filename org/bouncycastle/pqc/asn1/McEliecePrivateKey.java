/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.pqc.asn1;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;

public class McEliecePrivateKey
extends ASN1Object {
    private int n;
    private int k;
    private byte[] encField;
    private byte[] encGp;
    private byte[] encSInv;
    private byte[] encP1;
    private byte[] encP2;

    public McEliecePrivateKey(int n, int n2, GF2mField gF2mField, PolynomialGF2mSmallM polynomialGF2mSmallM, Permutation permutation, Permutation permutation2, GF2Matrix gF2Matrix) {
        this.n = n;
        this.k = n2;
        this.encField = gF2mField.getEncoded();
        this.encGp = polynomialGF2mSmallM.getEncoded();
        this.encSInv = gF2Matrix.getEncoded();
        this.encP1 = permutation.getEncoded();
        this.encP2 = permutation2.getEncoded();
    }

    public static McEliecePrivateKey getInstance(Object object) {
        if (object instanceof McEliecePrivateKey) {
            return (McEliecePrivateKey)object;
        }
        if (object != null) {
            return new McEliecePrivateKey(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private McEliecePrivateKey(ASN1Sequence aSN1Sequence) {
        BigInteger bigInteger = ((ASN1Integer)aSN1Sequence.getObjectAt(0)).getValue();
        this.n = bigInteger.intValue();
        BigInteger bigInteger2 = ((ASN1Integer)aSN1Sequence.getObjectAt(1)).getValue();
        this.k = bigInteger2.intValue();
        this.encField = ((ASN1OctetString)aSN1Sequence.getObjectAt(2)).getOctets();
        this.encGp = ((ASN1OctetString)aSN1Sequence.getObjectAt(3)).getOctets();
        this.encP1 = ((ASN1OctetString)aSN1Sequence.getObjectAt(4)).getOctets();
        this.encP2 = ((ASN1OctetString)aSN1Sequence.getObjectAt(5)).getOctets();
        this.encSInv = ((ASN1OctetString)aSN1Sequence.getObjectAt(6)).getOctets();
    }

    public int getN() {
        return this.n;
    }

    public int getK() {
        return this.k;
    }

    public GF2mField getField() {
        return new GF2mField(this.encField);
    }

    public PolynomialGF2mSmallM getGoppaPoly() {
        return new PolynomialGF2mSmallM(this.getField(), this.encGp);
    }

    public GF2Matrix getSInv() {
        return new GF2Matrix(this.encSInv);
    }

    public Permutation getP1() {
        return new Permutation(this.encP1);
    }

    public Permutation getP2() {
        return new Permutation(this.encP2);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.n));
        aSN1EncodableVector.add(new ASN1Integer(this.k));
        aSN1EncodableVector.add(new DEROctetString(this.encField));
        aSN1EncodableVector.add(new DEROctetString(this.encGp));
        aSN1EncodableVector.add(new DEROctetString(this.encP1));
        aSN1EncodableVector.add(new DEROctetString(this.encP2));
        aSN1EncodableVector.add(new DEROctetString(this.encSInv));
        return new DERSequence(aSN1EncodableVector);
    }
}

