/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.bouncycastle.i18n.filter;

public class TrustedInput {
    protected Object input;

    public TrustedInput(Object object) {
        this.input = object;
    }

    public Object getInput() {
        return this.input;
    }

    public String toString() {
        return this.input.toString();
    }
}

