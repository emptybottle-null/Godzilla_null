/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.util;

import com.kitfox.svg.util.Base64Consts;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Base64OutputStream
extends FilterOutputStream
implements Base64Consts {
    int buf;
    int bitsUsed;
    int charsPrinted;

    public Base64OutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public void close() throws IOException {
        this.writeBits();
        super.close();
    }

    @Override
    public void write(int b) throws IOException {
        this.buf = this.buf << 8 | b & 0xFF;
        this.bitsUsed += 8;
        if (this.bitsUsed == 24) {
            this.writeBits();
        }
    }

    private void writeBits() throws IOException {
        int padSize;
        switch (this.bitsUsed) {
            case 8: {
                this.bitsUsed = 12;
                this.buf <<= 4;
                padSize = 2;
                break;
            }
            case 16: {
                this.bitsUsed = 18;
                this.buf <<= 2;
                padSize = 1;
                break;
            }
            default: {
                padSize = 0;
            }
        }
        if (this.charsPrinted == 76) {
            this.out.write(13);
            this.out.write(10);
            this.charsPrinted = 0;
        }
        while (this.bitsUsed > 0) {
            int b = this.buf >> this.bitsUsed - 6 & 0x3F;
            this.out.write("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(b));
            this.bitsUsed -= 6;
        }
        for (int i = 0; i < padSize; ++i) {
            this.out.write(61);
        }
        this.charsPrinted += 4;
    }
}

