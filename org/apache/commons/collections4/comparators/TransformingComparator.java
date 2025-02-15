/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.ComparatorUtils;
import org.apache.commons.collections4.Transformer;

public class TransformingComparator<I, O>
implements Comparator<I>,
Serializable {
    private static final long serialVersionUID = 3456940356043606220L;
    private final Comparator<O> decorated;
    private final Transformer<? super I, ? extends O> transformer;

    public TransformingComparator(Transformer<? super I, ? extends O> transformer) {
        this(transformer, ComparatorUtils.NATURAL_COMPARATOR);
    }

    public TransformingComparator(Transformer<? super I, ? extends O> transformer, Comparator<O> decorated) {
        this.decorated = decorated;
        this.transformer = transformer;
    }

    @Override
    public int compare(I obj1, I obj2) {
        O value1 = this.transformer.transform(obj1);
        O value2 = this.transformer.transform(obj2);
        return this.decorated.compare(value1, value2);
    }

    public int hashCode() {
        int total = 17;
        total = total * 37 + (this.decorated == null ? 0 : this.decorated.hashCode());
        total = total * 37 + (this.transformer == null ? 0 : this.transformer.hashCode());
        return total;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (null == object) {
            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            TransformingComparator comp = (TransformingComparator)object;
            return (null == this.decorated ? null == comp.decorated : this.decorated.equals(comp.decorated)) && (null == this.transformer ? null == comp.transformer : this.transformer.equals(comp.transformer));
        }
        return false;
    }
}

