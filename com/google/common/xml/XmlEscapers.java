/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.xml;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.escape.Escaper;
import com.google.common.escape.Escapers;

@Beta
@GwtCompatible
public class XmlEscapers {
    private static final char MIN_ASCII_CONTROL_CHAR = '\u0000';
    private static final char MAX_ASCII_CONTROL_CHAR = '\u001f';
    private static final Escaper XML_ESCAPER;
    private static final Escaper XML_CONTENT_ESCAPER;
    private static final Escaper XML_ATTRIBUTE_ESCAPER;

    private XmlEscapers() {
    }

    public static Escaper xmlContentEscaper() {
        return XML_CONTENT_ESCAPER;
    }

    public static Escaper xmlAttributeEscaper() {
        return XML_ATTRIBUTE_ESCAPER;
    }

    static {
        Escapers.Builder builder = Escapers.builder();
        builder.setSafeRange('\u0000', '\ufffd');
        builder.setUnsafeReplacement("\ufffd");
        for (char c = '\u0000'; c <= '\u001f'; c = (char)((char)(c + 1))) {
            if (c == 9 || c == 10 || c == 13) continue;
            builder.addEscape(c, "\ufffd");
        }
        builder.addEscape('&', "&amp;");
        builder.addEscape('<', "&lt;");
        builder.addEscape('>', "&gt;");
        XML_CONTENT_ESCAPER = builder.build();
        builder.addEscape('\'', "&apos;");
        builder.addEscape('\"', "&quot;");
        XML_ESCAPER = builder.build();
        builder.addEscape('\t', "&#x9;");
        builder.addEscape('\n', "&#xA;");
        builder.addEscape('\r', "&#xD;");
        XML_ATTRIBUTE_ESCAPER = builder.build();
    }
}

