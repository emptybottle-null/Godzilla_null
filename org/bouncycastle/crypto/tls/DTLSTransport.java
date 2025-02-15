/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.tls.DTLSRecordLayer;
import org.bouncycastle.crypto.tls.DatagramTransport;
import org.bouncycastle.crypto.tls.TlsFatalAlert;

public class DTLSTransport
implements DatagramTransport {
    private final DTLSRecordLayer recordLayer;

    DTLSTransport(DTLSRecordLayer dTLSRecordLayer) {
        this.recordLayer = dTLSRecordLayer;
    }

    public int getReceiveLimit() throws IOException {
        return this.recordLayer.getReceiveLimit();
    }

    public int getSendLimit() throws IOException {
        return this.recordLayer.getSendLimit();
    }

    public int receive(byte[] byArray, int n, int n2, int n3) throws IOException {
        try {
            return this.recordLayer.receive(byArray, n, n2, n3);
        } catch (TlsFatalAlert tlsFatalAlert) {
            this.recordLayer.fail(tlsFatalAlert.getAlertDescription());
            throw tlsFatalAlert;
        } catch (IOException iOException) {
            this.recordLayer.fail((short)80);
            throw iOException;
        } catch (RuntimeException runtimeException) {
            this.recordLayer.fail((short)80);
            throw new TlsFatalAlert(80, (Throwable)runtimeException);
        }
    }

    public void send(byte[] byArray, int n, int n2) throws IOException {
        try {
            this.recordLayer.send(byArray, n, n2);
        } catch (TlsFatalAlert tlsFatalAlert) {
            this.recordLayer.fail(tlsFatalAlert.getAlertDescription());
            throw tlsFatalAlert;
        } catch (IOException iOException) {
            this.recordLayer.fail((short)80);
            throw iOException;
        } catch (RuntimeException runtimeException) {
            this.recordLayer.fail((short)80);
            throw new TlsFatalAlert(80, (Throwable)runtimeException);
        }
    }

    public void close() throws IOException {
        this.recordLayer.close();
    }
}

