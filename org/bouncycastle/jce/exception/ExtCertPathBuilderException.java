/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.exception;

import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import org.bouncycastle.jce.exception.ExtException;

public class ExtCertPathBuilderException
extends CertPathBuilderException
implements ExtException {
    private Throwable cause;

    public ExtCertPathBuilderException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public ExtCertPathBuilderException(String string, Throwable throwable, CertPath certPath, int n) {
        super(string, throwable);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

