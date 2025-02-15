/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.crypto.tls.TlsContext;
import org.bouncycastle.crypto.tls.TlsFatalAlert;
import org.bouncycastle.crypto.tls.TlsUtils;
import org.bouncycastle.util.Strings;

public class URLAndHash {
    protected String url;
    protected byte[] sha1Hash;

    public URLAndHash(String string, byte[] byArray) {
        if (string == null || string.length() < 1 || string.length() >= 65536) {
            throw new IllegalArgumentException("'url' must have length from 1 to (2^16 - 1)");
        }
        if (byArray != null && byArray.length != 20) {
            throw new IllegalArgumentException("'sha1Hash' must have length == 20, if present");
        }
        this.url = string;
        this.sha1Hash = byArray;
    }

    public String getURL() {
        return this.url;
    }

    public byte[] getSHA1Hash() {
        return this.sha1Hash;
    }

    public void encode(OutputStream outputStream) throws IOException {
        byte[] byArray = Strings.toByteArray(this.url);
        TlsUtils.writeOpaque16(byArray, outputStream);
        if (this.sha1Hash == null) {
            TlsUtils.writeUint8(0, outputStream);
        } else {
            TlsUtils.writeUint8(1, outputStream);
            outputStream.write(this.sha1Hash);
        }
    }

    public static URLAndHash parse(TlsContext tlsContext, InputStream inputStream) throws IOException {
        byte[] byArray = TlsUtils.readOpaque16(inputStream);
        if (byArray.length < 1) {
            throw new TlsFatalAlert(47);
        }
        String string = Strings.fromByteArray(byArray);
        byte[] byArray2 = null;
        short s = TlsUtils.readUint8(inputStream);
        switch (s) {
            case 0: {
                if (!TlsUtils.isTLSv12(tlsContext)) break;
                throw new TlsFatalAlert(47);
            }
            case 1: {
                byArray2 = TlsUtils.readFully(20, inputStream);
                break;
            }
            default: {
                throw new TlsFatalAlert(47);
            }
        }
        return new URLAndHash(string, byArray2);
    }
}

