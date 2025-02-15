/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.internal;

import org.hamcrest.Description;
import org.hamcrest.SelfDescribing;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SelfDescribingValue<T>
implements SelfDescribing {
    private T value;

    public SelfDescribingValue(T value) {
        this.value = value;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(this.value);
    }
}

