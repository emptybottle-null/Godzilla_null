/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;

public class ErrorMsgContent
extends ASN1Object {
    private PKIStatusInfo pkiStatusInfo;
    private ASN1Integer errorCode;
    private PKIFreeText errorDetails;

    private ErrorMsgContent(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        this.pkiStatusInfo = PKIStatusInfo.getInstance(enumeration.nextElement());
        while (enumeration.hasMoreElements()) {
            Object e = enumeration.nextElement();
            if (e instanceof ASN1Integer) {
                this.errorCode = ASN1Integer.getInstance(e);
                continue;
            }
            this.errorDetails = PKIFreeText.getInstance(e);
        }
    }

    public static ErrorMsgContent getInstance(Object object) {
        if (object instanceof ErrorMsgContent) {
            return (ErrorMsgContent)object;
        }
        if (object != null) {
            return new ErrorMsgContent(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ErrorMsgContent(PKIStatusInfo pKIStatusInfo) {
        this(pKIStatusInfo, null, null);
    }

    public ErrorMsgContent(PKIStatusInfo pKIStatusInfo, ASN1Integer aSN1Integer, PKIFreeText pKIFreeText) {
        if (pKIStatusInfo == null) {
            throw new IllegalArgumentException("'pkiStatusInfo' cannot be null");
        }
        this.pkiStatusInfo = pKIStatusInfo;
        this.errorCode = aSN1Integer;
        this.errorDetails = pKIFreeText;
    }

    public PKIStatusInfo getPKIStatusInfo() {
        return this.pkiStatusInfo;
    }

    public ASN1Integer getErrorCode() {
        return this.errorCode;
    }

    public PKIFreeText getErrorDetails() {
        return this.errorDetails;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.pkiStatusInfo);
        this.addOptional(aSN1EncodableVector, this.errorCode);
        this.addOptional(aSN1EncodableVector, this.errorDetails);
        return new DERSequence(aSN1EncodableVector);
    }

    private void addOptional(ASN1EncodableVector aSN1EncodableVector, ASN1Encodable aSN1Encodable) {
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(aSN1Encodable);
        }
    }
}

