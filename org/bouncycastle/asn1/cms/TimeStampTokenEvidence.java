/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cms;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.TimeStampAndCRL;

public class TimeStampTokenEvidence
extends ASN1Object {
    private TimeStampAndCRL[] timeStampAndCRLs;

    public TimeStampTokenEvidence(TimeStampAndCRL[] timeStampAndCRLArray) {
        this.timeStampAndCRLs = timeStampAndCRLArray;
    }

    public TimeStampTokenEvidence(TimeStampAndCRL timeStampAndCRL) {
        this.timeStampAndCRLs = new TimeStampAndCRL[1];
        this.timeStampAndCRLs[0] = timeStampAndCRL;
    }

    private TimeStampTokenEvidence(ASN1Sequence aSN1Sequence) {
        this.timeStampAndCRLs = new TimeStampAndCRL[aSN1Sequence.size()];
        int n = 0;
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            this.timeStampAndCRLs[n++] = TimeStampAndCRL.getInstance(enumeration.nextElement());
        }
    }

    public static TimeStampTokenEvidence getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return TimeStampTokenEvidence.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static TimeStampTokenEvidence getInstance(Object object) {
        if (object instanceof TimeStampTokenEvidence) {
            return (TimeStampTokenEvidence)object;
        }
        if (object != null) {
            return new TimeStampTokenEvidence(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public TimeStampAndCRL[] toTimeStampAndCRLArray() {
        return this.timeStampAndCRLs;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.timeStampAndCRLs.length; ++i) {
            aSN1EncodableVector.add(this.timeStampAndCRLs[i]);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}

