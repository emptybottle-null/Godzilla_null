/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

public class BasicOCSPResponse
extends ASN1Object {
    private ResponseData tbsResponseData;
    private AlgorithmIdentifier signatureAlgorithm;
    private DERBitString signature;
    private ASN1Sequence certs;

    public BasicOCSPResponse(ResponseData responseData, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString, ASN1Sequence aSN1Sequence) {
        this.tbsResponseData = responseData;
        this.signatureAlgorithm = algorithmIdentifier;
        this.signature = dERBitString;
        this.certs = aSN1Sequence;
    }

    private BasicOCSPResponse(ASN1Sequence aSN1Sequence) {
        this.tbsResponseData = ResponseData.getInstance(aSN1Sequence.getObjectAt(0));
        this.signatureAlgorithm = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.signature = (DERBitString)aSN1Sequence.getObjectAt(2);
        if (aSN1Sequence.size() > 3) {
            this.certs = ASN1Sequence.getInstance((ASN1TaggedObject)aSN1Sequence.getObjectAt(3), true);
        }
    }

    public static BasicOCSPResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return BasicOCSPResponse.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static BasicOCSPResponse getInstance(Object object) {
        if (object instanceof BasicOCSPResponse) {
            return (BasicOCSPResponse)object;
        }
        if (object != null) {
            return new BasicOCSPResponse(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ResponseData getTbsResponseData() {
        return this.tbsResponseData;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    public DERBitString getSignature() {
        return this.signature;
    }

    public ASN1Sequence getCerts() {
        return this.certs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.tbsResponseData);
        aSN1EncodableVector.add(this.signatureAlgorithm);
        aSN1EncodableVector.add(this.signature);
        if (this.certs != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.certs));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

