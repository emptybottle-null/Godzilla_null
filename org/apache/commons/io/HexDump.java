/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class HexDump {
    public static final String EOL = System.getProperty("line.separator");
    private static final char[] _hexcodes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final int[] _shifts = new int[]{28, 24, 20, 16, 12, 8, 4, 0};

    public static void dump(byte[] data, long offset, OutputStream stream, int index) throws IOException, ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (index < 0 || index >= data.length) {
            throw new ArrayIndexOutOfBoundsException("illegal index: " + index + " into array of length " + data.length);
        }
        if (stream == null) {
            throw new IllegalArgumentException("cannot write to nullstream");
        }
        long display_offset = offset + (long)index;
        StringBuilder buffer = new StringBuilder(74);
        for (int j = index; j < data.length; j += 16) {
            int k;
            int chars_read = data.length - j;
            if (chars_read > 16) {
                chars_read = 16;
            }
            HexDump.dump(buffer, display_offset).append(' ');
            for (k = 0; k < 16; ++k) {
                if (k < chars_read) {
                    HexDump.dump(buffer, data[k + j]);
                } else {
                    buffer.append("  ");
                }
                buffer.append(' ');
            }
            for (k = 0; k < chars_read; ++k) {
                if (data[k + j] >= 32 && data[k + j] < 127) {
                    buffer.append((char)data[k + j]);
                    continue;
                }
                buffer.append('.');
            }
            buffer.append(EOL);
            stream.write(buffer.toString().getBytes(Charset.defaultCharset()));
            stream.flush();
            buffer.setLength(0);
            display_offset += (long)chars_read;
        }
    }

    private static StringBuilder dump(StringBuilder _lbuffer, long value) {
        for (int j = 0; j < 8; ++j) {
            _lbuffer.append(_hexcodes[(int)(value >> _shifts[j]) & 0xF]);
        }
        return _lbuffer;
    }

    private static StringBuilder dump(StringBuilder _cbuffer, byte value) {
        for (int j = 0; j < 2; ++j) {
            _cbuffer.append(_hexcodes[value >> _shifts[j + 6] & 0xF]);
        }
        return _cbuffer;
    }
}

