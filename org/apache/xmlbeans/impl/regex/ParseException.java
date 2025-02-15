/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.xmlbeans.impl.regex;

public class ParseException
extends RuntimeException {
    int location;

    public ParseException(String mes, int location) {
        super(mes);
        this.location = location;
    }

    public int getLocation() {
        return this.location;
    }
}

