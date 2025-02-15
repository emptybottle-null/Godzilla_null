/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.asn1.BERGenerator;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DEROutputStream;

public class BEROctetStringGenerator
extends BERGenerator {
    public BEROctetStringGenerator(OutputStream outputStream) throws IOException {
        super(outputStream);
        this.writeBERHeader(36);
    }

    public BEROctetStringGenerator(OutputStream outputStream, int n, boolean bl) throws IOException {
        super(outputStream, n, bl);
        this.writeBERHeader(36);
    }

    public OutputStream getOctetOutputStream() {
        return this.getOctetOutputStream(new byte[1000]);
    }

    public OutputStream getOctetOutputStream(byte[] byArray) {
        return new BufferedBEROctetStream(byArray);
    }

    private class BufferedBEROctetStream
    extends OutputStream {
        private byte[] _buf;
        private int _off;
        private DEROutputStream _derOut;

        BufferedBEROctetStream(byte[] byArray) {
            this._buf = byArray;
            this._off = 0;
            this._derOut = new DEROutputStream(BEROctetStringGenerator.this._out);
        }

        public void write(int n) throws IOException {
            this._buf[this._off++] = (byte)n;
            if (this._off == this._buf.length) {
                DEROctetString.encode(this._derOut, this._buf);
                this._off = 0;
            }
        }

        public void write(byte[] byArray, int n, int n2) throws IOException {
            while (n2 > 0) {
                int n3 = Math.min(n2, this._buf.length - this._off);
                System.arraycopy(byArray, n, this._buf, this._off, n3);
                this._off += n3;
                if (this._off < this._buf.length) break;
                DEROctetString.encode(this._derOut, this._buf);
                this._off = 0;
                n += n3;
                n2 -= n3;
            }
        }

        public void close() throws IOException {
            if (this._off != 0) {
                byte[] byArray = new byte[this._off];
                System.arraycopy(this._buf, 0, byArray, 0, this._off);
                DEROctetString.encode(this._derOut, byArray);
            }
            BEROctetStringGenerator.this.writeBEREnd();
        }
    }
}

