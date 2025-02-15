/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.icao;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.x509.Certificate;

public class CscaMasterList
extends ASN1Object {
    private ASN1Integer version = new ASN1Integer(0L);
    private Certificate[] certList;

    public static CscaMasterList getInstance(Object object) {
        if (object instanceof CscaMasterList) {
            return (CscaMasterList)object;
        }
        if (object != null) {
            return new CscaMasterList(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private CscaMasterList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence == null || aSN1Sequence.size() == 0) {
            throw new IllegalArgumentException("null or empty sequence passed.");
        }
        if (aSN1Sequence.size() != 2) {
            throw new IllegalArgumentException("Incorrect sequence size: " + aSN1Sequence.size());
        }
        this.version = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        ASN1Set aSN1Set = ASN1Set.getInstance(aSN1Sequence.getObjectAt(1));
        this.certList = new Certificate[aSN1Set.size()];
        for (int i = 0; i < this.certList.length; ++i) {
            this.certList[i] = Certificate.getInstance(aSN1Set.getObjectAt(i));
        }
    }

    public CscaMasterList(Certificate[] certificateArray) {
        this.certList = this.copyCertList(certificateArray);
    }

    public int getVersion() {
        return this.version.getValue().intValue();
    }

    public Certificate[] getCertStructs() {
        return this.copyCertList(this.certList);
    }

    private Certificate[] copyCertList(Certificate[] certificateArray) {
        Certificate[] certificateArray2 = new Certificate[certificateArray.length];
        for (int i = 0; i != certificateArray2.length; ++i) {
            certificateArray2[i] = certificateArray[i];
        }
        return certificateArray2;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(this.version);
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        for (int i = 0; i < this.certList.length; ++i) {
            aSN1EncodableVector2.add(this.certList[i]);
        }
        aSN1EncodableVector.add(new DERSet(aSN1EncodableVector2));
        return new DERSequence(aSN1EncodableVector);
    }
}

