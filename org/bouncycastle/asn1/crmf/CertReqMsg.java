/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.crmf.AttributeTypeAndValue;
import org.bouncycastle.asn1.crmf.CertRequest;
import org.bouncycastle.asn1.crmf.ProofOfPossession;

public class CertReqMsg
extends ASN1Object {
    private CertRequest certReq;
    private ProofOfPossession pop;
    private ASN1Sequence regInfo;

    private CertReqMsg(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.certReq = CertRequest.getInstance(enumeration.nextElement());
        while (enumeration.hasMoreElements()) {
            Object e = enumeration.nextElement();
            if (e instanceof ASN1TaggedObject || e instanceof ProofOfPossession) {
                this.pop = ProofOfPossession.getInstance(e);
                continue;
            }
            this.regInfo = ASN1Sequence.getInstance(e);
        }
    }

    public static CertReqMsg getInstance(Object object) {
        if (object instanceof CertReqMsg) {
            return (CertReqMsg)object;
        }
        if (object != null) {
            return new CertReqMsg(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public static CertReqMsg getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return CertReqMsg.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public CertReqMsg(CertRequest certRequest, ProofOfPossession proofOfPossession, AttributeTypeAndValue[] attributeTypeAndValueArray) {
        if (certRequest == null) {
            throw new IllegalArgumentException("'certReq' cannot be null");
        }
        this.certReq = certRequest;
        this.pop = proofOfPossession;
        if (attributeTypeAndValueArray != null) {
            this.regInfo = new DERSequence(attributeTypeAndValueArray);
        }
    }

    public CertRequest getCertReq() {
        return this.certReq;
    }

    public ProofOfPossession getPop() {
        return this.pop;
    }

    public ProofOfPossession getPopo() {
        return this.pop;
    }

    public AttributeTypeAndValue[] getRegInfo() {
        if (this.regInfo == null) {
            return null;
        }
        AttributeTypeAndValue[] attributeTypeAndValueArray = new AttributeTypeAndValue[this.regInfo.size()];
        for (int i = 0; i != attributeTypeAndValueArray.length; ++i) {
            attributeTypeAndValueArray[i] = AttributeTypeAndValue.getInstance(this.regInfo.getObjectAt(i));
        }
        return attributeTypeAndValueArray;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.certReq);
        this.addOptional(aSN1EncodableVector, this.pop);
        this.addOptional(aSN1EncodableVector, this.regInfo);
        return new DERSequence(aSN1EncodableVector);
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(aSN1Encodable);
        }
    }
}

