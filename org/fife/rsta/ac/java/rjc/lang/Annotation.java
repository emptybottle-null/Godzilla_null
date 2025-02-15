/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.fife.rsta.ac.java.rjc.lang;

import org.fife.rsta.ac.java.rjc.lang.Type;

public class Annotation {
    private Type type;

    public Annotation(Type type) {
        this.type = type;
    }

    public String toString() {
        return "@" + this.type.toString();
    }
}

