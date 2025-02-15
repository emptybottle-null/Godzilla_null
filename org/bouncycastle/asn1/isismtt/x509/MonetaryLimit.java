/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.DERSequence;

public class MonetaryLimit
extends ASN1Object {
    DERPrintableString currency;
    ASN1Integer amount;
    ASN1Integer exponent;

    public static MonetaryLimit getInstance(Object object) {
        if (object == null || object instanceof MonetaryLimit) {
            return (MonetaryLimit)object;
        }
        if (object instanceof ASN1Sequence) {
            return new MonetaryLimit(ASN1Sequence.getInstance(object));
        }
        throw new IllegalArgumentException("unknown object in getInstance");
    }

    private MonetaryLimit(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.currency = DERPrintableString.getInstance(enumeration.nextElement());
        this.amount = ASN1Integer.getInstance(enumeration.nextElement());
        this.exponent = ASN1Integer.getInstance(enumeration.nextElement());
    }

    public MonetaryLimit(String string, int n, int n2) {
        this.currency = new DERPrintableString(string, true);
        this.amount = new ASN1Integer(n);
        this.exponent = new ASN1Integer(n2);
    }

    public String getCurrency() {
        return this.currency.getString();
    }

    public BigInteger getAmount() {
        return this.amount.getValue();
    }

    public BigInteger getExponent() {
        return this.exponent.getValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.currency);
        aSN1EncodableVector.add(this.amount);
        aSN1EncodableVector.add(this.exponent);
        return new DERSequence(aSN1EncodableVector);
    }
}

