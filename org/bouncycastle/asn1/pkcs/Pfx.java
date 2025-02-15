/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.BERSequence;
import org.bouncycastle.asn1.pkcs.ContentInfo;
import org.bouncycastle.asn1.pkcs.MacData;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

public class Pfx
extends ASN1Object
implements PKCSObjectIdentifiers {
    private ContentInfo contentInfo;
    private MacData macData = null;

    private Pfx(ASN1Sequence aSN1Sequence) {
        BigInteger bigInteger = ((ASN1Integer)aSN1Sequence.getObjectAt(0)).getValue();
        if (bigInteger.intValue() != 3) {
            throw new IllegalArgumentException("wrong version for PFX PDU");
        }
        this.contentInfo = ContentInfo.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() == 3) {
            this.macData = MacData.getInstance(aSN1Sequence.getObjectAt(2));
        }
    }

    public static Pfx getInstance(Object object) {
        if (object instanceof Pfx) {
            return (Pfx)object;
        }
        if (object != null) {
            return new Pfx(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public Pfx(ContentInfo contentInfo, MacData macData) {
        this.contentInfo = contentInfo;
        this.macData = macData;
    }

    public ContentInfo getAuthSafe() {
        return this.contentInfo;
    }

    public MacData getMacData() {
        return this.macData;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(3L));
        aSN1EncodableVector.add(this.contentInfo);
        if (this.macData != null) {
            aSN1EncodableVector.add(this.macData);
        }
        return new BERSequence(aSN1EncodableVector);
    }
}

