/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.util.Date;
import org.bouncycastle.asn1.ASN1GeneralizedTime;

public class DERGeneralizedTime
extends ASN1GeneralizedTime {
    DERGeneralizedTime(byte[] byArray) {
        super(byArray);
    }

    public DERGeneralizedTime(Date date) {
        super(date);
    }

    public DERGeneralizedTime(String string) {
        super(string);
    }
}

