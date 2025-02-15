/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.est;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bouncycastle.util.encoders.Base64;

class CTEBase64InputStream
extends InputStream {
    protected final InputStream src;
    protected final byte[] rawBuf = new byte[1024];
    protected final byte[] data = new byte[768];
    protected final OutputStream dataOutputStream;
    protected final Long max;
    protected int rp;
    protected int wp;
    protected boolean end;
    protected long read;

    public CTEBase64InputStream(InputStream inputStream, Long l) {
        this.src = inputStream;
        this.dataOutputStream = new OutputStream(){

            public void write(int n) throws IOException {
                CTEBase64InputStream.this.data[CTEBase64InputStream.this.wp++] = (byte)n;
            }
        };
        this.max = l;
    }

    protected int pullFromSrc() throws IOException {
        if (this.read >= this.max) {
            return -1;
        }
        int n = 0;
        int n2 = 0;
        do {
            if ((n = this.src.read()) >= 33 || n == 13 || n == 10) {
                if (n2 >= this.rawBuf.length) {
                    throw new IOException("Content Transfer Encoding, base64 line length > 1024");
                }
                this.rawBuf[n2++] = (byte)n;
                ++this.read;
                continue;
            }
            if (n < 0) continue;
            ++this.read;
        } while (n > -1 && n2 < this.rawBuf.length && n != 10 && this.read < this.max);
        if (n2 > 0) {
            try {
                Base64.decode(this.rawBuf, 0, n2, this.dataOutputStream);
            } catch (Exception exception) {
                throw new IOException("Decode Base64 Content-Transfer-Encoding: " + exception);
            }
        } else if (n == -1) {
            return -1;
        }
        return this.wp;
    }

    public int read() throws IOException {
        if (this.rp == this.wp) {
            this.rp = 0;
            this.wp = 0;
            int n = this.pullFromSrc();
            if (n == -1) {
                return n;
            }
        }
        return this.data[this.rp++] & 0xFF;
    }

    public void close() throws IOException {
        this.src.close();
    }
}

