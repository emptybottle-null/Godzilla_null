/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

public class ASN1ParsingException
extends IllegalStateException {
    private Throwable cause;

    public ASN1ParsingException(String string) {
        super(string);
    }

    public ASN1ParsingException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

