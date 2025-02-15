/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;

public class ASN1EncodableVector {
    private final Vector v = new Vector();

    public void add(ASN1Encodable aSN1Encodable) {
        this.v.addElement(aSN1Encodable);
    }

    public void addAll(ASN1EncodableVector aSN1EncodableVector) {
        Enumeration enumeration = aSN1EncodableVector.v.elements();
        while (enumeration.hasMoreElements()) {
            this.v.addElement(enumeration.nextElement());
        }
    }

    public ASN1Encodable get(int n) {
        return (ASN1Encodable)this.v.elementAt(n);
    }

    public int size() {
        return this.v.size();
    }
}

