/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ComparatorChain<E>
implements Comparator<E>,
Serializable {
    private static final long serialVersionUID = -721644942746081630L;
    private final List<Comparator<E>> comparatorChain;
    private BitSet orderingBits = null;
    private boolean isLocked = false;

    public ComparatorChain() {
        this(new ArrayList<Comparator<E>>(), new BitSet());
    }

    public ComparatorChain(Comparator<E> comparator) {
        this(comparator, false);
    }

    public ComparatorChain(Comparator<E> comparator, boolean reverse) {
        this.comparatorChain = new ArrayList<Comparator<E>>(1);
        this.comparatorChain.add(comparator);
        this.orderingBits = new BitSet(1);
        if (reverse) {
            this.orderingBits.set(0);
        }
    }

    public ComparatorChain(List<Comparator<E>> list) {
        this(list, new BitSet(list.size()));
    }

    public ComparatorChain(List<Comparator<E>> list, BitSet bits) {
        this.comparatorChain = list;
        this.orderingBits = bits;
    }

    public void addComparator(Comparator<E> comparator) {
        this.addComparator(comparator, false);
    }

    public void addComparator(Comparator<E> comparator, boolean reverse) {
        this.checkLocked();
        this.comparatorChain.add(comparator);
        if (reverse) {
            this.orderingBits.set(this.comparatorChain.size() - 1);
        }
    }

    public void setComparator(int index, Comparator<E> comparator) throws IndexOutOfBoundsException {
        this.setComparator(index, comparator, false);
    }

    public void setComparator(int index, Comparator<E> comparator, boolean reverse) {
        this.checkLocked();
        this.comparatorChain.set(index, comparator);
        if (reverse) {
            this.orderingBits.set(index);
        } else {
            this.orderingBits.clear(index);
        }
    }

    public void setForwardSort(int index) {
        this.checkLocked();
        this.orderingBits.clear(index);
    }

    public void setReverseSort(int index) {
        this.checkLocked();
        this.orderingBits.set(index);
    }

    public int size() {
        return this.comparatorChain.size();
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    private void checkLocked() {
        if (this.isLocked) {
            throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed");
        }
    }

    private void checkChainIntegrity() {
        if (this.comparatorChain.size() == 0) {
            throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator");
        }
    }

    @Override
    public int compare(E o1, E o2) throws UnsupportedOperationException {
        if (!this.isLocked) {
            this.checkChainIntegrity();
            this.isLocked = true;
        }
        Iterator<Comparator<E>> comparators = this.comparatorChain.iterator();
        int comparatorIndex = 0;
        while (comparators.hasNext()) {
            Comparator<E> comparator = comparators.next();
            int retval = comparator.compare(o1, o2);
            if (retval != 0) {
                if (this.orderingBits.get(comparatorIndex)) {
                    retval = retval > 0 ? -1 : 1;
                }
                return retval;
            }
            ++comparatorIndex;
        }
        return 0;
    }

    public int hashCode() {
        int hash = 0;
        if (null != this.comparatorChain) {
            hash ^= this.comparatorChain.hashCode();
        }
        if (null != this.orderingBits) {
            hash ^= this.orderingBits.hashCode();
        }
        return hash;
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
            ComparatorChain chain = (ComparatorChain)object;
            return (null == this.orderingBits ? null == chain.orderingBits : this.orderingBits.equals(chain.orderingBits)) && (null == this.comparatorChain ? null == chain.comparatorChain : this.comparatorChain.equals(chain.comparatorChain));
        }
        return false;
    }
}

