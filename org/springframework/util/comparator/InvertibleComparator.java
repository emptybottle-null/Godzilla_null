/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.comparator;

import java.io.Serializable;
import java.util.Comparator;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

@Deprecated
public class InvertibleComparator<T>
implements Comparator<T>,
Serializable {
    private final Comparator<T> comparator;
    private boolean ascending = true;

    public InvertibleComparator(Comparator<T> comparator) {
        Assert.notNull(comparator, "Comparator must not be null");
        this.comparator = comparator;
    }

    public InvertibleComparator(Comparator<T> comparator, boolean ascending) {
        Assert.notNull(comparator, "Comparator must not be null");
        this.comparator = comparator;
        this.setAscending(ascending);
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public boolean isAscending() {
        return this.ascending;
    }

    public void invertOrder() {
        this.ascending = !this.ascending;
    }

    @Override
    public int compare(T o1, T o2) {
        int result = this.comparator.compare(o1, o2);
        if (result != 0) {
            if (!this.ascending) {
                result = Integer.MIN_VALUE == result ? Integer.MAX_VALUE : (result *= -1);
            }
            return result;
        }
        return 0;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InvertibleComparator)) {
            return false;
        }
        InvertibleComparator otherComp = (InvertibleComparator)other;
        return this.comparator.equals(otherComp.comparator) && this.ascending == otherComp.ascending;
    }

    public int hashCode() {
        return this.comparator.hashCode();
    }

    public String toString() {
        return "InvertibleComparator: [" + this.comparator + "]; ascending=" + this.ascending;
    }
}

