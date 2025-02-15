/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript.commonjs.module.provider;

import java.io.Serializable;
import java.util.StringTokenizer;

public final class ParsedContentType
implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String contentType;
    private final String encoding;

    public ParsedContentType(String mimeType) {
        StringTokenizer tok;
        String contentType = null;
        String encoding = null;
        if (mimeType != null && (tok = new StringTokenizer(mimeType, ";")).hasMoreTokens()) {
            contentType = tok.nextToken().trim();
            while (tok.hasMoreTokens()) {
                String param = tok.nextToken().trim();
                if (!param.startsWith("charset=")) continue;
                encoding = param.substring(8).trim();
                int l = encoding.length();
                if (l <= 0) break;
                if (encoding.charAt(0) == '\"') {
                    encoding = encoding.substring(1);
                }
                if (encoding.charAt(l - 1) != '\"') break;
                encoding = encoding.substring(0, l - 1);
                break;
            }
        }
        this.contentType = contentType;
        this.encoding = encoding;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getEncoding() {
        return this.encoding;
    }
}

