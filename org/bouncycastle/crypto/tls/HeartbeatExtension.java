/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.HeartbeatMode;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;

public class HeartbeatExtension {
    protected short mode;

    public HeartbeatExtension(short s) {
        if (!HeartbeatMode.isValid(s)) {
            throw new IllegalArgumentException("'mode' is not a valid HeartbeatMode value");
        }
        this.mode = s;
    }

    public short getMode() {
        return this.mode;
    }

    public void encode(OutputStream outputStream) throws IOException {
        TlsUtils.writeUint8(this.mode, outputStream);
    }

    public static HeartbeatExtension parse(InputStream inputStream) throws IOException {
        short s = TlsUtils.readUint8(inputStream);
        if (!HeartbeatMode.isValid(s)) {
            throw new TlsFatalAlert(47);
        }
        return new HeartbeatExtension(s);
    }
}

