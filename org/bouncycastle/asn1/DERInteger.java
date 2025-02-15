/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Integer;

public class DERInteger
extends ASN1Integer {
    public DERInteger(byte[] byArray) {
        super(byArray, true);
    }

    public DERInteger(BigInteger bigInteger) {
        super(bigInteger);
    }

    public DERInteger(long l) {
        super(l);
    }
}

