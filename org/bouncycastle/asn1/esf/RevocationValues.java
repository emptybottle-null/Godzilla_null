/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.esf.OtherRevVals;
import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.bouncycastle.asn1.x509.CertificateList;

public class RevocationValues
extends ASN1Object {
    private ASN1Sequence crlVals;
    private ASN1Sequence ocspVals;
    private OtherRevVals otherRevVals;

    public static RevocationValues getInstance(Object object) {
        if (object instanceof RevocationValues) {
            return (RevocationValues)object;
        }
        if (object != null) {
            return new RevocationValues(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private RevocationValues(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() > 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration enumeration = aSN1Sequence.getObjects();
        block5: while (enumeration.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject)enumeration.nextElement();
            switch (aSN1TaggedObject.getTagNo()) {
                case 0: {
                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence)aSN1TaggedObject.getObject();
                    Enumeration enumeration2 = aSN1Sequence2.getObjects();
                    while (enumeration2.hasMoreElements()) {
                        CertificateList.getInstance(enumeration2.nextElement());
                    }
                    this.crlVals = aSN1Sequence2;
                    continue block5;
                }
                case 1: {
                    ASN1Sequence aSN1Sequence3 = (ASN1Sequence)aSN1TaggedObject.getObject();
                    Enumeration enumeration3 = aSN1Sequence3.getObjects();
                    while (enumeration3.hasMoreElements()) {
                        BasicOCSPResponse.getInstance(enumeration3.nextElement());
                    }
                    this.ocspVals = aSN1Sequence3;
                    continue block5;
                }
                case 2: {
                    this.otherRevVals = OtherRevVals.getInstance(aSN1TaggedObject.getObject());
                    continue block5;
                }
            }
            throw new IllegalArgumentException("invalid tag: " + aSN1TaggedObject.getTagNo());
        }
    }

    public RevocationValues(CertificateList[] certificateListArray, BasicOCSPResponse[] basicOCSPResponseArray, OtherRevVals otherRevVals) {
        if (null != certificateListArray) {
            this.crlVals = new DERSequence(certificateListArray);
        }
        if (null != basicOCSPResponseArray) {
            this.ocspVals = new DERSequence(basicOCSPResponseArray);
        }
        this.otherRevVals = otherRevVals;
    }

    public CertificateList[] getCrlVals() {
        if (null == this.crlVals) {
            return new CertificateList[0];
        }
        CertificateList[] certificateListArray = new CertificateList[this.crlVals.size()];
        for (int i = 0; i < certificateListArray.length; ++i) {
            certificateListArray[i] = CertificateList.getInstance(this.crlVals.getObjectAt(i));
        }
        return certificateListArray;
    }

    public BasicOCSPResponse[] getOcspVals() {
        if (null == this.ocspVals) {
            return new BasicOCSPResponse[0];
        }
        BasicOCSPResponse[] basicOCSPResponseArray = new BasicOCSPResponse[this.ocspVals.size()];
        for (int i = 0; i < basicOCSPResponseArray.length; ++i) {
            basicOCSPResponseArray[i] = BasicOCSPResponse.getInstance(this.ocspVals.getObjectAt(i));
        }
        return basicOCSPResponseArray;
    }

    public OtherRevVals getOtherRevVals() {
        return this.otherRevVals;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (null != this.crlVals) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, this.crlVals));
        }
        if (null != this.ocspVals) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, this.ocspVals));
        }
        if (null != this.otherRevVals) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, this.otherRevVals.toASN1Primitive()));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

