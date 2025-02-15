/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.bouncycastle.crypto.tls.NewSessionTicket;
import org.bouncycastle.crypto.tls.ProtocolVersion;
import org.bouncycastle.crypto.tls.TlsAuthentication;
import org.bouncycastle.crypto.tls.TlsClientContext;
import org.bouncycastle.crypto.tls.TlsKeyExchange;
import org.bouncycastle.crypto.tls.TlsPeer;
import org.bouncycastle.crypto.tls.TlsSession;

public interface TlsClient
extends TlsPeer {
    public void init(TlsClientContext var1);

    public TlsSession getSessionToResume();

    public ProtocolVersion getClientHelloRecordLayerVersion();

    public ProtocolVersion getClientVersion();

    public boolean isFallback();

    public int[] getCipherSuites();

    public short[] getCompressionMethods();

    public Hashtable getClientExtensions() throws IOException;

    public void notifyServerVersion(ProtocolVersion var1) throws IOException;

    public void notifySessionID(byte[] var1);

    public void notifySelectedCipherSuite(int var1);

    public void notifySelectedCompressionMethod(short var1);

    public void processServerExtensions(Hashtable var1) throws IOException;

    public void processServerSupplementalData(Vector var1) throws IOException;

    public TlsKeyExchange getKeyExchange() throws IOException;

    public TlsAuthentication getAuthentication() throws IOException;

    public Vector getClientSupplementalData() throws IOException;

    public void notifyNewSessionTicket(NewSessionTicket var1) throws IOException;
}

