/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.util.io.pem;

public class PemHeader {
    private String name;
    private String value;

    public PemHeader(String string, String string2) {
        this.name = string;
        this.value = string2;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.getHashCode(this.name) + 31 * this.getHashCode(this.value);
    }

    public boolean equals(Object object) {
        if (!(object instanceof PemHeader)) {
            return false;
        }
        PemHeader pemHeader = (PemHeader)object;
        return pemHeader == this || this.isEqual(this.name, pemHeader.name) && this.isEqual(this.value, pemHeader.value);
    }

    private int getHashCode(String string) {
        if (string == null) {
            return 1;
        }
        return string.hashCode();
    }

    private boolean isEqual(String string, String string2) {
        if (string == string2) {
            return true;
        }
        if (string == null || string2 == null) {
            return false;
        }
        return string.equals(string2);
    }
}

