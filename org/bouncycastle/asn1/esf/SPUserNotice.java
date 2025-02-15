/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.DisplayText;
import org.bouncycastle.asn1.x509.NoticeReference;

public class SPUserNotice
extends ASN1Object {
    private NoticeReference noticeRef;
    private DisplayText explicitText;

    public static SPUserNotice getInstance(Object object) {
        if (object instanceof SPUserNotice) {
            return (SPUserNotice)object;
        }
        if (object != null) {
            return new SPUserNotice(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private SPUserNotice(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable)enumeration.nextElement();
            if (aSN1Encodable instanceof DisplayText || aSN1Encodable instanceof ASN1String) {
                this.explicitText = DisplayText.getInstance(aSN1Encodable);
                continue;
            }
            if (aSN1Encodable instanceof NoticeReference || aSN1Encodable instanceof ASN1Sequence) {
                this.noticeRef = NoticeReference.getInstance(aSN1Encodable);
                continue;
            }
            throw new IllegalArgumentException("Invalid element in 'SPUserNotice': " + aSN1Encodable.getClass().getName());
        }
    }

    public SPUserNotice(NoticeReference noticeReference, DisplayText displayText) {
        this.noticeRef = noticeReference;
        this.explicitText = displayText;
    }

    public NoticeReference getNoticeRef() {
        return this.noticeRef;
    }

    public DisplayText getExplicitText() {
        return this.explicitText;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        if (this.noticeRef != null) {
            aSN1EncodableVector.add(this.noticeRef);
        }
        if (this.explicitText != null) {
            aSN1EncodableVector.add(this.explicitText);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

