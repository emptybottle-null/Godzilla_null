/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.yaml.snakeyaml.events;

public class ImplicitTuple {
    private final boolean plain;
    private final boolean nonPlain;

    public ImplicitTuple(boolean plain, boolean nonplain) {
        this.plain = plain;
        this.nonPlain = nonplain;
    }

    public boolean canOmitTagInPlainScalar() {
        return this.plain;
    }

    public boolean canOmitTagInNonPlainScalar() {
        return this.nonPlain;
    }

    public boolean bothFalse() {
        return !this.plain && !this.nonPlain;
    }

    public String toString() {
        return "implicit=[" + this.plain + ", " + this.nonPlain + "]";
    }
}

