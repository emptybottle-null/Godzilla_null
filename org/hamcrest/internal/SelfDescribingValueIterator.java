/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.hamcrest.internal;

import java.util.Iterator;
import org.hamcrest.SelfDescribing;
import org.hamcrest.internal.SelfDescribingValue;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class SelfDescribingValueIterator<T>
implements Iterator<SelfDescribing> {
    private Iterator<T> values;

    public SelfDescribingValueIterator(Iterator<T> values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return this.values.hasNext();
    }

    @Override
    public SelfDescribing next() {
        return new SelfDescribingValue<T>(this.values.next());
    }

    @Override
    public void remove() {
        this.values.remove();
    }
}

