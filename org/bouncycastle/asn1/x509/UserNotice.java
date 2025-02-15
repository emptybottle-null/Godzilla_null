/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.DisplayText;
import org.bouncycastle.asn1.x509.NoticeReference;

public class UserNotice
extends ASN1Object {
    private final NoticeReference noticeRef;
    private final DisplayText explicitText;

    public UserNotice(NoticeReference noticeReference, DisplayText displayText) {
        this.noticeRef = noticeReference;
        this.explicitText = displayText;
    }

    public UserNotice(NoticeReference noticeReference, String string) {
        this(noticeReference, new DisplayText(string));
    }

    private UserNotice(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() == 2) {
            this.noticeRef = NoticeReference.getInstance(aSN1Sequence.getObjectAt(0));
            this.explicitText = DisplayText.getInstance(aSN1Sequence.getObjectAt(1));
        } else if (aSN1Sequence.size() == 1) {
            if (aSN1Sequence.getObjectAt(0).toASN1Primitive() instanceof ASN1Sequence) {
                this.noticeRef = NoticeReference.getInstance(aSN1Sequence.getObjectAt(0));
                this.explicitText = null;
            } else {
                this.noticeRef = null;
                this.explicitText = DisplayText.getInstance(aSN1Sequence.getObjectAt(0));
            }
        } else if (aSN1Sequence.size() == 0) {
            this.noticeRef = null;
            this.explicitText = null;
        } else {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
    }

    public static UserNotice getInstance(Object object) {
        if (object instanceof UserNotice) {
            return (UserNotice)object;
        }
        if (object != null) {
            return new UserNotice(ASN1Sequence.getInstance(object));
        }
        return null;
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

