/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.qual.Nullable;

@GwtCompatible(serializable=true)
final class PairwiseEquivalence<T>
extends Equivalence<Iterable<T>>
implements Serializable {
    final Equivalence<? super T> elementEquivalence;
    private static final long serialVersionUID = 1L;

    PairwiseEquivalence(Equivalence<? super T> elementEquivalence) {
        this.elementEquivalence = Preconditions.checkNotNull(elementEquivalence);
    }

    @Override
    protected boolean doEquivalent(Iterable<T> iterableA, Iterable<T> iterableB) {
        Iterator<T> iteratorA = iterableA.iterator();
        Iterator<T> iteratorB = iterableB.iterator();
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            if (this.elementEquivalence.equivalent(iteratorA.next(), iteratorB.next())) continue;
            return false;
        }
        return !iteratorA.hasNext() && !iteratorB.hasNext();
    }

    @Override
    protected int doHash(Iterable<T> iterable) {
        int hash = 78721;
        for (T element : iterable) {
            hash = hash * 24943 + this.elementEquivalence.hash(element);
        }
        return hash;
    }

    public boolean equals(@Nullable Object object) {
        if (object instanceof PairwiseEquivalence) {
            PairwiseEquivalence that = (PairwiseEquivalence)object;
            return this.elementEquivalence.equals(that.elementEquivalence);
        }
        return false;
    }

    public int hashCode() {
        return this.elementEquivalence.hashCode() ^ 0x46A3EB07;
    }

    public String toString() {
        return this.elementEquivalence + ".pairwise()";
    }
}

