/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.CertReqMsg;

public class CertReqMessages
extends ASN1Object {
    private ASN1Sequence content;

    private CertReqMessages(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static CertReqMessages getInstance(Object object) {
        if (object instanceof CertReqMessages) {
            return (CertReqMessages)object;
        }
        if (object != null) {
            return new CertReqMessages(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public CertReqMessages(CertReqMsg certReqMsg) {
        this.content = new DERSequence(certReqMsg);
    }

    public CertReqMessages(CertReqMsg[] certReqMsgArray) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i < certReqMsgArray.length; ++i) {
            aSN1EncodableVector.add(certReqMsgArray[i]);
        }
        this.content = new DERSequence(aSN1EncodableVector);
    }

    public CertReqMsg[] toCertReqMsgArray() {
        CertReqMsg[] certReqMsgArray = new CertReqMsg[this.content.size()];
        for (int i = 0; i != certReqMsgArray.length; ++i) {
            certReqMsgArray[i] = CertReqMsg.getInstance(this.content.getObjectAt(i));
        }
        return certReqMsgArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}

