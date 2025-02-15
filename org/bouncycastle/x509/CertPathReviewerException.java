/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.x509;

import java.security.cert.CertPath;
import org.bouncycastle.i18n.ErrorBundle;
import org.bouncycastle.i18n.LocalizedException;

public class CertPathReviewerException
extends LocalizedException {
    private int index = -1;
    private CertPath certPath = null;

    public CertPathReviewerException(ErrorBundle errorBundle, Throwable throwable) {
        super(errorBundle, throwable);
    }

    public CertPathReviewerException(ErrorBundle errorBundle) {
        super(errorBundle);
    }

    public CertPathReviewerException(ErrorBundle errorBundle, Throwable throwable, CertPath certPath, int n) {
        super(errorBundle, throwable);
        if (certPath == null || n == -1) {
            throw new IllegalArgumentException();
        }
        if (n < -1 || certPath != null && n >= certPath.getCertificates().size()) {
            throw new IndexOutOfBoundsException();
        }
        this.certPath = certPath;
        this.index = n;
    }

    public CertPathReviewerException(ErrorBundle errorBundle, CertPath certPath, int n) {
        super(errorBundle);
        if (certPath == null || n == -1) {
            throw new IllegalArgumentException();
        }
        if (n < -1 || certPath != null && n >= certPath.getCertificates().size()) {
            throw new IndexOutOfBoundsException();
        }
        this.certPath = certPath;
        this.index = n;
    }

    public CertPath getCertPath() {
        return this.certPath;
    }

    public int getIndex() {
        return this.index;
    }
}

