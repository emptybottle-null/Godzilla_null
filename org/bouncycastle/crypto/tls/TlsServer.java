/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.Certificate;
import org.bouncycastle.crypto.tls.CertificateRequest;
import org.bouncycastle.crypto.tls.CertificateStatus;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsCredentials;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsPeer;
import org.bouncycastle.crypto.tls.TlsServerContext;

public interface TlsServer
extends TlsPeer {
    public void init(TlsServerContext var1);

    public void notifyClientVersion(ProtocolVersion var1) throws IOException;

    public void notifyFallback(boolean var1) throws IOException;

    public void notifyOfferedCipherSuites(int[] var1) throws IOException;

    public void notifyOfferedCompressionMethods(short[] var1) throws IOException;

    public void processClientExtensions(Hashtable var1) throws IOException;

    public ProtocolVersion getServerVersion() throws IOException;

    public int getSelectedCipherSuite() throws IOException;

    public short getSelectedCompressionMethod() throws IOException;

    public Hashtable getServerExtensions() throws IOException;

    public Vector getServerSupplementalData() throws IOException;

    public TlsCredentials getCredentials() throws IOException;

    public CertificateStatus getCertificateStatus() throws IOException;

    public TlsKeyExchange getKeyExchange() throws IOException;

    public CertificateRequest getCertificateRequest() throws IOException;

    public void processClientSupplementalData(Vector var1) throws IOException;

    public void notifyClientCertificate(Certificate var1) throws IOException;

    public NewSessionTicket getNewSessionTicket() throws IOException;
}

