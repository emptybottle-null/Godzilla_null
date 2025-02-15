/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import org.bouncycastle.crypto.tls.DatagramTransport;
import org.bouncycastle.crypto.tls.TlsFatalAlert;

public class UDPTransport
implements DatagramTransport {
    protected static final int MIN_IP_OVERHEAD = 20;
    protected static final int MAX_IP_OVERHEAD = 84;
    protected static final int UDP_OVERHEAD = 8;
    protected final DatagramSocket socket;
    protected final int receiveLimit;
    protected final int sendLimit;

    public UDPTransport(DatagramSocket datagramSocket, int n) throws IOException {
        if (!datagramSocket.isBound() || !datagramSocket.isConnected()) {
            throw new IllegalArgumentException("'socket' must be bound and connected");
        }
        this.socket = datagramSocket;
        this.receiveLimit = n - 20 - 8;
        this.sendLimit = n - 84 - 8;
    }

    public int getReceiveLimit() {
        return this.receiveLimit;
    }

    public int getSendLimit() {
        return this.sendLimit;
    }

    public int receive(byte[] byArray, int n, int n2, int n3) throws IOException {
        this.socket.setSoTimeout(n3);
        DatagramPacket datagramPacket = new DatagramPacket(byArray, n, n2);
        this.socket.receive(datagramPacket);
        return datagramPacket.getLength();
    }

    public void send(byte[] byArray, int n, int n2) throws IOException {
        if (n2 > this.getSendLimit()) {
            throw new TlsFatalAlert(80);
        }
        DatagramPacket datagramPacket = new DatagramPacket(byArray, n, n2);
        this.socket.send(datagramPacket);
    }

    public void close() throws IOException {
        this.socket.close();
    }
}

