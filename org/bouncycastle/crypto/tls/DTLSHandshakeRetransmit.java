/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;

interface DTLSHandshakeRetransmit {
    public void receivedHandshakeRecord(int var1, byte[] var2, int var3, int var4) throws IOException;
}

