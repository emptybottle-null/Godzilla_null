/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.x509;

public class X509NameTokenizer {
    private String value;
    private int index;
    private char separator;
    private StringBuffer buf = new StringBuffer();

    public X509NameTokenizer(String string) {
        this(string, ',');
    }

    public X509NameTokenizer(String string, char c) {
        this.value = string;
        this.index = -1;
        this.separator = c;
    }

    public boolean hasMoreTokens() {
        return this.index != this.value.length();
    }

    public String nextToken() {
        int n;
        if (this.index == this.value.length()) {
            return null;
        }
        boolean bl = false;
        boolean bl2 = false;
        this.buf.setLength(0);
        for (n = this.index + 1; n != this.value.length(); ++n) {
            char c = this.value.charAt(n);
            if (c == '\"') {
                if (!bl2) {
                    bl = !bl;
                }
                this.buf.append(c);
                bl2 = false;
                continue;
            }
            if (bl2 || bl) {
                this.buf.append(c);
                bl2 = false;
                continue;
            }
            if (c == '\\') {
                this.buf.append(c);
                bl2 = true;
                continue;
            }
            if (c == this.separator) break;
            this.buf.append(c);
        }
        this.index = n;
        return this.buf.toString();
    }
}

