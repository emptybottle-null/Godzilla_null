/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;

public class ASN1Exception
extends IOException {
    private Throwable cause;

    ASN1Exception(String string) {
        super(string);
    }

    ASN1Exception(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

