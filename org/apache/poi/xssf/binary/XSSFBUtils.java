/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.poi.xssf.binary;

import java.nio.charset.StandardCharsets;
import org.apache.poi.ooxml.POIXMLException;
import org.apache.poi.util.Internal;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.xssf.binary.XSSFBParseException;

@Internal
public class XSSFBUtils {
    static int readXLNullableWideString(byte[] data, int offset, StringBuilder sb) throws XSSFBParseException {
        long numChars = LittleEndian.getUInt(data, offset);
        if (numChars < 0L) {
            throw new XSSFBParseException("too few chars to read");
        }
        if (numChars == 0xFFFFFFFFL) {
            return 0;
        }
        if (numChars > 0xFFFFFFFFL) {
            throw new XSSFBParseException("too many chars to read");
        }
        int numBytes = 2 * (int)numChars;
        if ((offset += 4) + numBytes > data.length) {
            throw new XSSFBParseException("trying to read beyond data length: offset=" + offset + ", numBytes=" + numBytes + ", data.length=" + data.length);
        }
        sb.append(new String(data, offset, numBytes, StandardCharsets.UTF_16LE));
        return numBytes += 4;
    }

    public static int readXLWideString(byte[] data, int offset, StringBuilder sb) throws XSSFBParseException {
        long numChars = LittleEndian.getUInt(data, offset);
        if (numChars < 0L) {
            throw new XSSFBParseException("too few chars to read");
        }
        if (numChars > 0xFFFFFFFFL) {
            throw new XSSFBParseException("too many chars to read");
        }
        int numBytes = 2 * (int)numChars;
        if ((offset += 4) + numBytes > data.length) {
            throw new XSSFBParseException("trying to read beyond data length");
        }
        sb.append(new String(data, offset, numBytes, StandardCharsets.UTF_16LE));
        return numBytes += 4;
    }

    static int castToInt(long val) {
        if (val < Integer.MAX_VALUE && val > Integer.MIN_VALUE) {
            return (int)val;
        }
        throw new POIXMLException("val (" + val + ") can't be cast to int");
    }

    static short castToShort(int val) {
        if (val < Short.MAX_VALUE && val > Short.MIN_VALUE) {
            return (short)val;
        }
        throw new POIXMLException("val (" + val + ") can't be cast to short");
    }

    static int get24BitInt(byte[] data, int offset) {
        int i = offset;
        int b0 = data[i++] & 0xFF;
        int b1 = data[i++] & 0xFF;
        int b2 = data[i] & 0xFF;
        return (b2 << 16) + (b1 << 8) + b0;
    }
}

