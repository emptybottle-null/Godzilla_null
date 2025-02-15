/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.cmp;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;

public class PKIFreeText
extends ASN1Object {
    ASN1Sequence strings;

    public static PKIFreeText getInstance(ASN1TaggedObject aSN1TaggedObject, boolean bl) {
        return PKIFreeText.getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, bl));
    }

    public static PKIFreeText getInstance(Object object) {
        if (object instanceof PKIFreeText) {
            return (PKIFreeText)object;
        }
        if (object != null) {
            return new PKIFreeText(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private PKIFreeText(ASN1Sequence aSN1Sequence) {
        Enumeration enumeration = aSN1Sequence.getObjects();
        while (enumeration.hasMoreElements()) {
            if (enumeration.nextElement() instanceof DERUTF8String) continue;
            throw new IllegalArgumentException("attempt to insert non UTF8 STRING into PKIFreeText");
        }
        this.strings = aSN1Sequence;
    }

    public PKIFreeText(DERUTF8String dERUTF8String) {
        this.strings = new DERSequence(dERUTF8String);
    }

    public PKIFreeText(String string) {
        this(new DERUTF8String(string));
    }

    public PKIFreeText(DERUTF8String[] dERUTF8StringArray) {
        this.strings = new DERSequence(dERUTF8StringArray);
    }

    public PKIFreeText(String[] stringArray) {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i < stringArray.length; ++i) {
            aSN1EncodableVector.add(new DERUTF8String(stringArray[i]));
        }
        this.strings = new DERSequence(aSN1EncodableVector);
    }

    public int size() {
        return this.strings.size();
    }

    public DERUTF8String getStringAt(int n) {
        return (DERUTF8String)this.strings.getObjectAt(n);
    }

    public ASN1Primitive toASN1Primitive() {
        return this.strings;
    }
}

