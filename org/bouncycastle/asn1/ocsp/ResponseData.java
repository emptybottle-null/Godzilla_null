/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.X509Extensions;

public class ResponseData
extends ASN1Object {
    private static final ASN1Integer V1 = new ASN1Integer(0L);
    private boolean versionPresent;
    private ASN1Integer version;
    private ResponderID responderID;
    private ASN1GeneralizedTime producedAt;
    private ASN1Sequence responses;
    private Extensions responseExtensions;

    public ResponseData(ASN1Integer aSN1Integer, ResponderID responderID, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1Sequence aSN1Sequence, Extensions extensions) {
        this.version = aSN1Integer;
        this.responderID = responderID;
        this.producedAt = aSN1GeneralizedTime;
        this.responses = aSN1Sequence;
        this.responseExtensions = extensions;
    }

    public ResponseData(ResponderID responderID, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1Sequence aSN1Sequence, X509Extensions x509Extensions) {
        this(V1, responderID, ASN1GeneralizedTime.getInstance(aSN1GeneralizedTime), aSN1Sequence, Extensions.getInstance(x509Extensions));
    }

    public ResponseData(ResponderID responderID, ASN1GeneralizedTime aSN1GeneralizedTime, ASN1Sequence aSN1Sequence, Extensions extensions) {
        this(V1, responderID, aSN1GeneralizedTime, aSN1Sequence, extensions);
    }

    private ResponseData(ASN1Sequence aSN1Sequence) {
        int n = 0;
        if (aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)aSN1Sequence.getObjectAt(0);
            if (aSN1TaggedObject.getTagNo() == 0) {
                this.versionPresent = true;
                this.version = ASN1Integer.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(0), true);
            } else {
                this.version = V1;
            }
        } else {
            this.version = V1;
        }
        int n2 = ++n;
        this.responderID = ResponderID.getInstance(aSN1Sequence.getObjectAt(n2));
        int n3 = ++n;
        this.producedAt = ASN1GeneralizedTime.getInstance(aSN1Sequence.getObjectAt(n3));
        int n4 = ++n;
        this.responses = (ASN1Sequence)aSN1Sequence.getObjectAt(n4);
        if (aSN1Sequence.size() > ++n) {
            this.responseExtensions = Extensions.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(n), true);
        }
    }

    public static ResponseData getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return ResponseData.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static ResponseData getInstance(Object object) {
        if (object instanceof ResponseData) {
            return (ResponseData)object;
        }
        if (object != null) {
            return new ResponseData(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer getVersion() {
        return this.version;
    }

    public ResponderID getResponderID() {
        return this.responderID;
    }

    public ASN1GeneralizedTime getProducedAt() {
        return this.producedAt;
    }

    public ASN1Sequence getResponses() {
        return this.responses;
    }

    public Extensions getResponseExtensions() {
        return this.responseExtensions;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.versionPresent || !this.version.equals(V1)) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.version));
        }
        aSN1EncodableVector.add(this.responderID);
        aSN1EncodableVector.add(this.producedAt);
        aSN1EncodableVector.add(this.responses);
        if (this.responseExtensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.responseExtensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

