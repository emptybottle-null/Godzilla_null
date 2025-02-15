/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.dvcs;

import java.util.Arrays;
import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.PolicyInformation;

public class PathProcInput
extends ASN1Object {
    private PolicyInformation[] acceptablePolicySet;
    private boolean inhibitPolicyMapping = false;
    private boolean explicitPolicyReqd = false;
    private boolean inhibitAnyPolicy = false;

    public PathProcInput(PolicyInformation[] policyInformationArray) {
        this.acceptablePolicySet = policyInformationArray;
    }

    public PathProcInput(PolicyInformation[] policyInformationArray, boolean bl, boolean bl2, boolean bl3) {
        this.acceptablePolicySet = policyInformationArray;
        this.inhibitPolicyMapping = bl;
        this.explicitPolicyReqd = bl2;
        this.inhibitAnyPolicy = bl3;
    }

    private static PolicyInformation[] fromSequence(ASN1Sequence aSN1Sequence) {
        PolicyInformation[] policyInformationArray = new PolicyInformation[aSN1Sequence.size()];
        for (int i = 0; i != policyInformationArray.length; ++i) {
            policyInformationArray[i] = PolicyInformation.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return policyInformationArray;
    }

    public static PathProcInput getInstance(Object object) {
        if (object instanceof PathProcInput) {
            return (PathProcInput)object;
        }
        if (object != null) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(object);
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(aSN1Sequence.getObjectAt(0));
            PathProcInput pathProcInput = new PathProcInput(PathProcInput.fromSequence(aSN1Sequence2));
            block4: for (int i = 1; i < aSN1Sequence.size(); ++i) {
                ASN1Primitive aSN1Primitive;
                ASN1Encodable aSN1Encodable = aSN1Sequence.getObjectAt(i);
                if (aSN1Encodable instanceof ASN1Boolean) {
                    aSN1Primitive = ASN1Boolean.getInstance(aSN1Encodable);
                    pathProcInput.setInhibitPolicyMapping(((ASN1Boolean)aSN1Primitive).isTrue());
                    continue;
                }
                if (!(aSN1Encodable instanceof ASN1TaggedObject)) continue;
                aSN1Primitive = ASN1TaggedObject.getInstance(aSN1Encodable);
                switch (((ASN1TaggedObject)aSN1Primitive).getTagNo()) {
                    case 0: {
                        ASN1Boolean aSN1Boolean = ASN1Boolean.getInstance((ASN1TaggedObject)aSN1Primitive, false);
                        pathProcInput.setExplicitPolicyReqd(aSN1Boolean.isTrue());
                        continue block4;
                    }
                    case 1: {
                        ASN1Boolean aSN1Boolean = ASN1Boolean.getInstance((ASN1TaggedObject)aSN1Primitive, false);
                        pathProcInput.setInhibitAnyPolicy(aSN1Boolean.isTrue());
                        continue block4;
                    }
                    default: {
                        throw new IllegalArgumentException("Unknown tag encountered: " + ((ASN1TaggedObject)aSN1Primitive).getTagNo());
                    }
                }
            }
            return pathProcInput;
        }
        return null;
    }

    public static PathProcInput getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return PathProcInput.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int i = 0; i != this.acceptablePolicySet.length; ++i) {
            aSN1EncodableVector2.add(this.acceptablePolicySet[i]);
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        if (this.inhibitPolicyMapping) {
            aSN1EncodableVector.add(ASN1Boolean.getInstance(this.inhibitPolicyMapping));
        }
        if (this.explicitPolicyReqd) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, ASN1Boolean.getInstance(this.explicitPolicyReqd)));
        }
        if (this.inhibitAnyPolicy) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, ASN1Boolean.getInstance(this.inhibitAnyPolicy)));
        }
        return new DERSequence(aSN1EncodableVector);
    }

    public String toString() {
        return "PathProcInput: {\nacceptablePolicySet: " + Arrays.asList(this.acceptablePolicySet) + "\n" + "inhibitPolicyMapping: " + this.inhibitPolicyMapping + "\n" + "explicitPolicyReqd: " + this.explicitPolicyReqd + "\n" + "inhibitAnyPolicy: " + this.inhibitAnyPolicy + "\n" + "}\n";
    }

    public PolicyInformation[] getAcceptablePolicySet() {
        return this.acceptablePolicySet;
    }

    public boolean isInhibitPolicyMapping() {
        return this.inhibitPolicyMapping;
    }

    private void setInhibitPolicyMapping(boolean bl) {
        this.inhibitPolicyMapping = bl;
    }

    public boolean isExplicitPolicyReqd() {
        return this.explicitPolicyReqd;
    }

    private void setExplicitPolicyReqd(boolean bl) {
        this.explicitPolicyReqd = bl;
    }

    public boolean isInhibitAnyPolicy() {
        return this.inhibitAnyPolicy;
    }

    private void setInhibitAnyPolicy(boolean bl) {
        this.inhibitAnyPolicy = bl;
    }
}

