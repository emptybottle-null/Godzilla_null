/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.springframework.util.comparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.comparator.InvertibleComparator;

@Deprecated
public class CompoundComparator<T>
implements Comparator<T>,
Serializable {
    private final List<InvertibleComparator> comparators;

    public CompoundComparator() {
        this.comparators = new ArrayList<InvertibleComparator>();
    }

    public CompoundComparator(Comparator ... comparators) {
        Assert.notNull((Object)comparators, "Comparators must not be null");
        this.comparators = new ArrayList<InvertibleComparator>(comparators.length);
        for (Comparator comparator : comparators) {
            this.addComparator(comparator);
        }
    }

    public void addComparator(Comparator<? extends T> comparator) {
        if (comparator instanceof InvertibleComparator) {
            this.comparators.add((InvertibleComparator)comparator);
        } else {
            this.comparators.add(new InvertibleComparator<T>(comparator));
        }
    }

    public void addComparator(Comparator<? extends T> comparator, boolean ascending) {
        this.comparators.add(new InvertibleComparator<T>(comparator, ascending));
    }

    public void setComparator(int index, Comparator<? extends T> comparator) {
        if (comparator instanceof InvertibleComparator) {
            this.comparators.set(index, (InvertibleComparator)comparator);
        } else {
            this.comparators.set(index, new InvertibleComparator<T>(comparator));
        }
    }

    public void setComparator(int index, Comparator<T> comparator, boolean ascending) {
        this.comparators.set(index, new InvertibleComparator<T>(comparator, ascending));
    }

    public void invertOrder() {
        for (InvertibleComparator comparator : this.comparators) {
            comparator.invertOrder();
        }
    }

    public void invertOrder(int index) {
        this.comparators.get(index).invertOrder();
    }

    public void setAscendingOrder(int index) {
        this.comparators.get(index).setAscending(true);
    }

    public void setDescendingOrder(int index) {
        this.comparators.get(index).setAscending(false);
    }

    public int getComparatorCount() {
        return this.comparators.size();
    }

    @Override
    public int compare(T o1, T o2) {
        Assert.state(!this.comparators.isEmpty(), "No sort definitions have been added to this CompoundComparator to compare");
        for (InvertibleComparator comparator : this.comparators) {
            int result = comparator.compare(o1, o2);
            if (result == 0) continue;
            return result;
        }
        return 0;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return this == other || other instanceof CompoundComparator && this.comparators.equals(((CompoundComparator)other).comparators);
    }

    public int hashCode() {
        return this.comparators.hashCode();
    }

    public String toString() {
        return "CompoundComparator: " + this.comparators;
    }
}

