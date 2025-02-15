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
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;

public class PolicyConstraints
extends ASN1Object {
    private BigInteger requireExplicitPolicyMapping;
    private BigInteger inhibitPolicyMapping;

    public PolicyConstraints(BigInteger bigInteger, BigInteger bigInteger2) {
        this.requireExplicitPolicyMapping = bigInteger;
        this.inhibitPolicyMapping = bigInteger2;
    }

    private PolicyConstraints(ASN1Sequence aSN1Sequence) {
        for (int i = 0; i != aSN1Sequence.size(); ++i) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.requireExplicitPolicyMapping = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue();
                continue;
            }
            if (aSN1TaggedObject.getTagNo() == 1) {
                this.inhibitPolicyMapping = ASN1Integer.getInstance(aSN1TaggedObject, false).getValue();
                continue;
            }
            throw new IllegalArgumentException("Unknown tag encountered.");
        }
    }

    public static PolicyConstraints getInstance(Object object) {
        if (object instanceof PolicyConstraints) {
            return (PolicyConstraints)object;
        }
        if (object != null) {
            return new PolicyConstraints(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static PolicyConstraints fromExtensions(Extensions extensions) {
        return PolicyConstraints.getInstance(extensions.getExtensionParsedValue(Extension.policyConstraints));
    }

    public BigInteger getRequireExplicitPolicyMapping() {
        return this.requireExplicitPolicyMapping;
    }

    public BigInteger getInhibitPolicyMapping() {
        return this.inhibitPolicyMapping;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.requireExplicitPolicyMapping != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, new ASN1Integer(this.requireExplicitPolicyMapping)));
        }
        if (this.inhibitPolicyMapping != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, new ASN1Integer(this.inhibitPolicyMapping)));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

