/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.TlsCredentials;

public interface TlsAuthentication {
    public void notifyServerCertificate(Certificate var1) throws IOException;

    public TlsCredentials getClientCredentials(CertificateRequest var1) throws IOException;
}

