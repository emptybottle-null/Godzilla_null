/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.codec.binary;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.codec.binary.BaseNCodec;

public class BaseNCodecOutputStream
extends FilterOutputStream {
    private final boolean doEncode;
    private final BaseNCodec baseNCodec;
    private final byte[] singleByte = new byte[1];
    private final BaseNCodec.Context context = new BaseNCodec.Context();

    public BaseNCodecOutputStream(OutputStream out, BaseNCodec basedCodec, boolean doEncode) {
        super(out);
        this.baseNCodec = basedCodec;
        this.doEncode = doEncode;
    }

    @Override
    public void write(int i) throws IOException {
        this.singleByte[0] = (byte)i;
        this.write(this.singleByte, 0, 1);
    }

    @Override
    public void write(byte[] b, int offset, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        }
        if (offset < 0 || len < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (offset > b.length || offset + len > b.length) {
            throw new IndexOutOfBoundsException();
        }
        if (len > 0) {
            if (this.doEncode) {
                this.baseNCodec.encode(b, offset, len, this.context);
            } else {
                this.baseNCodec.decode(b, offset, len, this.context);
            }
            this.flush(false);
        }
    }

    private void flush(boolean propagate) throws IOException {
        byte[] buf;
        int c;
        int avail = this.baseNCodec.available(this.context);
        if (avail > 0 && (c = this.baseNCodec.readResults(buf = new byte[avail], 0, avail, this.context)) > 0) {
            this.out.write(buf, 0, c);
        }
        if (propagate) {
            this.out.flush();
        }
    }

    @Override
    public void flush() throws IOException {
        this.flush(true);
    }

    @Override
    public void close() throws IOException {
        this.eof();
        this.flush();
        this.out.close();
    }

    public void eof() throws IOException {
        if (this.doEncode) {
            this.baseNCodec.encode(this.singleByte, 0, -1, this.context);
        } else {
            this.baseNCodec.decode(this.singleByte, 0, -1, this.context);
        }
    }
}

