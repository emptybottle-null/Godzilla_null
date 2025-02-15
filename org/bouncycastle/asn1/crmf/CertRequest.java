/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.CertTemplate;
import org.bouncycastle.asn1.crmf.Controls;

public class CertRequest
extends ASN1Object {
    private ASN1Integer certReqId;
    private CertTemplate certTemplate;
    private Controls controls;

    private CertRequest(ASN1Sequence aSN1Sequence) {
        this.certReqId = new ASN1Integer(ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0)).getValue());
        this.certTemplate = CertTemplate.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            this.controls = Controls.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public static CertRequest getInstance(Object object) {
        if (object instanceof CertRequest) {
            return (CertRequest)object;
        }
        if (object != null) {
            return new CertRequest(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public CertRequest(int n, CertTemplate certTemplate, Controls controls) {
        this(new ASN1Integer(n), certTemplate, controls);
    }

    public CertRequest(ASN1Integer aSN1Integer, CertTemplate certTemplate, Controls controls) {
        this.certReqId = aSN1Integer;
        this.certTemplate = certTemplate;
        this.controls = controls;
    }

    public ASN1Integer getCertReqId() {
        return this.certReqId;
    }

    public CertTemplate getCertTemplate() {
        return this.certTemplate;
    }

    public Controls getControls() {
        return this.controls;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certReqId);
        aSN1EncodableVector.add(this.certTemplate);
        if (this.controls != null) {
            aSN1EncodableVector.add(this.controls);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

