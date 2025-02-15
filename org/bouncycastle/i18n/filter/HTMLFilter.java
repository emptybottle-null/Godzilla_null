/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n.filter;

import org.bouncycastle.i18n.filter.Filter;

public class HTMLFilter
implements Filter {
    public String doFilter(String string) {
        StringBuffer stringBuffer = new StringBuffer(string);
        block14: for (int i = 0; i < stringBuffer.length(); i += 4) {
            char c = stringBuffer.charAt(i);
            switch (c) {
                case '<': {
                    stringBuffer.replace(i, i + 1, "&#60");
                    continue block14;
                }
                case '>': {
                    stringBuffer.replace(i, i + 1, "&#62");
                    continue block14;
                }
                case '(': {
                    stringBuffer.replace(i, i + 1, "&#40");
                    continue block14;
                }
                case ')': {
                    stringBuffer.replace(i, i + 1, "&#41");
                    continue block14;
                }
                case '#': {
                    stringBuffer.replace(i, i + 1, "&#35");
                    continue block14;
                }
                case '&': {
                    stringBuffer.replace(i, i + 1, "&#38");
                    continue block14;
                }
                case '\"': {
                    stringBuffer.replace(i, i + 1, "&#34");
                    continue block14;
                }
                case '\'': {
                    stringBuffer.replace(i, i + 1, "&#39");
                    continue block14;
                }
                case '%': {
                    stringBuffer.replace(i, i + 1, "&#37");
                    continue block14;
                }
                case ';': {
                    stringBuffer.replace(i, i + 1, "&#59");
                    continue block14;
                }
                case '+': {
                    stringBuffer.replace(i, i + 1, "&#43");
                    continue block14;
                }
                case '-': {
                    stringBuffer.replace(i, i + 1, "&#45");
                    continue block14;
                }
                default: {
                    i -= 3;
                }
            }
        }
        return stringBuffer.toString();
    }

    public String doFilterUrl(String string) {
        return this.doFilter(string);
    }
}

