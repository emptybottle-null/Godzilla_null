/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.math3.util;

import java.util.EventObject;

public class IterationEvent
extends EventObject {
    private static final long serialVersionUID = 20120128L;
    private final int iterations;

    public IterationEvent(Object source, int iterations) {
        super(source);
        this.iterations = iterations;
    }

    public int getIterations() {
        return this.iterations;
    }
}

