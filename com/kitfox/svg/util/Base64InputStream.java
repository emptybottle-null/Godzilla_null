/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.kitfox.svg.util;

import com.kitfox.svg.util.Base64Consts;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Base64InputStream
extends FilterInputStream
implements Base64Consts {
    static final HashMap<Byte, Integer> lookup64 = new HashMap();
    int buf;
    int charsInBuf;

    public Base64InputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        for (int i = 0; i < len; ++i) {
            int val = this.read();
            if (val == -1) {
                return i == 0 ? -1 : i;
            }
            b[off + i] = (byte)val;
        }
        return len;
    }

    @Override
    public int read() throws IOException {
        if (this.charsInBuf == 0) {
            this.fillBuffer();
            if (this.charsInBuf == 0) {
                return -1;
            }
        }
        return this.buf >> --this.charsInBuf * 8 & 0xFF;
    }

    private void fillBuffer() throws IOException {
        int val;
        int bitsRead = 0;
        while (bitsRead < 24 && (val = this.in.read()) != -1 && val != 61) {
            Integer lval = lookup64.get(new Byte((byte)val));
            if (lval == null) continue;
            this.buf = this.buf << 6 | lval.byteValue();
            bitsRead += 6;
        }
        switch (bitsRead) {
            case 6: {
                throw new RuntimeException("Invalid termination of base64 encoding.");
            }
            case 12: {
                this.buf >>= 4;
                bitsRead = 8;
                break;
            }
            case 18: {
                this.buf >>= 2;
                bitsRead = 16;
                break;
            }
            case 0: 
            case 24: {
                break;
            }
            default: {
                assert (false) : "Should never encounter other bit counts";
                break;
            }
        }
        this.charsInBuf = bitsRead / 8;
    }

    static {
        byte[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes();
        for (int i = 0; i < ch.length; ++i) {
            lookup64.put(new Byte(ch[i]), new Integer(i));
        }
    }
}

