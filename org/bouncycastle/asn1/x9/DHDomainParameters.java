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
import org.bouncycastle.asn1.x9.DHValidationParms;

public class DHDomainParameters
extends ASN1Object {
    private ASN1Integer p;
    private ASN1Integer g;
    private ASN1Integer q;
    private ASN1Integer j;
    private DHValidationParms validationParms;

    public static DHDomainParameters getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return DHDomainParameters.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static DHDomainParameters getInstance(Object object) {
        if (object == null || object instanceof DHDomainParameters) {
            return (DHDomainParameters)object;
        }
        if (object instanceof ASN1Sequence) {
            return new DHDomainParameters((ASN1Sequence)object);
        }
        throw new IllegalArgumentException("Invalid DHDomainParameters: " + object.getClass().getName());
    }

    public DHDomainParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, DHValidationParms dHValidationParms) {
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
        this.j = new ASN1Integer(bigInteger4);
        this.validationParms = dHValidationParms;
    }

    public DHDomainParameters(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2, ASN1Integer aSN1Integer3, ASN1Integer aSN1Integer4, DHValidationParms dHValidationParms) {
        if (aSN1Integer == null) {
            throw new IllegalArgumentException("'p' cannot be null");
        }
        if (aSN1Integer2 == null) {
            throw new IllegalArgumentException("'g' cannot be null");
        }
        if (aSN1Integer3 == null) {
            throw new IllegalArgumentException("'q' cannot be null");
        }
        this.p = aSN1Integer;
        this.g = aSN1Integer2;
        this.q = aSN1Integer3;
        this.j = aSN1Integer4;
        this.validationParms = dHValidationParms;
    }

    private DHDomainParameters(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() < 3 || aSN1Sequence.size() > 5) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.p = ASN1Integer.getInstance(enumeration.nextElement());
        this.g = ASN1Integer.getInstance(enumeration.nextElement());
        this.q = ASN1Integer.getInstance(enumeration.nextElement());
        ASN1Encodable aSN1Encodable = DHDomainParameters.getNext(enumeration);
        if (aSN1Encodable != null && aSN1Encodable instanceof ASN1Integer) {
            this.j = ASN1Integer.getInstance(aSN1Encodable);
            aSN1Encodable = DHDomainParameters.getNext(enumeration);
        }
        if (aSN1Encodable != null) {
            this.validationParms = DHValidationParms.getInstance(aSN1Encodable.toASN1Primitive());
        }
    }

    private static ASN1Encodable getNext(Enumeration enumeration) {
        return enumeration.hasMoreElements() ? (ASN1Encodable)enumeration.nextElement() : null;
    }

    public ASN1Integer getP() {
        return this.p;
    }

    public ASN1Integer getG() {
        return this.g;
    }

    public ASN1Integer getQ() {
        return this.q;
    }

    public ASN1Integer getJ() {
        return this.j;
    }

    public DHValidationParms getValidationParms() {
        return this.validationParms;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.p);
        aSN1EncodableVector.add(this.g);
        aSN1EncodableVector.add(this.q);
        if (this.j != null) {
            aSN1EncodableVector.add(this.j);
        }
        if (this.validationParms != null) {
            aSN1EncodableVector.add(this.validationParms);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

