/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.jce.exception;

import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import org.bouncycastle.jce.exception.ExtException;

public class ExtCertPathValidatorException
extends CertPathValidatorException
implements ExtException {
    private Throwable cause;

    public ExtCertPathValidatorException(String string, Throwable throwable) {
        super(string);
        this.cause = throwable;
    }

    public ExtCertPathValidatorException(String string, Throwable throwable, CertPath certPath, int n) {
        super(string, throwable, certPath, n);
        this.cause = throwable;
    }

    public Throwable getCause() {
        return this.cause;
    }
}

