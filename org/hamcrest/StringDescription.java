/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest;

import java.io.IOException;
import org.hamcrest.BaseDescription;
import org.hamcrest.SelfDescribing;

public class StringDescription
extends BaseDescription {
    private final Appendable out;

    public StringDescription() {
        this(new StringBuilder());
    }

    public StringDescription(Appendable out) {
        this.out = out;
    }

    public static String toString(SelfDescribing selfDescribing) {
        return new StringDescription().appendDescriptionOf(selfDescribing).toString();
    }

    public static String asString(SelfDescribing selfDescribing) {
        return StringDescription.toString(selfDescribing);
    }

    protected void append(String str) {
        try {
            this.out.append(str);
        } catch (IOException e) {
            throw new RuntimeException("Could not write description", e);
        }
    }

    protected void append(char c) {
        try {
            this.out.append(c);
        } catch (IOException e) {
            throw new RuntimeException("Could not write description", e);
        }
    }

    public String toString() {
        return this.out.toString();
    }
}

