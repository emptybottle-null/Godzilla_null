/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

public class OriginatorInfo
extends ASN1Object {
    private ASN1Set certs;
    private ASN1Set crls;

    public OriginatorInfo(ASN1Set aSN1Set, ASN1Set aSN1Set2) {
        this.certs = aSN1Set;
        this.crls = aSN1Set2;
    }

    private OriginatorInfo(ASN1Sequence aSN1Sequence) {
        block0 : switch (aSN1Sequence.size()) {
            case 0: {
                break;
            }
            case 1: {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Sequence.getObjectAt(0);
                switch (aSN1TaggedObject.getTagNo()) {
                    case 0: {
                        this.certs = ASN1Set.getInstance(aSN1TaggedObject, false);
                        break block0;
                    }
                    case 1: {
                        this.crls = ASN1Set.getInstance(aSN1TaggedObject, false);
                        break block0;
                    }
                }
                throw new IllegalArgumentException("Bad tag in OriginatorInfo: " + aSN1TaggedObject.getTagNo());
            }
            case 2: {
                this.certs = ASN1Set.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(0), false);
                this.crls = ASN1Set.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(1), false);
                break;
            }
            default: {
                throw new IllegalArgumentException("OriginatorInfo too big");
            }
        }
    }

    public static OriginatorInfo getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return OriginatorInfo.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static OriginatorInfo getInstance(Object object) {
        if (object instanceof OriginatorInfo) {
            return (OriginatorInfo)object;
        }
        if (object != null) {
            return new OriginatorInfo(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Set getCertificates() {
        return this.certs;
    }

    public ASN1Set getCRLs() {
        return this.crls;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.certs != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, this.certs));
        }
        if (this.crls != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, this.crls));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

