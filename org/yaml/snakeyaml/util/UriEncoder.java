/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.Escaper;
import org.yaml.snakeyaml.external.com.google.gdata.util.common.base.PercentEscaper;

public abstract class UriEncoder {
    private static final CharsetDecoder UTF8Decoder = Charset.forName("UTF-8").newDecoder().onMalformedInput(CodingErrorAction.REPORT);
    private static final String SAFE_CHARS = "-_.!~*'()@:$&,;=[]/";
    private static final Escaper escaper = new PercentEscaper("-_.!~*'()@:$&,;=[]/", false);

    public static String encode(String uri) {
        return escaper.escape(uri);
    }

    public static String decode(ByteBuffer buff) throws CharacterCodingException {
        CharBuffer chars = UTF8Decoder.decode(buff);
        return chars.toString();
    }

    public static String decode(String buff) {
        try {
            return URLDecoder.decode(buff, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new YAMLException(e);
        }
    }
}

