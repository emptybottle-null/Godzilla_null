/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.exception;

import java.security.cert.CertificateEncodingException;
import org.bouncycastle.jce.exception.ExtException;

public class ExtCertificateEncodingException
extends CertificateEncodingException
implements ExtException {
    private Throwable cause;

    public ExtCertificateEncodingException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

