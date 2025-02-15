/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.esf.OtherHashAlgAndValue;
import org.bouncycastle.asn1.esf.SigPolicyQualifiers;

public class SignaturePolicyId
extends ASN1Object {
    private ASN1ObjectIdentifier sigPolicyId;
    private OtherHashAlgAndValue sigPolicyHash;
    private SigPolicyQualifiers sigPolicyQualifiers;

    public static SignaturePolicyId getInstance(Object object) {
        if (object instanceof SignaturePolicyId) {
            return (SignaturePolicyId)object;
        }
        if (object != null) {
            return new SignaturePolicyId(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private SignaturePolicyId(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 2 && aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.sigPolicyId = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(0));
        this.sigPolicyHash = OtherHashAlgAndValue.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 3) {
            this.sigPolicyQualifiers = SigPolicyQualifiers.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public SignaturePolicyId(ASN1ObjectIdentifier aSN1ObjectIdentifier, OtherHashAlgAndValue otherHashAlgAndValue) {
        this(aSN1ObjectIdentifier, otherHashAlgAndValue, null);
    }

    public SignaturePolicyId(ASN1ObjectIdentifier aSN1ObjectIdentifier, OtherHashAlgAndValue otherHashAlgAndValue, SigPolicyQualifiers sigPolicyQualifiers) {
        this.sigPolicyId = aSN1ObjectIdentifier;
        this.sigPolicyHash = otherHashAlgAndValue;
        this.sigPolicyQualifiers = sigPolicyQualifiers;
    }

    public ASN1ObjectIdentifier getSigPolicyId() {
        return new ASN1ObjectIdentifier(this.sigPolicyId.getId());
    }

    public OtherHashAlgAndValue getSigPolicyHash() {
        return this.sigPolicyHash;
    }

    public SigPolicyQualifiers getSigPolicyQualifiers() {
        return this.sigPolicyQualifiers;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.sigPolicyId);
        aSN1EncodableVector.add(this.sigPolicyHash);
        if (this.sigPolicyQualifiers != null) {
            aSN1EncodableVector.add(this.sigPolicyQualifiers);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

