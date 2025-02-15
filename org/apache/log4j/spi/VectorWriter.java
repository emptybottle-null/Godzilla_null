/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.log4j.spi;

import java.io.PrintWriter;
import java.util.Vector;
import org.apache.log4j.spi.NullWriter;

class VectorWriter
extends PrintWriter {
    private Vector v = new Vector();

    VectorWriter() {
        super(new NullWriter());
    }

    public void print(Object o) {
        this.v.addElement(String.valueOf(o));
    }

    public void print(char[] chars) {
        this.v.addElement(new String(chars));
    }

    public void print(String s) {
        this.v.addElement(s);
    }

    public void println(Object o) {
        this.v.addElement(String.valueOf(o));
    }

    public void println(char[] chars) {
        this.v.addElement(new String(chars));
    }

    public void println(String s) {
        this.v.addElement(s);
    }

    public void write(char[] chars) {
        this.v.addElement(new String(chars));
    }

    public void write(char[] chars, int off, int len) {
        this.v.addElement(new String(chars, off, len));
    }

    public void write(String s, int off, int len) {
        this.v.addElement(s.substring(off, off + len));
    }

    public void write(String s) {
        this.v.addElement(s);
    }

    public String[] toStringArray() {
        int len = this.v.size();
        String[] sa = new String[len];
        for (int i = 0; i < len; ++i) {
            sa[i] = (String)this.v.elementAt(i);
        }
        return sa;
    }
}

