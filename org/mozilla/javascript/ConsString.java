/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.Serializable;

public class ConsString
implements CharSequence,
Serializable {
    private static final long serialVersionUID = -8432806714471372570L;
    private CharSequence s1;
    private CharSequence s2;
    private final int length;
    private int depth;

    public ConsString(CharSequence str1, CharSequence str2) {
        this.s1 = str1;
        this.s2 = str2;
        this.length = str1.length() + str2.length();
        this.depth = 1;
        if (str1 instanceof ConsString) {
            this.depth += ((ConsString)str1).depth;
        }
        if (str2 instanceof ConsString) {
            this.depth += ((ConsString)str2).depth;
        }
        if (this.depth > 2000) {
            this.flatten();
        }
    }

    private Object writeReplace() {
        return this.toString();
    }

    @Override
    public String toString() {
        return this.depth == 0 ? (String)this.s1 : this.flatten();
    }

    private synchronized String flatten() {
        if (this.depth > 0) {
            StringBuilder b = new StringBuilder(this.length);
            this.appendTo(b);
            this.s1 = b.toString();
            this.s2 = "";
            this.depth = 0;
        }
        return (String)this.s1;
    }

    private synchronized void appendTo(StringBuilder b) {
        ConsString.appendFragment(this.s1, b);
        ConsString.appendFragment(this.s2, b);
    }

    private static void appendFragment(CharSequence s, StringBuilder b) {
        if (s instanceof ConsString) {
            ((ConsString)s).appendTo(b);
        } else {
            b.append(s);
        }
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public char charAt(int index) {
        String str = this.depth == 0 ? (String)this.s1 : this.flatten();
        return str.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        String str = this.depth == 0 ? (String)this.s1 : this.flatten();
        return str.substring(start, end);
    }
}

