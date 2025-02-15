/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;

public class POPODecKeyRespContent
extends ASN1Object {
    private ASN1Sequence content;

    private POPODecKeyRespContent(ASN1Sequence aSN1Sequence) {
        this.content = aSN1Sequence;
    }

    public static POPODecKeyRespContent getInstance(Object object) {
        if (object instanceof POPODecKeyRespContent) {
            return (POPODecKeyRespContent)object;
        }
        if (object != null) {
            return new POPODecKeyRespContent(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    public ASN1Integer[] toASN1IntegerArray() {
        ASN1Integer[] aSN1IntegerArray = new ASN1Integer[this.content.size()];
        for (int i = 0; i != aSN1IntegerArray.length; ++i) {
            aSN1IntegerArray[i] = ASN1Integer.getInstance(this.content.getObjectAt(i));
        }
        return aSN1IntegerArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.content;
    }
}

