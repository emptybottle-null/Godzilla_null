/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x500.style;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameStyle;
import org.bouncycastle.asn1.x500.style.BCStyle;

public class BCStrictStyle
extends BCStyle {
    public static final X500NameStyle INSTANCE = new BCStrictStyle();

    public boolean areEqual(X500Name x500Name, X500Name x500Name2) {
        RDN[] rDNArray;
        RDN[] rDNArray2 = x500Name.getRDNs();
        if (rDNArray2.length != (rDNArray = x500Name2.getRDNs()).length) {
            return false;
        }
        for (int i = 0; i != rDNArray2.length; ++i) {
            if (this.rdnAreEqual(rDNArray2[i], rDNArray[i])) continue;
            return false;
        }
        return true;
    }
}

