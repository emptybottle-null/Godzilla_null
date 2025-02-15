/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.commons.compress.archivers.zip.NioZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncoding;

public abstract class ZipEncodingHelper {
    static final String UTF8 = "UTF8";
    static final ZipEncoding UTF8_ZIP_ENCODING = ZipEncodingHelper.getZipEncoding("UTF8");

    public static ZipEncoding getZipEncoding(String name) {
        Charset cs = Charset.defaultCharset();
        if (name != null) {
            try {
                cs = Charset.forName(name);
            } catch (UnsupportedCharsetException unsupportedCharsetException) {
                // empty catch block
            }
        }
        boolean useReplacement = ZipEncodingHelper.isUTF8(cs.name());
        return new NioZipEncoding(cs, useReplacement);
    }

    static boolean isUTF8(String charsetName) {
        if (charsetName == null) {
            charsetName = Charset.defaultCharset().name();
        }
        if (StandardCharsets.UTF_8.name().equalsIgnoreCase(charsetName)) {
            return true;
        }
        for (String alias : StandardCharsets.UTF_8.aliases()) {
            if (!alias.equalsIgnoreCase(charsetName)) continue;
            return true;
        }
        return false;
    }

    static ByteBuffer growBufferBy(ByteBuffer buffer, int increment) {
        buffer.limit(buffer.position());
        buffer.rewind();
        ByteBuffer on = ByteBuffer.allocate(buffer.capacity() + increment);
        on.put(buffer);
        return on;
    }
}

