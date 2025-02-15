/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.cert.jcajce;

import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import org.bouncycastle.cert.jcajce.CertHelper;

class NamedCertHelper
extends CertHelper {
    private final String providerName;

    NamedCertHelper(String string) {
        this.providerName = string;
    }

    protected CertificateFactory createCertificateFactory(String string) throws CertificateException, NoSuchProviderException {
        return CertificateFactory.getInstance(string, this.providerName);
    }
}

