/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.esf.CrlValidatedID;

public class CrlListID
extends ASN1Object {
    private ASN1Sequence crls;

    public static CrlListID getInstance(Object object) {
        if (object instanceof CrlListID) {
            return (CrlListID)object;
        }
        if (object != null) {
            return new CrlListID(ASN1Sequence.getInstance(object));
        }
        return null;
    }

    private CrlListID(ASN1Sequence aSN1Sequence) {
        this.crls = (ASN1Sequence)aSN1Sequence.getObjectAt(0);
        Enumeration enumeration = this.crls.getObjects();
        while (enumeration.hasMoreElements()) {
            CrlValidatedID.getInstance(enumeration.nextElement());
        }
    }

    public CrlListID(CrlValidatedID[] crlValidatedIDArray) {
        this.crls = new DERSequence(crlValidatedIDArray);
    }

    public CrlValidatedID[] getCrls() {
        CrlValidatedID[] crlValidatedIDArray = new CrlValidatedID[this.crls.size()];
        for (int i = 0; i < crlValidatedIDArray.length; ++i) {
            crlValidatedIDArray[i] = CrlValidatedID.getInstance(this.crls.getObjectAt(i));
        }
        return crlValidatedIDArray;
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERSequence(this.crls);
    }
}

