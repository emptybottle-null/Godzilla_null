/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x9;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x9.ValidationParams;

public class DomainParameters
extends ASN1Object {
    private final ASN1Integer p;
    private final ASN1Integer g;
    private final ASN1Integer q;
    private final ASN1Integer j;
    private final ValidationParams validationParams;

    public static DomainParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DomainParameters.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static DomainParameters getInstance(Object object) {
        if (object instanceof DomainParameters) {
            return (DomainParameters)object;
        }
        if (object != null) {
            return new DomainParameters(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public DomainParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, ValidationParams validationParams) {
        if (bigInteger == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (bigInteger2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (bigInteger3 == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        }
        this.p = new ASN1Integer(bigInteger);
        this.g = new ASN1Integer(bigInteger2);
        this.q = new ASN1Integer(bigInteger3);
        this.j = bigInteger4 != null ? new ASN1Integer(bigInteger4) : null;
        this.validationParams = validationParams;
    }

    private DomainParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.p = ASN1Integer.getInstance(enumeration.nextElement());
        this.g = ASN1Integer.getInstance(enumeration.nextElement());
        this.q = ASN1Integer.getInstance(enumeration.nextElement());
        ASN1Encodable aSN1Encodable = DomainParameters.getNext(enumeration);
        if (aSN1Encodable != null && aSN1Encodable instanceof ASN1Integer) {
            this.j = ASN1Integer.getInstance(aSN1Encodable);
            aSN1Encodable = DomainParameters.getNext(enumeration);
        } else {
            this.j = null;
        }
        this.validationParams = aSN1Encodable != null ? ValidationParams.getInstance(aSN1Encodable.toASN1Primitive()) : null;
    }

    private static ASN1Encodable getNext(Enumeration enumeration) {
        return enumeration.hasMoreElements() ? (ASN1Encodable)enumeration.nextElement() : null;
    }

    public BigInteger getP() {
        return this.p.getPositiveValue();
    }

    public BigInteger getG() {
        return this.g.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.q.getPositiveValue();
    }

    public BigInteger getJ() {
        if (this.j == null) {
            return null;
        }
        return this.j.getPositiveValue();
    }

    public ValidationParams getValidationParams() {
        return this.validationParams;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.p);
        aSN1EncodableVector.add(this.g);
        aSN1EncodableVector.add(this.q);
        if (this.j != null) {
            aSN1EncodableVector.add(this.j);
        }
        if (this.validationParams != null) {
            aSN1EncodableVector.add(this.validationParams);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

