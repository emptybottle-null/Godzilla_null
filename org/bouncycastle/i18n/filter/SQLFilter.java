/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n.filter;

import org.bouncycastle.i18n.filter.Filter;

public class SQLFilter
implements Filter {
    public String doFilter(String string) {
        StringBuffer stringBuffer = new StringBuffer(string);
        block11: for (int i = 0; i < stringBuffer.length(); ++i) {
            char c = stringBuffer.charAt(i);
            switch (c) {
                case '\'': {
                    stringBuffer.replace(i, i + 1, "\\'");
                    ++i;
                    continue block11;
                }
                case '\"': {
                    stringBuffer.replace(i, i + 1, "\\\"");
                    ++i;
                    continue block11;
                }
                case '=': {
                    stringBuffer.replace(i, i + 1, "\\=");
                    ++i;
                    continue block11;
                }
                case '-': {
                    stringBuffer.replace(i, i + 1, "\\-");
                    ++i;
                    continue block11;
                }
                case '/': {
                    stringBuffer.replace(i, i + 1, "\\/");
                    ++i;
                    continue block11;
                }
                case '\\': {
                    stringBuffer.replace(i, i + 1, "\\\\");
                    ++i;
                    continue block11;
                }
                case ';': {
                    stringBuffer.replace(i, i + 1, "\\;");
                    ++i;
                    continue block11;
                }
                case '\r': {
                    stringBuffer.replace(i, i + 1, "\\r");
                    ++i;
                    continue block11;
                }
                case '\n': {
                    stringBuffer.replace(i, i + 1, "\\n");
                    ++i;
                    continue block11;
                }
            }
        }
        return stringBuffer.toString();
    }

    public String doFilterUrl(String string) {
        return this.doFilter(string);
    }
}

