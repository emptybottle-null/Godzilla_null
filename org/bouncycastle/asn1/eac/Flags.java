/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.asn1.eac;

import java.util.Enumeration;
import java.util.Hashtable;

public class Flags {
    int value = 0;

    public Flags() {
    }

    public Flags(int n) {
        this.value = n;
    }

    public void set(int n) {
        this.value |= n;
    }

    public boolean isSet(int n) {
        return (this.value & n) != 0;
    }

    public int getFlags() {
        return this.value;
    }

    String decode(Hashtable hashtable) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        Enumeration enumeration = hashtable.keys();
        while (enumeration.hasMoreElements()) {
            Integer n = (Integer)enumeration.nextElement();
            if (!this.isSet(n)) continue;
            stringJoiner.add((String)hashtable.get(n));
        }
        return stringJoiner.toString();
    }

    private class StringJoiner {
        String mSeparator;
        boolean First = true;
        StringBuffer b = new StringBuffer();

        public StringJoiner(String string) {
            this.mSeparator = string;
        }

        public void add(String string) {
            if (this.First) {
                this.First = false;
            } else {
                this.b.append(this.mSeparator);
            }
            this.b.append(string);
        }

        public String toString() {
            return this.b.toString();
        }
    }
}

