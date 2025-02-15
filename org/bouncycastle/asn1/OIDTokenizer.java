/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1;

public class OIDTokenizer {
    private String oid;
    private int index;

    public OIDTokenizer(String string) {
        this.oid = string;
        this.index = 0;
    }

    public boolean hasMoreTokens() {
        return this.index != -1;
    }

    public String nextToken() {
        if (this.index == -1) {
            return null;
        }
        int n = this.oid.indexOf(46, this.index);
        if (n == -1) {
            String string = this.oid.substring(this.index);
            this.index = -1;
            return string;
        }
        String string = this.oid.substring(this.index, n);
        this.index = n + 1;
        return string;
    }
}

