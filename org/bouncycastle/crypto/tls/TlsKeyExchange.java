/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsCredentials;

public interface TlsKeyExchange {
    public void init(TlsContext var1);

    public void skipServerCredentials() throws IOException;

    public void processServerCredentials(TlsCredentials var1) throws IOException;

    public void processServerCertificate(Certificate var1) throws IOException;

    public boolean requiresServerKeyExchange();

    public byte[] generateServerKeyExchange() throws IOException;

    public void skipServerKeyExchange() throws IOException;

    public void processServerKeyExchange(InputStream var1) throws IOException;

    public void validateCertificateRequest(CertificateRequest var1) throws IOException;

    public void skipClientCredentials() throws IOException;

    public void processClientCredentials(TlsCredentials var1) throws IOException;

    public void processClientCertificate(Certificate var1) throws IOException;

    public void generateClientKeyExchange(OutputStream var1) throws IOException;

    public void processClientKeyExchange(InputStream var1) throws IOException;

    public byte[] generatePremasterSecret() throws IOException;
}

