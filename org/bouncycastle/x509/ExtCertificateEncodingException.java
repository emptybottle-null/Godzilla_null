/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.security.cert.CertificateEncodingException;

class ExtCertificateEncodingException
extends CertificateEncodingException {
    Throwable cause;

    ExtCertificateEncodingException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

