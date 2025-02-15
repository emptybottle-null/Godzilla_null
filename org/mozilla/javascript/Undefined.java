/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.mozilla.javascript;

import java.io.Serializable;

public class Undefined
implements Serializable {
    static final long serialVersionUID = 9195680630202616767L;
    public static final Object instance = new Undefined();

    private Undefined() {
    }

    public Object readResolve() {
        return instance;
    }
}

