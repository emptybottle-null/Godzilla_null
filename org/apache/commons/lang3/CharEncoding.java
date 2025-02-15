/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.lang3;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;

@Deprecated
public class CharEncoding {
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String US_ASCII = "US-ASCII";
    public static final String UTF_16 = "UTF-16";
    public static final String UTF_16BE = "UTF-16BE";
    public static final String UTF_16LE = "UTF-16LE";
    public static final String UTF_8 = "UTF-8";

    @Deprecated
    public static boolean isSupported(String name) {
        if (name == null) {
            return false;
        }
        try {
            return Charset.isSupported(name);
        } catch (IllegalCharsetNameException ex) {
            return false;
        }
    }
}

