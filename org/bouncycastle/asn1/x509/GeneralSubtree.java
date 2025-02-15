/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.GeneralName;

public class GeneralSubtree
extends ASN1Object {
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private GeneralName base;
    private ASN1Integer minimum;
    private ASN1Integer maximum;

    private GeneralSubtree(ASN1Sequence aSN1Sequence) {
        this.base = GeneralName.getInstance(aSN1Sequence.getObjectAt(0));
        block0 : switch (aSN1Sequence.size()) {
            case 1: {
                break;
            }
            case 2: {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                switch (aSN1TaggedObject.getTagNo()) {
                    case 0: {
                        this.minimum = ASN1Integer.getInstance(aSN1TaggedObject, false);
                        break block0;
                    }
                    case 1: {
                        this.maximum = ASN1Integer.getInstance(aSN1TaggedObject, false);
                        break block0;
                    }
                }
                throw new IllegalArgumentException("Bad tag number: " + aSN1TaggedObject.getTagNo());
            }
            case 3: {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(1));
                if (aSN1TaggedObject.getTagNo() != 0) {
                    throw new IllegalArgumentException("Bad tag number for 'minimum': " + aSN1TaggedObject.getTagNo());
                }
                this.minimum = ASN1Integer.getInstance(aSN1TaggedObject, false);
                aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(2));
                if (aSN1TaggedObject.getTagNo() != 1) {
                    throw new IllegalArgumentException("Bad tag number for 'maximum': " + aSN1TaggedObject.getTagNo());
                }
                this.maximum = ASN1Integer.getInstance(aSN1TaggedObject, false);
                break;
            }
            default: {
                throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
            }
        }
    }

    public GeneralSubtree(GeneralName generalName, BigInteger bigInteger, BigInteger bigInteger2) {
        this.base = generalName;
        if (bigInteger2 != null) {
            this.maximum = new ASN1Integer(bigInteger2);
        }
        this.minimum = bigInteger == null ? null : new ASN1Integer(bigInteger);
    }

    public GeneralSubtree(GeneralName generalName) {
        this(generalName, null, null);
    }

    public static GeneralSubtree getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return new GeneralSubtree(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static GeneralSubtree getInstance(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof GeneralSubtree) {
            return (GeneralSubtree)object;
        }
        return new GeneralSubtree(ASN1Sequence.getInstance(object));
    }

    public GeneralName getBase() {
        return this.base;
    }

    public BigInteger getMinimum() {
        if (this.minimum == null) {
            return ZERO;
        }
        return this.minimum.getValue();
    }

    public BigInteger getMaximum() {
        if (this.maximum == null) {
            return null;
        }
        return this.maximum.getValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.base);
        if (this.minimum != null && !this.minimum.getValue().equals(ZERO)) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.minimum));
        }
        if (this.maximum != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.maximum));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

