/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.crmf;

import org.bouncycastle.asn1.ASN1Integer;

public class SubsequentMessage
extends ASN1Integer {
    public static final SubsequentMessage encrCert = new SubsequentMessage(0);
    public static final SubsequentMessage challengeResp = new SubsequentMessage(1);

    private SubsequentMessage(int n) {
        super(n);
    }

    public static SubsequentMessage valueOf(int n) {
        if (n == 0) {
            return encrCert;
        }
        if (n == 1) {
            return challengeResp;
        }
        throw new IllegalArgumentException("unknown value: " + n);
    }
}

