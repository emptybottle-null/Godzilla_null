/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;

public class DERObjectIdentifier
extends ASN1ObjectIdentifier {
    public DERObjectIdentifier(String string) {
        super(string);
    }

    DERObjectIdentifier(byte[] byArray) {
        super(byArray);
    }

    DERObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String string) {
        super(aSN1ObjectIdentifier, string);
    }
}

