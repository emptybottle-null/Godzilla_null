/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;

public class CRLNumber
extends ASN1Object {
    private BigInteger number;

    public CRLNumber(BigInteger bigInteger) {
        this.number = bigInteger;
    }

    public BigInteger getCRLNumber() {
        return this.number;
    }

    public String toString() {
        return "CRLNumber: " + this.getCRLNumber();
    }

    public ASN1Primitive toASN1Primitive() {
        return new ASN1Integer(this.number);
    }

    public static CRLNumber getInstance(Object object) {
        if (object instanceof CRLNumber) {
            return (CRLNumber)object;
        }
        if (object != null) {
            return new CRLNumber(ASN1Integer.getInstance(object).getValue());
        }
        return null;
    }
}

