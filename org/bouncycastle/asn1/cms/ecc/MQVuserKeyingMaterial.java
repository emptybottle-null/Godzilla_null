/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms.ecc;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.cms.OriginatorPublicKey;

public class MQVuserKeyingMaterial
extends ASN1Object {
    private OriginatorPublicKey ephemeralPublicKey;
    private ASN1OctetString addedukm;

    public MQVuserKeyingMaterial(OriginatorPublicKey originatorPublicKey, ASN1OctetString aSN1OctetString) {
        if (originatorPublicKey == null) {
            throw new IllegalArgumentException("Ephemeral public key cannot be null");
        }
        this.ephemeralPublicKey = originatorPublicKey;
        this.addedukm = aSN1OctetString;
    }

    private MQVuserKeyingMaterial(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 1 && aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Sequence has incorrect number of elements");
        }
        this.ephemeralPublicKey = OriginatorPublicKey.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() > 1) {
            this.addedukm = ASN1OctetString.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(1), true);
        }
    }

    public static MQVuserKeyingMaterial getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return MQVuserKeyingMaterial.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static MQVuserKeyingMaterial getInstance(Object object) {
        if (object instanceof MQVuserKeyingMaterial) {
            return (MQVuserKeyingMaterial)object;
        }
        if (object != null) {
            return new MQVuserKeyingMaterial(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public OriginatorPublicKey getEphemeralPublicKey() {
        return this.ephemeralPublicKey;
    }

    public ASN1OctetString getAddedukm() {
        return this.addedukm;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.ephemeralPublicKey);
        if (this.addedukm != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.addedukm));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

