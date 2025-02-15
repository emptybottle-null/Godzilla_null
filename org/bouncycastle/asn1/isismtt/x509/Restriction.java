/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.isismtt.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x500.DirectoryString;

public class Restriction
extends ASN1Object {
    private DirectoryString restriction;

    public static Restriction getInstance(Object object) {
        if (object instanceof Restriction) {
            return (Restriction)object;
        }
        if (object != null) {
            return new Restriction(DirectoryString.getInstance(object));
        }
        return null;
    }

    private Restriction(DirectoryString directoryString) {
        this.restriction = directoryString;
    }

    public Restriction(String string) {
        this.restriction = new DirectoryString(string);
    }

    public DirectoryString getRestriction() {
        return this.restriction;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.restriction.toASN1Primitive();
    }
}

