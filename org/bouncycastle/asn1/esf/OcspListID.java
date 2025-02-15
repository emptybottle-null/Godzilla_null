/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.esf.OcspResponsesID;

public class OcspListID
extends ASN1Object {
    private ASN1Sequence ocspResponses;

    public static OcspListID getInstance(Object object) {
        if (object instanceof OcspListID) {
            return (OcspListID)object;
        }
        if (object != null) {
            return new OcspListID(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private OcspListID(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 1) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        this.ocspResponses = (ASN1Sequence)aSN1Sequence.getObjectAt(0);
        Enumeration enumeration = this.ocspResponses.getObjects();
        while (enumeration.hasMoreElements()) {
            OcspResponsesID.getInstance(enumeration.nextElement());
        }
    }

    public OcspListID(OcspResponsesID[] ocspResponsesIDArray) {
        this.ocspResponses = new DERSequence(ocspResponsesIDArray);
    }

    public OcspResponsesID[] getOcspResponses() {
        OcspResponsesID[] ocspResponsesIDArray = new OcspResponsesID[this.ocspResponses.size()];
        for (int i = 0; i < ocspResponsesIDArray.length; ++i) {
            ocspResponsesIDArray[i] = OcspResponsesID.getInstance(this.ocspResponses.getObjectAt(i));
        }
        return ocspResponsesIDArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.ocspResponses);
    }
}

