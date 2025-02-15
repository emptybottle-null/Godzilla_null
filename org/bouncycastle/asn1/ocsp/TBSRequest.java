/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509Extensions;

public class TBSRequest
extends ASN1Object {
    private static final ASN1Integer V1 = new ASN1Integer(0L);
    ASN1Integer version;
    GeneralName requestorName;
    ASN1Sequence requestList;
    Extensions requestExtensions;
    boolean versionSet;

    public TBSRequest(GeneralName generalName, ASN1Sequence aSN1Sequence, X509Extensions x509Extensions) {
        this.version = V1;
        this.requestorName = generalName;
        this.requestList = aSN1Sequence;
        this.requestExtensions = Extensions.getInstance(x509Extensions);
    }

    public TBSRequest(GeneralName generalName, ASN1Sequence aSN1Sequence, Extensions extensions) {
        this.version = V1;
        this.requestorName = generalName;
        this.requestList = aSN1Sequence;
        this.requestExtensions = extensions;
    }

    private TBSRequest(ASN1Sequence aSN1Sequence) {
        int n = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Sequence.getObjectAt(0);
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.versionSet = true;
                this.version = ASN1Integer.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(0), true);
                ++n;
            } else {
                this.version = V1;
            }
        } else {
            this.version = V1;
        }
        if (aSN1Sequence.getObjectAt(n) instanceof ASN1TaggedObject) {
            this.requestorName = GeneralName.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(n++), true);
        }
        this.requestList = (ASN1Sequence)aSN1Sequence.getObjectAt(n++);
        if (aSN1Sequence.size() == n + 1) {
            this.requestExtensions = Extensions.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(n), true);
        }
    }

    public static TBSRequest getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return TBSRequest.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static TBSRequest getInstance(Object object) {
        if (object instanceof TBSRequest) {
            return (TBSRequest)object;
        }
        if (object != null) {
            return new TBSRequest(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public GeneralName getRequestorName() {
        return this.requestorName;
    }

    public ASN1Sequence getRequestList() {
        return this.requestList;
    }

    public Extensions getRequestExtensions() {
        return this.requestExtensions;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (!this.version.equals(V1) || this.versionSet) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.version));
        }
        if (this.requestorName != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.requestorName));
        }
        aSN1EncodableVector.add(this.requestList);
        if (this.requestExtensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.requestExtensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

