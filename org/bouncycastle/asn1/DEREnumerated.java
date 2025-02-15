/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Enumerated;

public class DEREnumerated
extends ASN1Enumerated {
    DEREnumerated(byte[] byArray) {
        super(byArray);
    }

    public DEREnumerated(BigInteger bigInteger) {
        super(bigInteger);
    }

    public DEREnumerated(int n) {
        super(n);
    }
}

