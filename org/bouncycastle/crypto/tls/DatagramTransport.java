/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;

public interface DatagramTransport {
    public int getReceiveLimit() throws IOException;

    public int getSendLimit() throws IOException;

    public int receive(byte[] var1, int var2, int var3, int var4) throws IOException;

    public void send(byte[] var1, int var2, int var3) throws IOException;

    public void close() throws IOException;
}

