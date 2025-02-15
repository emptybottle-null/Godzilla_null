/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

abstract class CertHelper {
    CertHelper() {
    }

    public CertificateFactory getCertificateFactory(String string) throws NoSuchProviderException, CertificateException {
        return this.createCertificateFactory(string);
    }

    protected abstract CertificateFactory createCertificateFactory(String var1) throws CertificateException, NoSuchProviderException;
}

