/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import org.bouncycastle.asn1.ASN1Boolean;

public class DERBoolean
extends ASN1Boolean {
    public DERBoolean(boolean bl) {
        super(bl);
    }

    DERBoolean(byte[] byArray) {
        super(byArray);
    }
}

