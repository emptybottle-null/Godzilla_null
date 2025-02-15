/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

public class PollReqContent
extends ASN1Object {
    private ASN1Sequence content;

    private PollReqContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static PollReqContent getInstance(Object object) {
        if (object instanceof PollReqContent) {
            return (PollReqContent)object;
        }
        if (object != null) {
            return new PollReqContent(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public PollReqContent(ASN1Integer aSN1Integer) {
        this(new DERSequence(new DERSequence(aSN1Integer)));
    }

    public ASN1Integer[][] getCertReqIds() {
        ASN1Integer[][] aSN1IntegerArray = new ASN1Integer[this.content.size()][];
        for (int i = 0; i != aSN1IntegerArray.length; ++i) {
            aSN1IntegerArray[i] = PollReqContent.sequenceToASN1IntegerArray((ASN1Sequence)this.content.getObjectAt(i));
        }
        return aSN1IntegerArray;
    }

    private static ASN1Integer[] sequenceToASN1IntegerArray(ASN1Sequence aSN1Sequence) {
        ASN1Integer[] aSN1IntegerArray = new ASN1Integer[aSN1Sequence.size()];
        for (int i = 0; i != aSN1IntegerArray.length; ++i) {
            aSN1IntegerArray[i] = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return aSN1IntegerArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}

